package ru.vsouth.wellbeingdiary.utils.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.vsouth.wellbeingdiary.dto.integration.WeatherApiCurrentResponse;
import ru.vsouth.wellbeingdiary.dto.integration.WeatherApiGeoResponse;

/**
 * Клиент для работы с API погоды.
 */
@Component
public class WeatherApiClient {
    final String CURRENT_WEATHER_API_URL = "https://api.open-meteo.com/v1/forecast";
    final String GEO_API_URL = "https://geocoding-api.open-meteo.com/v1/search";
    private final WeatherApiMapper weatherApiMapper;

    public WeatherApiClient(WeatherApiMapper weatherApiMapper) {
        this.weatherApiMapper = weatherApiMapper;
    }

    /**
     * Метод для получения координат города по его названию
     *
     * @param cityName Город
     * @return Географические координаты города
     * @throws JsonProcessingException Исключение при обработке JSON
     */
    public WeatherApiGeoResponse getCoordinatesByCity(String cityName) throws JsonProcessingException {
        String url = GEO_API_URL + "?name=" + cityName + "&count=1&language=ru&format=json";
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForEntity(url, String.class).getBody();
        return weatherApiMapper.toWeatherApiGeoResponse(response);
    }

    /**
     * Метод для получения текущей погоды по координатам
     *
     * @param lat Широта
     * @param lon Долгота
     * @return Текущая погода
     * @throws JsonProcessingException Исключение при обработке JSON
     */
    public WeatherApiCurrentResponse getCurrentWeather(Double lat, Double lon) throws JsonProcessingException {
        String url = CURRENT_WEATHER_API_URL + "?latitude=" + lat + "&longitude=" + lon + "&current=temperature_2m,weather_code";
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForEntity(url, String.class).getBody();
        return weatherApiMapper.toWeatherApiCurrentResponse(response);
    }

}
