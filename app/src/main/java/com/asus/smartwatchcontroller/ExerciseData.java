package com.asus.smartwatchcontroller;

import java.util.Date;

/**
 * Created by Monect on 25/11/2016.
 */


public class ExerciseData {
    private Integer AverageHeartRate;
    private Float ExerciseCal;
    private Integer ExerciseDistance;
    private Integer ExerciseMode;
    private Integer ExerciseTime;
    private Integer HeartZone1;
    private Integer HeartZone2;
    private Integer HeartZone3;
    private Integer HeartZone4;
    private Integer HeartZone5;
    private Integer HighestHeartRate;
    private Integer LowestHeartRate;
    private Date RecordDate;
    private Long id;
    private String memo1;
    private String memo2;
    private String memo3;

    public ExerciseData() {
    }

    public ExerciseData(final Long id) {
        this.id = id;
    }

    public ExerciseData(final Long id, final Date recordDate, final Integer exerciseMode, final Integer averageHeartRate, final Integer lowestHeartRate, final Integer highestHeartRate, final Integer heartZone1, final Integer heartZone2, final Integer heartZone3, final Integer heartZone4, final Integer heartZone5, final Integer exerciseTime, final Integer exerciseDistance, final Float exerciseCal, final String memo1, final String memo2, final String memo3) {
        this.id = id;
        this.RecordDate = recordDate;
        this.ExerciseMode = exerciseMode;
        this.AverageHeartRate = averageHeartRate;
        this.LowestHeartRate = lowestHeartRate;
        this.HighestHeartRate = highestHeartRate;
        this.HeartZone1 = heartZone1;
        this.HeartZone2 = heartZone2;
        this.HeartZone3 = heartZone3;
        this.HeartZone4 = heartZone4;
        this.HeartZone5 = heartZone5;
        this.ExerciseTime = exerciseTime;
        this.ExerciseDistance = exerciseDistance;
        this.ExerciseCal = exerciseCal;
        this.memo1 = memo1;
        this.memo2 = memo2;
        this.memo3 = memo3;
    }

    public Integer getAverageHeartRate() {
        return this.AverageHeartRate;
    }

    public Float getExerciseCal() {
        return this.ExerciseCal;
    }

    public Integer getExerciseDistance() {
        return this.ExerciseDistance;
    }

    public Integer getExerciseMode() {
        return this.ExerciseMode;
    }

    public Integer getExerciseTime() {
        return this.ExerciseTime;
    }

    public Integer getHeartZone1() {
        return this.HeartZone1;
    }

    public Integer getHeartZone2() {
        return this.HeartZone2;
    }

    public Integer getHeartZone3() {
        return this.HeartZone3;
    }

    public Integer getHeartZone4() {
        return this.HeartZone4;
    }

    public Integer getHeartZone5() {
        return this.HeartZone5;
    }

    public Integer getHighestHeartRate() {
        return this.HighestHeartRate;
    }

    public Long getId() {
        return this.id;
    }

    public Integer getLowestHeartRate() {
        return this.LowestHeartRate;
    }

    public String getMemo1() {
        return this.memo1;
    }

    public String getMemo2() {
        return this.memo2;
    }

    public String getMemo3() {
        return this.memo3;
    }

    public Date getRecordDate() {
        return this.RecordDate;
    }

    public void setAverageHeartRate(final Integer averageHeartRate) {
        this.AverageHeartRate = averageHeartRate;
    }

    public void setExerciseCal(final Float exerciseCal) {
        this.ExerciseCal = exerciseCal;
    }

    public void setExerciseDistance(final Integer exerciseDistance) {
        this.ExerciseDistance = exerciseDistance;
    }

    public void setExerciseMode(final Integer exerciseMode) {
        this.ExerciseMode = exerciseMode;
    }

    public void setExerciseTime(final Integer exerciseTime) {
        this.ExerciseTime = exerciseTime;
    }

    public void setHeartZone1(final Integer heartZone1) {
        this.HeartZone1 = heartZone1;
    }

    public void setHeartZone2(final Integer heartZone2) {
        this.HeartZone2 = heartZone2;
    }

    public void setHeartZone3(final Integer heartZone3) {
        this.HeartZone3 = heartZone3;
    }

    public void setHeartZone4(final Integer heartZone4) {
        this.HeartZone4 = heartZone4;
    }

    public void setHeartZone5(final Integer heartZone5) {
        this.HeartZone5 = heartZone5;
    }

    public void setHighestHeartRate(final Integer highestHeartRate) {
        this.HighestHeartRate = highestHeartRate;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setLowestHeartRate(final Integer lowestHeartRate) {
        this.LowestHeartRate = lowestHeartRate;
    }

    public void setMemo1(final String memo1) {
        this.memo1 = memo1;
    }

    public void setMemo2(final String memo2) {
        this.memo2 = memo2;
    }

    public void setMemo3(final String memo3) {
        this.memo3 = memo3;
    }

    public void setRecordDate(final Date recordDate) {
        this.RecordDate = recordDate;
    }
}
