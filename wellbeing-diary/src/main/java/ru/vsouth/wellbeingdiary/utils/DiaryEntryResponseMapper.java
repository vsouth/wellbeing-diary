package ru.vsouth.wellbeingdiary.utils;

import org.springframework.stereotype.Component;
import ru.vsouth.wellbeingdiary.dto.DiaryEntryResponse;
import ru.vsouth.wellbeingdiary.dto.OpenDiaryEntryResponse;
import ru.vsouth.wellbeingdiary.model.DiaryEntry;

@Component
public class DiaryEntryResponseMapper {
    public DiaryEntryResponse mapDiaryEntry(DiaryEntry diaryEntry) {
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

    public OpenDiaryEntryResponse mapOpenDiaryEntry(DiaryEntry diaryEntry) {
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
}
