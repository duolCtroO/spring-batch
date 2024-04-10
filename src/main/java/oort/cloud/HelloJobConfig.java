package com.oort.batch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class HelloJobConfig {

    @Bean
    public Job helloJob(JobRepository jobRepository, Step helloStep1, Step helloStep2){
        return new JobBuilder("helloJob", jobRepository)
                   .start(helloStep1)
                   .next(helloStep2)
                   .build();
    }

    @Bean
    public Step helloStep1(JobRepository jobRepository, PlatformTransactionManager ptm){
        return new StepBuilder("helloStep", jobRepository)
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("hello Step1!!!");
                        return RepeatStatus.FINISHED;
                    }
                }, ptm)
                .build();
    }

    @Bean
    public Step helloStep2(JobRepository jobRepository, PlatformTransactionManager ptm){
        return new StepBuilder("helloStep", jobRepository)
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("Hello Step2");
                        return RepeatStatus.FINISHED;
                    }
                }, ptm)
                .build();
    }

//    @Bean
//    public Tasklet helloTask1(){
//        return new Tasklet() {
//            @Override
//            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//                System.out.println("Hello Task1!!!");
//                return RepeatStatus.FINISHED;
//            }
//        };
//    }
//
//    @Bean
//    public Tasklet helloTask2(){
//        return new Tasklet() {
//            @Override
//            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//                System.out.println("Hello Task2!!!");
//                return RepeatStatus.FINISHED;
//            }
//        };
//    }
}
