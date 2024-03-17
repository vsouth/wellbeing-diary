package ru.vsouth.wellbeingdiary.dto.diary;

import ru.vsouth.wellbeingdiary.model.diary.PartOfDay;

import java.util.Date;

public class WeatherEntryResponse extends EntryResponse {
    private int id;
    private Double lat;
    private Double lon;
    private Date date;
    private PartOfDay partOfDay;
    private Double temperature;
    private String weatherType;

    public WeatherEntryResponse() {
    }

    public WeatherEntryResponse(int id, Double lat, Double lon, Date date, PartOfDay partOfDay, Double temperature, String weatherType) {
        this.id = id;
        this.lat = lat;
        this.lon = lon;
        this.date = date;
        this.partOfDay = partOfDay;
        this.temperature = temperature;
        this.weatherType = weatherType;
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

    public PartOfDay getPartOfDay() {
        return partOfDay;
    }

    public void setPartOfDay(PartOfDay partOfDay) {
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

}
