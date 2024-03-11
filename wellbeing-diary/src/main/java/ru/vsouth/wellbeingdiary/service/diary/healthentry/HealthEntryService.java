package ru.vsouth.wellbeingdiary.service.diary.healthentry;

import ru.vsouth.wellbeingdiary.dto.HealthEntryResponse;
import ru.vsouth.wellbeingdiary.model.HealthEntry;
import ru.vsouth.wellbeingdiary.service.diary.EntryService;

import java.util.List;

public interface HealthEntryService extends EntryService<HealthEntry, HealthEntryResponse> {
    @Override
    List<HealthEntryResponse> getAllEntries();

    @Override
    HealthEntryResponse getEntryById(int id);

    @Override
    HealthEntryResponse saveEntry(HealthEntry entry);

    @Override
    HealthEntryResponse deleteEntry(int id);

}
