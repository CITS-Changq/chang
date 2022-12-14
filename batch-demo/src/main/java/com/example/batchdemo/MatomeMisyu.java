package com.example.batchdemo;

/*
 * データモデル
 *  テーブル内の 1 レコードに対応するクラス
 *  
 *  @author XXX
 */
public class MatomeMisyu {
    private int id;
    private String name;
    private String externalKey;
    private String CreditID;
    private String PmacDirectDebitAccountNumber;
    private String C_CheckDateBaseYearMonth;
    //private Timestamp updatetime;

    public MatomeMisyu(){
              
    }

    public MatomeMisyu(int id, String name, String CreditID, String PmacDirectDebitAccountNumber, String C_CheckDateBaseYearMonth){
        this.id = id;
        this.name = name;
        this.externalKey = "EX" + id;
        this.CreditID = CreditID;
        this.PmacDirectDebitAccountNumber = PmacDirectDebitAccountNumber;
        this.C_CheckDateBaseYearMonth = C_CheckDateBaseYearMonth;
        //TODO for test
        this.toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExternalKey() {
        return externalKey;
    }

    public void setExternalKey(String externalKey) {
        this.externalKey = externalKey;
    }

    public String getCreditID() {
        return CreditID;
    }

    public void setCreditID(String creditID) {
        CreditID = creditID;
    }

    public String getPmacDirectDebitAccountNumber() {
        return PmacDirectDebitAccountNumber;
    }

    public void setPmacDirectDebitAccountNumber(String pmacDirectDebitAccountNumber) {
        PmacDirectDebitAccountNumber = pmacDirectDebitAccountNumber;
    }

    public String getC_CheckDateBaseYearMonth() {
        return C_CheckDateBaseYearMonth;
    }

    public void setC_CheckDateBaseYearMonth(String c_CheckDateBaseYearMonth) {
        C_CheckDateBaseYearMonth = c_CheckDateBaseYearMonth;
    }

//    public Timestamp getUpdatetime() {
//        return updatetime;
//    }
//
//    public void setUpdatetime(Timestamp updatetime) {
//        this.updatetime = updatetime;
//    }

    public String toString() {
        return "id=" + this.id
                + ", name=" + this.name
                + ", externalKey=" + this.externalKey
                + ", CreditID=" + this.CreditID
                + ", PmacDirectDebitAccountNumber=" + this.PmacDirectDebitAccountNumber
                + ", C_CheckDateBaseYearMonth=" + this.C_CheckDateBaseYearMonth;
    }
    
}
