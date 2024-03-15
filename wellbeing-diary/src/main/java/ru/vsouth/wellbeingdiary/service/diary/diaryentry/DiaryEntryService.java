package ru.vsouth.wellbeingdiary.service.diary.diaryentry;

import ru.vsouth.wellbeingdiary.dto.diary.DiaryEntryRequest;
import ru.vsouth.wellbeingdiary.dto.diary.DiaryEntryResponse;
import ru.vsouth.wellbeingdiary.dto.diary.OpenDiaryEntryResponse;
import ru.vsouth.wellbeingdiary.service.diary.EntryService;

import java.util.List;

public interface DiaryEntryService extends EntryService<DiaryEntryRequest, DiaryEntryResponse> {
    @Override
    List<DiaryEntryResponse> getAllEntries();

    @Override
    DiaryEntryResponse getEntryById(int id);

    @Override
    DiaryEntryResponse saveEntry(DiaryEntryRequest diaryEntryRequest);

    @Override
    DiaryEntryResponse deleteEntry(int id);

    List<DiaryEntryResponse> getEntriesByUserId(int userId);

    List<OpenDiaryEntryResponse> getAllOpenEntries();
    DiaryEntryResponse getEntryByUserIdAndDiaryEntryId(int userId, int diaryEntryId);

}
