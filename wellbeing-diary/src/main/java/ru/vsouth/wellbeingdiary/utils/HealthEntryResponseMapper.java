package ru.vsouth.wellbeingdiary.utils;

import org.springframework.stereotype.Component;
import ru.vsouth.wellbeingdiary.dto.HealthEntryResponse;
import ru.vsouth.wellbeingdiary.model.HealthEntry;
@Component
public class HealthEntryResponseMapper {
    public HealthEntryResponse mapHealthEntryResponse(HealthEntry healthEntry) {
        return new HealthEntryResponse(
                healthEntry.getId(),
                healthEntry.getHeartRate(),
                healthEntry.getSystolicBloodPressure(),
                healthEntry.getDiastolicBloodPressure()
        );
    }
}
