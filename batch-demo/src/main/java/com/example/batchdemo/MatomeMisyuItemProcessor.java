package com.example.batchdemo;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.jdbc.core.JdbcTemplate;

/*
 * バッチプロセッサー
 * 一連バッチ処理「Read → Process → Write」における Process 処理のためのクラス
 * Spring Batch が提供する ItemProcessor インターフェースを実装する。
 * 
 * @author XXX
 */
public class MatomeMisyuItemProcessor implements ItemProcessor<MatomeMisyu, MatomeMisyu> {

	private JdbcTemplate jdbcTemplate;

	private String pattern;

	private static int currentId = -1;

	public MatomeMisyuItemProcessor(String pattern, JdbcTemplate jdbcTemplate) {
		this.pattern = pattern;
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public MatomeMisyu process(final MatomeMisyu MatomeMisyu) throws Exception {

		MatomeMisyu result = null;

		switch (pattern) {
		case "fileToDb":
			result = processFileToDb(MatomeMisyu);
			break;
		case "dbToDb":
			result = processDbToDb(MatomeMisyu);
			break;
		case "dbToFile":
			result = processDbToFile(MatomeMisyu);
			break;
		}

		return result;
	}

	private MatomeMisyu processFileToDb(final MatomeMisyu MatomeMisyu) throws Exception {
		if (currentId == -1) {
			currentId = (int) jdbcTemplate.queryForMap("SELECT MAX(id) as maxid FROM salesforce.MatomeMisyu__c")
					.get("maxid");
		}

		return new MatomeMisyu(++currentId, MatomeMisyu.getName(), MatomeMisyu.getCreditID(),
				MatomeMisyu.getPmacDirectDebitAccountNumber(), MatomeMisyu.getC_CheckDateBaseYearMonth());
	}

	private MatomeMisyu processDbToDb(final MatomeMisyu MatomeMisyu) throws Exception {
		return new MatomeMisyu(MatomeMisyu.getId(), MatomeMisyu.getName(), MatomeMisyu.getCreditID(),
				MatomeMisyu.getPmacDirectDebitAccountNumber(), MatomeMisyu.getC_CheckDateBaseYearMonth());
	}

	private MatomeMisyu processDbToFile(final MatomeMisyu MatomeMisyu) throws Exception {
		return new MatomeMisyu(MatomeMisyu.getId(), MatomeMisyu.getName(), MatomeMisyu.getCreditID(),
				MatomeMisyu.getPmacDirectDebitAccountNumber(), MatomeMisyu.getC_CheckDateBaseYearMonth());
	}

}
