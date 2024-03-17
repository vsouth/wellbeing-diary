package ru.vsouth.wellbeingdiary.service.diary.statistics;

import org.springframework.stereotype.Service;
import ru.vsouth.wellbeingdiary.dto.diary.DiaryEntryResponse;
import ru.vsouth.wellbeingdiary.dto.diary.OpenDiaryEntryResponse;
import ru.vsouth.wellbeingdiary.model.diary.Grade;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StatisticsServiceImpl implements StatisticsService {
    @Override
    public Map<String, Map<Grade, Long>> getStateOfHealthStatisticsByWeatherType(List<DiaryEntryResponse> entries) {
        return entries.stream()
                .filter(entry -> entry.getWeatherEntry() != null && entry.getWeatherEntry().getWeatherType() != null && entry.getStateOfHealth() != null)
                .collect(Collectors.groupingBy(entry -> entry.getWeatherEntry().getWeatherType(),
                        Collectors.groupingBy(DiaryEntryResponse::getStateOfHealth,
                                Collectors.counting())));
    }
    @Override
    public Map<String, Map<Grade, Long>> getMoodStatisticsByWeatherType(List<DiaryEntryResponse> entries) {
        return entries.stream()
                .filter(entry -> entry.getWeatherEntry() != null && entry.getWeatherEntry().getWeatherType() != null && entry.getMood() != null)
                .collect(Collectors.groupingBy(entry -> entry.getWeatherEntry().getWeatherType(),
                        Collectors.groupingBy(DiaryEntryResponse::getMood,
                                Collectors.counting())));
    }
    @Override
    public Map<String, Map<Grade, Long>> getOpenStateOfHealthStatisticsByWeatherType(List<OpenDiaryEntryResponse> entries) {
        return entries.stream()
                .filter(entry -> entry.getWeatherEntry() != null && entry.getWeatherEntry().getWeatherType() != null && entry.getStateOfHealth() != null)
                .collect(Collectors.groupingBy(entry -> entry.getWeatherEntry().getWeatherType(),
                        Collectors.groupingBy(OpenDiaryEntryResponse::getStateOfHealth,
                                Collectors.counting())));
    }
    @Override
    public Map<String, Map<Grade, Long>> getOpenMoodStatisticsByWeatherType(List<OpenDiaryEntryResponse> entries) {
        return entries.stream()
                .filter(entry -> entry.getWeatherEntry() != null && entry.getWeatherEntry().getWeatherType() != null && entry.getMood() != null)
                .collect(Collectors.groupingBy(entry -> entry.getWeatherEntry().getWeatherType(),
                        Collectors.groupingBy(OpenDiaryEntryResponse::getMood,
                                Collectors.counting())));
    }
}
