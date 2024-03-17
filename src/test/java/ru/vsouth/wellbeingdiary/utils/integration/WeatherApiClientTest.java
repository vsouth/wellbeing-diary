package ru.vsouth.wellbeingdiary.utils.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.vsouth.wellbeingdiary.dto.integration.WeatherApiCurrentResponse;
import ru.vsouth.wellbeingdiary.dto.integration.WeatherApiGeoResponse;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class WeatherApiClientTest {

    @Autowired
    private WeatherApiClient weatherApiClient;

    @Test
    void testGetCoordinatesByCity() throws JsonProcessingException {
        String cityName = "Moscow";
        WeatherApiGeoResponse response = weatherApiClient.getCoordinatesByCity(cityName);
        assertNotNull(response);
        assertNotNull(response.getLat());
        assertNotNull(response.getLon());
    }

    @Test
    void testGetCurrentWeather() throws JsonProcessingException {
        Double lat = 55.7558;
        Double lon = 37.6176;
        WeatherApiCurrentResponse response = weatherApiClient.getCurrentWeather(lat, lon);
        assertNotNull(response);
        assertNotNull(response.getTemperature());
        assertNotNull(response.getWeatherCode());
    }
}