package ru.vsouth.wellbeingdiary.service.diary.weatherentry;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.vsouth.wellbeingdiary.dto.diary.WeatherEntryResponse;
import ru.vsouth.wellbeingdiary.model.diary.WeatherEntry;
import ru.vsouth.wellbeingdiary.service.diary.EntryService;

import java.util.Date;
import java.util.List;

public interface WeatherEntryService extends EntryService<WeatherEntry, WeatherEntryResponse> {
    @Override
    List<WeatherEntryResponse> getAllEntries();

    @Override
    WeatherEntryResponse getEntryById(int id);

    @Override
    WeatherEntryResponse saveEntry(WeatherEntry entry) throws JsonProcessingException;

    @Override
    WeatherEntryResponse deleteEntry(int id);

    WeatherEntryResponse saveNewEntry(String city, Date createdAt) throws JsonProcessingException;
}
