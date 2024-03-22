package ru.vsouth.wellbeingdiary.service.diary.statistics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.vsouth.wellbeingdiary.dto.diary.DiaryEntryResponse;
import ru.vsouth.wellbeingdiary.model.diary.Grade;
import ru.vsouth.wellbeingdiary.model.diary.PartOfDay;
import ru.vsouth.wellbeingdiary.model.diary.WeatherEntry;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class StatisticsServiceImplTest {
    private StatisticsServiceImpl statisticsService;

    @BeforeEach
    void setUp() {
        statisticsService = new StatisticsServiceImpl();
    }
    @Test
    void testGetStateOfHealthStatisticsByWeatherType() {
        List<DiaryEntryResponse> entries = Arrays.asList(
                new DiaryEntryResponse(3, 2, null,
                        new WeatherEntry(13.0, 13.0, new Date(11111), PartOfDay.MORNING, 12.1, "Дождь"),
                        new Date(11111), "", Grade.BAD, Grade.GOOD, Grade.BAD),
                new DiaryEntryResponse(3, 2, null,
                        new WeatherEntry(13.0, 13.0, new Date(11111), PartOfDay.MORNING, 10.1, "Переменная облачность"),
                        new Date(11111), "", Grade.EXCELLENT, Grade.GOOD, Grade.EXCELLENT)
                );

        Map<String, Map<Grade, Long>> result = statisticsService.getStateOfHealthStatisticsByWeatherType(entries);

        Map<String, Map<Grade, Long>> expected = new HashMap<>();

        Map<Grade, Long> rainMap = new HashMap<>();
        rainMap.put(Grade.GOOD, 1L);
        expected.put("Дождь", rainMap);

        Map<Grade, Long> cloudMap = new HashMap<>();
        cloudMap.put(Grade.GOOD, 1L);
        expected.put("Переменная облачность", cloudMap);

        assertEquals(expected, result);
    }

    @Test
    void testGetMoodStatisticsByWeatherType() {
        List<DiaryEntryResponse> entries = Arrays.asList(
                new DiaryEntryResponse(3, 2, null,
                        new WeatherEntry(13.0, 13.0, new Date(11111), PartOfDay.MORNING, 12.1, "Дождь"),
                        new Date(11111), "", Grade.BAD, Grade.GOOD, Grade.BAD),
                new DiaryEntryResponse(3, 2, null,
                        new WeatherEntry(13.0, 13.0, new Date(11111), PartOfDay.MORNING, 10.1, "Переменная облачность"),
                        new Date(11111), "", Grade.EXCELLENT, Grade.GOOD, Grade.EXCELLENT)
        );

        Map<String, Map<Grade, Long>> result = statisticsService.getMoodStatisticsByWeatherType(entries);

        Map<String, Map<Grade, Long>> expected = new HashMap<>();

        Map<Grade, Long> rainMap = new HashMap<>();
        rainMap.put(Grade.BAD, 1L);
        expected.put("Дождь", rainMap);

        Map<Grade, Long> cloudMap = new HashMap<>();
        cloudMap.put(Grade.EXCELLENT, 1L);
        expected.put("Переменная облачность", cloudMap);

        assertEquals(expected, result);
    }

    @Test
    void testGetOpenStateOfHealthStatisticsByWeatherType() {
        List<DiaryEntryResponse> entries = Arrays.asList(
                new DiaryEntryResponse(3, 2, null,
                        new WeatherEntry(13.0, 13.0, new Date(11111), PartOfDay.MORNING, 12.1, "Ясно"),
                        new Date(11111), "", Grade.BAD, Grade.AWFUL, Grade.BAD),
                new DiaryEntryResponse(3, 2, null,
                        new WeatherEntry(13.0, 13.0, new Date(11111), PartOfDay.MORNING, 10.1, "Облачно"),
                        new Date(11111), "", Grade.EXCELLENT, Grade.GOOD, Grade.EXCELLENT)
        );

        Map<String, Map<Grade, Long>> result = statisticsService.getStateOfHealthStatisticsByWeatherType(entries);

        Map<String, Map<Grade, Long>> expected = new HashMap<>();

        Map<Grade, Long> clearMap = new HashMap<>();
        clearMap.put(Grade.AWFUL, 1L);
        expected.put("Ясно", clearMap);

        Map<Grade, Long> cloudMap = new HashMap<>();
        cloudMap.put(Grade.GOOD, 1L);
        expected.put("Облачно", cloudMap);

        assertEquals(expected, result);
    }

    @Test
    void testGetOpenMoodStatisticsByWeatherType() {
        List<DiaryEntryResponse> entries = Arrays.asList(
                new DiaryEntryResponse(3, 2, null,
                        new WeatherEntry(13.0, 13.0, new Date(11111), PartOfDay.MORNING, 12.1, "Ясно"),
                        new Date(11111), "", Grade.BAD, Grade.AWFUL, Grade.BAD),
                new DiaryEntryResponse(3, 2, null,
                        new WeatherEntry(13.0, 13.0, new Date(11111), PartOfDay.MORNING, 10.1, "Облачно"),
                        new Date(11111), "", Grade.EXCELLENT, Grade.GOOD, Grade.EXCELLENT)
        );

        Map<String, Map<Grade, Long>> result = statisticsService.getMoodStatisticsByWeatherType(entries);

        Map<String, Map<Grade, Long>> expected = new HashMap<>();

        Map<Grade, Long> clearMap = new HashMap<>();
        clearMap.put(Grade.BAD, 1L);
        expected.put("Ясно", clearMap);

        Map<Grade, Long> cloudMap = new HashMap<>();
        cloudMap.put(Grade.EXCELLENT, 1L);
        expected.put("Облачно", cloudMap);

        assertEquals(expected, result);
    }
}