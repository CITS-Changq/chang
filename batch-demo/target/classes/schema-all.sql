create schema salesforce AUTHORIZATION SA;

CREATE TABLE salesforce.MatomeMisyu__c  (
    id INT IDENTITY NOT NULL PRIMARY KEY,
    name VARCHAR(80),
    CreditID__c VARCHAR(7),
    PmacDirectDebitAccountNumber__c VARCHAR(7),
    C_CheckDateBaseYearMonth__c  VARCHAR(6),
    externalID__c VARCHAR(10),
    updatetime TIMESTAMP
);

insert into salesforce.MatomeMisyu__c (id, name, CreditID__c, PmacDirectDebitAccountNumber__c, C_CheckDateBaseYearMonth__c) 
values 
(1, 'local1', 'card1', 'acc1', '202101');