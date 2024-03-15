package ru.vsouth.wellbeingdiary.service.diary.healthentry;

import org.springframework.stereotype.Service;
import ru.vsouth.wellbeingdiary.dto.diary.HealthEntryResponse;
import ru.vsouth.wellbeingdiary.model.diary.HealthEntry;
import ru.vsouth.wellbeingdiary.repository.HealthEntryRepository;
import ru.vsouth.wellbeingdiary.utils.diary.HealthEntryMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class HealthEntryServiceImpl implements HealthEntryService {
    private final HealthEntryRepository healthEntryRepository;
    private final HealthEntryMapper healthEntryMapper;

    public HealthEntryServiceImpl(HealthEntryRepository healthEntryRepository, HealthEntryMapper healthEntryMapper) {
        this.healthEntryRepository = healthEntryRepository;
        this.healthEntryMapper = healthEntryMapper;
    }

    @Override
    public List<HealthEntryResponse> getAllEntries() {
        return healthEntryRepository.findAll().stream().map(healthEntryMapper::mapHealthEntryResponse).collect(Collectors.toList());
    }

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

    @Override
    public HealthEntryResponse saveEntry(HealthEntry entry) {
        HealthEntry savedEntry = healthEntryRepository.save(entry);
        return healthEntryMapper.mapHealthEntryResponse(savedEntry);
    }

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
