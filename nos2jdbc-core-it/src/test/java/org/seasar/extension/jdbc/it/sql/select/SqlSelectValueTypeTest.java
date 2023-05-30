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
package org.seasar.extension.jdbc.it.sql.select;

import static org.junit.jupiter.api.Assertions.*;
import static org.seasar.extension.jdbc.parameter.Parameter.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.extension.jdbc.it.entity.Tense;

import jakarta.persistence.TemporalType;
import nos2jdbc.core.it.NoS2JdbcExtension;

/**
 * @author taedium
 * 
 */
@ExtendWith(NoS2JdbcExtension.class)
class SqlSelectValueTypeTest {

    private JdbcManager jdbcManager;

    /** @throws ParseException */
    @Test
    void bean_temporalType() throws ParseException  {
        String sql = "SELECT * FROM TENSE WHERE ID = 1";
        Tense tense = jdbcManager.selectBySql(Tense.class, sql).getSingleResult();
        long date = new SimpleDateFormat("yyyy-MM-dd").parse("2005-02-14").getTime();
        long time = new SimpleDateFormat("HH:mm:ss").parse("12:11:10").getTime();
        long timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2005-02-14 12:11:10").getTime();
        LocalDate ld = LocalDate.of(2005, 2, 14);
        LocalTime lt = LocalTime.of(12, 11, 10); 
        LocalDateTime ldt = LocalDateTime.of(ld, lt);
        OffsetDateTime odt = OffsetDateTime.of(ldt, ZoneOffset.UTC);
        assertNotNull(tense);
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

    /** @throws ParseException */
    @Test
    void bean_temporalType_Calendar() throws ParseException  {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new SimpleDateFormat("yyyy-MM-dd").parse("2005-02-14"));
        Tense tense = jdbcManager.selectBySql(Tense.class, "SELECT * FROM TENSE WHERE CAL_DATE = ?", date(calendar)).getSingleResult();
        assertNotNull(tense);
        calendar.setTime(new SimpleDateFormat("HH:mm:ss").parse("12:11:10"));
        tense = jdbcManager.selectBySql(Tense.class, "SELECT * FROM TENSE WHERE CAL_TIME = ?", time(calendar)).getSingleResult();
        assertNotNull(tense);
        calendar.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2005-02-14 12:11:10"));
        tense = jdbcManager.selectBySql(Tense.class, "SELECT * FROM TENSE WHERE CAL_TIMESTAMP = ?", timestamp(calendar)).getSingleResult();
        assertNotNull(tense);
    }

    /** @throws ParseException  */
    @Test
    void bean_temporalType_Date() throws ParseException  {
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2005-02-14");
        Tense tense = jdbcManager.selectBySql(Tense.class, "SELECT * FROM TENSE WHERE DATE_DATE = ?", date(date)).getSingleResult();
        assertNotNull(tense);
        date = new SimpleDateFormat("HH:mm:ss").parse("12:11:10");
        tense = jdbcManager.selectBySql(Tense.class, "SELECT * FROM TENSE WHERE DATE_TIME = ?", time(date)).getSingleResult();
        assertNotNull(tense);
        date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2005-02-14 12:11:10");
        tense = jdbcManager.selectBySql(Tense.class, "SELECT * FROM TENSE WHERE DATE_TIMESTAMP = ?", timestamp(date)).getSingleResult();
        assertNotNull(tense);
    }

    /**  */
    @Test
    void bean_temporalType_Jdbc42() throws ParseException  {
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2005-02-14");
        Tense tense = jdbcManager.selectBySql(Tense.class, "SELECT * FROM TENSE WHERE LOCAL_DATE = ?", date(date)).getSingleResult();
        assertNotNull(tense);
        date = new SimpleDateFormat("HH:mm:ss").parse("12:11:10");
        tense = jdbcManager.selectBySql(Tense.class, "SELECT * FROM TENSE WHERE LOCAL_TIME = ?", time(date)).getSingleResult();
        assertNotNull(tense);
        date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2005-02-14 12:11:10");
        tense = jdbcManager.selectBySql(Tense.class, "SELECT * FROM TENSE WHERE LOCAL_DATE_TIME = ?", timestamp(date)).getSingleResult();
        assertNotNull(tense);
    }

    /**  */
    @Test
    void map_temporalType()  {
        String sql = "SELECT * FROM TENSE WHERE ID = 1";
        Map<?, ?> tense = jdbcManager.selectBySql(Map.class, sql).getSingleResult();
        assertNotNull(tense);
        assertNotNull(tense.get("calDate"));
        assertNotNull(tense.get("dateDate"));
        assertNotNull(tense.get("sqlDate"));
        assertNotNull(tense.get("calTime"));
        assertNotNull(tense.get("dateTime"));
        assertNotNull(tense.get("sqlTime"));
        assertNotNull(tense.get("calTimestamp"));
        assertNotNull(tense.get("dateTimestamp"));
        assertNotNull(tense.get("sqlTimestamp"));
        assertNotNull(tense.get("localDate"));
        assertNotNull(tense.get("localTime"));
        assertNotNull(tense.get("localDateTime"));
        assertNotNull(tense.get("offsetDateTime"));
    }

    /** @throws ParseException  */
    @Test
    void object_temporalType() throws ParseException   {
        String sql = "SELECT CAL_TIMESTAMP FROM TENSE WHERE ID = 1";
        Calendar calTimestamp = jdbcManager.selectBySql(Calendar.class, sql).temporal(TemporalType.TIMESTAMP).getSingleResult();
        assertNotNull(calTimestamp);
        long time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2005-02-14 12:11:10").getTime();
        assertEquals(time, calTimestamp.getTimeInMillis());
    }
}
