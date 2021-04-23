/*
 * Copyright 2004-2015 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.seasar.extension.jdbc.it.sql;

import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.EntityExistsException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.extension.jdbc.it.entity.Tense;
import nos2jdbc.core.it.NoS2JdbcExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.seasar.extension.jdbc.parameter.Parameter.*;

/**
 * @author taedium
 * 
 */
@ExtendWith(NoS2JdbcExtension.class)
class SqlUpdateTest {

    private JdbcManager jdbcManager;

    /**
     */
    @Test
    void parameter_none()  {
        String sql = "DELETE FROM EMPLOYEE";
        int actual = jdbcManager.updateBySql(sql).execute();
        assertEquals(14, actual);
    }

    /**
     */
    @Test
    void parameter()  {
        String sql = "DELETE FROM EMPLOYEE WHERE DEPARTMENT_ID = ? AND SALARY > ?";
        int actual = jdbcManager.updateBySql(sql, int.class, BigDecimal.class).params(3, new BigDecimal(1000)).execute();
        assertEquals(5, actual);
    }

    /**
     */
    @Test
    void entityExistsException_insert()  {
        String sql = "INSERT INTO DEPARTMENT (DEPARTMENT_ID, DEPARTMENT_NO) VALUES(?, ?)";
        assertThrows(EntityExistsException.class, () -> jdbcManager.updateBySql(sql, int.class, int.class).params(1, 50).execute());
    }

    /**
     */
    @Test
    void entityExistsException_update()  {
        jdbcManager.updateBySql("INSERT INTO DEPARTMENT (DEPARTMENT_ID, DEPARTMENT_NO) VALUES (99, 99)").execute();
        String sql = "UPDATE DEPARTMENT SET DEPARTMENT_ID = ? WHERE DEPARTMENT_ID = ?";
        assertThrows(EntityExistsException.class, () -> jdbcManager.updateBySql(sql, int.class, int.class).params(1, 99).execute());
    }

    /**
     * @throws ParseException 
     */
    @Test
    void temporalType() throws ParseException  {
	//String sql = "UPDATE TENSE SET DATE_DATE = ?, DATE_TIME = ?, DATE_TIMESTAMP = ?, CAL_DATE = ?, CAL_TIME = ?, CAL_TIMESTAMP = ?, SQL_DATE = ?, SQL_TIME = ?, SQL_TIMESTAMP = ? WHERE ID = ?";
        String sql = "UPDATE TENSE SET DATE_DATE = ?, DATE_TIME = ?, DATE_TIMESTAMP = ?, CAL_DATE = ?, CAL_TIME = ?, CAL_TIMESTAMP = ?,"
                + " SQL_DATE = ?, SQL_TIME = ?, SQL_TIMESTAMP = ?,"
                + " LOCAL_DATE = ?, LOCAL_TIME = ?, LOCAL_DATE_TIME = ?, OFFSET_DATE_TIME = ?"
                + " WHERE ID = ?";
        long date = new SimpleDateFormat("yyyy-MM-dd").parse("2005-03-14").getTime();
        long time = new SimpleDateFormat("HH:mm:ss").parse("13:11:10").getTime();
        long timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2005-03-14 13:11:10").getTime();
        Calendar calDate = Calendar.getInstance();
        calDate.setTimeInMillis(date);
        Calendar calTime = Calendar.getInstance();
        calTime.setTimeInMillis(time);
        Calendar calTimestamp = Calendar.getInstance();
        calTimestamp.setTimeInMillis(timestamp);
        LocalDate ld = LocalDate.of(2005, 3, 14);
        LocalTime lt = LocalTime.of(13, 11, 10); 
        LocalDateTime ldt = LocalDateTime.of(ld, lt);
        OffsetDateTime odt = OffsetDateTime.of(ldt, ZoneOffset.UTC);
        jdbcManager.updateBySql(sql, Date.class, Date.class, Date.class, Calendar.class, Calendar.class, Calendar.class,
        	java.sql.Date.class, Time.class, Timestamp.class,
        	LocalDate.class, LocalTime.class, LocalDateTime.class, OffsetDateTime.class,
        	int.class).params(date(new Date(date)), time(new Date(time)), timestamp(new Date(timestamp)),
                calDate, calTime, calTimestamp,
                new java.sql.Date(date), new Time(time), new Timestamp(timestamp),
                ld, lt, ldt, odt, 
                1).execute();
        Tense tense = jdbcManager.selectBySql(Tense.class, "SELECT DATE_DATE, DATE_TIME, DATE_TIMESTAMP, CAL_DATE, CAL_TIME, CAL_TIMESTAMP,"
        	+ " SQL_DATE, SQL_TIME, SQL_TIMESTAMP,"
        	+ " LOCAL_DATE, LOCAL_TIME, LOCAL_DATE_TIME, OFFSET_DATE_TIME"
        	+ " FROM TENSE "
        	+ " WHERE ID = 1").getSingleResult();
        assertEquals(date, tense.calDate.getTimeInMillis());
        assertEquals(date, tense.dateDate.getTime());
        assertEquals(date, tense.sqlDate.getTime());
        assertEquals(time, tense.calTime.getTimeInMillis());
        assertEquals(time, tense.dateTime.getTime());
        assertEquals(time, tense.sqlTime.getTime());
        assertEquals(timestamp, tense.calTimestamp.getTimeInMillis());
        assertEquals(timestamp, tense.dateTimestamp.getTime());
        assertEquals(timestamp, tense.sqlTimestamp.getTime());
        assertEquals(ld, tense.localDate);
        assertEquals(lt, tense.localTime);
        assertEquals(ldt, tense.localDateTime);
        assertEquals(odt, tense.offsetDateTime);
    }
}
