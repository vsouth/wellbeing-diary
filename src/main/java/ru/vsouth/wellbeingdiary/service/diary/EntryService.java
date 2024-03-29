package ru.vsouth.wellbeingdiary.service.diary;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.vsouth.wellbeingdiary.dto.diary.EntryResponse;

import java.util.List;

public interface EntryService<T, R extends EntryResponse> {

    List<R> getAllEntries();

    R getEntryById(int id);

    R saveEntry(T entry) throws JsonProcessingException;

    R deleteEntry(int id);
}
