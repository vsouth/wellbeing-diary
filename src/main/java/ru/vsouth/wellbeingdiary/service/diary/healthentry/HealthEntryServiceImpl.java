package ru.vsouth.wellbeingdiary.service.diary.healthentry;

import org.springframework.stereotype.Service;
import ru.vsouth.wellbeingdiary.dto.diary.HealthEntryResponse;
import ru.vsouth.wellbeingdiary.model.diary.HealthEntry;
import ru.vsouth.wellbeingdiary.repository.HealthEntryRepository;
import ru.vsouth.wellbeingdiary.utils.diary.HealthEntryMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Сервис для работы с записями о состоянии здоровья
 */
@Service
public class HealthEntryServiceImpl implements HealthEntryService {
    private final HealthEntryRepository healthEntryRepository;
    private final HealthEntryMapper healthEntryMapper;

    public HealthEntryServiceImpl(HealthEntryRepository healthEntryRepository, HealthEntryMapper healthEntryMapper) {
        this.healthEntryRepository = healthEntryRepository;
        this.healthEntryMapper = healthEntryMapper;
    }

    /**
     * Метод для получения всех записей о состоянии здоровья
     *
     * @return Список записей о здоровье
     */
    @Override
    public List<HealthEntryResponse> getAllEntries() {
        return healthEntryRepository.findAll().stream().map(healthEntryMapper::mapHealthEntryResponse).collect(Collectors.toList());
    }

    /**
     * Метод для получения записи о состоянии здоровья по идентификатору
     *
     * @param id Идентификатор записи
     * @return Запись о состоянии здоровья, или null, если запись не найдена
     */
    @Override
    public HealthEntryResponse getEntryById(int id) {
        Optional<HealthEntry> optionalHealthEntry = healthEntryRepository.findById(id);
        if (optionalHealthEntry.isPresent()) {
            HealthEntry healthEntry = optionalHealthEntry.get();
            return healthEntryMapper.mapHealthEntryResponse(healthEntry);
        } else {
            return null;
        }
    }

    /**
     * Метод для сохранения записи о состоянии здоровья
     *
     * @param entry Запись о состоянии здоровья для сохранения
     * @return Сохраненная запись о состоянии здоровья
     */
    @Override
    public HealthEntryResponse saveEntry(HealthEntry entry) {
        HealthEntry savedEntry = healthEntryRepository.save(entry);
        return healthEntryMapper.mapHealthEntryResponse(savedEntry);
    }

    /**
     * Метод для удаления записи о состоянии здоровья по идентификатору
     *
     * @param id Идентификатор записи о состоянии здоровья для удаления
     * @return Удаленная запись о состоянии здоровья, или null, если запись не найдена
     */
    @Override
    public HealthEntryResponse deleteEntry(int id) {
        Optional<HealthEntry> optionalEntry = healthEntryRepository.findById(id);
        if (optionalEntry.isPresent()) {
            HealthEntry entry = optionalEntry.get();
            healthEntryRepository.deleteById(id);
            return healthEntryMapper.mapHealthEntryResponse(entry);
        } else {
            return null;
        }
    }
}
