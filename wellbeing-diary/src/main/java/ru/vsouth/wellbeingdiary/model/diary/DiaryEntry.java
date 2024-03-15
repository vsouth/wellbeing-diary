package ru.vsouth.wellbeingdiary.model.diary;

import org.springframework.lang.NonNull;
import ru.vsouth.wellbeingdiary.model.user.User;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "diary_entries")
public class DiaryEntry extends Entry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne()
    @JoinColumn(name = "userId", referencedColumnName = "id", nullable = false)
    @NonNull
    private User user;
    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "healthEntryId", referencedColumnName = "id")
    private HealthEntry healthEntry;
    @ManyToOne
    @JoinColumn(name = "weatherEntryId", referencedColumnName = "id")
    private WeatherEntry weatherEntry;
    @Temporal(TemporalType.TIMESTAMP)
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

    public DiaryEntry(int id, @NonNull User user, HealthEntry healthEntry, WeatherEntry weatherEntry, Date createdAt, String entryText, Grade mood, Grade stateOfHealth, Grade activityAmount) {
        this.id = id;
        this.user = user;
        this.healthEntry = healthEntry;
        this.weatherEntry = weatherEntry;
        this.createdAt = createdAt;
        this.entryText = entryText;
        this.mood = mood;
        this.stateOfHealth = stateOfHealth;
        this.activityAmount = activityAmount;
    }

    public DiaryEntry(@NonNull User user, HealthEntry healthEntry, WeatherEntry weatherEntry, Date createdAt, String entryText, Grade mood, Grade stateOfHealth, Grade activityAmount) {
        this.user = user;
        this.healthEntry = healthEntry;
        this.weatherEntry = weatherEntry;
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

    @NonNull
    public User getUser() {
        return user;
    }

    public void setUser(@NonNull User user) {
        this.user = user;
    }

    public HealthEntry getHealthEntry() {
        return healthEntry;
    }

    public void setHealthEntry(HealthEntry healthEntry) {
        this.healthEntry = healthEntry;
    }

    public WeatherEntry getWeatherEntry() {
        return weatherEntry;
    }

    public void setWeatherEntry(WeatherEntry weatherEntry) {
        this.weatherEntry = weatherEntry;
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
        return getId() == that.getId() && getUser().equals(that.getUser()) && Objects.equals(getHealthEntry(), that.getHealthEntry()) && Objects.equals(getWeatherEntry(), that.getWeatherEntry()) && getCreatedAt().equals(that.getCreatedAt()) && Objects.equals(getEntryText(), that.getEntryText()) && getMood() == that.getMood() && getStateOfHealth() == that.getStateOfHealth() && getActivityAmount() == that.getActivityAmount();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUser(), getHealthEntry(), getWeatherEntry(), getCreatedAt(), getEntryText(), getMood(), getStateOfHealth(), getActivityAmount());
    }
}