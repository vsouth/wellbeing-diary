package ru.vsouth.wellbeingdiary.dto;

import ru.vsouth.wellbeingdiary.model.Grade;

import java.util.Date;

public class OpenDiaryEntryResponse extends EntryResponse{
    private int id;
    private Integer healthEntryId;
    private Integer weatherEntryId;
    private Date createdAt;
    private Grade mood;
    private Grade stateOfHealth;
    private Grade activityAmount;

    public OpenDiaryEntryResponse(int id, Integer healthEntryId, Integer weatherEntryId, Date createdAt, Grade mood, Grade stateOfHealth, Grade activityAmount) {
        this.id = id;
        this.healthEntryId = healthEntryId;
        this.weatherEntryId = weatherEntryId;
        this.createdAt = createdAt;
        this.mood = mood;
        this.stateOfHealth = stateOfHealth;
        this.activityAmount = activityAmount;
    }

    public int getId() {
        return id;
    }

    public Integer getHealthEntryId() {
        return healthEntryId;
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
