package ru.vsouth.wellbeingdiary.model.diary;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "health_entries")
public class HealthEntry extends Entry{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int id;

    private Integer heartRate;

    private Integer systolicBloodPressure;

    private Integer diastolicBloodPressure;

    public HealthEntry() {
    }

    public HealthEntry(int id, Integer heartRate, Integer systolicBloodPressure, Integer diastolicBloodPressure) {
        this.id = id;
        this.heartRate = heartRate;
        this.systolicBloodPressure = systolicBloodPressure;
        this.diastolicBloodPressure = diastolicBloodPressure;
    }

    public HealthEntry(Integer heartRate, Integer systolicBloodPressure, Integer diastolicBloodPressure) {
        this.heartRate = heartRate;
        this.systolicBloodPressure = systolicBloodPressure;
        this.diastolicBloodPressure = diastolicBloodPressure;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(Integer heartRate) {
        this.heartRate = heartRate;
    }

    public Integer getSystolicBloodPressure() {
        return systolicBloodPressure;
    }

    public void setSystolicBloodPressure(Integer systolicBloodPressure) {
        this.systolicBloodPressure = systolicBloodPressure;
    }

    public Integer getDiastolicBloodPressure() {
        return diastolicBloodPressure;
    }

    public void setDiastolicBloodPressure(Integer diastolicBloodPressure) {
        this.diastolicBloodPressure = diastolicBloodPressure;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HealthEntry that = (HealthEntry) o;
        return getId() == that.getId() && Objects.equals(getHeartRate(), that.getHeartRate()) && Objects.equals(getSystolicBloodPressure(), that.getSystolicBloodPressure()) && Objects.equals(getDiastolicBloodPressure(), that.getDiastolicBloodPressure());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getHeartRate(), getSystolicBloodPressure(), getDiastolicBloodPressure());
    }
}
