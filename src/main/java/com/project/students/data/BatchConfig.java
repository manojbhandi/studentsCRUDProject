package com.project.students.data;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import com.project.students.model.Student;

@Configuration
public class BatchConfig {

    private final String[] FIELD_NAMES = new String[] {
            "studentID", "gender", "nationality", "placeofBirth", "stageID", "gradeID", "sectionID",
            "topic", "semester", "relation", "raisedhands", "visitedResources", "announcementsView",
            "discussion", "parentAnsweringSurvey", "parentSchoolSatisfaction", "studentAbsenceDays",
            "studentMarks", "classIn"
    };
// 1. input part
    @Bean
    public FlatFileItemReader<StudentInput> reader() {
        return new FlatFileItemReaderBuilder<StudentInput>()
                .name("StudentItemReader")
                .resource(new ClassPathResource("Student-original.csv"))
                .delimited()
                .names(FIELD_NAMES)
                .fieldSetMapper(new BeanWrapperFieldSetMapper<StudentInput>() {
                    {
                        setTargetType(StudentInput.class);
                    }
                })
                .build();
    }

    // 2. processor part
    @Bean
    public StudentDataProcessor processor() {
        return new StudentDataProcessor();
    }


    // 3. writing into DB part

    @Bean
    public JdbcBatchItemWriter<Student> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Student>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO Student (student_Id, gender, nationality, placeof_birth, stage_id, grade_id, section_id, topic, semester, relation, raisedhands, visited_resources, announcements_view, discussion, parent_answering_survey, parent_school_satisfaction, student_absence_days, student_marks, class_in ) "
                        + " VALUES ( :studentId, :gender, :nationality, :placeofBirth, :stageId, :gradeId, :sectionId ,:topic, :semester, :relation, :raisedhands, :visitedResources, :announcementsView , :discussion, :parentAnsweringSurvey, :parentSchoolSatisfaction, :studentAbsenceDays, :studentMarks, :classIn)")
                .dataSource(dataSource)
                .build();

    }

    @Bean
	public Job importUserJob(JobRepository jobRepository,
		NotificationListner listener, Step step1) {
		return new JobBuilder("importUserJob", jobRepository)
			.incrementer(new RunIdIncrementer())
			.listener(listener)
			.flow(step1)
			.end()
			.build();
	}

    @Bean
	public Step step1(JobRepository jobRepository,
			PlatformTransactionManager transactionManager, JdbcBatchItemWriter<Student> writer) {
		return new StepBuilder("step1", jobRepository)
			.<StudentInput, Student> chunk(10, transactionManager)
			.reader(reader())
			.processor(processor())
			.writer(writer)
			.build();
	}

}
