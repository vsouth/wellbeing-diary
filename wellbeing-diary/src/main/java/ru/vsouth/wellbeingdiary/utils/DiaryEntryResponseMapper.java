package ru.vsouth.wellbeingdiary.utils;

import org.springframework.stereotype.Component;
import ru.vsouth.wellbeingdiary.dto.DiaryEntryResponse;
import ru.vsouth.wellbeingdiary.dto.OpenDiaryEntryResponse;
import ru.vsouth.wellbeingdiary.dto.UserResponse;
import ru.vsouth.wellbeingdiary.model.DiaryEntry;
import ru.vsouth.wellbeingdiary.model.Grade;
import ru.vsouth.wellbeingdiary.model.User;

import java.util.Date;

@Component
public class DiaryEntryResponseMapper {
    public DiaryEntryResponse mapDiaryEntry(DiaryEntry diaryEntry) {
        return new DiaryEntryResponse(
                diaryEntry.getId(),
                diaryEntry.getUserId(),
                diaryEntry.getHealthEntryId(),
                diaryEntry.getWeatherEntryId(),
                diaryEntry.getCreatedAt(),
                diaryEntry.getEntryText(),
                diaryEntry.getMood(),
                diaryEntry.getStateOfHealth(),
                diaryEntry.getActivityAmount()
                );
    }

    public OpenDiaryEntryResponse mapOpenDiaryEntry(DiaryEntry diaryEntry) {
        return new OpenDiaryEntryResponse(
                diaryEntry.getId(),
                diaryEntry.getHealthEntryId(),
                diaryEntry.getWeatherEntryId(),
                diaryEntry.getCreatedAt(),
                diaryEntry.getMood(),
                diaryEntry.getStateOfHealth(),
                diaryEntry.getActivityAmount()
        );
    }
}
