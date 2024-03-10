package ru.vsouth.wellbeingdiary.service.diary.weatherentry;

import ru.vsouth.wellbeingdiary.dto.WeatherEntryResponse;
import ru.vsouth.wellbeingdiary.model.WeatherEntry;
import ru.vsouth.wellbeingdiary.service.diary.EntryService;

import java.util.List;

public interface WeatherEntryService extends EntryService<WeatherEntry, WeatherEntryResponse> {
    @Override
    List<WeatherEntryResponse> getAllEntries();

    @Override
    WeatherEntryResponse getEntryById(int id);

    @Override
    WeatherEntryResponse saveEntry(WeatherEntry entry);

    @Override
    WeatherEntryResponse deleteEntry(int id);

    @Override
    WeatherEntryResponse updateEntry(WeatherEntry entry);
}
