package ru.vsouth.wellbeingdiary.utils.diary;

import org.springframework.stereotype.Component;
import ru.vsouth.wellbeingdiary.dto.diary.DiaryEntryRequest;
import ru.vsouth.wellbeingdiary.dto.diary.DiaryEntryResponse;
import ru.vsouth.wellbeingdiary.dto.diary.OpenDiaryEntryResponse;
import ru.vsouth.wellbeingdiary.model.diary.DiaryEntry;
import ru.vsouth.wellbeingdiary.model.user.User;

import java.util.Optional;

@Component
public class DiaryEntryMapper {
    public DiaryEntryResponse toDiaryEntryResponse(DiaryEntry diaryEntry) {
        return new DiaryEntryResponse(
                diaryEntry.getId(),
                diaryEntry.getUser().getId(),
                diaryEntry.getHealthEntry(),
                diaryEntry.getWeatherEntry(),
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
                diaryEntry.getWeatherEntry(),
                diaryEntry.getCreatedAt(),
                diaryEntry.getMood(),
                diaryEntry.getStateOfHealth(),
                diaryEntry.getActivityAmount()
        );
    }

    public DiaryEntry toDiaryEntry(DiaryEntryRequest diaryEntryRequest) {
        DiaryEntry diaryEntry = new DiaryEntry();
        diaryEntry.setId(Optional.ofNullable(diaryEntryRequest.getId()).orElse(0));
        User user = new User();
        user.setId(diaryEntryRequest.getUserId());
        diaryEntry.setUser(user);
        diaryEntry.setHealthEntry(diaryEntryRequest.getHealthEntry());
        diaryEntry.setWeatherEntry(diaryEntryRequest.getWeatherEntry());
        diaryEntry.setCreatedAt(diaryEntryRequest.getCreatedAt());
        diaryEntry.setEntryText(diaryEntryRequest.getEntryText());
        diaryEntry.setMood(diaryEntryRequest.getMood());
        diaryEntry.setStateOfHealth(diaryEntryRequest.getStateOfHealth());
        diaryEntry.setActivityAmount(diaryEntryRequest.getActivityAmount());
        return diaryEntry;
    }
}
