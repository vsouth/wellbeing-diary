package ru.vsouth.wellbeingdiary.utils.diary;

import org.junit.jupiter.api.Test;
import ru.vsouth.wellbeingdiary.dto.diary.WeatherEntryResponse;
import ru.vsouth.wellbeingdiary.model.diary.PartOfDay;
import ru.vsouth.wellbeingdiary.model.diary.WeatherEntry;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class WeatherEntryMapperTest {
    @Test
    void testToWeatherEntryResponse() {
        WeatherEntryMapper mapper = new WeatherEntryMapper();
        WeatherEntry weatherEntry = new WeatherEntry();
        weatherEntry.setId(1);
        weatherEntry.setLat(55.0);
        weatherEntry.setLon(37.5);
        weatherEntry.setDate(new Date(10000));
        weatherEntry.setPartOfDay(PartOfDay.MORNING);
        weatherEntry.setTemperature(20.0);
        weatherEntry.setWeatherType("Rain");

        WeatherEntryResponse response = mapper.toWeatherEntryResponse(weatherEntry);

        assertEquals(weatherEntry.getId(), response.getId());
        assertEquals(weatherEntry.getLat(), response.getLat());
        assertEquals(weatherEntry.getLon(), response.getLon());
        assertEquals(weatherEntry.getDate(), response.getDate());
        assertEquals(weatherEntry.getPartOfDay(), response.getPartOfDay());
        assertEquals(weatherEntry.getTemperature(), response.getTemperature());
        assertEquals(weatherEntry.getWeatherType(), response.getWeatherType());
    }

    @Test
    void testToWeatherEntry() {
        WeatherEntryMapper mapper = new WeatherEntryMapper();
        WeatherEntryResponse response = new WeatherEntryResponse();
        response.setId(1);
        response.setLat(55.0);
        response.setLon(37.5);
        response.setDate(new Date(1000));
        response.setPartOfDay(PartOfDay.MORNING);
        response.setTemperature(20.0);
        response.setWeatherType("Rain");

        WeatherEntry weatherEntry = mapper.toWeatherEntry(response);

        assertEquals(response.getId(), weatherEntry.getId());
        assertEquals(response.getLat(), weatherEntry.getLat());
        assertEquals(response.getLon(), weatherEntry.getLon());
        assertEquals(response.getDate(), weatherEntry.getDate());
        assertEquals(response.getPartOfDay(), weatherEntry.getPartOfDay());
        assertEquals(response.getTemperature(), weatherEntry.getTemperature());
        assertEquals(response.getWeatherType(), weatherEntry.getWeatherType());
    }
}