package ru.vsouth.wellbeingdiary.dto;

import ru.vsouth.wellbeingdiary.model.Grade;
import ru.vsouth.wellbeingdiary.model.HealthEntry;

import java.util.Date;

public class OpenDiaryEntryResponse extends EntryResponse{
    private int id;
    private HealthEntry healthEntry;
    private Integer weatherEntryId;
    private Date createdAt;
    private Grade mood;
    private Grade stateOfHealth;
    private Grade activityAmount;

    public OpenDiaryEntryResponse(int id, HealthEntry healthEntry, Integer weatherEntryId, Date createdAt, Grade mood, Grade stateOfHealth, Grade activityAmount) {
        this.id = id;
        this.healthEntry = healthEntry;
        this.weatherEntryId = weatherEntryId;
        this.createdAt = createdAt;
        this.mood = mood;
        this.stateOfHealth = stateOfHealth;
        this.activityAmount = activityAmount;
    }

    public int getId() {
        return id;
    }

    public HealthEntry getHealthEntry() {
        return healthEntry;
    }

    public Integer getWeatherEntryId() {
        return weatherEntryId;
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
}