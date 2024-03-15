package ru.vsouth.wellbeingdiary.dto.diary;

import ru.vsouth.wellbeingdiary.model.diary.Grade;
import ru.vsouth.wellbeingdiary.model.diary.HealthEntry;
import ru.vsouth.wellbeingdiary.model.diary.WeatherEntry;

import java.util.Date;

public class OpenDiaryEntryResponse extends EntryResponse{
    private int id;
    private HealthEntry healthEntry;
    private WeatherEntry weatherEntry;
    private Date createdAt;
    private Grade mood;
    private Grade stateOfHealth;
    private Grade activityAmount;

    public OpenDiaryEntryResponse(int id, HealthEntry healthEntry, WeatherEntry weatherEntry, Date createdAt, Grade mood, Grade stateOfHealth, Grade activityAmount) {
        this.id = id;
        this.healthEntry = healthEntry;
        this.weatherEntry = weatherEntry;
        this.createdAt = createdAt;
        this.mood = mood;
        this.stateOfHealth = stateOfHealth;
        this.activityAmount = activityAmount;
    }

    public OpenDiaryEntryResponse() {
    }

    public int getId() {
        return id;
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

    public Grade getMood() {
        return mood;
    }

    public Grade getStateOfHealth() {
        return stateOfHealth;
    }

    public Grade getActivityAmount() {
        return activityAmount;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setMood(Grade mood) {
        this.mood = mood;
    }

    public void setStateOfHealth(Grade stateOfHealth) {
        this.stateOfHealth = stateOfHealth;
    }

    public void setActivityAmount(Grade activityAmount) {
        this.activityAmount = activityAmount;
    }
}
