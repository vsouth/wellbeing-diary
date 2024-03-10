package ru.vsouth.wellbeingdiary.dto;

import java.util.Date;

public class WeatherEntryResponse extends EntryResponse {

    private int id;

    private Double lat;

    private Double lon;

    private Date date;

    private String partOfDay;

    private Double temperature;

    private String weatherType;

    private String moonPhase;

    public WeatherEntryResponse() {
    }

    public WeatherEntryResponse(int id, Double lat, Double lon, Date date, String partOfDay, Double temperature, String weatherType, String moonPhase) {
        this.id = id;
        this.lat = lat;
        this.lon = lon;
        this.date = date;
        this.partOfDay = partOfDay;
        this.temperature = temperature;
        this.weatherType = weatherType;
        this.moonPhase = moonPhase;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPartOfDay() {
        return partOfDay;
    }

    public void setPartOfDay(String partOfDay) {
        this.partOfDay = partOfDay;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public String getWeatherType() {
        return weatherType;
    }

    public void setWeatherType(String weatherType) {
        this.weatherType = weatherType;
    }

    public String getMoonPhase() {
        return moonPhase;
    }

    public void setMoonPhase(String moonPhase) {
        this.moonPhase = moonPhase;
    }


}
