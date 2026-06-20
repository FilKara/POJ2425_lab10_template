package com.example;

import java.time.LocalDateTime;

public interface Job {
    public void run();
    void setJobTime(LocalDateTime time);
    LocalDateTime getJobTime();
}
