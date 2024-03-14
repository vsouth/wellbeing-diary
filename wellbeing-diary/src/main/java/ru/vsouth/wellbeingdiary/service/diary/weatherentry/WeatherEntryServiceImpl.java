package ru.vsouth.wellbeingdiary.service.diary.weatherentry;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;
import ru.vsouth.wellbeingdiary.dto.WeatherApiCurrentResponse;
import ru.vsouth.wellbeingdiary.dto.WeatherApiGeoResponse;
import ru.vsouth.wellbeingdiary.dto.WeatherEntryResponse;
import ru.vsouth.wellbeingdiary.model.PartOfDay;
import ru.vsouth.wellbeingdiary.model.WeatherEntry;
import ru.vsouth.wellbeingdiary.repository.WeatherEntryRepository;
import ru.vsouth.wellbeingdiary.utils.PartOfDayIdentifier;
import ru.vsouth.wellbeingdiary.utils.WeatherApiClient;
import ru.vsouth.wellbeingdiary.utils.WeatherEntryMapper;
import ru.vsouth.wellbeingdiary.utils.WeatherTypeIdentifier;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
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

    @Override
    public List<WeatherEntryResponse> getAllEntries() {
        return weatherEntryRepository.findAll().stream().map(weatherEntryMapper::toWeatherEntryResponse).collect(Collectors.toList());
    }

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

    @Override
    public WeatherEntryResponse saveEntry(WeatherEntry entry) throws JsonProcessingException {
        WeatherEntry savedEntry = weatherEntryRepository.save(entry);
        return weatherEntryMapper.toWeatherEntryResponse(savedEntry);
    }

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

    @Override
    public WeatherEntryResponse saveNewEntry(String city, Date createdAt) throws JsonProcessingException {
        WeatherApiGeoResponse coordinates = weatherApiClient.getCoordinatesByCity(city);
        PartOfDay partOfDay = partOfDayIdentifier.calculatePartOfDay(createdAt);
        Double lat = coordinates.getLat();
        Double lon = coordinates.getLon();
        Optional<WeatherEntry> existingEntry = weatherEntryRepository.findByLatAndLonAndDateAndPartOfDay(lat, lon, createdAt, partOfDay);
        if (existingEntry.isPresent()) {
            return weatherEntryMapper.toWeatherEntryResponse(existingEntry.get());
        }
        WeatherApiCurrentResponse currentWeather = weatherApiClient.getCurrentWeather(lat, lon);
        String weatherType = weatherTypeIdentifier.getWeatherType(currentWeather.getWeatherCode());
        return saveEntry(new WeatherEntry(lat, lon, createdAt, partOfDay, currentWeather.getTemperature(), weatherType, ""));
    }
}
