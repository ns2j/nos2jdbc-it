CREATE SEQUENCE SEQUENCE_STRATEGY_ID START WITH 1 INCREMENT BY 100;
CREATE SEQUENCE MY_SEQUENCE_STRATEGY_ID START WITH 1 INCREMENT BY 100 ;

CREATE TABLE DEPARTMENT(DEPARTMENT_ID INTEGER NOT NULL PRIMARY KEY, DEPARTMENT_NO NUMERIC(2) NOT NULL UNIQUE,DEPARTMENT_NAME VARCHAR(20),LOCATION VARCHAR(20) DEFAULT 'TOKYO', VERSION INTEGER);
CREATE TABLE ADDRESS(ADDRESS_ID INTEGER NOT NULL PRIMARY KEY, STREET VARCHAR(20), VERSION INTEGER);
CREATE TABLE EMPLOYEE(EMPLOYEE_ID INTEGER NOT NULL PRIMARY KEY, EMPLOYEE_NO NUMERIC(4) NOT NULL ,EMPLOYEE_NAME VARCHAR(20),MANAGER_ID INTEGER,HIREDATE DATE,SALARY NUMERIC(7,2),DEPARTMENT_ID INTEGER,ADDRESS_ID INTEGER, VERSION INTEGER, CONSTRAINT FK_DEPARTMENT_ID FOREIGN KEY(DEPARTMENT_ID) REFERENCES DEPARTMENT(DEPARTMENT_ID), CONSTRAINT FK_ADDRESS_ID FOREIGN KEY(ADDRESS_ID) REFERENCES ADDRESS(ADDRESS_ID));

CREATE TABLE COMP_KEY_DEPARTMENT(DEPARTMENT_ID1 INTEGER NOT NULL, DEPARTMENT_ID2 INTEGER NOT NULL, DEPARTMENT_NO NUMERIC(2) NOT NULL UNIQUE,DEPARTMENT_NAME VARCHAR(20),LOCATION VARCHAR(20) DEFAULT 'TOKYO', VERSION INTEGER, CONSTRAINT PK_DEPT PRIMARY KEY(DEPARTMENT_ID1, DEPARTMENT_ID2));
CREATE TABLE COMP_KEY_ADDRESS(ADDRESS_ID1 INTEGER NOT NULL, ADDRESS_ID2 INTEGER NOT NULL, STREET VARCHAR(20), VERSION INTEGER, CONSTRAINT PK_ADD PRIMARY KEY(ADDRESS_ID1, ADDRESS_ID2));
CREATE TABLE COMP_KEY_EMPLOYEE(EMPLOYEE_ID1 INTEGER NOT NULL, EMPLOYEE_ID2 INTEGER NOT NULL, EMPLOYEE_NO NUMERIC(4) NOT NULL ,EMPLOYEE_NAME VARCHAR(20),MANAGER_ID1 INTEGER,MANAGER_ID2 INTEGER,HIREDATE DATE,SALARY NUMERIC(7,2),DEPARTMENT_ID1 INTEGER,DEPARTMENT_ID2 INTEGER,ADDRESS_ID1 INTEGER,ADDRESS_ID2 INTEGER,VERSION INTEGER, CONSTRAINT PK_EMP PRIMARY KEY(EMPLOYEE_ID1, EMPLOYEE_ID2), CONSTRAINT FK_DEPT_ID FOREIGN KEY(DEPARTMENT_ID1, DEPARTMENT_ID2) REFERENCES COMP_KEY_DEPARTMENT(DEPARTMENT_ID1, DEPARTMENT_ID2), CONSTRAINT FK_ADD_ID FOREIGN KEY(ADDRESS_ID1, ADDRESS_ID2) REFERENCES COMP_KEY_ADDRESS(ADDRESS_ID1, ADDRESS_ID2));

CREATE TABLE LARGE_OBJECT(ID NUMERIC(8) NOT NULL PRIMARY KEY, NAME VARCHAR(20), LARGE_NAME CLOB, BYTES VARBINARY(100), LARGE_BYTES BLOB, DTO VARBINARY(1000), LARGE_DTO BLOB);
CREATE TABLE TENSE (ID INTEGER NOT NULL PRIMARY KEY,DATE_DATE DATE, DATE_TIME TIME, DATE_TIMESTAMP TIMESTAMP, CAL_DATE DATE, CAL_TIME TIME, CAL_TIMESTAMP TIMESTAMP, SQL_DATE DATE, SQL_TIME TIME, SQL_TIMESTAMP TIMESTAMP);
CREATE TABLE JOB (ID INTEGER NOT NULL PRIMARY KEY, JOB_TYPE VARCHAR(20));
CREATE TABLE AUTHORITY (ID INTEGER NOT NULL PRIMARY KEY, AUTHORITY_TYPE INTEGER);
CREATE TABLE NO_ID (VALUE1 INTEGER, VALUE2 INTEGER);
CREATE TABLE OWNER_OF_NO_ID (ID INTEGER NOT NULL PRIMARY KEY, NO_ID_VALUE1 INTEGER);
CREATE TABLE CONSTRAINT_CHECKING (PRIMARY_KEY INTEGER PRIMARY KEY, UNIQUE_KEY INTEGER NOT NULL UNIQUE, FOREIGN_KEY INTEGER, CHECK_CONSTRAINT INTEGER, NOT_NULL INTEGER NOT NULL, CONSTRAINT CK_CONSTRAINT_CHECKING_1 CHECK (CHECK_CONSTRAINT > 0), CONSTRAINT FK_JOB_ID FOREIGN KEY (FOREIGN_KEY) REFERENCES JOB (ID));
CREATE TABLE PATTERN (VALUE VARCHAR(10));

CREATE TABLE ID_GENERATOR(PK VARCHAR(20) NOT NULL PRIMARY KEY, VALUE INTEGER NOT NULL);
CREATE TABLE MY_ID_GENERATOR(MY_PK VARCHAR(20) NOT NULL PRIMARY KEY, MY_VALUE INTEGER NOT NULL);
CREATE TABLE AUTO_STRATEGY(ID INTEGER NOT NULL IDENTITY, VALUE VARCHAR(10));
CREATE TABLE IDENTITY_STRATEGY(ID INTEGER NOT NULL IDENTITY, VALUE VARCHAR(10));
CREATE TABLE SEQUENCE_STRATEGY(ID INTEGER NOT NULL PRIMARY KEY, VALUE VARCHAR(10));
CREATE TABLE SEQUENCE_STRATEGY2(ID INTEGER NOT NULL PRIMARY KEY, VALUE VARCHAR(10));
CREATE TABLE TABLE_STRATEGY(ID INTEGER NOT NULL PRIMARY KEY, VALUE VARCHAR(10));
CREATE TABLE TABLE_STRATEGY2(ID INTEGER NOT NULL PRIMARY KEY, VALUE VARCHAR(10));
INSERT INTO MY_ID_GENERATOR VALUES('TableStrategy2', 1);