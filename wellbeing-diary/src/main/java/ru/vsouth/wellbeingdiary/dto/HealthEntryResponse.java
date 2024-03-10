package ru.vsouth.wellbeingdiary.dto;

public class HealthEntryResponse extends EntryResponse {
    private int id;

    private Integer heartRate;

    private Integer systolicBloodPressure;

    private Integer diastolicBloodPressure;

    public HealthEntryResponse(int id, Integer heartRate, Integer systolicBloodPressure, Integer diastolicBloodPressure) {
        this.id = id;
        this.heartRate = heartRate;
        this.systolicBloodPressure = systolicBloodPressure;
        this.diastolicBloodPressure = diastolicBloodPressure;
    }

    public HealthEntryResponse() {
    }

    public int getId() {
        return id;
    }

    public Integer getHeartRate() {
        return heartRate;
    }

    public Integer getSystolicBloodPressure() {
        return systolicBloodPressure;
    }

    public Integer getDiastolicBloodPressure() {
        return diastolicBloodPressure;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setHeartRate(Integer heartRate) {
        this.heartRate = heartRate;
    }

    public void setSystolicBloodPressure(Integer systolicBloodPressure) {
        this.systolicBloodPressure = systolicBloodPressure;
    }

    public void setDiastolicBloodPressure(Integer diastolicBloodPressure) {
        this.diastolicBloodPressure = diastolicBloodPressure;
    }
}
