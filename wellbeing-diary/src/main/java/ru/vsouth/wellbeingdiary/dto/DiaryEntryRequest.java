package ru.vsouth.wellbeingdiary.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import ru.vsouth.wellbeingdiary.model.Grade;
import ru.vsouth.wellbeingdiary.model.HealthEntry;

import java.util.Date;
@JsonIgnoreProperties(ignoreUnknown = true)
public class DiaryEntryRequest {
    private Integer id;
    private int userId;
    private HealthEntry healthEntry;
    private Integer weatherEntryId;
    private Date createdAt;
    private String entryText;
    private Grade mood;
    private Grade stateOfHealth;
    private Grade activityAmount;

    public DiaryEntryRequest(Integer id, int userId, HealthEntry healthEntry, Integer weatherEntryId, Date createdAt, String entryText, Grade mood, Grade stateOfHealth, Grade activityAmount) {
        this.id = id;
        this.userId = userId;
        this.healthEntry = healthEntry;
        this.weatherEntryId = weatherEntryId;
        this.createdAt = createdAt;
        this.entryText = entryText;
        this.mood = mood;
        this.stateOfHealth = stateOfHealth;
        this.activityAmount = activityAmount;
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

    public Integer getWeatherEntryId() {
        return weatherEntryId;
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
}
