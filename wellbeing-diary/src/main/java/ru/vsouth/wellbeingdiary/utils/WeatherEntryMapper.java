package ru.vsouth.wellbeingdiary.utils;

import org.springframework.stereotype.Component;
import ru.vsouth.wellbeingdiary.dto.WeatherEntryResponse;
import ru.vsouth.wellbeingdiary.model.WeatherEntry;
@Component
public class WeatherEntryMapper {
    public WeatherEntryResponse toWeatherEntryResponse(WeatherEntry weatherEntry) {
        WeatherEntryResponse weatherEntryResponse = new WeatherEntryResponse();
        weatherEntryResponse.setId(weatherEntry.getId());
        weatherEntryResponse.setLat(weatherEntry.getLat());
        weatherEntryResponse.setLon(weatherEntry.getLon());
        weatherEntryResponse.setDate(weatherEntry.getDate());
        weatherEntryResponse.setPartOfDay(weatherEntry.getPartOfDay());
        weatherEntryResponse.setTemperature(weatherEntry.getTemperature());
        weatherEntryResponse.setWeatherType(weatherEntry.getWeatherType());
        weatherEntryResponse.setMoonPhase(weatherEntry.getMoonPhase());
        return weatherEntryResponse;
    }
}
