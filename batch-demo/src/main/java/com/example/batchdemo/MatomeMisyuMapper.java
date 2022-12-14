package com.example.batchdemo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * まとめ明細テーブルのマッピングクラス
 *  
 *  @author XXX
 */
public class MatomeMisyuMapper implements RowMapper {

    //カスタムオブジェクト ID
    private static final String ID_COLUMN = "id";
    //決済予定ID
    private static final String NAME_COLUMN = "name";
    //クレジットカードID
    private static final String CREDITID_COLUM = "CreditID__c";
    //振込先パーフェクト口座番号
    private static final String PMACDIRECTDEBITACCOUNTNUMBER_COLUM = "PmacDirectDebitAccountNumber__c";
    //検針年月
    private static final String C_CHECKDATEBASEYEARMONTH_COLUM = "C_CheckDateBaseYearMonth__c";

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		MatomeMisyu matomemisyu = new MatomeMisyu();
		matomemisyu.setId(rs.getInt(ID_COLUMN));
		matomemisyu.setName(rs.getString(NAME_COLUMN));
		matomemisyu.setCreditID(rs.getString(CREDITID_COLUM));
		matomemisyu.setPmacDirectDebitAccountNumber(rs.getString(PMACDIRECTDEBITACCOUNTNUMBER_COLUM));
		matomemisyu.setC_CheckDateBaseYearMonth(rs.getString(C_CHECKDATEBASEYEARMONTH_COLUM));
		return matomemisyu;
	}

    
}
