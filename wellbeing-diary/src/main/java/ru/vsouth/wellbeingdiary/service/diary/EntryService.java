package ru.vsouth.wellbeingdiary.service.diary;

import ru.vsouth.wellbeingdiary.dto.EntryResponse;
import ru.vsouth.wellbeingdiary.model.Entry;

import java.util.List;

public interface EntryService<T extends Entry, R extends EntryResponse> {

    List<R> getAllEntries();

    R getEntryById(int id);

    R saveEntry(T entry);

    R deleteEntry(int id);

    R updateEntry(T entry);
}
