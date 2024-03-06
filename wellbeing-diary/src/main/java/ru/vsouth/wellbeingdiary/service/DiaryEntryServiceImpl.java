package ru.vsouth.wellbeingdiary.service;

import org.springframework.stereotype.Service;
import ru.vsouth.wellbeingdiary.dto.EntryResponse;
import ru.vsouth.wellbeingdiary.model.DiaryEntry;
import ru.vsouth.wellbeingdiary.model.Entry;
import ru.vsouth.wellbeingdiary.repository.DiaryEntryRepository;
import ru.vsouth.wellbeingdiary.utils.DiaryEntryResponseMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class DiaryEntryServiceImpl implements EntryService {
    private final DiaryEntryRepository diaryEntryRepository;
    private final DiaryEntryResponseMapper diaryEntryResponseMapper;

    public DiaryEntryServiceImpl(DiaryEntryRepository diaryEntryRepository, DiaryEntryResponseMapper diaryEntryResponseMapper) {
        this.diaryEntryRepository = diaryEntryRepository;
        this.diaryEntryResponseMapper = diaryEntryResponseMapper;
    }

    @Override
    public List<EntryResponse> getAllEntries() {
        return diaryEntryRepository.findAll().stream()
                .map(diaryEntryResponseMapper::mapDiaryEntry)
                .collect(Collectors.toList());
    }

    @Override
    public List<EntryResponse> getAllOpenEntries() {
        return diaryEntryRepository.findAll().stream()
                .map(diaryEntryResponseMapper::mapOpenDiaryEntry)
                .collect(Collectors.toList());
    }

    @Override
    public EntryResponse getEntryById(int id) {
        DiaryEntry diaryEntry = diaryEntryRepository.findById(id).orElse(null);
        if (diaryEntry == null) {
            return null;
        }
        return diaryEntryResponseMapper.mapDiaryEntry(diaryEntry);
    }

    @Override
    public List<EntryResponse> getEntriesByUserId(int userId) {
        // TODO: add impl
        return null;
    }

    public EntryResponse getEntryByUserIdAndDiaryEntryId(int userId, int diaryEntryId) {
        DiaryEntry entry = diaryEntryRepository.findByUserIdAndDiaryEntryId(userId, diaryEntryId).orElse(null);
        if (entry == null) {
            return null;
        }
        return diaryEntryResponseMapper.mapDiaryEntry(entry);
    }

    @Override
    public EntryResponse saveEntry(Entry entry) {
        // TODO: add impl
        return null;
    }

    @Override
    public EntryResponse deleteEntry(int id) {
        // TODO: add impl
        return null;
    }

    @Override
    public EntryResponse updateEntry(Entry entry) {
        // TODO: add impl
        return null;
    }
}
