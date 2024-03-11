package ru.vsouth.wellbeingdiary.service.diary;

import org.springframework.stereotype.Service;
import ru.vsouth.wellbeingdiary.dto.DiaryEntryRequest;
import ru.vsouth.wellbeingdiary.dto.DiaryEntryResponse;
import ru.vsouth.wellbeingdiary.dto.OpenDiaryEntryResponse;
import ru.vsouth.wellbeingdiary.model.HealthEntry;
import ru.vsouth.wellbeingdiary.model.WeatherEntry;
import ru.vsouth.wellbeingdiary.service.diary.diaryentry.DiaryEntryService;
import ru.vsouth.wellbeingdiary.service.diary.healthentry.HealthEntryService;
import ru.vsouth.wellbeingdiary.service.diary.weatherentry.WeatherEntryService;

import java.util.List;

@Service
public class DiaryManagementService {
    private final DiaryEntryService diaryEntryService;
    private final HealthEntryService healthEntryService;
    private final WeatherEntryService weatherEntryService;

    public DiaryManagementService(DiaryEntryService diaryEntryService, HealthEntryService healthEntryService, WeatherEntryService weatherEntryService) {
        this.diaryEntryService = diaryEntryService;
        this.healthEntryService = healthEntryService;
        this.weatherEntryService = weatherEntryService;
    }

    public List<OpenDiaryEntryResponse> getOpenDiaryEntries() {
        return diaryEntryService.getAllOpenEntries();
    }

    public List<DiaryEntryResponse> getDiaryEntries() {
        return diaryEntryService.getAllEntries();
    }

    public List<DiaryEntryResponse> getDiaryEntries(int userId) {
        return diaryEntryService.getEntriesByUserId(userId);
    }

    public DiaryEntryResponse getDiaryEntry(int userId, int diaryEntryId) {
        return diaryEntryService.getEntryByUserIdAndDiaryEntryId(userId, diaryEntryId);
    }

    public DiaryEntryResponse getDiaryEntry(int id) {
        return diaryEntryService.getEntryById(id);
    }

    public DiaryEntryResponse updateDiaryEntry(DiaryEntryRequest diaryEntryRequest) {
        DiaryEntryResponse diaryEntry = diaryEntryService.getEntryById(diaryEntryRequest.getId());
        HealthEntry healthEntry = diaryEntry.getHealthEntry();
        WeatherEntry weatherEntry = diaryEntry.getWeatherEntry();
        HealthEntry updatedHealthEntry = diaryEntryRequest.getHealthEntry();
        WeatherEntry updatedWeatherEntry = diaryEntryRequest.getWeatherEntry();
        if (healthEntry != null && updatedHealthEntry != null) {
            updatedHealthEntry.setId(healthEntry.getId());
        }
        if (weatherEntry != null && updatedWeatherEntry != null) {
            updatedWeatherEntry.setId(weatherEntry.getId());
        }
        weatherEntryService.saveEntry(updatedWeatherEntry);
        healthEntryService.saveEntry(updatedHealthEntry);
        return diaryEntryService.saveEntry(diaryEntryRequest);
    }

    public DiaryEntryResponse deleteDiaryEntry(int id) {
        return diaryEntryService.deleteEntry(id);
    }

    public DiaryEntryResponse addDiaryEntry(DiaryEntryRequest diaryEntryRequest) {
        weatherEntryService.saveEntry(diaryEntryRequest.getWeatherEntry());
        healthEntryService.saveEntry(diaryEntryRequest.getHealthEntry());
        return diaryEntryService.saveEntry(diaryEntryRequest);
    }
}
