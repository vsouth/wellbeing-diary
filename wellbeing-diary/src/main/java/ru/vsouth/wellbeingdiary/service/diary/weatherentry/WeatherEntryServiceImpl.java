package ru.vsouth.wellbeingdiary.service.diary.weatherentry;

import org.springframework.stereotype.Service;
import ru.vsouth.wellbeingdiary.dto.WeatherEntryResponse;
import ru.vsouth.wellbeingdiary.model.WeatherEntry;
import ru.vsouth.wellbeingdiary.repository.WeatherEntryRepository;
import ru.vsouth.wellbeingdiary.utils.WeatherEntryMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class WeatherEntryServiceImpl implements WeatherEntryService {
    private final WeatherEntryRepository weatherEntryRepository;
    private final WeatherEntryMapper weatherEntryMapper;

    public WeatherEntryServiceImpl(WeatherEntryRepository weatherEntryRepository, WeatherEntryMapper weatherEntryMapper) {
        this.weatherEntryRepository = weatherEntryRepository;
        this.weatherEntryMapper = weatherEntryMapper;
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
    public WeatherEntryResponse saveEntry(WeatherEntry entry) {
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
}
