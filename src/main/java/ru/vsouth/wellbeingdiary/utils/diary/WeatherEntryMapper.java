package ru.vsouth.wellbeingdiary.utils.diary;

import org.springframework.stereotype.Component;
import ru.vsouth.wellbeingdiary.dto.diary.WeatherEntryResponse;
import ru.vsouth.wellbeingdiary.model.diary.WeatherEntry;
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
        return weatherEntryResponse;
    }
    public WeatherEntry toWeatherEntry(WeatherEntryResponse weatherEntryResponse) {
        WeatherEntry weatherEntry = new WeatherEntry();
        weatherEntry.setId(weatherEntryResponse.getId());
        weatherEntry.setLat(weatherEntryResponse.getLat());
        weatherEntry.setLon(weatherEntryResponse.getLon());
        weatherEntry.setDate(weatherEntryResponse.getDate());
        weatherEntry.setPartOfDay(weatherEntryResponse.getPartOfDay());
        weatherEntry.setTemperature(weatherEntryResponse.getTemperature());
        weatherEntry.setWeatherType(weatherEntryResponse.getWeatherType());
        return weatherEntry;
    }
}
