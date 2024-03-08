package ru.vsouth.wellbeingdiary.service;

import ru.vsouth.wellbeingdiary.dto.EntryResponse;
import ru.vsouth.wellbeingdiary.model.Entry;

import java.util.List;

public interface EntryService<T extends Entry, R extends EntryResponse, O extends EntryResponse> {

    List<R> getAllEntries();

    List<O> getAllOpenEntries();

    R getEntryById(int id);

    List<R> getEntriesByUserId(int userId);

    R getEntryByUserIdAndDiaryEntryId(int userId, int diaryEntryId);

    R saveEntry(T entry);

    R deleteEntry(int id);

    R updateEntry(T entry);
}
