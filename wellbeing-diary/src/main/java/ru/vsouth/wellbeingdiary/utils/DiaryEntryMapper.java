package ru.vsouth.wellbeingdiary.utils;

import org.springframework.stereotype.Component;
import ru.vsouth.wellbeingdiary.dto.DiaryEntryRequest;
import ru.vsouth.wellbeingdiary.dto.DiaryEntryResponse;
import ru.vsouth.wellbeingdiary.dto.OpenDiaryEntryResponse;
import ru.vsouth.wellbeingdiary.model.DiaryEntry;
import ru.vsouth.wellbeingdiary.model.User;

@Component
public class DiaryEntryMapper {
    public DiaryEntryResponse toDiaryEntryResponse(DiaryEntry diaryEntry) {
        return new DiaryEntryResponse(
                diaryEntry.getId(),
                diaryEntry.getUser().getId(),
                diaryEntry.getHealthEntry(),
                diaryEntry.getWeatherEntryId(),
                diaryEntry.getCreatedAt(),
                diaryEntry.getEntryText(),
                diaryEntry.getMood(),
                diaryEntry.getStateOfHealth(),
                diaryEntry.getActivityAmount()
                );
    }

    public OpenDiaryEntryResponse toOpenDiaryEntryResponse(DiaryEntry diaryEntry) {
        return new OpenDiaryEntryResponse(
                diaryEntry.getId(),
                diaryEntry.getHealthEntry(),
                diaryEntry.getWeatherEntryId(),
                diaryEntry.getCreatedAt(),
                diaryEntry.getMood(),
                diaryEntry.getStateOfHealth(),
                diaryEntry.getActivityAmount()
        );
    }

    public DiaryEntry toDiaryEntry(DiaryEntryRequest diaryEntryRequest) {
        DiaryEntry diaryEntry = new DiaryEntry();
        diaryEntry.setId(diaryEntryRequest.getId());
        User user = new User();
        user.setId(diaryEntryRequest.getUserId());
        diaryEntry.setUser(user);
        diaryEntry.setHealthEntry(diaryEntryRequest.getHealthEntry());
        diaryEntry.setWeatherEntryId(diaryEntryRequest.getWeatherEntryId());
        diaryEntry.setCreatedAt(diaryEntryRequest.getCreatedAt());
        diaryEntry.setEntryText(diaryEntryRequest.getEntryText());
        diaryEntry.setMood(diaryEntryRequest.getMood());
        diaryEntry.setStateOfHealth(diaryEntryRequest.getStateOfHealth());
        diaryEntry.setActivityAmount(diaryEntryRequest.getActivityAmount());
        return diaryEntry;
    }
}
