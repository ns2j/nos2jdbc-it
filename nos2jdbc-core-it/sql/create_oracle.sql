CREATE SEQUENCE AUTO_STRATEGY_ID INCREMENT BY 100 START WITH 1;
CREATE SEQUENCE SEQUENCE_STRATEGY_ID INCREMENT BY 100 START WITH 1;
CREATE SEQUENCE MY_SEQUENCE_STRATEGY_ID INCREMENT BY 100 START WITH 1;

CREATE TABLE DEPARTMENT(DEPARTMENT_ID NUMERIC(8) NOT NULL PRIMARY KEY, DEPARTMENT_NO NUMERIC(2) NOT NULL UNIQUE,DEPARTMENT_NAME VARCHAR2(20),LOCATION VARCHAR2(20) DEFAULT 'TOKYO', VERSION NUMERIC(8));
CREATE TABLE ADDRESS(ADDRESS_ID NUMERIC(8) NOT NULL PRIMARY KEY, STREET VARCHAR2(20), VERSION NUMERIC(8));
CREATE TABLE EMPLOYEE(EMPLOYEE_ID NUMERIC(8) NOT NULL PRIMARY KEY, EMPLOYEE_NO NUMERIC(4) NOT NULL ,EMPLOYEE_NAME VARCHAR2(20),MANAGER_ID NUMERIC(8),HIREDATE DATE,SALARY NUMERIC(7,2),DEPARTMENT_ID NUMERIC(8),ADDRESS_ID NUMERIC(8),VERSION NUMERIC(8), CONSTRAINT FK_DEPARTMENT_ID FOREIGN KEY(DEPARTMENT_ID) REFERENCES DEPARTMENT(DEPARTMENT_ID), CONSTRAINT FK_ADDRESS_ID FOREIGN KEY(ADDRESS_ID) REFERENCES ADDRESS(ADDRESS_ID));

CREATE TABLE COMP_KEY_DEPARTMENT(DEPARTMENT_ID1 NUMERIC(8) NOT NULL, DEPARTMENT_ID2 NUMERIC(8) NOT NULL, DEPARTMENT_NO NUMERIC(2) NOT NULL UNIQUE,DEPARTMENT_NAME VARCHAR2(20),LOCATION VARCHAR2(20) DEFAULT 'TOKYO', VERSION NUMERIC(8), CONSTRAINT PK_COMP_KEY_DEPARTMENT PRIMARY KEY(DEPARTMENT_ID1, DEPARTMENT_ID2));
CREATE TABLE COMP_KEY_ADDRESS(ADDRESS_ID1 NUMERIC(8) NOT NULL, ADDRESS_ID2 NUMERIC(8) NOT NULL, STREET VARCHAR2(20), VERSION NUMERIC(8), CONSTRAINT PK_COMP_KEY_ADDRESS PRIMARY KEY(ADDRESS_ID1, ADDRESS_ID2));
CREATE TABLE COMP_KEY_EMPLOYEE(EMPLOYEE_ID1 NUMERIC(8) NOT NULL, EMPLOYEE_ID2 NUMERIC(8) NOT NULL, EMPLOYEE_NO NUMERIC(4) NOT NULL ,EMPLOYEE_NAME VARCHAR2(20),MANAGER_ID1 NUMERIC(8),MANAGER_ID2 NUMERIC(8),HIREDATE DATE,SALARY NUMERIC(7,2),DEPARTMENT_ID1 NUMERIC(8),DEPARTMENT_ID2 NUMERIC(8),ADDRESS_ID1 NUMERIC(8),ADDRESS_ID2 NUMERIC(8),VERSION NUMERIC(8), CONSTRAINT PK_COMP_KEY_EMPLOYEE PRIMARY KEY(EMPLOYEE_ID1, EMPLOYEE_ID2), CONSTRAINT FK_COMP_KEY_DEPARTMENT_ID FOREIGN KEY(DEPARTMENT_ID1, DEPARTMENT_ID2) REFERENCES COMP_KEY_DEPARTMENT(DEPARTMENT_ID1, DEPARTMENT_ID2), CONSTRAINT FK_COMP_KEY_ADDRESS_ID FOREIGN KEY(ADDRESS_ID1, ADDRESS_ID2) REFERENCES COMP_KEY_ADDRESS(ADDRESS_ID1, ADDRESS_ID2));

CREATE TABLE LARGE_OBJECT(ID NUMERIC(8) NOT NULL PRIMARY KEY, NAME VARCHAR2(20), LARGE_NAME CLOB, BYTES RAW(2000), LARGE_BYTES BLOB, DTO RAW(2000), LARGE_DTO BLOB);
CREATE TABLE TENSE (ID NUMERIC PRIMARY KEY,DATE_DATE DATE, DATE_TIME DATE, DATE_TIMESTAMP TIMESTAMP, CAL_DATE DATE, CAL_TIME TIMESTAMP, CAL_TIMESTAMP DATE, SQL_DATE DATE, SQL_TIME TIMESTAMP, SQL_TIMESTAMP TIMESTAMP);
CREATE TABLE JOB (ID NUMERIC NOT NULL PRIMARY KEY, JOB_TYPE VARCHAR2(20));
CREATE TABLE AUTHORITY (ID NUMERIC NOT NULL PRIMARY KEY, AUTHORITY_TYPE INTEGER);
CREATE TABLE NO_ID (VALUE1 NUMERIC, VALUE2 NUMERIC);
CREATE TABLE OWNER_OF_NO_ID (ID NUMERIC NOT NULL PRIMARY KEY, NO_ID_VALUE1 NUMERIC);
CREATE TABLE CONSTRAINT_CHECKING (PRIMARY_KEY NUMERIC PRIMARY KEY, UNIQUE_KEY NUMERIC UNIQUE, FOREIGN_KEY NUMERIC, CHECK_CONSTRAINT NUMERIC, NOT_NULL NUMERIC NOT NULL, CONSTRAINT CK_CONSTRAINT_CHECKING_1 CHECK (CHECK_CONSTRAINT > 0), CONSTRAINT FK_JOB_ID FOREIGN KEY (FOREIGN_KEY) REFERENCES JOB (ID));
CREATE TABLE PATTERN (VALUE VARCHAR2(10));

CREATE TABLE ID_GENERATOR(PK VARCHAR2(20) NOT NULL PRIMARY KEY, VALUE INTEGER NOT NULL);
CREATE TABLE MY_ID_GENERATOR(MY_PK VARCHAR2(20) NOT NULL PRIMARY KEY, MY_VALUE INTEGER NOT NULL);
CREATE TABLE AUTO_STRATEGY(ID NUMERIC(8) PRIMARY KEY, VALUE VARCHAR2(10));
CREATE TABLE IDENTITY_STRATEGY(ID NUMERIC(8) PRIMARY KEY, VALUE VARCHAR2(10));
CREATE TABLE SEQUENCE_STRATEGY(ID NUMERIC(8) NOT NULL PRIMARY KEY, VALUE VARCHAR2(10));
CREATE TABLE SEQUENCE_STRATEGY2(ID NUMERIC(8) NOT NULL PRIMARY KEY, VALUE VARCHAR2(10));
CREATE TABLE TABLE_STRATEGY(ID NUMERIC(8) NOT NULL PRIMARY KEY, VALUE VARCHAR2(10));
CREATE TABLE TABLE_STRATEGY2(ID NUMERIC(8) NOT NULL PRIMARY KEY, VALUE VARCHAR2(10));

CREATE INDEX IX_EMPLOYEE_1 ON EMPLOYEE (EMPLOYEE_NAME);

INSERT INTO DEPARTMENT VALUES(1,10,'ACCOUNTING','NEW YORK',1);
INSERT INTO DEPARTMENT VALUES(2,20,'RESEARCH','DALLAS',1);
INSERT INTO DEPARTMENT VALUES(3,30,'SALES','CHICAGO',1);
INSERT INTO DEPARTMENT VALUES(4,40,'OPERATIONS','BOSTON',1);
INSERT INTO ADDRESS VALUES(1,'STREET 1',1);
INSERT INTO ADDRESS VALUES(2,'STREET 2',1);
INSERT INTO ADDRESS VALUES(3,'STREET 3',1);
INSERT INTO ADDRESS VALUES(4,'STREET 4',1);
INSERT INTO ADDRESS VALUES(5,'STREET 5',1);
INSERT INTO ADDRESS VALUES(6,'STREET 6',1);
INSERT INTO ADDRESS VALUES(7,'STREET 7',1);
INSERT INTO ADDRESS VALUES(8,'STREET 8',1);
INSERT INTO ADDRESS VALUES(9,'STREET 9',1);
INSERT INTO ADDRESS VALUES(10,'STREET 10',1);
INSERT INTO ADDRESS VALUES(11,'STREET 11',1);
INSERT INTO ADDRESS VALUES(12,'STREET 12',1);
INSERT INTO ADDRESS VALUES(13,'STREET 13',1);
INSERT INTO ADDRESS VALUES(14,'STREET 14',1);
INSERT INTO EMPLOYEE VALUES(1,7369,'SMITH',13,TO_DATE('1980-12-17','YYYY-MM-DD'),800,2,1,1);
INSERT INTO EMPLOYEE VALUES(2,7499,'ALLEN',6,TO_DATE('1981-02-20','YYYY-MM-DD'),1600,3,2,1);
INSERT INTO EMPLOYEE VALUES(3,7521,'WARD',6,TO_DATE('1981-02-22','YYYY-MM-DD'),1250,3,3,1);
INSERT INTO EMPLOYEE VALUES(4,7566,'JONES',9,TO_DATE('1981-04-02','YYYY-MM-DD'),2975,2,4,1);
INSERT INTO EMPLOYEE VALUES(5,7654,'MARTIN',6,TO_DATE('1981-09-28','YYYY-MM-DD'),1250,3,5,1);
INSERT INTO EMPLOYEE VALUES(6,7698,'BLAKE',9,TO_DATE('1981-05-01','YYYY-MM-DD'),2850,3,6,1);
INSERT INTO EMPLOYEE VALUES(7,7782,'CLARK',9,TO_DATE('1981-06-09','YYYY-MM-DD'),2450,1,7,1);
INSERT INTO EMPLOYEE VALUES(8,7788,'SCOTT',4,TO_DATE('1982-12-09','YYYY-MM-DD'),3000.0,2,8,1);
INSERT INTO EMPLOYEE VALUES(9,7839,'KING',NULL,TO_DATE('1981-11-17','YYYY-MM-DD'),5000,1,9,1);
INSERT INTO EMPLOYEE VALUES(10,7844,'TURNER',6,TO_DATE('1981-09-08','YYYY-MM-DD'),1500,3,10,1);
INSERT INTO EMPLOYEE VALUES(11,7876,'ADAMS',8,TO_DATE('1983-01-12','YYYY-MM-DD'),1100,2,11,1);
INSERT INTO EMPLOYEE VALUES(12,7900,'JAMES',6,TO_DATE('1981-12-03','YYYY-MM-DD'),950,3,12,1);
INSERT INTO EMPLOYEE VALUES(13,7902,'FORD',4,TO_DATE('1981-12-03','YYYY-MM-DD'),3000,2,13,1);
INSERT INTO EMPLOYEE VALUES(14,7934,'MILLER',7,TO_DATE('1982-01-23','YYYY-MM-DD'),1300,1,14,1);

INSERT INTO COMP_KEY_DEPARTMENT VALUES(1,1,10,'ACCOUNTING','NEW YORK',1);
INSERT INTO COMP_KEY_DEPARTMENT VALUES(2,2,20,'RESEARCH','DALLAS',1);
INSERT INTO COMP_KEY_DEPARTMENT VALUES(3,3,30,'SALES','CHICAGO',1);
INSERT INTO COMP_KEY_DEPARTMENT VALUES(4,4,40,'OPERATIONS','BOSTON',1);
INSERT INTO COMP_KEY_ADDRESS VALUES(1,1,'STREET 1',1);
INSERT INTO COMP_KEY_ADDRESS VALUES(2,2,'STREET 2',1);
INSERT INTO COMP_KEY_ADDRESS VALUES(3,3,'STREET 3',1);
INSERT INTO COMP_KEY_ADDRESS VALUES(4,4,'STREET 4',1);
INSERT INTO COMP_KEY_ADDRESS VALUES(5,5,'STREET 5',1);
INSERT INTO COMP_KEY_ADDRESS VALUES(6,6,'STREET 6',1);
INSERT INTO COMP_KEY_ADDRESS VALUES(7,7,'STREET 7',1);
INSERT INTO COMP_KEY_ADDRESS VALUES(8,8,'STREET 8',1);
INSERT INTO COMP_KEY_ADDRESS VALUES(9,9,'STREET 9',1);
INSERT INTO COMP_KEY_ADDRESS VALUES(10,10,'STREET 10',1);
INSERT INTO COMP_KEY_ADDRESS VALUES(11,11,'STREET 11',1);
INSERT INTO COMP_KEY_ADDRESS VALUES(12,12,'STREET 12',1);
INSERT INTO COMP_KEY_ADDRESS VALUES(13,13,'STREET 13',1);
INSERT INTO COMP_KEY_ADDRESS VALUES(14,14,'STREET 14',1);
INSERT INTO COMP_KEY_EMPLOYEE VALUES(1,1,7369,'SMITH',13,13,'1980-12-17',800,2,2,1,1,1);
INSERT INTO COMP_KEY_EMPLOYEE VALUES(2,2,7499,'ALLEN',6,6,'1981-02-20',1600,3,3,2,2,1);
INSERT INTO COMP_KEY_EMPLOYEE VALUES(3,3,7521,'WARD',6,6,'1981-02-22',1250,3,3,3,3,1);
INSERT INTO COMP_KEY_EMPLOYEE VALUES(4,4,7566,'JONES',9,9,'1981-04-02',2975,2,2,4,4,1);
INSERT INTO COMP_KEY_EMPLOYEE VALUES(5,5,7654,'MARTIN',6,6,'1981-09-28',1250,3,3,5,5,1);
INSERT INTO COMP_KEY_EMPLOYEE VALUES(6,6,7698,'BLAKE',9,9,'1981-05-01',2850,3,3,6,6,1);
INSERT INTO COMP_KEY_EMPLOYEE VALUES(7,7,7782,'CLARK',9,9,'1981-06-09',2450,1,1,7,7,1);
INSERT INTO COMP_KEY_EMPLOYEE VALUES(8,8,7788,'SCOTT',4,4,'1982-12-09',3000.0,2,2,8,8,1);
INSERT INTO COMP_KEY_EMPLOYEE VALUES(9,9,7839,'KING',NULL,NULL,'1981-11-17',5000,1,1,9,9,1);
INSERT INTO COMP_KEY_EMPLOYEE VALUES(10,10,7844,'TURNER',6,6,'1981-09-08',1500,3,3,10,10,1);
INSERT INTO COMP_KEY_EMPLOYEE VALUES(11,11,7876,'ADAMS',8,8,'1983-01-12',1100,2,2,11,11,1);
INSERT INTO COMP_KEY_EMPLOYEE VALUES(12,12,7900,'JAMES',6,6,'1981-12-03',950,3,3,12,12,1);
INSERT INTO COMP_KEY_EMPLOYEE VALUES(13,13,7902,'FORD',4,4,'1981-12-03',3000,2,2,13,13,1);
INSERT INTO COMP_KEY_EMPLOYEE VALUES(14,14,7934,'MILLER',7,7,'1982-01-23',1300,1,1,14,14,1);

INSERT INTO TENSE VALUES (1, TO_DATE('2005/02/14', 'YYYY/MM/DD'), TO_DATE('1970/01/01 12:11:10', 'YYYY/MM/DD HH24:MI:SS'), TO_DATE('2005/02/14 12:11:10', 'YYYY/MM/DD HH24:MI:SS'), TO_DATE('2005/02/14', 'YYYY/MM/DD'), TO_DATE('1970/01/01 12:11:10', 'YYYY/MM/DD HH24:MI:SS'), TO_DATE('2005/02/14 12:11:10', 'YYYY/MM/DD HH24:MI:SS'), TO_DATE('2005/02/14', 'YYYY/MM/DD'), TO_DATE('1970/01/01 12:11:10', 'YYYY/MM/DD HH24:MI:SS'), TO_DATE('2005/02/14 12:11:10', 'YYYY/MM/DD HH24:MI:SS'));
INSERT INTO JOB VALUES (1, 'SALESMAN');
INSERT INTO JOB VALUES (2, 'MANAGER');
INSERT INTO JOB VALUES (3, 'PRESIDENT');
INSERT INTO AUTHORITY VALUES (1, 10);
INSERT INTO AUTHORITY VALUES (2, 20);
INSERT INTO AUTHORITY VALUES (3, 30);
INSERT INTO NO_ID VALUES (1, 1);
INSERT INTO NO_ID VALUES (1, 1);

INSERT INTO ID_GENERATOR VALUES('TABLE_STRATEGY_ID', 1);
INSERT INTO MY_ID_GENERATOR VALUES('TableStrategy2', 1);

CREATE OR REPLACE PROCEDURE PROC_NONE_PARAM AS
BEGIN
  NULL;
END PROC_NONE_PARAM;
/

CREATE OR REPLACE PROCEDURE PROC_SIMPLETYPE_PARAM
( param1 IN NUMBER
) AS
BEGIN
  NULL;
END PROC_SIMPLETYPE_PARAM;
/

CREATE OR REPLACE PROCEDURE PROC_SIMPLETYPE_TIME_PARAM
( param1 IN TIMESTAMP
) AS
BEGIN
  NULL;
END PROC_SIMPLETYPE_TIME_PARAM;
/

CREATE OR REPLACE PROCEDURE PROC_DTO_PARAM
( param1 IN NUMBER
, param2 IN OUT NUMBER
, param3 OUT NUMBER
) AS
BEGIN
  param2 := param2 + param1;
  param3 := param1;
  NULL;
END PROC_DTO_PARAM;
/

CREATE OR REPLACE PROCEDURE PROC_DTO_TIME_PARAM
( param1 IN TIMESTAMP
, param2 IN OUT TIMESTAMP
, param3 OUT TIMESTAMP
) AS
BEGIN
  param2 := param1;
  param3 := param1;
  NULL;
END PROC_DTO_TIME_PARAM;
/

CREATE OR REPLACE PROCEDURE PROC_RESULTSET 
( cur OUT SYS_REFCURSOR, 
  employeeId IN NUMERIC
) AS
BEGIN
  OPEN cur FOR SELECT EMPLOYEE_NO, EMPLOYEE_NAME FROM EMPLOYEE WHERE employee_id > employeeId ORDER BY employee_id; 
END PROC_RESULTSET;
/

CREATE OR REPLACE PROCEDURE PROC_RESULTSET_OUT
( cur OUT SYS_REFCURSOR, 
  employeeId IN NUMERIC,
  employeeCount OUT NUMERIC
) AS
BEGIN
  OPEN cur FOR SELECT * FROM EMPLOYEE WHERE employee_id > employeeId ORDER BY employee_id;
  SELECT COUNT(*) INTO employeeCount FROM EMPLOYEE;
END PROC_RESULTSET_OUT;
/

CREATE OR REPLACE PROCEDURE PROC_RESULTSET_UPDATE
( cur OUT SYS_REFCURSOR, 
  employeeId IN NUMERIC
) AS
BEGIN
  OPEN cur FOR SELECT * FROM EMPLOYEE WHERE employee_id > employeeId ORDER BY employee_id;
  UPDATE DEPARTMENT SET department_name = 'HOGE' WHERE department_id = 1;
END PROC_RESULTSET_UPDATE;
/

CREATE OR REPLACE PROCEDURE PROC_RESULTSET_UPDATE2
( cur OUT SYS_REFCURSOR, 
  employeeId IN NUMERIC
) AS
BEGIN
  UPDATE DEPARTMENT SET department_name = 'HOGE' WHERE department_id = 1;
  OPEN cur FOR SELECT * FROM EMPLOYEE WHERE employee_id > employeeId ORDER BY employee_id;
END PROC_RESULTSET_UPDATE2;
/

CREATE OR REPLACE PROCEDURE PROC_RESULTSETS
( empCur OUT SYS_REFCURSOR, 
  deptCur OUT SYS_REFCURSOR, 
  employeeId IN NUMERIC, 
  departmentId IN NUMERIC
) AS
BEGIN
  OPEN empCur FOR SELECT * FROM EMPLOYEE WHERE employee_id > employeeId ORDER BY employee_id;  
  OPEN deptCur FOR SELECT * FROM DEPARTMENT WHERE department_id > departmentId ORDER BY department_id;
END PROC_RESULTSETS;
/

CREATE OR REPLACE PROCEDURE PROC_RESULTSETS_UPDATES_OUT
( empCur OUT SYS_REFCURSOR, 
  deptCur OUT SYS_REFCURSOR, 
  employeeId IN NUMERIC, 
  departmentId IN NUMERIC,
  employeeCount OUT NUMERIC
) AS
BEGIN
  OPEN empCur FOR SELECT * FROM EMPLOYEE WHERE employee_id > employeeId ORDER BY employee_id;
  UPDATE ADDRESS SET STREET = 'HOGE' WHERE address_id = 1;
  OPEN deptCur FOR SELECT * FROM DEPARTMENT WHERE department_id > departmentId ORDER BY department_id;
  UPDATE ADDRESS SET STREET = 'FOO' WHERE address_id = 2;
  SELECT COUNT(*) INTO employeeCount FROM EMPLOYEE;
END PROC_RESULTSETS_UPDATES_OUT;
/


CREATE OR REPLACE FUNCTION FUNC_NONE_PARAM 
RETURN NUMBER
AS
BEGIN
  RETURN 10;
END FUNC_NONE_PARAM;
/

CREATE OR REPLACE FUNCTION FUNC_SIMPLETYPE_PARAM
( param1 IN NUMBER )
RETURN NUMBER
AS
BEGIN
  RETURN 20;
END FUNC_SIMPLETYPE_PARAM;
/

CREATE OR REPLACE FUNCTION FUNC_SIMPLETYPE_TIME_PARAM
( param1 IN TIMESTAMP )
RETURN TIMESTAMP
AS
BEGIN
  RETURN param1;
END FUNC_SIMPLETYPE_TIME_PARAM;
/

CREATE OR REPLACE FUNCTION FUNC_DTO_PARAM
( param1 IN NUMBER
, param2 IN NUMBER) 
RETURN NUMBER
AS
BEGIN
  RETURN param2 + param1;
END FUNC_DTO_PARAM;
/

CREATE OR REPLACE FUNCTION FUNC_DTO_TIME_PARAM
( param1 IN TIMESTAMP
, param2 IN NUMBER) 
RETURN TIMESTAMP
AS
BEGIN
  RETURN param1;
END FUNC_DTO_TIME_PARAM;
/

CREATE OR REPLACE FUNCTION FUNC_RESULTSET 
( employeeId IN NUMERIC ) 
RETURN SYS_REFCURSOR
AS
  cur SYS_REFCURSOR;
BEGIN
  OPEN cur FOR SELECT * FROM EMPLOYEE WHERE employee_id > employeeId ORDER BY employee_id;
  RETURN cur;
END FUNC_RESULTSET;
/

CREATE OR REPLACE FUNCTION FUNC_RESULTSET_UPDATE
( employeeId IN NUMERIC ) 
RETURN SYS_REFCURSOR
AS
  cur SYS_REFCURSOR;
BEGIN
  OPEN cur FOR SELECT * FROM EMPLOYEE WHERE employee_id > employeeId ORDER BY employee_id;
  UPDATE DEPARTMENT SET department_name = 'HOGE' WHERE department_id = 1;
  RETURN cur;
END FUNC_RESULTSET_UPDATE;
/

CREATE OR REPLACE FUNCTION FUNC_RESULTSET_UPDATE2
( employeeId IN NUMERIC ) 
RETURN SYS_REFCURSOR
AS
  cur SYS_REFCURSOR;
BEGIN
  UPDATE DEPARTMENT SET department_name = 'HOGE' WHERE department_id = 1;
  OPEN cur FOR SELECT * FROM EMPLOYEE WHERE employee_id > employeeId ORDER BY employee_id;
  RETURN cur;
END FUNC_RESULTSET_UPDATE2;
/

COMMIT;