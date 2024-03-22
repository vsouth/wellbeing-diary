package ru.vsouth.wellbeingdiary.service.diary.healthentry;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.vsouth.wellbeingdiary.dto.diary.HealthEntryResponse;
import ru.vsouth.wellbeingdiary.model.diary.HealthEntry;
import ru.vsouth.wellbeingdiary.repository.HealthEntryRepository;
import ru.vsouth.wellbeingdiary.repository.UserRepository;
import ru.vsouth.wellbeingdiary.utils.diary.HealthEntryMapper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
@SpringBootTest
class HealthEntryServiceImplTest {
    @Autowired
    private HealthEntryRepository healthEntryRepository;

    private final HealthEntryMapper healthEntryMapper = new HealthEntryMapper();

    private HealthEntryServiceImpl healthEntryService;

    @BeforeEach
    public void setUp() {
        healthEntryService = new HealthEntryServiceImpl(healthEntryRepository, healthEntryMapper);
    }

    @Test
    void testGetAllEntries() {
        List<HealthEntryResponse> result = healthEntryService.getAllEntries();
        assertEquals(2, result.size());
    }

    @Test
    void testGetEntryById() {
        HealthEntryResponse result = healthEntryService.getEntryById(1);

        assertEquals(76, result.getHeartRate());
        assertEquals(120, result.getSystolicBloodPressure());
        assertEquals(80, result.getDiastolicBloodPressure());
    }

    @Test
    void testSaveEntryAndDeleteEntry() {
        HealthEntry testEntry = new HealthEntry();
        testEntry.setHeartRate(60);
        testEntry.setSystolicBloodPressure(120);
        testEntry.setDiastolicBloodPressure(80);
        HealthEntryResponse savedEntry = healthEntryService.saveEntry(testEntry);

        HealthEntryResponse entryById = healthEntryService.getEntryById(savedEntry.getId());
        assertEquals(entryById.getHeartRate(), savedEntry.getHeartRate());
        assertEquals(entryById.getSystolicBloodPressure(), savedEntry.getSystolicBloodPressure());
        assertEquals(entryById.getDiastolicBloodPressure(), savedEntry.getDiastolicBloodPressure());

        healthEntryService.deleteEntry(entryById.getId());
        assertNull(healthEntryService.getEntryById(entryById.getId()));
    }
}