package com.example.batchdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/*
 * バッチ処理が完了した後に実行する処理を設定する。
 * 
 * @author XXX
 */
@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

	private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

	private JdbcTemplate jdbcTemplate;

	public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
			//TODO ログテスト
			log.info("!!! JOB FINISHED! Time to verify the results");
			log.warn("!!! JOB FINISHED! Time to verify the results");
			log.error("!!! JOB FINISHED! Time to verify the results");
			log.debug("!!! JOB FINISHED! Time to verify the results");
			log.trace("!!! JOB FINISHED! Time to verify the results");

			jdbcTemplate.query(
					"SELECT id, name, CreditID__c, PmacDirectDebitAccountNumber__c, C_CheckDateBaseYearMonth__c FROM salesforce.MatomeMisyu__c",
					(rs, row) -> new MatomeMisyu(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
							rs.getString(5)))
					.forEach(MatomeMisyu -> System.out.println("Found <" + MatomeMisyu + "> in the database."));
		}
	}
}