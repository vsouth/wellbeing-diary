package ru.vsouth.wellbeingdiary.utils.diary;

import org.junit.jupiter.api.Test;
import ru.vsouth.wellbeingdiary.dto.diary.HealthEntryResponse;
import ru.vsouth.wellbeingdiary.model.diary.HealthEntry;

import static org.junit.jupiter.api.Assertions.*;

class HealthEntryMapperTest {
    @Test
    void testMapHealthEntryResponse() {
        HealthEntryMapper mapper = new HealthEntryMapper();
        HealthEntry healthEntry = new HealthEntry();
        healthEntry.setId(1);
        healthEntry.setHeartRate(75);
        healthEntry.setSystolicBloodPressure(120);
        healthEntry.setDiastolicBloodPressure(80);

        HealthEntryResponse response = mapper.mapHealthEntryResponse(healthEntry);

        assertEquals(healthEntry.getId(), response.getId());
        assertEquals(healthEntry.getHeartRate(), response.getHeartRate());
        assertEquals(healthEntry.getSystolicBloodPressure(), response.getSystolicBloodPressure());
        assertEquals(healthEntry.getDiastolicBloodPressure(), response.getDiastolicBloodPressure());
    }
}