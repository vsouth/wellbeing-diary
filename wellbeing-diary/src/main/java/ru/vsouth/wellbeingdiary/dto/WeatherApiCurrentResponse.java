package ru.vsouth.wellbeingdiary.dto;

public class WeatherApiCurrentResponse {
    private Double temperature;
    private int weatherCode;

    public WeatherApiCurrentResponse(Double temperature, int weatherCode) {
        this.temperature = temperature;
        this.weatherCode = weatherCode;
    }

    public WeatherApiCurrentResponse() {
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public int getWeatherCode() {
        return weatherCode;
    }

    public void setWeatherCode(int weatherCode) {
        this.weatherCode = weatherCode;
    }
}
