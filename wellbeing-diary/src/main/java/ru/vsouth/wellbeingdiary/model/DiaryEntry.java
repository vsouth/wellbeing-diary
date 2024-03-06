package ru.vsouth.wellbeingdiary.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name="diary_entries")
public class DiaryEntry extends Entry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int userId;
    private Integer healthEntryId;
    private Integer weatherEntryId;
    private Date createdAt;
    private String entryText;
    @Enumerated(EnumType.STRING)
    private Grade mood;
    @Enumerated(EnumType.STRING)
    private Grade stateOfHealth;
    @Enumerated(EnumType.STRING)
    private Grade activityAmount;

    public DiaryEntry() {
    }

    public DiaryEntry(int id, int userId, Integer healthEntryId, Integer weatherEntryId, Date createdAt, String entryText, Grade mood, Grade stateOfHealth, Grade activityAmount) {
        this.id = id;
        this.userId = userId;
        this.healthEntryId = healthEntryId;
        this.weatherEntryId = weatherEntryId;
        this.createdAt = createdAt;
        this.entryText = entryText;
        this.mood = mood;
        this.stateOfHealth = stateOfHealth;
        this.activityAmount = activityAmount;
    }

    public DiaryEntry(int userId, Integer healthEntryId, Integer weatherEntryId, Date createdAt, String entryText, Grade mood, Grade stateOfHealth, Grade activityAmount) {
        this.userId = userId;
        this.healthEntryId = healthEntryId;
        this.weatherEntryId = weatherEntryId;
        this.createdAt = createdAt;
        this.entryText = entryText;
        this.mood = mood;
        this.stateOfHealth = stateOfHealth;
        this.activityAmount = activityAmount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Integer getHealthEntryId() {
        return healthEntryId;
    }

    public void setHealthEntryId(Integer healthEntryId) {
        this.healthEntryId = healthEntryId;
    }

    public Integer getWeatherEntryId() {
        return weatherEntryId;
    }

    public void setWeatherEntryId(Integer weatherEntryId) {
        this.weatherEntryId = weatherEntryId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getEntryText() {
        return entryText;
    }

    public void setEntryText(String entryText) {
        this.entryText = entryText;
    }

    public Grade getMood() {
        return mood;
    }

    public void setMood(Grade mood) {
        this.mood = mood;
    }

    public Grade getStateOfHealth() {
        return stateOfHealth;
    }

    public void setStateOfHealth(Grade stateOfHealth) {
        this.stateOfHealth = stateOfHealth;
    }

    public Grade getActivityAmount() {
        return activityAmount;
    }

    public void setActivityAmount(Grade activityAmount) {
        this.activityAmount = activityAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiaryEntry that = (DiaryEntry) o;
        return getId() == that.getId() && getUserId() == that.getUserId() && Objects.equals(getHealthEntryId(), that.getHealthEntryId()) && Objects.equals(getWeatherEntryId(), that.getWeatherEntryId()) && Objects.equals(getCreatedAt(), that.getCreatedAt()) && Objects.equals(getEntryText(), that.getEntryText()) && getMood() == that.getMood() && getStateOfHealth() == that.getStateOfHealth() && getActivityAmount() == that.getActivityAmount();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUserId(), getHealthEntryId(), getWeatherEntryId(), getCreatedAt(), getEntryText(), getMood(), getStateOfHealth(), getActivityAmount());
    }
}
