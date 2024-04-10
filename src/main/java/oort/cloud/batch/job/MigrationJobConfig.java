package oort.cloud.batch.job;

import oort.cloud.mapper.master.MasterDao;
import oort.cloud.mapper.slave.SlaveDao;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
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
public class MigrationJob {

    @Bean
    public Job migrationJob(JobRepository jobRepository, Step masterMigrationStep, Step slaveMigrationStep){
        return new JobBuilder("MigrationJob", jobRepository)
                .start(masterMigrationStep)
                .next(slaveMigrationStep)
                .build();
    }

    @Bean
    public Step masterMigrationStep(JobRepository jobRepository, MasterDao dao, PlatformTransactionManager ptm){
        return new StepBuilder("MasterMigrationStep", jobRepository)
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        String dataBases = dao.getDataBases();
                        System.out.println(dataBases);
                        return RepeatStatus.FINISHED;
                    }
                }, ptm)
                .build();
    }

    @Bean
    public Step slaveMigrationStep(JobRepository jobRepository, SlaveDao dao, PlatformTransactionManager ptm){
        return new StepBuilder("SlaveMigrationStep", jobRepository)
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        String dataBases = dao.getDataBases();
                        System.out.println(dataBases);
                        return RepeatStatus.FINISHED;
                    }
                }, ptm)
                .build();
    }
}
