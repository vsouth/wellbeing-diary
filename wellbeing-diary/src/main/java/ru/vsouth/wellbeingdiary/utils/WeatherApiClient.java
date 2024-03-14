package ru.vsouth.wellbeingdiary.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.vsouth.wellbeingdiary.dto.WeatherApiCurrentResponse;
import ru.vsouth.wellbeingdiary.dto.WeatherApiGeoResponse;

import java.util.Map;

@Component
public class WeatherApiClient {
    final String CURRENT_WEATHER_API_URL = "https://api.open-meteo.com/v1/forecast";
    final String GEO_API_URL = "https://geocoding-api.open-meteo.com/v1/search";
    private final WeatherApiMapper weatherApiMapper;

    public WeatherApiClient(WeatherApiMapper weatherApiMapper) {
        this.weatherApiMapper = weatherApiMapper;
    }

    public WeatherApiGeoResponse getCoordinatesByCity(String cityName) throws JsonProcessingException {
        String url = GEO_API_URL + "?name=" + cityName + "&count=1&language=ru&format=json";
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForEntity(url, String.class).getBody();
        return weatherApiMapper.toWeatherApiGeoResponse(response);
    }

    public WeatherApiCurrentResponse getCurrentWeather(Double lat, Double lon) throws JsonProcessingException {
        String url = CURRENT_WEATHER_API_URL + "?latitude=" + lat + "&longitude=" + lon + "&current=temperature_2m,weather_code";
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForEntity(url, String.class).getBody();
        return weatherApiMapper.toWeatherApiCurrentResponse(response);
    }

}
