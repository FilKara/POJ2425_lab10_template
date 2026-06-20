package com.example;

import java.time.LocalDateTime;

public class DescribedJob implements Job {
    private final String description;
    private LocalDateTime time = LocalDateTime.now();

    public DescribedJob(String description) {
        this.description = description;
    }

    @Override
    public void run() {
        System.out.println( description);
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