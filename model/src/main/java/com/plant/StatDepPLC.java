package com.plant;

/**
 * Created by Shevchenko on 22.02.2016.
 */
public class StatDepPLC {


    private float hour;
    private float oldHour;
    private float shift;
    private float oldShift;
    private float day;
    private float oldDay;
    private float tenDay;
    private float oldTenDay;
    private float month;
    private float oldMonth;
    private float season;
    private boolean clear;

    public StatDepPLC() {
    }

    public float getHour() {
        return hour;
    }

    public float getOldHour() {
        return oldHour;
    }

    public float getShift() {
        return shift;
    }

    public float getOldShift() {
        return oldShift;
    }

    public float getDay() {
        return day;
    }

    public float getOldDay() {
        return oldDay;
    }

    public float getTenDay() {
        return tenDay;
    }

    public float getOldTenDay() {
        return oldTenDay;
    }

    public float getMonth() {
        return month;
    }

    public float getOldMonth() {
        return oldMonth;
    }

    public float getSeason() {
        return season;
    }

    public boolean isClear() {
        return clear;
    }

    public void setHour(float hour) {
        this.hour = hour;
    }

    public void setOldHour(float oldHour) {
        this.oldHour = oldHour;
    }

    public void setShift(float shift) {
        this.shift = shift;
    }

    public void setOldShift(float oldShift) {
        this.oldShift = oldShift;
    }

    public void setDay(float day) {
        this.day = day;
    }

    public void setOldDay(float oldDay) {
        this.oldDay = oldDay;
    }

    public void setTenDay(float tenDay) {
        this.tenDay = tenDay;
    }

    public void setOldTenDay(float oldTenDay) {
        this.oldTenDay = oldTenDay;
    }

    public void setMonth(float month) {
        this.month = month;
    }

    public void setOldMonth(float oldMonth) {
        this.oldMonth = oldMonth;
    }

    public void setSeason(float season) {
        this.season = season;
    }

    public void setClear(boolean clear) {
        this.clear = clear;
    }

}
