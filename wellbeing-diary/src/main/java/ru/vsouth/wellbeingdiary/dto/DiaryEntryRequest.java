package ru.vsouth.wellbeingdiary.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import ru.vsouth.wellbeingdiary.model.Grade;
import ru.vsouth.wellbeingdiary.model.HealthEntry;
import ru.vsouth.wellbeingdiary.model.WeatherEntry;

import java.util.Date;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DiaryEntryRequest {
    private Integer id;
    private int userId;
    private HealthEntry healthEntry;
    private WeatherEntry weatherEntry;
    private Date createdAt;
    private String entryText;
    private Grade mood;
    private Grade stateOfHealth;
    private Grade activityAmount;

    public DiaryEntryRequest(Integer id, int userId, HealthEntry healthEntry, WeatherEntry weatherEntry, Date createdAt, String entryText, Grade mood, Grade stateOfHealth, Grade activityAmount) {
        this.id = id;
        this.userId = userId;
        this.healthEntry = healthEntry;
        this.weatherEntry = weatherEntry;
        this.createdAt = createdAt;
        this.entryText = entryText;
        this.mood = mood;
        this.stateOfHealth = stateOfHealth;
        this.activityAmount = activityAmount;
    }

    public DiaryEntryRequest() {
    }

    public Integer getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public HealthEntry getHealthEntry() {
        return healthEntry;
    }

    public WeatherEntry getWeatherEntry() {
        return weatherEntry;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public String getEntryText() {
        return entryText;
    }

    public Grade getMood() {
        return mood;
    }

    public Grade getStateOfHealth() {
        return stateOfHealth;
    }

    public Grade getActivityAmount() {
        return activityAmount;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setHealthEntry(HealthEntry healthEntry) {
        this.healthEntry = healthEntry;
    }

    public void setWeatherEntry(WeatherEntry weatherEntry) {
        this.weatherEntry = weatherEntry;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setEntryText(String entryText) {
        this.entryText = entryText;
    }

    public void setMood(Grade mood) {
        this.mood = mood;
    }

    public void setStateOfHealth(Grade stateOfHealth) {
        this.stateOfHealth = stateOfHealth;
    }

    public void setActivityAmount(Grade activityAmount) {
        this.activityAmount = activityAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiaryEntryRequest that = (DiaryEntryRequest) o;
        return getUserId() == that.getUserId() && Objects.equals(getId(), that.getId()) && Objects.equals(getHealthEntry(), that.getHealthEntry()) && Objects.equals(getWeatherEntry(), that.getWeatherEntry()) && Objects.equals(getCreatedAt(), that.getCreatedAt()) && Objects.equals(getEntryText(), that.getEntryText()) && getMood() == that.getMood() && getStateOfHealth() == that.getStateOfHealth() && getActivityAmount() == that.getActivityAmount();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUserId(), getHealthEntry(), getWeatherEntry(), getCreatedAt(), getEntryText(), getMood(), getStateOfHealth(), getActivityAmount());
    }
}
