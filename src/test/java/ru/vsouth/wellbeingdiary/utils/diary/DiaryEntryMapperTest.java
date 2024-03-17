package ru.vsouth.wellbeingdiary.utils.diary;

import org.junit.jupiter.api.Test;
import ru.vsouth.wellbeingdiary.dto.diary.DiaryEntryRequest;
import ru.vsouth.wellbeingdiary.dto.diary.DiaryEntryResponse;
import ru.vsouth.wellbeingdiary.dto.diary.OpenDiaryEntryResponse;
import ru.vsouth.wellbeingdiary.model.diary.DiaryEntry;
import ru.vsouth.wellbeingdiary.model.diary.Grade;
import ru.vsouth.wellbeingdiary.model.diary.HealthEntry;
import ru.vsouth.wellbeingdiary.model.diary.WeatherEntry;
import ru.vsouth.wellbeingdiary.model.user.User;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class DiaryEntryMapperTest {
    @Test
    void testToDiaryEntryResponse() {
        DiaryEntryMapper mapper = new DiaryEntryMapper();
        DiaryEntry diaryEntry = new DiaryEntry();
        diaryEntry.setId(1);

        User user = new User();
        user.setId(10);
        diaryEntry.setUser(user);

        HealthEntry healthEntry = new HealthEntry();
        WeatherEntry weatherEntry = new WeatherEntry();

        diaryEntry.setHealthEntry(healthEntry);
        diaryEntry.setWeatherEntry(weatherEntry);

        diaryEntry.setCreatedAt(new Date());
        diaryEntry.setEntryText("Test entry text");
        diaryEntry.setMood(Grade.GOOD);
        diaryEntry.setStateOfHealth(Grade.EXCELLENT);
        diaryEntry.setActivityAmount(Grade.GOOD);

        DiaryEntryResponse response = mapper.toDiaryEntryResponse(diaryEntry);

        assertEquals(diaryEntry.getId(), response.getId());
        assertEquals(diaryEntry.getUser().getId(), response.getUserId());
        assertEquals(diaryEntry.getHealthEntry(), response.getHealthEntry());
        assertEquals(diaryEntry.getWeatherEntry(), response.getWeatherEntry());
        assertEquals(diaryEntry.getCreatedAt(), response.getCreatedAt());
        assertEquals(diaryEntry.getEntryText(), response.getEntryText());
        assertEquals(diaryEntry.getMood(), response.getMood());
        assertEquals(diaryEntry.getStateOfHealth(), response.getStateOfHealth());
        assertEquals(diaryEntry.getActivityAmount(), response.getActivityAmount());
    }

    @Test
    void testToOpenDiaryEntryResponse() {
        DiaryEntryMapper mapper = new DiaryEntryMapper();
        DiaryEntry diaryEntry = new DiaryEntry();
        diaryEntry.setId(1);

        HealthEntry healthEntry = new HealthEntry();
        WeatherEntry weatherEntry = new WeatherEntry();

        diaryEntry.setHealthEntry(healthEntry);
        diaryEntry.setWeatherEntry(weatherEntry);

        diaryEntry.setCreatedAt(new Date());
        diaryEntry.setMood(Grade.GOOD);
        diaryEntry.setStateOfHealth(Grade.EXCELLENT);
        diaryEntry.setActivityAmount(Grade.GOOD);

        OpenDiaryEntryResponse response = mapper.toOpenDiaryEntryResponse(diaryEntry);

        assertEquals(diaryEntry.getId(), response.getId());
        assertEquals(diaryEntry.getHealthEntry(), response.getHealthEntry());
        assertEquals(diaryEntry.getWeatherEntry(), response.getWeatherEntry());
        assertEquals(diaryEntry.getCreatedAt(), response.getCreatedAt());
        assertEquals(diaryEntry.getMood(), response.getMood());
        assertEquals(diaryEntry.getStateOfHealth(), response.getStateOfHealth());
        assertEquals(diaryEntry.getActivityAmount(), response.getActivityAmount());
    }

    @Test
    void testToDiaryEntry() {
        DiaryEntryMapper mapper = new DiaryEntryMapper();
        DiaryEntryRequest request = new DiaryEntryRequest();
        request.setId(1);
        request.setUserId(10);

        request.setHealthEntry(new HealthEntry());
        request.setWeatherEntry(new WeatherEntry());
        request.setCity("Test");
        request.setCreatedAt(new Date());
        request.setEntryText("Test");
        request.setMood(Grade.GOOD);
        request.setStateOfHealth(Grade.EXCELLENT);
        request.setActivityAmount(Grade.GOOD);

        DiaryEntry diaryEntry = mapper.toDiaryEntry(request);

        assertEquals(request.getId(), diaryEntry.getId());
        assertEquals(request.getUserId(), diaryEntry.getUser().getId());
        assertEquals(request.getHealthEntry(), diaryEntry.getHealthEntry());
        assertEquals(request.getWeatherEntry(), diaryEntry.getWeatherEntry());
        assertEquals(request.getCreatedAt(), diaryEntry.getCreatedAt());
        assertEquals(request.getEntryText(), diaryEntry.getEntryText());
        assertEquals(request.getMood(), diaryEntry.getMood());
        assertEquals(request.getStateOfHealth(), diaryEntry.getStateOfHealth());
        assertEquals(request.getActivityAmount(), diaryEntry.getActivityAmount());
    }

}