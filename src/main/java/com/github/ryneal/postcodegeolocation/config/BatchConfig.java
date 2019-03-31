package com.github.ryneal.postcodegeolocation.config;

import com.github.ryneal.postcodegeolocation.batch.task.CsvReader;
import com.github.ryneal.postcodegeolocation.batch.task.PostcodeProcessor;
import com.github.ryneal.postcodegeolocation.model.Postcode;
import com.github.ryneal.postcodegeolocation.service.PostcodeService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.util.Map;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Value("${task.postcode.file}")
    private String postcodeFile;

    @Value("${task.postcode.batchSize:100}")
    private Integer postcodeBatchSize;

    public BatchConfig(JobBuilderFactory jobBuilderFactory,
                       StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    @StepScope
    public CsvReader postcodeCsvReader() {
        return new CsvReader(new File(this.postcodeFile), true);
    }

    @Bean
    public Step postcodeCsvStep(CsvReader postcodeCsvReader,
                                PostcodeProcessor postcodeProcessor,
                                PostcodeService postcodeService) {
        return this.stepBuilderFactory
                .get("postcodeCsvStep")
                .<Map<String, String>, Postcode>chunk(this.postcodeBatchSize)
                .reader(postcodeCsvReader)
                .processor(postcodeProcessor)
                .writer(postcodeService)
                .build();
    }

    @Bean
    public Job postcodeJob(Step postcodeCsvStep) {
        return this.jobBuilderFactory
                .get("postcodeJob")
                .start(postcodeCsvStep)
                .preventRestart()
                .build();
    }
}
