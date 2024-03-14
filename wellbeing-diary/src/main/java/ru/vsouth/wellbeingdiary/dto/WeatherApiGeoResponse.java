package ru.vsouth.wellbeingdiary.dto;

public class WeatherApiGeoResponse {
    private Double lat;
    private Double lon;

    public WeatherApiGeoResponse(Double lat, Double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public WeatherApiGeoResponse() {
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }
}
