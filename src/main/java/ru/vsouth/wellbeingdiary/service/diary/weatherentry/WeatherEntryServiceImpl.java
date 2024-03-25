package ru.vsouth.wellbeingdiary.service.diary.weatherentry;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;
import ru.vsouth.wellbeingdiary.dto.diary.WeatherEntryResponse;
import ru.vsouth.wellbeingdiary.dto.integration.WeatherApiCurrentResponse;
import ru.vsouth.wellbeingdiary.dto.integration.WeatherApiGeoResponse;
import ru.vsouth.wellbeingdiary.model.diary.PartOfDay;
import ru.vsouth.wellbeingdiary.model.diary.WeatherEntry;
import ru.vsouth.wellbeingdiary.repository.WeatherEntryRepository;
import ru.vsouth.wellbeingdiary.utils.diary.PartOfDayIdentifier;
import ru.vsouth.wellbeingdiary.utils.diary.WeatherEntryMapper;
import ru.vsouth.wellbeingdiary.utils.diary.WeatherTypeIdentifier;
import ru.vsouth.wellbeingdiary.utils.integration.WeatherApiClient;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Сервис для работы с записями о погоде.
 */
@Service
public class WeatherEntryServiceImpl implements WeatherEntryService {
    private final WeatherEntryRepository weatherEntryRepository;
    private final WeatherEntryMapper weatherEntryMapper;
    private final WeatherApiClient weatherApiClient;
    private final WeatherTypeIdentifier weatherTypeIdentifier;
    private final PartOfDayIdentifier partOfDayIdentifier;

    public WeatherEntryServiceImpl(WeatherEntryRepository weatherEntryRepository, WeatherEntryMapper weatherEntryMapper, WeatherApiClient weatherApiClient, WeatherTypeIdentifier weatherTypeIdentifier, PartOfDayIdentifier partOfDayIdentifier) {
        this.weatherEntryRepository = weatherEntryRepository;
        this.weatherEntryMapper = weatherEntryMapper;
        this.weatherApiClient = weatherApiClient;
        this.weatherTypeIdentifier = weatherTypeIdentifier;
        this.partOfDayIdentifier = partOfDayIdentifier;
    }

    /**
     * Метод для получения всех записей о погоде
     *
     * @return Список записей о погоде
     */
    @Override
    public List<WeatherEntryResponse> getAllEntries() {
        return weatherEntryRepository.findAll().stream().map(weatherEntryMapper::toWeatherEntryResponse).collect(Collectors.toList());
    }

    /**
     * Метод для получения записи о погоде по идентификатору
     *
     * @param id Идентификатор записи о погоде
     * @return Информация о погоде, или null, если запись не найдена
     */
    @Override
    public WeatherEntryResponse getEntryById(int id) {
        Optional<WeatherEntry> optionalWeatherEntry = weatherEntryRepository.findById(id);
        if (optionalWeatherEntry.isPresent()) {
            WeatherEntry weatherEntry = optionalWeatherEntry.get();
            return weatherEntryMapper.toWeatherEntryResponse(weatherEntry);
        } else {
            return null;
        }
    }

    /**
     * Метод для сохранения записи о погоде
     *
     * @param entry Запись о погоде
     * @return Сохраненная запись о погоде
     * @throws JsonProcessingException Исключение при обработке JSON
     */
    @Override
    public WeatherEntryResponse saveEntry(WeatherEntry entry) throws JsonProcessingException {
        WeatherEntry savedEntry = weatherEntryRepository.save(entry);
        return weatherEntryMapper.toWeatherEntryResponse(savedEntry);
    }

    /**
     * Метод для удаления записи о погоде по идентификатору
     *
     * @param id Идентификатор записи о погоде
     * @return Удаленная запись о погоде, или null, если запись не найдена
     */
    @Override
    public WeatherEntryResponse deleteEntry(int id) {
        Optional<WeatherEntry> optionalEntry = weatherEntryRepository.findById(id);
        if (optionalEntry.isPresent()) {
            WeatherEntry entry = optionalEntry.get();
            weatherEntryRepository.deleteById(id);
            return weatherEntryMapper.toWeatherEntryResponse(entry);
        } else {
            return null;
        }
    }

    /**
     * Метод для сохранения новой записи о погоде
     *
     * @param city      Город
     * @param createdAt Дата создания записи
     * @return Сохраненная запись о погоде
     * @throws JsonProcessingException Исключение при обработке JSON
     */
    @Override
    public WeatherEntryResponse saveNewEntry(String city, Date createdAt) throws JsonProcessingException {
        if (city.isEmpty()) {
            return null;
        }
        WeatherApiGeoResponse coordinates = weatherApiClient.getCoordinatesByCity(city);
        PartOfDay partOfDay = partOfDayIdentifier.calculatePartOfDay(createdAt);
        Double lat = coordinates.getLat();
        Double lon = coordinates.getLon();
        Optional<WeatherEntry> existingEntry = weatherEntryRepository.findByLatAndLonAndDateAndPartOfDay(lat, lon, createdAt, partOfDay).stream().findFirst();
        if (existingEntry.isPresent()) {
            return weatherEntryMapper.toWeatherEntryResponse(existingEntry.get());
        }
        WeatherApiCurrentResponse currentWeather = weatherApiClient.getCurrentWeather(lat, lon);
        String weatherType = weatherTypeIdentifier.getWeatherType(currentWeather.getWeatherCode());
        return saveEntry(new WeatherEntry(lat, lon, createdAt, partOfDay, currentWeather.getTemperature(), weatherType));
    }
}
