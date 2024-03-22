package ru.vsouth.wellbeingdiary.service.diary.weatherentry;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.vsouth.wellbeingdiary.dto.diary.WeatherEntryResponse;
import ru.vsouth.wellbeingdiary.model.diary.PartOfDay;
import ru.vsouth.wellbeingdiary.model.diary.WeatherEntry;
import ru.vsouth.wellbeingdiary.repository.WeatherEntryRepository;
import ru.vsouth.wellbeingdiary.utils.diary.PartOfDayIdentifier;
import ru.vsouth.wellbeingdiary.utils.diary.WeatherEntryMapper;
import ru.vsouth.wellbeingdiary.utils.diary.WeatherTypeIdentifier;
import ru.vsouth.wellbeingdiary.utils.integration.WeatherApiClient;
import ru.vsouth.wellbeingdiary.utils.integration.WeatherApiMapper;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
class WeatherEntryServiceImplTest {
    private final WeatherEntryMapper weatherEntryMapper = new WeatherEntryMapper();
    private final WeatherApiClient weatherApiClient = new WeatherApiClient(new WeatherApiMapper());
    private final WeatherTypeIdentifier weatherTypeIdentifier = new WeatherTypeIdentifier();
    private final PartOfDayIdentifier partOfDayIdentifier = new PartOfDayIdentifier();
    @Autowired
    private WeatherEntryRepository weatherEntryRepository;
    private WeatherEntryServiceImpl weatherEntryService;

    @BeforeEach
    public void setUp() {
        weatherEntryService = new WeatherEntryServiceImpl(weatherEntryRepository,
                weatherEntryMapper, weatherApiClient, weatherTypeIdentifier, partOfDayIdentifier);
    }

    @Test
    void testGetAllEntries() {
        List<WeatherEntryResponse> result = weatherEntryService.getAllEntries();
        assertEquals(3, result.size());
    }

    @Test
    void testGetEntryById() {
        WeatherEntryResponse result = weatherEntryService.getEntryById(1);
        assertEquals(40.7128, result.getLat());
        assertEquals(-74.0060, result.getLon());
        assertEquals(PartOfDay.MORNING, result.getPartOfDay());
        assertEquals(20.5, result.getTemperature());
        assertEquals("Ясно", result.getWeatherType());
    }

    @Test
    void testSaveEntryAndDeleteEntry() throws JsonProcessingException {
        WeatherEntry testEntry = new WeatherEntry();
        testEntry.setLat(1.0);
        testEntry.setLon(1.0);
        testEntry.setDate(new Date(1111));
        testEntry.setPartOfDay(PartOfDay.EVENING);
        testEntry.setTemperature(11.0);
        testEntry.setWeatherType("Облачно");
        WeatherEntryResponse savedEntry = weatherEntryService.saveEntry(testEntry);

        WeatherEntryResponse entryById = weatherEntryService.getEntryById(savedEntry.getId());
        assertEquals(entryById.getLat(), testEntry.getLat());
        assertEquals(entryById.getLon(), testEntry.getLon());
        assertEquals(entryById.getPartOfDay(), testEntry.getPartOfDay());
        assertEquals(entryById.getTemperature(), testEntry.getTemperature());
        assertEquals(entryById.getWeatherType(), testEntry.getWeatherType());

        weatherEntryService.deleteEntry(entryById.getId());
        assertNull(weatherEntryService.getEntryById(entryById.getId()));
    }
}