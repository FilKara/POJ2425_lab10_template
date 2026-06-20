package com.example;

import java.time.LocalDateTime;

public class SimpleJobScheduler implements JobScheduler {
    private Job job;
    private LocalDateTime nextExecutionTime;
    private int intervalSeconds ;
    private int remainingRepeats;

    @Override
    public JobScheduler forJob(Job job) {
        this.job = job;
        if (job != null && job.getJobTime() != null) {
            this.nextExecutionTime = job.getJobTime();
        }
        return this;
    }

    @Override
    public JobScheduler startsAt(LocalDateTime time) {
        this.nextExecutionTime = time;
        if (this.job != null) {
            this.job.setJobTime(time);
        }
        return this;
    }

    @Override
    public JobScheduler everySeconds(int seconds) {
        this.intervalSeconds = seconds;
        return this;
    }

    @Override
    public JobScheduler repeatTimes(int times) {

        this.remainingRepeats = times;

        return this;
    }

    @Override
    public void listenTo(TimeEvent event) {
        if (job == null || nextExecutionTime == null) return;


        if (remainingRepeats == 0) return;

        LocalDateTime currentTime = event.getTime();


        if (currentTime.isAfter(nextExecutionTime) || currentTime.isEqual(nextExecutionTime)) {


            new JobThread(job).start();


            if (remainingRepeats > 0) {
                remainingRepeats--;
            }


            if (intervalSeconds > 0 && remainingRepeats != 0) {
                nextExecutionTime = nextExecutionTime.plusSeconds(intervalSeconds);
            } else {

                nextExecutionTime = null;
            }
        }
    }
}