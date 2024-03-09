package ru.vsouth.wellbeingdiary.service;

import ru.vsouth.wellbeingdiary.dto.DiaryEntryResponse;
import ru.vsouth.wellbeingdiary.dto.OpenDiaryEntryResponse;
import ru.vsouth.wellbeingdiary.model.DiaryEntry;

import java.util.List;

public interface DiaryEntryService extends EntryService<DiaryEntry, DiaryEntryResponse> {
    @Override
    List<DiaryEntryResponse> getAllEntries();

    @Override
    DiaryEntryResponse getEntryById(int id);

    @Override
    DiaryEntryResponse saveEntry(DiaryEntry entry);

    @Override
    DiaryEntryResponse deleteEntry(int id);

    @Override
    DiaryEntryResponse updateEntry(DiaryEntry entry);

    List<DiaryEntryResponse> getEntriesByUserId(int userId);

    List<OpenDiaryEntryResponse> getAllOpenEntries();
    DiaryEntryResponse getEntryByUserIdAndDiaryEntryId(int userId, int diaryEntryId);

}
