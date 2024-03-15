package ru.vsouth.wellbeingdiary.service.diary.diaryentry;

import org.springframework.stereotype.Service;
import ru.vsouth.wellbeingdiary.dto.diary.DiaryEntryRequest;
import ru.vsouth.wellbeingdiary.dto.diary.DiaryEntryResponse;
import ru.vsouth.wellbeingdiary.dto.diary.OpenDiaryEntryResponse;
import ru.vsouth.wellbeingdiary.model.diary.DiaryEntry;
import ru.vsouth.wellbeingdiary.repository.DiaryEntryRepository;
import ru.vsouth.wellbeingdiary.utils.diary.DiaryEntryMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DiaryEntryServiceImpl implements DiaryEntryService {
    private final DiaryEntryRepository diaryEntryRepository;
    private final DiaryEntryMapper diaryEntryMapper;

    public DiaryEntryServiceImpl(DiaryEntryRepository diaryEntryRepository, DiaryEntryMapper diaryEntryMapper) {
        this.diaryEntryRepository = diaryEntryRepository;
        this.diaryEntryMapper = diaryEntryMapper;
    }

    @Override
    public List<DiaryEntryResponse> getAllEntries() {
        return diaryEntryRepository.findAll().stream().map(diaryEntryMapper::toDiaryEntryResponse).collect(Collectors.toList());
    }

    @Override
    public List<OpenDiaryEntryResponse> getAllOpenEntries() {
        return diaryEntryRepository.findOpenDiaryEntries().stream().map(diaryEntryMapper::toOpenDiaryEntryResponse).collect(Collectors.toList());
    }

    @Override
    public DiaryEntryResponse getEntryById(int id) {
        Optional<DiaryEntry> optionalDiaryEntry = diaryEntryRepository.findById(id);
        if (optionalDiaryEntry.isPresent()) {
            DiaryEntry diaryEntry = optionalDiaryEntry.get();
            return diaryEntryMapper.toDiaryEntryResponse(diaryEntry);
        } else {
            return null;
        }
    }

    @Override
    public List<DiaryEntryResponse> getEntriesByUserId(int userId) {
        return diaryEntryRepository.findByUserId(userId).stream().map(diaryEntryMapper::toDiaryEntryResponse).collect(Collectors.toList());
    }

    @Override
    public DiaryEntryResponse getEntryByUserIdAndDiaryEntryId(int userId, int diaryEntryId) {
        Optional<DiaryEntry> optionalEntry = diaryEntryRepository.findByUserIdAndId(userId, diaryEntryId);
        if (optionalEntry.isPresent()) {
            DiaryEntry entry = optionalEntry.get();
            return diaryEntryMapper.toDiaryEntryResponse(entry);
        } else {
            return null;
        }
    }

    @Override
    public DiaryEntryResponse saveEntry(DiaryEntryRequest diaryEntryRequest) {
        DiaryEntry diaryEntryToSave = diaryEntryMapper.toDiaryEntry(diaryEntryRequest);
        DiaryEntry savedEntry = diaryEntryRepository.save(diaryEntryToSave);
        return diaryEntryMapper.toDiaryEntryResponse(savedEntry);
    }

    @Override
    public DiaryEntryResponse deleteEntry(int id) {
        Optional<DiaryEntry> optionalEntry = diaryEntryRepository.findById(id);
        if (optionalEntry.isPresent()) {
            DiaryEntry entry = optionalEntry.get();
            diaryEntryRepository.deleteById(id);
            return diaryEntryMapper.toDiaryEntryResponse(entry);
        } else {
            return null;
        }
    }
}