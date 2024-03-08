package ru.vsouth.wellbeingdiary.service;

import org.springframework.stereotype.Service;
import ru.vsouth.wellbeingdiary.dto.DiaryEntryResponse;
import ru.vsouth.wellbeingdiary.dto.OpenDiaryEntryResponse;
import ru.vsouth.wellbeingdiary.model.DiaryEntry;
import ru.vsouth.wellbeingdiary.repository.DiaryEntryRepository;
import ru.vsouth.wellbeingdiary.utils.DiaryEntryResponseMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiaryEntryServiceImpl implements EntryService<DiaryEntry, DiaryEntryResponse, OpenDiaryEntryResponse> {
    private final DiaryEntryRepository diaryEntryRepository;
    private final DiaryEntryResponseMapper diaryEntryResponseMapper;

    public DiaryEntryServiceImpl(DiaryEntryRepository diaryEntryRepository, DiaryEntryResponseMapper diaryEntryResponseMapper) {
        this.diaryEntryRepository = diaryEntryRepository;
        this.diaryEntryResponseMapper = diaryEntryResponseMapper;
    }

    @Override
    public List<DiaryEntryResponse> getAllEntries() {
        return diaryEntryRepository.findAll().stream()
                .map(diaryEntryResponseMapper::mapDiaryEntry)
                .collect(Collectors.toList());
    }

    @Override
    public List<OpenDiaryEntryResponse> getAllOpenEntries() {
        return diaryEntryRepository.findOpenDiaryEntries().stream()
                .map(diaryEntryResponseMapper::mapOpenDiaryEntry)
                .collect(Collectors.toList());
    }

    @Override
    public DiaryEntryResponse getEntryById(int id) {
        DiaryEntry diaryEntry = diaryEntryRepository.findById(id).orElse(null);
        if (diaryEntry == null) {
            return null;
        }
        return diaryEntryResponseMapper.mapDiaryEntry(diaryEntry);
    }

    @Override
    public List<DiaryEntryResponse> getEntriesByUserId(int userId) {
        return diaryEntryRepository.findByUserId(userId).stream()
                .map(diaryEntryResponseMapper::mapDiaryEntry)
                .collect(Collectors.toList());
    }

    public DiaryEntryResponse getEntryByUserIdAndDiaryEntryId(int userId, int diaryEntryId) {
        DiaryEntry entry = diaryEntryRepository.findByUserIdAndId(userId, diaryEntryId).orElse(null);
        if (entry == null) {
            return null;
        }
        return diaryEntryResponseMapper.mapDiaryEntry(entry);
    }

    @Override
    public DiaryEntryResponse saveEntry(DiaryEntry entry) {
        DiaryEntry savedEntry = diaryEntryRepository.save(entry);
        return diaryEntryResponseMapper.mapDiaryEntry(savedEntry);
    }

    @Override
    public DiaryEntryResponse deleteEntry(int id) {
        DiaryEntry entry = diaryEntryRepository.findById(id).orElse(null);
        if (entry == null) {
            return null;
        }
        diaryEntryRepository.deleteById(id);
        return diaryEntryResponseMapper.mapDiaryEntry(entry);
    }

    @Override
    public DiaryEntryResponse updateEntry(DiaryEntry entry) {
        DiaryEntry existingEntry = diaryEntryRepository.findById(entry.getId()).orElse(null);
        if (existingEntry == null) {
            return null;
        }
        existingEntry.setEntryText(entry.getEntryText());
        existingEntry.setMood(entry.getMood());
        existingEntry.setStateOfHealth(entry.getStateOfHealth());
        existingEntry.setActivityAmount(entry.getActivityAmount());
        DiaryEntry savedEntry = diaryEntryRepository.save(existingEntry);
        return diaryEntryResponseMapper.mapDiaryEntry(savedEntry);
    }
}
