package ru.vsouth.wellbeingdiary.service.diary.diaryentry;

import org.springframework.stereotype.Service;
import ru.vsouth.wellbeingdiary.dto.DiaryEntryResponse;
import ru.vsouth.wellbeingdiary.dto.OpenDiaryEntryResponse;
import ru.vsouth.wellbeingdiary.model.DiaryEntry;
import ru.vsouth.wellbeingdiary.repository.DiaryEntryRepository;
import ru.vsouth.wellbeingdiary.utils.DiaryEntryResponseMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DiaryEntryServiceImpl implements DiaryEntryService {
    private final DiaryEntryRepository diaryEntryRepository;
    private final DiaryEntryResponseMapper diaryEntryResponseMapper;

    public DiaryEntryServiceImpl(DiaryEntryRepository diaryEntryRepository, DiaryEntryResponseMapper diaryEntryResponseMapper) {
        this.diaryEntryRepository = diaryEntryRepository;
        this.diaryEntryResponseMapper = diaryEntryResponseMapper;
    }

    @Override
    public List<DiaryEntryResponse> getAllEntries() {
        return diaryEntryRepository.findAll().stream().map(diaryEntryResponseMapper::mapDiaryEntry).collect(Collectors.toList());
    }

    @Override
    public List<OpenDiaryEntryResponse> getAllOpenEntries() {
        return diaryEntryRepository.findOpenDiaryEntries().stream().map(diaryEntryResponseMapper::mapOpenDiaryEntry).collect(Collectors.toList());
    }

    @Override
    public DiaryEntryResponse getEntryById(int id) {
        Optional<DiaryEntry> optionalDiaryEntry = diaryEntryRepository.findById(id);
        if (optionalDiaryEntry.isPresent()) {
            DiaryEntry diaryEntry = optionalDiaryEntry.get();
            return diaryEntryResponseMapper.mapDiaryEntry(diaryEntry);
        } else {
            return null;
        }
    }

    @Override
    public List<DiaryEntryResponse> getEntriesByUserId(int userId) {
        return diaryEntryRepository.findByUserId(userId).stream().map(diaryEntryResponseMapper::mapDiaryEntry).collect(Collectors.toList());
    }

    @Override
    public DiaryEntryResponse getEntryByUserIdAndDiaryEntryId(int userId, int diaryEntryId) {
        Optional<DiaryEntry> optionalEntry = diaryEntryRepository.findByUserIdAndId(userId, diaryEntryId);
        if (optionalEntry.isPresent()) {
            DiaryEntry entry = optionalEntry.get();
            return diaryEntryResponseMapper.mapDiaryEntry(entry);
        } else {
            return null;
        }
    }

    @Override
    public DiaryEntryResponse saveEntry(DiaryEntry entry) {
        DiaryEntry savedEntry = diaryEntryRepository.save(entry);
        return diaryEntryResponseMapper.mapDiaryEntry(savedEntry);
    }

    @Override
    public DiaryEntryResponse deleteEntry(int id) {
        Optional<DiaryEntry> optionalEntry = diaryEntryRepository.findById(id);
        if (optionalEntry.isPresent()) {
            DiaryEntry entry = optionalEntry.get();
            diaryEntryRepository.deleteById(id);
            return diaryEntryResponseMapper.mapDiaryEntry(entry);
        } else {
            return null;
        }
    }

    @Override
    public DiaryEntryResponse updateEntry(DiaryEntry entry) {
        Optional<DiaryEntry> optionalExistingEntry = diaryEntryRepository.findById(entry.getId());
        if (optionalExistingEntry.isPresent()) {
            DiaryEntry existingEntry = optionalExistingEntry.get();
            existingEntry.setEntryText(entry.getEntryText());
            existingEntry.setMood(entry.getMood());
            existingEntry.setStateOfHealth(entry.getStateOfHealth());
            existingEntry.setActivityAmount(entry.getActivityAmount());
            DiaryEntry savedEntry = diaryEntryRepository.save(existingEntry);
            return diaryEntryResponseMapper.mapDiaryEntry(savedEntry);
        } else {
            return null;
        }
    }
}
