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

/**
 * Сервис для работы с записями дневника
 */
@Service
public class DiaryEntryServiceImpl implements DiaryEntryService {
    private final DiaryEntryRepository diaryEntryRepository;
    private final DiaryEntryMapper diaryEntryMapper;

    public DiaryEntryServiceImpl(DiaryEntryRepository diaryEntryRepository, DiaryEntryMapper diaryEntryMapper) {
        this.diaryEntryRepository = diaryEntryRepository;
        this.diaryEntryMapper = diaryEntryMapper;
    }

    /**
     * Метод для получения всех записей
     *
     * @return Список записей дневника
     */
    @Override
    public List<DiaryEntryResponse> getAllEntries() {
        return diaryEntryRepository.findAll().stream().map(diaryEntryMapper::toDiaryEntryResponse).collect(Collectors.toList());
    }

    /**
     * Метод для получения открытых записей
     *
     * @return Список открытых записей дневника
     */
    @Override
    public List<OpenDiaryEntryResponse> getAllOpenEntries() {
        return diaryEntryRepository.findOpenDiaryEntries().stream().map(diaryEntryMapper::toOpenDiaryEntryResponse).collect(Collectors.toList());
    }

    /**
     * Метод для получения записи дневника по идентификатору
     *
     * @param id Идентификатор записи дневника
     * @return Запись дневника, или null, если запись не найдена
     */
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

    /**
     * Метод для получения записей дневника по идентификатору пользователя
     *
     * @param userId Идентификатор пользователя
     * @return Список записей дневника
     */
    @Override
    public List<DiaryEntryResponse> getEntriesByUserId(int userId) {
        return diaryEntryRepository.findByUserId(userId).stream().map(diaryEntryMapper::toDiaryEntryResponse).collect(Collectors.toList());
    }

    /**
     * Метод для получения записи дневника по идентификаторам пользователя и записи
     *
     * @param userId       Идентификатор пользователя
     * @param diaryEntryId Идентификатор записи дневника
     * @return Запись дневника, или null, если запись не найдена
     */
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

    /**
     * Метод для сохранения записи дневника
     *
     * @param diaryEntryRequest Запрос на сохранение записи дневника
     * @return Сохраненная запись
     */
    @Override
    public DiaryEntryResponse saveEntry(DiaryEntryRequest diaryEntryRequest) {
        DiaryEntry diaryEntryToSave = diaryEntryMapper.toDiaryEntry(diaryEntryRequest);
        DiaryEntry savedEntry = diaryEntryRepository.save(diaryEntryToSave);
        return diaryEntryMapper.toDiaryEntryResponse(savedEntry);
    }

    /**
     * Метод для удаления записи дневника по идентификатору
     *
     * @param id Идентификатор записи дневника
     * @return Удаленная запись, или null, если запись не найдена
     */
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
