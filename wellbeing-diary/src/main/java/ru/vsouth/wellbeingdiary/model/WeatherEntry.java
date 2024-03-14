package ru.vsouth.wellbeingdiary.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "weather_entries")
public class WeatherEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int id;

    private Double lat;

    private Double lon;

    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date date;
    @Enumerated(EnumType.STRING)
    private PartOfDay partOfDay;

    private Double temperature;

    private String weatherType;

    private String moonPhase;

    public WeatherEntry() {
    }

    public WeatherEntry(int id, Double lat, Double lon, Date date, PartOfDay partOfDay, Double temperature, String weatherType, String moonPhase) {
        this.id = id;
        this.lat = lat;
        this.lon = lon;
        this.date = date;
        this.partOfDay = partOfDay;
        this.temperature = temperature;
        this.weatherType = weatherType;
        this.moonPhase = moonPhase;
    }

    public WeatherEntry(Double lat, Double lon, Date date, PartOfDay partOfDay, Double temperature, String weatherType, String moonPhase) {
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

    public String getMoonPhase() {
        return moonPhase;
    }

    public void setMoonPhase(String moonPhase) {
        this.moonPhase = moonPhase;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeatherEntry that = (WeatherEntry) o;
        return getId() == that.getId() && Objects.equals(getLat(), that.getLat()) && Objects.equals(getLon(), that.getLon()) && Objects.equals(getDate(), that.getDate()) && Objects.equals(getPartOfDay(), that.getPartOfDay()) && Objects.equals(getTemperature(), that.getTemperature()) && Objects.equals(getWeatherType(), that.getWeatherType()) && Objects.equals(getMoonPhase(), that.getMoonPhase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLat(), getLon(), getDate(), getPartOfDay(), getTemperature(), getWeatherType(), getMoonPhase());
    }

    @Override
    public String toString() {
        return "WeatherEntry{" +
                "id=" + id +
                ", lat=" + lat +
                ", lon=" + lon +
                ", date=" + date +
                ", partOfDay=" + partOfDay +
                ", temperature=" + temperature +
                ", weatherType='" + weatherType + '\'' +
                ", moonPhase='" + moonPhase + '\'' +
                '}';
    }
}
