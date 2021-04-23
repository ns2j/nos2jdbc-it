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
package org.seasar.extension.jdbc.it.auto.select;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.extension.jdbc.it.entity.Authority;
import org.seasar.extension.jdbc.it.entity.AuthorityType;
import org.seasar.extension.jdbc.it.entity.Job;
import org.seasar.extension.jdbc.it.entity.JobType;
import org.seasar.extension.jdbc.it.entity.Tense;
import org.seasar.extension.jdbc.where.SimpleWhere;
import nos2jdbc.core.it.NoS2JdbcExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.seasar.extension.jdbc.parameter.Parameter.*;

/**
 * @author taedium
 * 
 */
@ExtendWith(NoS2JdbcExtension.class)
class AutoSelectValueTypeTest {

    private JdbcManager jdbcManager;

    private long clearTime(long time) {
        //for oracle date
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(time);
        cal.clear(Calendar.AM_PM);
        cal.clear(Calendar.HOUR);
        cal.clear(Calendar.HOUR_OF_DAY);
        cal.clear(Calendar.MINUTE);
        cal.clear(Calendar.SECOND);
        cal.clear(Calendar.MILLISECOND);
        return cal.getTimeInMillis();
    }

    @Test
    void testTemporalType()  {
        Tense tense = jdbcManager.from(Tense.class).where("id = ?", 1).getSingleResult();
        assertEquals(tense.sqlDate.getTime(), tense.calDate.getTimeInMillis());
        assertEquals(tense.sqlDate.getTime(), tense.dateDate.getTime());
        assertEquals(tense.sqlTime.getTime(), tense.calTime.getTimeInMillis());
        assertEquals(tense.sqlTime.getTime(), tense.dateTime.getTime());
        assertEquals(tense.sqlTimestamp.getTime(), tense.calTimestamp.getTimeInMillis());
        assertEquals(tense.sqlTimestamp.getTime(), tense.dateTimestamp.getTime());
    }

    /**
     * 
     * @throws ParseException 
     */
    @Test
    void testTemporalTypeCalendar_criteria() throws ParseException  {
        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2005-02-14 12:11:10");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Tense tense = jdbcManager.from(Tense.class).where("calDate = ?", date(calendar)).getSingleResult();
        assertNotNull(tense);
        tense = jdbcManager.from(Tense.class).where("calTime = ?", time(calendar)).getSingleResult();
        assertNotNull(tense);
        tense = jdbcManager.from(Tense.class).where("calTimestamp = ?", timestamp(calendar)).getSingleResult();
        assertNotNull(tense);
    }

    /**
     * 
     * @throws ParseException 
     */
    @Test
    void testTemporalTypeCalendar_simpleWhere() throws ParseException  {
        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2005-02-14 12:11:10");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Tense tense = jdbcManager.from(Tense.class).where(new SimpleWhere().eq("calDate", calendar)).getSingleResult();
        assertNotNull(tense);
        tense = jdbcManager.from(Tense.class).where(new SimpleWhere().eq("calTime", calendar)).getSingleResult();
        assertNotNull(tense);
        tense = jdbcManager.from(Tense.class).where(new SimpleWhere().eq("calTimestamp", calendar)).getSingleResult();
        assertNotNull(tense);
    }

    /**
     * 
     * @throws ParseException 
     */
    @Test
    void testTemporalTypeDate_criteria() throws ParseException  {
        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2005-02-14 12:11:10");
        Tense tense = jdbcManager.from(Tense.class).where("dateDate = ?", date(date)).getSingleResult();
        assertNotNull(tense);
        tense = jdbcManager.from(Tense.class).where("dateTime = ?", time(date)).getSingleResult();
        assertNotNull(tense);
        tense = jdbcManager.from(Tense.class).where("dateTimestamp = ?", timestamp(date)).getSingleResult();
        assertNotNull(tense);
    }

    /**
     * 
     * @throws ParseException 
     */
    @Test
    void testTemporalTypeDate_simpleWhere() throws ParseException  {
        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2005-02-14 12:11:10");
        Tense tense = jdbcManager.from(Tense.class).where(new SimpleWhere().eq("dateDate", date)).getSingleResult();
        assertNotNull(tense);
        tense = jdbcManager.from(Tense.class).where(new SimpleWhere().eq("dateTime", date)).getSingleResult();
        assertNotNull(tense);
        tense = jdbcManager.from(Tense.class).where(new SimpleWhere().eq("dateTimestamp", date)).getSingleResult();
        assertNotNull(tense);
    }

    /**
     * 
     * @throws ParseException 
     */
    @Test
    void testTemporalTypeSql_criteria() throws ParseException  {
        long time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2005-02-14 12:11:10").getTime();
        Tense tense = jdbcManager.from(Tense.class).where("sqlDate = ?", //                new java.sql.Date(time)).getSingleResult();
        new java.sql.Date(clearTime(time))).getSingleResult();
        assertNotNull(tense);
        tense = jdbcManager.from(Tense.class).where("sqlTime = ?", Time.valueOf("12:11:10")).getSingleResult();
        assertNotNull(tense);
        tense = jdbcManager.from(Tense.class).where("sqlTimestamp = ?", new Timestamp(time)).getSingleResult();
        assertNotNull(tense);
    }

    /**
     * 
     * @throws ParseException 
     */
    @Test
    void testTemporalTypeSql_simpleWhere() throws ParseException  {
        long time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2005-02-14 12:11:10").getTime();
        Tense tense = jdbcManager.from(Tense.class).where(new SimpleWhere().eq("sqlDate", new java.sql.Date(clearTime(time)))).getSingleResult();
        assertNotNull(tense);
        tense = jdbcManager.from(Tense.class).where(new SimpleWhere().eq("sqlTime", Time.valueOf("12:11:10"))).getSingleResult();
        assertNotNull(tense);
        tense = jdbcManager.from(Tense.class).where(new SimpleWhere().eq("sqlTimestamp", new Timestamp(time))).getSingleResult();
        assertNotNull(tense);
    }

    @Test
    void testLocalDateTypeSql() {
        LocalDate localDate = LocalDate.of(2005, 2, 14); 
        LocalDate expected = localDate;

        Tense tense = jdbcManager.from(Tense.class).where("id = ?", 1).getSingleResult();
        assertEquals(expected, tense.localDate);

        tense = jdbcManager.from(Tense.class).where("localDate = ?", localDate).getSingleResult();
        assertEquals(expected, tense.localDate);

        tense = jdbcManager.from(Tense.class).where(new SimpleWhere().eq("localDate", localDate)).getSingleResult();
        assertEquals(expected, tense.localDate);
    }
    @Test
    void testLocalTimeTypeSql() {
        LocalTime localTime = LocalTime.of(12, 11, 10);
        LocalTime expected = localTime;

        Tense tense = jdbcManager.from(Tense.class).where("id = ?", 1).getSingleResult();
        assertEquals(expected, tense.localTime);

        tense = jdbcManager.from(Tense.class).where("localTime = ?", localTime).getSingleResult();
        assertEquals(expected, tense.localTime);

        tense = jdbcManager.from(Tense.class).where(new SimpleWhere().eq("localTime", localTime)).getSingleResult();
        assertEquals(expected, tense.localTime);
    }
    @Test
    void testOffsetDateTimeTypeSql() {
        OffsetDateTime offsetDateTime = OffsetDateTime.of(LocalDateTime.of(2005, 2, 14, 12, 11, 10), ZoneOffset.UTC);
        OffsetDateTime expected = offsetDateTime;

        Tense tense = jdbcManager.from(Tense.class).where("id = ?", 1).getSingleResult();
        assertEquals(expected, tense.offsetDateTime);

        tense = jdbcManager.from(Tense.class).where("offsetDateTime = ?", offsetDateTime).getSingleResult();
        assertEquals(expected, tense.offsetDateTime);

        tense = jdbcManager.from(Tense.class).where(new SimpleWhere().eq("offsetDateTime", offsetDateTime)).getSingleResult();
        assertEquals(expected, tense.offsetDateTime);
    }
    @Test
    void testOffsetDateTimeTypeSql_equalAfterUpdate() {
        OffsetDateTime offsetDateTime = OffsetDateTime.of(LocalDateTime.of(2005, 2, 14, 12, 11, 10), ZoneOffset.UTC);
        ZoneId zone = ZoneId.of("Asia/Tokyo");
        ZonedDateTime zonedDateTime = offsetDateTime.atZoneSameInstant(zone);
        OffsetDateTime expected = zonedDateTime.toOffsetDateTime();
        System.out.println("mmmmmmmmm   " + offsetDateTime + "    " + expected + "     mmmmmm");
        Tense tense = jdbcManager.from(Tense.class).where("id = ?", 1).getSingleResult();
        tense.offsetDateTime = zonedDateTime.toOffsetDateTime();
        jdbcManager.update(tense).execute();

        tense = jdbcManager.from(Tense.class).where("id = ?", 1).getSingleResult();
        assertTrue(expected.isEqual(tense.offsetDateTime));
        tense = jdbcManager.from(Tense.class).where("offsetDateTime = ?", offsetDateTime).getSingleResult();
        assertTrue(expected.isEqual(tense.offsetDateTime));
        tense = jdbcManager.from(Tense.class).where(new SimpleWhere().eq("offsetDateTime", offsetDateTime)).getSingleResult();
        assertTrue(expected.isEqual(tense.offsetDateTime));
    }
    
    @Test
    void testUserDefineValueType()  {
        Authority authority = jdbcManager.from(Authority.class).id(3).getSingleResult();
        assertEquals(30, authority.authorityType.value());
    }

    @Test
    void testUserDefineValueType_criteria()  {
        Authority authority = jdbcManager.from(Authority.class).where("authorityType = ?", AuthorityType.valueOf(20)).getSingleResult();
        assertEquals(2, authority.id);
    }

    @Test
    void testUserDefineValueType_simpleWhere()  {
        Authority authority = jdbcManager.from(Authority.class).where(new SimpleWhere().eq("authorityType", AuthorityType.valueOf(20))).getSingleResult();
        assertEquals(2, authority.id);
    }

    @Test
    void testEnumType()  {
        Job job = jdbcManager.from(Job.class).id(3).getSingleResult();
        assertEquals(JobType.PRESIDENT, job.jobType);
    }

    @Test
    void testEnumType_criteria()  {
        Job job = jdbcManager.from(Job.class).where("jobType = ?", JobType.MANAGER).getSingleResult();
        assertEquals(2, job.id);
    }

    /**
     * 
     * @
     */
    @Test
    void testEnumType_simpleWhere()  {
        Job job = jdbcManager.from(Job.class).where(new SimpleWhere().eq("jobType", JobType.MANAGER)).getSingleResult();
        assertEquals(2, job.id);
    }
}
