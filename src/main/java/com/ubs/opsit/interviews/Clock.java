package com.ubs.opsit.interviews;

public class Clock {

    private final int hour;
    private final int minute;
    private final int second;

    public Clock(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getSecond() {
        return second;
    }

    public int getQuarter() {
        return minute / 15;
    }
}
