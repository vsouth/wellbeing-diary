package ru.vsouth.wellbeingdiary.service.diary.statistics;

import org.springframework.stereotype.Service;
import ru.vsouth.wellbeingdiary.dto.diary.DiaryEntryResponse;
import ru.vsouth.wellbeingdiary.dto.diary.OpenDiaryEntryResponse;
import ru.vsouth.wellbeingdiary.model.diary.Grade;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Сервис статистики зависимости от погоды
 */
@Service
public class StatisticsServiceImpl implements StatisticsService {
    /**
     * Метод для получения статистики по состоянию здоровья в зависимости от типа погоды
     *
     * @param entries Список записей пользователя
     * @return Статистика
     */
    @Override
    public Map<String, Map<Grade, Long>> getStateOfHealthStatisticsByWeatherType(List<DiaryEntryResponse> entries) {
        return entries.stream()
                .filter(entry -> entry.getWeatherEntry() != null && entry.getWeatherEntry().getWeatherType() != null && entry.getStateOfHealth() != null)
                .collect(Collectors.groupingBy(entry -> entry.getWeatherEntry().getWeatherType(),
                        Collectors.groupingBy(DiaryEntryResponse::getStateOfHealth,
                                Collectors.counting())));
    }

    /**
     * Метод для получения статистики по настроению в зависимости от типа погоды
     *
     * @param entries Список записей пользователя
     * @return Статистика
     */
    @Override
    public Map<String, Map<Grade, Long>> getMoodStatisticsByWeatherType(List<DiaryEntryResponse> entries) {
        return entries.stream()
                .filter(entry -> entry.getWeatherEntry() != null && entry.getWeatherEntry().getWeatherType() != null && entry.getMood() != null)
                .collect(Collectors.groupingBy(entry -> entry.getWeatherEntry().getWeatherType(),
                        Collectors.groupingBy(DiaryEntryResponse::getMood,
                                Collectors.counting())));
    }

    /**
     * Метод для получения статистики по состоянию здоровья в зависимости от типа погоды для открытых записей
     *
     * @param entries Список открытых записей
     * @return Статистика
     */
    @Override
    public Map<String, Map<Grade, Long>> getOpenStateOfHealthStatisticsByWeatherType(List<OpenDiaryEntryResponse> entries) {
        return entries.stream()
                .filter(entry -> entry.getWeatherEntry() != null && entry.getWeatherEntry().getWeatherType() != null && entry.getStateOfHealth() != null)
                .collect(Collectors.groupingBy(entry -> entry.getWeatherEntry().getWeatherType(),
                        Collectors.groupingBy(OpenDiaryEntryResponse::getStateOfHealth,
                                Collectors.counting())));
    }

    /**
     * Метод для получения статистики по настроению в зависимости от типа погоды для открытых записей
     *
     * @param entries Список открытых записей
     * @return Статистика
     */
    @Override
    public Map<String, Map<Grade, Long>> getOpenMoodStatisticsByWeatherType(List<OpenDiaryEntryResponse> entries) {
        return entries.stream()
                .filter(entry -> entry.getWeatherEntry() != null && entry.getWeatherEntry().getWeatherType() != null && entry.getMood() != null)
                .collect(Collectors.groupingBy(entry -> entry.getWeatherEntry().getWeatherType(),
                        Collectors.groupingBy(OpenDiaryEntryResponse::getMood,
                                Collectors.counting())));
    }
}
