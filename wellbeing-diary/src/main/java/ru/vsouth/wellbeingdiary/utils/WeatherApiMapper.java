package ru.vsouth.wellbeingdiary.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import ru.vsouth.wellbeingdiary.dto.WeatherApiCurrentResponse;
import ru.vsouth.wellbeingdiary.dto.WeatherApiGeoResponse;

@Component
public class WeatherApiMapper {
    private final ObjectMapper jsonParser = new ObjectMapper();

    public WeatherApiGeoResponse toWeatherApiGeoResponse(String str) throws JsonProcessingException {
        JsonNode jsonNode = jsonParser.readTree(str).get("results").get(0);
        Double lat = jsonNode.get("latitude").asDouble();
        Double lon = jsonNode.get("longitude").asDouble();
        return new WeatherApiGeoResponse(lat, lon);
    }

    public WeatherApiCurrentResponse toWeatherApiCurrentResponse(String str) throws JsonProcessingException {
        JsonNode jsonNode = jsonParser.readTree(str).get("current");
        Double temperature = jsonNode.get("temperature_2m").asDouble();
        int weatherCode = jsonNode.get("weather_code").asInt();
        return new WeatherApiCurrentResponse(temperature, weatherCode);
    }
}
