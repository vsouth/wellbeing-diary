package ru.vsouth.wellbeingdiary.service;

import ru.vsouth.wellbeingdiary.dto.EntryResponse;
import ru.vsouth.wellbeingdiary.dto.UserResponse;
import ru.vsouth.wellbeingdiary.model.Entry;
import ru.vsouth.wellbeingdiary.model.User;

import java.util.List;

public interface EntryService {

    List<EntryResponse> getAllEntries();
    List<EntryResponse> getAllOpenEntries();

    EntryResponse getEntryById(int id);

    List<EntryResponse> getEntriesByUserId(int userId);

    EntryResponse getEntryByUserIdAndDiaryEntryId(int userId, int diaryEntryId);

    EntryResponse saveEntry(Entry entry);

    EntryResponse deleteEntry(int id);

    EntryResponse updateEntry(Entry entry);
}
