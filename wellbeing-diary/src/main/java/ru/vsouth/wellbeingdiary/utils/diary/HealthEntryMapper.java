package ru.vsouth.wellbeingdiary.utils.diary;

import org.springframework.stereotype.Component;
import ru.vsouth.wellbeingdiary.dto.diary.HealthEntryResponse;
import ru.vsouth.wellbeingdiary.model.diary.HealthEntry;
@Component
public class HealthEntryMapper {
    public HealthEntryResponse mapHealthEntryResponse(HealthEntry healthEntry) {
        return new HealthEntryResponse(
                healthEntry.getId(),
                healthEntry.getHeartRate(),
                healthEntry.getSystolicBloodPressure(),
                healthEntry.getDiastolicBloodPressure()
        );
    }
}
