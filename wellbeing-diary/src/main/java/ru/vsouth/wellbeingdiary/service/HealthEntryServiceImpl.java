package ru.vsouth.wellbeingdiary.service;

import org.springframework.stereotype.Service;
import ru.vsouth.wellbeingdiary.dto.HealthEntryResponse;
import ru.vsouth.wellbeingdiary.model.HealthEntry;
import ru.vsouth.wellbeingdiary.repository.HealthEntryRepository;
import ru.vsouth.wellbeingdiary.utils.HealthEntryResponseMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class HealthEntryServiceImpl implements HealthEntryService {
    private final HealthEntryRepository healthEntryRepository;
    private final HealthEntryResponseMapper healthEntryResponseMapper;

    public HealthEntryServiceImpl(HealthEntryRepository healthEntryRepository, HealthEntryResponseMapper healthEntryResponseMapper) {
        this.healthEntryRepository = healthEntryRepository;
        this.healthEntryResponseMapper = healthEntryResponseMapper;
    }

    @Override
    public List<HealthEntryResponse> getAllEntries() {
        return healthEntryRepository.findAll().stream().map(healthEntryResponseMapper::mapHealthEntryResponse).collect(Collectors.toList());
    }

    @Override
    public HealthEntryResponse getEntryById(int id) {
        Optional<HealthEntry> optionalHealthEntry = healthEntryRepository.findById(id);
        if (optionalHealthEntry.isPresent()) {
            HealthEntry healthEntry = optionalHealthEntry.get();
            return healthEntryResponseMapper.mapHealthEntryResponse(healthEntry);
        } else {
            return null;
        }
    }

    @Override
    public HealthEntryResponse saveEntry(HealthEntry entry) {
        HealthEntry savedEntry = healthEntryRepository.save(entry);
        return healthEntryResponseMapper.mapHealthEntryResponse(savedEntry);
    }

    @Override
    public HealthEntryResponse deleteEntry(int id) {
        Optional<HealthEntry> optionalEntry = healthEntryRepository.findById(id);
        if (optionalEntry.isPresent()) {
            HealthEntry entry = optionalEntry.get();
            healthEntryRepository.deleteById(id);
            return healthEntryResponseMapper.mapHealthEntryResponse(entry);
        } else {
            return null;
        }
    }

    @Override
    public HealthEntryResponse updateEntry(HealthEntry entry) {
        Optional<HealthEntry> optionalExistingEntry = healthEntryRepository.findById(entry.getId());
        if (optionalExistingEntry.isPresent()) {
            HealthEntry existingEntry = optionalExistingEntry.get();

            existingEntry.setHeartRate(entry.getHeartRate());
            existingEntry.setSystolicBloodPressure(entry.getSystolicBloodPressure());
            existingEntry.setDiastolicBloodPressure(entry.getDiastolicBloodPressure());

            HealthEntry savedEntry = healthEntryRepository.save(existingEntry);
            return healthEntryResponseMapper.mapHealthEntryResponse(savedEntry);
        } else {
            return null;
        }
    }
}
