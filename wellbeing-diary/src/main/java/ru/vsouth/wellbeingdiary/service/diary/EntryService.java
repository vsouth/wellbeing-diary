package ru.vsouth.wellbeingdiary.service.diary;

import ru.vsouth.wellbeingdiary.dto.EntryResponse;

import java.util.List;

public interface EntryService<T, R extends EntryResponse> {

    List<R> getAllEntries();

    R getEntryById(int id);

    R saveEntry(T entry);

    R deleteEntry(int id);

    R updateEntry(T entry);
}
