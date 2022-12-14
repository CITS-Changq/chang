package com.example.batchdemo;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/*
 * まとめ未収バッチ処理を設定するクラス
 * 
 * @author XXX
 */
@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

	@Value("${spring.datasource.url}")
	private String dbUrl;

	@Value("${file.input}")
	private String fileInput;

	@Value("${file.output}")
	private String fileOutput;

	@Autowired
	public DataSource dataSource;

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	public JdbcTemplate jdbcTemplate;

	/**
	 * まとめ未収job
	 * @param listener
	 * @param step1 まとめ未収作成step
	 * @param step2 まとめ未収更新step
	 * @return
	 */
	@Bean
	public Job matomeMisyuJob(JobCompletionNotificationListener listener,
			@Qualifier("createMatomeMisyuRecordStep") Step step1, @Qualifier("updateMatomeMisyuRecordStep") Step step2) {
		//まとめ未収作成stepが正常終了の場合だけまとめ未収更新stepが実行される。
		return jobBuilderFactory.get("matomeMisyuJob").incrementer(new RunIdIncrementer())
				.listener(listener).start(step1).on("COMPLETED").to(step2).end().build();
	}

	/**
	 * まとめ未収作成step
	 * @param processor
	 * @return
	 */
	@Bean
	public Step createMatomeMisyuRecordStep(@Qualifier("createMatomeMisyuProcessor") MatomeMisyuItemProcessor processor) {
		return stepBuilderFactory.get("createMatomeMisyuRecordStep").<MatomeMisyu, MatomeMisyu>chunk(10)
				.reader(createMatomeMisyuItemReader()).processor(processor)
//        .faultTolerant() //faultTolerant()を設定すると、ステップ単位でのリトライや失敗時のスキップの設定を追加することができる。
//        .retryLimit(3) //バッチ方式設計に従ってretryLimit()、retryPolicy() or skipLimit()、skipPolicy()などを設定する。
				.writer(createMatomeMisyuWriter()).build();
	}

	/**
	 * まとめ未収作成reader
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Bean
	public JdbcCursorItemReader<MatomeMisyu> createMatomeMisyuItemReader() {

		return new JdbcCursorItemReaderBuilder<MatomeMisyu>().dataSource(dataSource).name("createMatomeMisyuItemReader").sql(
				"SELECT id, name, CreditID__c, PmacDirectDebitAccountNumber__c, C_CheckDateBaseYearMonth__c FROM salesforce.MatomeMisyu__c where id >= 22561 and id <= 22563")
//                .rowMapper((rs, row) -> new MatomeMisyu (
//                  rs.getInt(1),
//                  rs.getString(2),
//                  rs.getString(3),
//                  rs.getString(4),
//                  rs.getString(5)))
				.rowMapper(new MatomeMisyuMapper()).build();
	}

	/**
	 * まとめ未収作成processor
	 * @return
	 */
	@Bean("createMatomeMisyuProcessor")
	public MatomeMisyuItemProcessor createMatomeMisyuProcessor() {
		return new MatomeMisyuItemProcessor("dbToDb", jdbcTemplate);
	}

	/**
	 * まとめ未収作成writer
	 * @return
	 */
	@Bean("createMatomeMisyuWriter")
	public JdbcBatchItemWriter<MatomeMisyu> createMatomeMisyuWriter() {
		return new JdbcBatchItemWriterBuilder<MatomeMisyu>()
				.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
				.sql("UPDATE salesforce.MatomeMisyu__c SET C_CheckDateBaseYearMonth__c = '202104' , updatetime = now() WHERE id = :id")
				.dataSource(dataSource).build();
	}

	/**
	 * まとめ未収更新step
	 * @param processor
	 * @return
	 */
	@Bean
	public Step updateMatomeMisyuRecordStep(@Qualifier("updateMatomeMisyuProcessor") MatomeMisyuItemProcessor processor) {
		return stepBuilderFactory.get("updateMatomeMisyuRecordStep").<MatomeMisyu, MatomeMisyu>chunk(10)
				.reader(updateMatomeMisyuItemReader()).processor(processor)
				.writer(updateMatomeMisyuWriter()).build();
	}

	/**
	 * まとめ未収更新reader
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Bean
	public JdbcCursorItemReader<MatomeMisyu> updateMatomeMisyuItemReader() {

		return new JdbcCursorItemReaderBuilder<MatomeMisyu>().dataSource(dataSource).name("updateMatomeMisyuItemReader").sql(
				"SELECT id, name, CreditID__c, PmacDirectDebitAccountNumber__c, C_CheckDateBaseYearMonth__c FROM salesforce.MatomeMisyu__c where id >= 22561 and id <= 22563")
				.rowMapper(new MatomeMisyuMapper()).build();
	}

	/**
	 * まとめ未収更新processor
	 * @return
	 */
	@Bean("updateMatomeMisyuProcessor")
	public MatomeMisyuItemProcessor updateMatomeMisyuProcessor() {
		return new MatomeMisyuItemProcessor("dbToDb", jdbcTemplate);
	}

	/**
	 * まとめ未収更新writer
	 * @return
	 */
	@Bean("updateMatomeMisyuWriter")
	public JdbcBatchItemWriter<MatomeMisyu> updateMatomeMisyuWriter() {
		return new JdbcBatchItemWriterBuilder<MatomeMisyu>()
				.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
				.sql("UPDATE salesforce.MatomeMisyu__c SET CreditID__c = 'test' , updatetime = now() WHERE id = :id")
				.dataSource(dataSource).build();
	}

    @Bean
    public JobExecutionDecider decider() {
        return (JobExecution jobExecution, StepExecution stepExecution) -> {
            // write code here
            boolean someCondition = true;
            return someCondition ? new FlowExecutionStatus("COMPLETED") : new FlowExecutionStatus("FAILED");
        };
    }
 
	@Profile("production")
	@Bean
	public DataSource dataSource() throws SQLException {
		if (dbUrl == null || dbUrl.isEmpty()) {
			return new HikariDataSource();
		} else {
			HikariConfig config = new HikariConfig();
			config.setJdbcUrl(dbUrl);
			return new HikariDataSource(config);
		}
	}
}