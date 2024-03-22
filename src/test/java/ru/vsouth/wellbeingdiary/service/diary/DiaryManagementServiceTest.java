package ru.vsouth.wellbeingdiary.service.diary;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import ru.vsouth.wellbeingdiary.dto.diary.DiaryEntryRequest;
import ru.vsouth.wellbeingdiary.dto.diary.DiaryEntryResponse;
import ru.vsouth.wellbeingdiary.dto.diary.OpenDiaryEntryResponse;
import ru.vsouth.wellbeingdiary.model.diary.*;
import ru.vsouth.wellbeingdiary.service.diary.diaryentry.DiaryEntryService;
import ru.vsouth.wellbeingdiary.service.diary.healthentry.HealthEntryService;
import ru.vsouth.wellbeingdiary.service.diary.statistics.StatisticsService;
import ru.vsouth.wellbeingdiary.service.diary.weatherentry.WeatherEntryService;
import ru.vsouth.wellbeingdiary.utils.diary.DiaryEntryMapper;
import ru.vsouth.wellbeingdiary.utils.diary.HealthEntryMapper;
import ru.vsouth.wellbeingdiary.utils.diary.WeatherEntryMapper;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class DiaryManagementServiceTest {
    @InjectMocks
    private DiaryManagementService diaryManagementService;

    @Mock
    private DiaryEntryService diaryEntryService;

    @Mock
    private HealthEntryService healthEntryService;

    @Mock
    private WeatherEntryService weatherEntryService;

    @Mock
    private WeatherEntryMapper weatherEntryMapper;

    @Mock
    private StatisticsService statisticsService;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetOpenDiaryEntries() {
        List<OpenDiaryEntryResponse> openDiaryEntries = Arrays.asList(
                new OpenDiaryEntryResponse(3, null,
                        new WeatherEntry(13.0, 13.0, new Date(11111), PartOfDay.MORNING, 12.1, "Ясно"),
                        new Date(11111), Grade.BAD, Grade.AWFUL, Grade.BAD),
                new OpenDiaryEntryResponse(3, null,
                        new WeatherEntry(13.0, 13.0, new Date(11111), PartOfDay.MORNING, 10.1, "Облачно"),
                        new Date(11111), Grade.EXCELLENT, Grade.GOOD, Grade.EXCELLENT)
        );

        when(diaryEntryService.getAllOpenEntries()).thenReturn(openDiaryEntries);

        List<OpenDiaryEntryResponse> result = diaryManagementService.getOpenDiaryEntries();

        assertEquals(openDiaryEntries, result);
    }

    @Test
    void testGetDiaryEntries() {
        List<DiaryEntryResponse> diaryEntries = Arrays.asList(
                new DiaryEntryResponse(3, 2, null,
                        new WeatherEntry(13.0, 13.0, new Date(11111), PartOfDay.MORNING, 12.1, "Дождь"),
                        new Date(11111), "", Grade.BAD, Grade.GOOD, Grade.BAD),
                new DiaryEntryResponse(3, 2, null,
                        new WeatherEntry(13.0, 13.0, new Date(11111), PartOfDay.MORNING, 10.1, "Переменная облачность"),
                        new Date(11111), "", Grade.EXCELLENT, Grade.GOOD, Grade.EXCELLENT)
        );

        when(diaryEntryService.getAllEntries()).thenReturn(diaryEntries);

        List<DiaryEntryResponse> result = diaryManagementService.getDiaryEntries();

        assertEquals(diaryEntries, result);
    }

    @Test
    void testGetDiaryEntriesByUserId() {
        int userId = 1;

        List<DiaryEntryResponse> diaryEntriesByUserId = Arrays.asList(
                new DiaryEntryResponse(4, 1, null,
                        new WeatherEntry(13.0, 13.0, new Date(11111), PartOfDay.MORNING, 12.1, "Дождь"),
                        new Date(11111), "", Grade.BAD, Grade.GOOD, Grade.BAD),
                new DiaryEntryResponse(5, 1, null,
                        new WeatherEntry(13.0, 13.0, new Date(11111), PartOfDay.MORNING, 10.1, "Переменная облачность"),
                        new Date(11111), "", Grade.EXCELLENT, Grade.GOOD, Grade.EXCELLENT)
        );

        when(diaryEntryService.getEntriesByUserId(userId)).thenReturn(diaryEntriesByUserId);

        List<DiaryEntryResponse> result = diaryManagementService.getDiaryEntries(userId);

        assertEquals(diaryEntriesByUserId, result);
    }

    @Test
    void testGetDiaryEntryByUserIdAndDiaryEntryId() {
        int userId = 1;
        int diaryEntryId = 1;

        DiaryEntryResponse expectedDiaryEntry = new DiaryEntryResponse(1, 1, null,
                new WeatherEntry(13.0, 13.0, new Date(11111), PartOfDay.MORNING, 10.1, "Переменная облачность"),
                new Date(11111), "", Grade.EXCELLENT, Grade.GOOD, Grade.EXCELLENT);

        when(diaryEntryService.getEntryByUserIdAndDiaryEntryId(userId, diaryEntryId)).thenReturn(expectedDiaryEntry);

        DiaryEntryResponse result = diaryManagementService.getDiaryEntry(userId, diaryEntryId);

        assertEquals(expectedDiaryEntry, result);
    }

    @Test
    void testGetDiaryEntryById() {
        int id = 1;

        DiaryEntryResponse expectedDiaryEntry = new DiaryEntryResponse(1, 1, null,
                new WeatherEntry(13.0, 13.0, new Date(11111), PartOfDay.MORNING, 10.1, "Переменная облачность"),
                new Date(11111), "", Grade.EXCELLENT, Grade.GOOD, Grade.EXCELLENT);

        when(diaryEntryService.getEntryById(id)).thenReturn(expectedDiaryEntry);

        DiaryEntryResponse result = diaryManagementService.getDiaryEntry(id);

        assertEquals(expectedDiaryEntry, result);
    }

    @Test
    void testUpdateDiaryEntry() {
        DiaryEntryRequest diaryEntryRequest = new DiaryEntryRequest();
        diaryEntryRequest.setId(2);
        diaryEntryRequest.setUserId(1);
        diaryEntryRequest.setCreatedAt(new Date(1000));
        diaryEntryRequest.setEntryText("Test entry");
        diaryEntryRequest.setHealthEntry(new HealthEntry(60, 120, 80));
        diaryEntryRequest.setWeatherEntry(new WeatherEntry(
                1.0, 1.0, new Date(1111), PartOfDay.EVENING, 11.0, "Облачно"
        ));

        DiaryEntryMapper diaryEntryMapper = new DiaryEntryMapper();
        DiaryEntry diaryEntry = diaryEntryMapper.toDiaryEntry(diaryEntryRequest);

        DiaryEntryResponse expectedDiaryEntry = diaryEntryMapper.toDiaryEntryResponse(diaryEntry);

        when(diaryEntryService.getEntryById(diaryEntryRequest.getId())).thenReturn(expectedDiaryEntry);

        HealthEntry healthEntry = expectedDiaryEntry.getHealthEntry();
        WeatherEntry weatherEntry = expectedDiaryEntry.getWeatherEntry();
        diaryEntryRequest.setWeatherEntry(weatherEntry);
        HealthEntry updatedHealthEntry = diaryEntryRequest.getHealthEntry();
        if (healthEntry != null && updatedHealthEntry != null) {
            updatedHealthEntry.setId(healthEntry.getId());
        }

        when(diaryEntryService.saveEntry(diaryEntryRequest)).thenReturn(expectedDiaryEntry);

        DiaryEntryResponse result = diaryManagementService.updateDiaryEntry(diaryEntryRequest);

        assertEquals(expectedDiaryEntry, result);
    }

    @Test
    void testDeleteDiaryEntry() {
        int id = 2;
        DiaryEntryResponse expectedDiaryEntry = new DiaryEntryResponse(2, 1, null,
                new WeatherEntry(13.0, 13.0, new Date(11111), PartOfDay.MORNING, 10.1, "Переменная облачность"),
                new Date(11111), "", Grade.EXCELLENT, Grade.GOOD, Grade.EXCELLENT);

        when(diaryEntryService.deleteEntry(id)).thenReturn(expectedDiaryEntry);

        DiaryEntryResponse result = diaryManagementService.deleteDiaryEntry(id);

        assertEquals(expectedDiaryEntry, result);
    }

    @Test
    void testAddDiaryEntry() throws JsonProcessingException {
        DiaryEntryRequest diaryEntryRequest = new DiaryEntryRequest();
        diaryEntryRequest.setId(1);
        diaryEntryRequest.setUserId(1);
        diaryEntryRequest.setCreatedAt(new Date(1000));
        diaryEntryRequest.setEntryText("Test entry");

        DiaryEntryResponse expectedDiaryEntry = new DiaryEntryResponse(1, 1, null, null, new Date(1000), "Test entry", null, null, null);

        when(diaryEntryService.saveEntry(diaryEntryRequest)).thenReturn(expectedDiaryEntry);

        DiaryEntryResponse result = diaryManagementService.addDiaryEntry(diaryEntryRequest);

        assertEquals(expectedDiaryEntry, result);
    }

    @Test
    void testGetDiaryEntriesStatisticsById() {
        int userId = 1;
        List<DiaryEntryResponse> entries = Arrays.asList(
                new DiaryEntryResponse(3, 1, null,
                        new WeatherEntry(13.0, 13.0, new Date(11111), PartOfDay.MORNING, 12.1, "Дождь"),
                        new Date(11111), "", Grade.BAD, Grade.GOOD, Grade.BAD),
                new DiaryEntryResponse(3, 1, null,
                        new WeatherEntry(13.0, 13.0, new Date(11111), PartOfDay.MORNING, 10.1, "Переменная облачность"),
                        new Date(11111), "", Grade.EXCELLENT, Grade.GOOD, Grade.EXCELLENT)
        );

        Map<String, Map<Grade, Long>> stateOfHealthStats = new HashMap<>();
        Map<Grade, Long> rainMapHealth = new HashMap<>();
        rainMapHealth.put(Grade.GOOD, 1L);
        stateOfHealthStats.put("Дождь", rainMapHealth);
        Map<Grade, Long> cloudMapHealth = new HashMap<>();
        cloudMapHealth.put(Grade.GOOD, 1L);
        stateOfHealthStats.put("Переменная облачность", cloudMapHealth);


        Map<String, Map<Grade, Long>> moodStats = new HashMap<>();
        Map<Grade, Long> rainMapMood = new HashMap<>();
        rainMapMood.put(Grade.BAD, 1L);
        moodStats.put("Дождь", rainMapMood);
        Map<Grade, Long> cloudMapMood = new HashMap<>();
        cloudMapMood.put(Grade.EXCELLENT, 1L);
        moodStats.put("Переменная облачность", cloudMapMood);

        when(diaryEntryService.getEntriesByUserId(userId)).thenReturn(entries);
        when(statisticsService.getStateOfHealthStatisticsByWeatherType(entries)).thenReturn(stateOfHealthStats);
        when(statisticsService.getMoodStatisticsByWeatherType(entries)).thenReturn(moodStats);

        Map<String, Map<String, Map<Grade, Long>>> result = diaryManagementService.getDiaryEntriesStatisticsById(1);
        System.out.println(result);

        Map<String, Map<Grade, Long>> expectedStateOfHealthStats = new HashMap<>();
        Map<Grade, Long> rainMapHealthExpected = new HashMap<>();
        rainMapHealthExpected.put(Grade.GOOD, 1L);
        expectedStateOfHealthStats.put("Дождь", rainMapHealthExpected);
        Map<Grade, Long> cloudMapHealthExpected = new HashMap<>();
        cloudMapHealthExpected.put(Grade.GOOD, 1L);
        expectedStateOfHealthStats.put("Переменная облачность", cloudMapHealthExpected);

        assertEquals(expectedStateOfHealthStats, result.get("stateOfHealthStats"));

        Map<String, Map<Grade, Long>> expectedMoodStats = new HashMap<>();
        Map<Grade, Long> rainMapMoodExpected = new HashMap<>();
        rainMapMoodExpected.put(Grade.BAD, 1L);
        expectedMoodStats.put("Дождь", rainMapMoodExpected);
        Map<Grade, Long> cloudMapMoodExpected = new HashMap<>();
        cloudMapMoodExpected.put(Grade.EXCELLENT, 1L);
        expectedMoodStats.put("Переменная облачность", cloudMapMoodExpected);

        assertEquals(expectedMoodStats, result.get("moodStats"));
    }

    @Test
    void testGetOpenDiaryEntriesStatistics() {
        List<OpenDiaryEntryResponse> entries = Arrays.asList(
                new OpenDiaryEntryResponse(3, null,
                        new WeatherEntry(13.0, 13.0, new Date(11111), PartOfDay.MORNING, 12.1, "Ясно"),
                        new Date(11111), Grade.BAD, Grade.AWFUL, Grade.BAD),
                new OpenDiaryEntryResponse(3, null,
                        new WeatherEntry(13.0, 13.0, new Date(11111), PartOfDay.MORNING, 10.1, "Облачно"),
                        new Date(11111), Grade.EXCELLENT, Grade.GOOD, Grade.EXCELLENT)
        );


        Map<String, Map<Grade, Long>> stateOfHealthStats = new HashMap<>();
        Map<Grade, Long> clearMapHealth = new HashMap<>();
        clearMapHealth.put(Grade.AWFUL, 1L);
        stateOfHealthStats.put("Ясно", clearMapHealth);
        Map<Grade, Long> cloudMapHealth = new HashMap<>();
        cloudMapHealth.put(Grade.GOOD, 1L);
        stateOfHealthStats.put("Облачно", cloudMapHealth);


        Map<String, Map<Grade, Long>> moodStats = new HashMap<>();
        Map<Grade, Long> clearMapMood = new HashMap<>();
        clearMapMood.put(Grade.BAD, 1L);
        moodStats.put("Ясно", clearMapMood);

        Map<Grade, Long> cloudMapMood = new HashMap<>();
        cloudMapMood.put(Grade.EXCELLENT, 1L);
        moodStats.put("Облачно", cloudMapMood);

        when(diaryEntryService.getAllOpenEntries()).thenReturn(entries);
        when(statisticsService.getOpenStateOfHealthStatisticsByWeatherType(entries)).thenReturn(stateOfHealthStats);
        when(statisticsService.getOpenMoodStatisticsByWeatherType(entries)).thenReturn(moodStats);

        Map<String, Map<String, Map<Grade, Long>>> result = diaryManagementService.getOpenDiaryEntriesStatistics();

        Map<String, Map<Grade, Long>> expectedStateOfHealthStats = new HashMap<>();
        Map<Grade, Long> clearMapHealthExpected = new HashMap<>();
        clearMapHealthExpected.put(Grade.AWFUL, 1L);
        expectedStateOfHealthStats.put("Ясно", clearMapHealthExpected);
        Map<Grade, Long> cloudMapHealthExpected = new HashMap<>();
        cloudMapHealthExpected.put(Grade.GOOD, 1L);
        expectedStateOfHealthStats.put("Облачно", cloudMapHealthExpected);

        assertEquals(expectedStateOfHealthStats, result.get("stateOfHealthStats"));

        Map<String, Map<Grade, Long>> expectedMoodStats = new HashMap<>();
        Map<Grade, Long> clearMapMoodExpected = new HashMap<>();
        clearMapMoodExpected.put(Grade.BAD, 1L);
        expectedMoodStats.put("Ясно", clearMapMoodExpected);
        Map<Grade, Long> cloudMapMoodExpected = new HashMap<>();
        cloudMapMoodExpected.put(Grade.EXCELLENT, 1L);
        expectedMoodStats.put("Облачно", cloudMapMoodExpected);

        assertEquals(expectedMoodStats, result.get("moodStats"));
    }
}