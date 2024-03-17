package ru.vsouth.wellbeingdiary.utils.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import ru.vsouth.wellbeingdiary.dto.integration.WeatherApiCurrentResponse;
import ru.vsouth.wellbeingdiary.dto.integration.WeatherApiGeoResponse;

import static org.junit.jupiter.api.Assertions.*;

class WeatherApiMapperTest {
    private WeatherApiMapper weatherApiMapper = new WeatherApiMapper();

    @Test
    void testToWeatherApiGeoResponse() throws JsonProcessingException {
        String jsonStr = "{\"results\":[{\"id\":2950159,\"name\":\"Berlin\",\"latitude\":52.52437,\"longitude\":13.41053,\"elevation\":74.0,\"feature_code\":\"PPLC\",\"country_code\":\"DE\",\"admin1_id\":2950157,\"admin3_id\":6547383,\"admin4_id\":6547539,\"timezone\":\"Europe/Berlin\",\"population\":3426354,\"postcodes\":[\"10967\",\"13347\"],\"country_id\":2921044,\"country\":\"Germany\",\"admin1\":\"Land Berlin\",\"admin3\":\"Berlin, Stadt\",\"admin4\":\"Berlin\"}],\"generationtime_ms\":1.502037}";
        WeatherApiGeoResponse expectedResponse = new WeatherApiGeoResponse(52.52437, 13.41053);

        WeatherApiGeoResponse actualResponse = weatherApiMapper.toWeatherApiGeoResponse(jsonStr);

        assertEquals(expectedResponse.getLat(), actualResponse.getLat());
        assertEquals(expectedResponse.getLon(), actualResponse.getLon());
    }

    @Test
    void testToWeatherApiCurrentResponse() throws JsonProcessingException {
        String jsonStr = "{\"latitude\":52.52,\"longitude\":13.419998,\"generationtime_ms\":0.03600120544433594,\"utc_offset_seconds\":0,\"timezone\":\"GMT\",\"timezone_abbreviation\":\"GMT\",\"elevation\":38.0,\"current_units\":{\"time\":\"iso8601\",\"interval\":\"seconds\",\"temperature_2m\":\"°C\",\"weather_code\":\"wmo code\"},\"current\":{\"time\":\"2024-03-17T20:30\",\"interval\":900,\"temperature_2m\":3.4,\"weather_code\":3}}";
        WeatherApiCurrentResponse expectedResponse = new WeatherApiCurrentResponse(3.4, 3);

        WeatherApiCurrentResponse actualResponse = weatherApiMapper.toWeatherApiCurrentResponse(jsonStr);

        assertEquals(expectedResponse.getTemperature(), actualResponse.getTemperature());
        assertEquals(expectedResponse.getWeatherCode(), actualResponse.getWeatherCode());
    }
}