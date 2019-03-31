package com.github.ryneal.postcodegeolocation.controller;

import com.github.ryneal.postcodegeolocation.model.JobInfo;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostcodeJobController {

    private JobLauncher jobLauncher;
    private Job postcodeCsvJob;

    public PostcodeJobController(JobLauncher jobLauncher,
                                 Job postcodeCsvJob) {
        this.jobLauncher = jobLauncher;
        this.postcodeCsvJob = postcodeCsvJob;
    }

    @PostMapping("/v1/postcodes/jobs")
    public ResponseEntity<JobInfo> startJob() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        JobInfo jobInfo = new JobInfo();
        jobInfo.setJobId(System.currentTimeMillis());
        JobExecution jobExecution = this.jobLauncher.run(this.postcodeCsvJob, new JobParametersBuilder()
                .addLong("JobId", jobInfo.getJobId())
                .toJobParameters());
        return ResponseEntity.ok(jobInfo);
    }

}
