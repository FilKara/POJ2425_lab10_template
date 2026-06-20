package com.example;

import java.time.LocalDateTime;

public class ShowTimeJob implements Job {
    private LocalDateTime time;

    public ShowTimeJob(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public void run() {
        System.out.println(time);
    }

    @Override
    public void setJobTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public LocalDateTime getJobTime() {
        return this.time;
    }
}