package ru.vsouth.wellbeingdiary.service.diary.statistics;

import ru.vsouth.wellbeingdiary.dto.diary.DiaryEntryResponse;
import ru.vsouth.wellbeingdiary.dto.diary.OpenDiaryEntryResponse;
import ru.vsouth.wellbeingdiary.model.diary.Grade;

import java.util.List;
import java.util.Map;

public interface StatisticsService {
    public Map<String, Map<Grade, Long>> getStateOfHealthStatisticsByWeatherType(List<DiaryEntryResponse> entries);
    public Map<String, Map<Grade, Long>> getMoodStatisticsByWeatherType(List<DiaryEntryResponse> entries);
    public Map<String, Map<Grade, Long>> getOpenStateOfHealthStatisticsByWeatherType(List<OpenDiaryEntryResponse> entries);
    public Map<String, Map<Grade, Long>> getOpenMoodStatisticsByWeatherType(List<OpenDiaryEntryResponse> entries);
}