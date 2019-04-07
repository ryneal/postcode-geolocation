package com.github.ryneal.postcodegeolocation.batch.schedule;

import net.javacrumbs.shedlock.core.SchedulerLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@ConditionalOnProperty(name = "scheduling.enabled", havingValue = "true")
@Component
public class ScheduledJob {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledJob.class);

    private JobLauncher jobLauncher;
    private Job postcodeJob;
    private Job postcodeDistrictJob;

    public ScheduledJob(JobLauncher jobLauncher,
                        Job postcodeJob,
                        Job postcodeDistrictJob) {
        this.jobLauncher = jobLauncher;
        this.postcodeJob = postcodeJob;
        this.postcodeDistrictJob = postcodeDistrictJob;
    }

    @Scheduled(cron = "${task.postcode.cron}")
    @SchedulerLock(name = "ScheduledJob_runPostcodeBatchJob",
            lockAtLeastForString = "${task.postcode.lock.at-least:PT2H}",
            lockAtMostForString = "${task.postcode.lock.at-most:PT4H}")
    public void runPostcodeBatchJob() {
        try {
            LOGGER.info("Started Postcode Batch Job");
            this.jobLauncher.run(postcodeJob, new JobParametersBuilder()
                    .addString("JobId", "P" + System.currentTimeMillis())
                    .toJobParameters());
            LOGGER.info("Finished Postcode Batch Job");
        } catch (JobExecutionAlreadyRunningException | JobRestartException |
                JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {
            LOGGER.error("Postcode Job Failed: " + e.getMessage());
        }
    }

    @Scheduled(cron = "${task.postcode-district.cron}")
    @SchedulerLock(name = "ScheduledJob_runPostcodeDistrictBatchJob",
            lockAtLeastForString = "${task.postcode-district.lock.at-least:PT2H}",
            lockAtMostForString = "${task.postcode-district.lock.at-most:PT4H}")
    public void runPostcodeDistrictBatchJob() {
        try {
            LOGGER.info("Started Postcode District Batch Job");
            this.jobLauncher.run(postcodeDistrictJob, new JobParametersBuilder()
                    .addString("JobId", "PD" + System.currentTimeMillis())
                    .toJobParameters());
            LOGGER.info("Finished Postcode District Batch Job");
        } catch (JobExecutionAlreadyRunningException | JobRestartException |
                JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {
            LOGGER.error("PostcodeDistrict Job Failed: " + e.getMessage());
        }
    }
}
