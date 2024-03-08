package ru.vsouth.wellbeingdiary.service;

import org.springframework.stereotype.Service;
import ru.vsouth.wellbeingdiary.dto.EntryResponse;
import ru.vsouth.wellbeingdiary.model.DiaryEntry;
import ru.vsouth.wellbeingdiary.model.Entry;
import ru.vsouth.wellbeingdiary.repository.DiaryEntryRepository;
import ru.vsouth.wellbeingdiary.utils.DiaryEntryResponseMapper;

import java.util.List;
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
        return diaryEntryRepository.findOpenDiaryEntries().stream()
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
        return diaryEntryRepository.findByUserId(userId).stream()
                .map(diaryEntryResponseMapper::mapOpenDiaryEntry)
                .collect(Collectors.toList());
    }

    public EntryResponse getEntryByUserIdAndDiaryEntryId(int userId, int diaryEntryId) {
        DiaryEntry entry = diaryEntryRepository.findByUserIdAndId(userId, diaryEntryId).orElse(null);
        if (entry == null) {
            return null;
        }
        return diaryEntryResponseMapper.mapDiaryEntry(entry);
    }

    @Override
    public EntryResponse saveEntry(Entry entry) {
        DiaryEntry savedEntry = diaryEntryRepository.save((DiaryEntry) entry);
        return diaryEntryResponseMapper.mapDiaryEntry(savedEntry);
    }

    @Override
    public EntryResponse deleteEntry(int id) {
        DiaryEntry entry = diaryEntryRepository.findById(id).orElse(null);
        if (entry == null) {
            return null;
        }
        diaryEntryRepository.deleteById(id);
        return diaryEntryResponseMapper.mapDiaryEntry(entry);
    }

    @Override
    public EntryResponse updateEntry(Entry entry) {
        DiaryEntry existingEntry = diaryEntryRepository.findById(((DiaryEntry) entry).getId()).orElse(null);
        if (existingEntry == null) {
            return null;
        }
        existingEntry.setEntryText(((DiaryEntry) entry).getEntryText());
        existingEntry.setMood(((DiaryEntry) entry).getMood());
        existingEntry.setStateOfHealth(((DiaryEntry) entry).getStateOfHealth());
        existingEntry.setActivityAmount(((DiaryEntry) entry).getActivityAmount());
        DiaryEntry savedEntry = diaryEntryRepository.save(existingEntry);
        return diaryEntryResponseMapper.mapDiaryEntry(savedEntry);
    }
}
