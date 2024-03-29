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
package org.seasar.extension.jdbc.it.sqlfile.select;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.extension.jdbc.it.entity.Employee;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import nos2jdbc.core.it.NoS2JdbcExtension;

/**
 * @author taedium
 * 
 */
@ExtendWith(NoS2JdbcExtension.class)
class SqlFileSelectWhereTest {

    private JdbcManager jdbcManager;

    /** @throws Exception */
    @Test
    void bean_parameter_dto() throws Exception {
        String path = getClass().getName().replace(".", "/") + "_dto.sql";
        Param param = new Param();
        param.departmentId = 3;
        param.salary = new BigDecimal(1000);
        param.orderBy = "employee_name";
        param.offset = 1;
        param.limit = 3;
        List<Employee> list = jdbcManager.selectBySqlFile(Employee.class, path, param).getResultList();
        assertEquals(3, list.size());
        assertEquals("BLAKE", list.get(0).employeeName);
        assertEquals("MARTIN", list.get(1).employeeName);
        assertEquals("TURNER", list.get(2).employeeName);
    }

    /** @throws Exception */
    @Test
    void bean_parameter_none() throws Exception {
        String path = getClass().getName().replace(".", "/") + "_no.sql";
        List<Employee> list = jdbcManager.selectBySqlFile(Employee.class, path).getResultList();
        assertEquals(14, list.size());
    }

    /** @throws Exception */
    @Test
    void bean_parameter_simpleType() throws Exception {
        String path = getClass().getName().replace(".", "/") + "_simpleType.sql";
        List<Employee> list = jdbcManager.selectBySqlFile(Employee.class, path, 3).getResultList();
        assertEquals(6, list.size());
        assertEquals("ALLEN", list.get(0).employeeName);
        assertEquals("BLAKE", list.get(1).employeeName);
        assertEquals("JAMES", list.get(2).employeeName);
        assertEquals("MARTIN", list.get(3).employeeName);
        assertEquals("TURNER", list.get(4).employeeName);
        assertEquals("WARD", list.get(5).employeeName);
    }

    /** @throws Exception */
    @Test
    void map_parameter_dto() throws Exception {
        String path = getClass().getName().replace(".", "/") + "_dto.sql";
        Param param = new Param();
        param.departmentId = 3;
        param.salary = new BigDecimal(1000);
        param.orderBy = "employee_name";
        param.offset = 1;
        param.limit = 3;
        @SuppressWarnings("unchecked") List<Map> list = jdbcManager.selectBySqlFile(Map.class, path, param).getResultList();
        assertEquals(3, list.size());
        assertEquals("BLAKE", list.get(0).get("employeeName"));
        assertEquals("MARTIN", list.get(1).get("employeeName"));
        assertEquals("TURNER", list.get(2).get("employeeName"));
    }

    /** @throws Exception */
    @Test
    void map_parameter_none() throws Exception {
        String path = getClass().getName().replace(".", "/") + "_no.sql";
        @SuppressWarnings("unchecked") List<Map> list = jdbcManager.selectBySqlFile(Map.class, path).getResultList();
        assertEquals(14, list.size());
    }

    /** @throws Exception */
    @Test
    void map_parameter_simpleType() throws Exception {
        String path = getClass().getName().replace(".", "/") + "_simpleType.sql";
        @SuppressWarnings("unchecked") List<Map> list = jdbcManager.selectBySqlFile(Map.class, path, 3).getResultList();
        assertEquals(6, list.size());
        assertEquals("ALLEN", list.get(0).get("employeeName"));
        assertEquals("BLAKE", list.get(1).get("employeeName"));
        assertEquals("JAMES", list.get(2).get("employeeName"));
        assertEquals("MARTIN", list.get(3).get("employeeName"));
        assertEquals("TURNER", list.get(4).get("employeeName"));
        assertEquals("WARD", list.get(5).get("employeeName"));
    }

    /** @throws Exception */
    @Test
    void object_parameter_dto() throws Exception {
        String path = getClass().getName().replace(".", "/") + "_dto2.sql";
        Param param = new Param();
        param.departmentId = 3;
        param.salary = new BigDecimal(1000);
        param.orderBy = "employee_name";
        param.offset = 1;
        param.limit = 3;
        List<Integer> list = jdbcManager.selectBySqlFile(Integer.class, path, param).getResultList();
        assertEquals(3, list.size());
        assertEquals(6, list.get(0).intValue());
        assertEquals(5, list.get(1).intValue());
        assertEquals(10, list.get(2).intValue());
    }

    /** @throws Exception */
    @Test
    void object_parameter_none() throws Exception {
        String path = getClass().getName().replace(".", "/") + "_no2.sql";
        List<Integer> list = jdbcManager.selectBySqlFile(Integer.class, path).getResultList();
        assertEquals(14, list.size());
    }

    /** @throws Exception */
    @Test
    void object_parameter_simpleType() throws Exception {
        String path = getClass().getName().replace(".", "/") + "_simpleType2.sql";
        List<Integer> list = jdbcManager.selectBySqlFile(Integer.class, path, 3).getResultList();
        assertEquals(6, list.size());
        assertEquals(2, list.get(0).intValue());
        assertEquals(6, list.get(1).intValue());
        assertEquals(12, list.get(2).intValue());
        assertEquals(5, list.get(3).intValue());
        assertEquals(10, list.get(4).intValue());
        assertEquals(3, list.get(5).intValue());
    }

    /** @throws Exception */
    @Test
    void bean_WithoutInverseField_parameter_dto() throws Exception {
        String path = getClass().getName().replace(".", "/") + "_dto.sql";
        Param param = new Param();
        param.departmentId = 3;
        param.salary = new BigDecimal(1000);
        param.orderBy = "employee_name";
        param.offset = 1;
        param.limit = 3;
        List<Employee> list = jdbcManager.selectBySqlFile(Employee.class, path, param).getResultListWithoutInverseField();
        assertEquals(3, list.size());
        assertEquals("BLAKE", list.get(0).employeeName);
        assertEquals("MARTIN", list.get(1).employeeName);
        assertEquals("TURNER", list.get(2).employeeName);
    }

    /** @throws Exception */
    @Test
    void bean_WithoutInverseField_parameter_none() throws Exception {
        String path = getClass().getName().replace(".", "/") + "_no.sql";
        List<Employee> list = jdbcManager.selectBySqlFile(Employee.class, path).getResultListWithoutInverseField();
        assertEquals(14, list.size());
    }

    /** @throws Exception */
    @Test
    void bean_WithoutInverseField_parameter_simpleType() throws Exception {
        String path = getClass().getName().replace(".", "/") + "_simpleType.sql";
        List<Employee> list = jdbcManager.selectBySqlFile(Employee.class, path, 3).getResultListWithoutInverseField();
        assertEquals(6, list.size());
        assertEquals("ALLEN", list.get(0).employeeName);
        assertEquals("BLAKE", list.get(1).employeeName);
        assertEquals("JAMES", list.get(2).employeeName);
        assertEquals("MARTIN", list.get(3).employeeName);
        assertEquals("TURNER", list.get(4).employeeName);
        assertEquals("WARD", list.get(5).employeeName);
    }

    /** @throws Exception */
    @Test
    void map_WithoutInverseField_parameter_dto() throws Exception {
        String path = getClass().getName().replace(".", "/") + "_dto.sql";
        Param param = new Param();
        param.departmentId = 3;
        param.salary = new BigDecimal(1000);
        param.orderBy = "employee_name";
        param.offset = 1;
        param.limit = 3;
        @SuppressWarnings("unchecked") List<Map> list = jdbcManager.selectBySqlFile(Map.class, path, param).getResultListWithoutInverseField();
        assertEquals(3, list.size());
        assertEquals("BLAKE", list.get(0).get("employeeName"));
        assertEquals("MARTIN", list.get(1).get("employeeName"));
        assertEquals("TURNER", list.get(2).get("employeeName"));
    }

    /** @throws Exception */
    @Test
    void map_WithoutInverseField_parameter_none() throws Exception {
        String path = getClass().getName().replace(".", "/") + "_no.sql";
        @SuppressWarnings("unchecked") List<Map> list = jdbcManager.selectBySqlFile(Map.class, path).getResultListWithoutInverseField();
        assertEquals(14, list.size());
    }

    /** @throws Exception */
    @Test
    void map_WithoutInverseField_parameter_simpleType() throws Exception {
        String path = getClass().getName().replace(".", "/") + "_simpleType.sql";
        @SuppressWarnings("unchecked") List<Map> list = jdbcManager.selectBySqlFile(Map.class, path, 3).getResultListWithoutInverseField();
        assertEquals(6, list.size());
        assertEquals("ALLEN", list.get(0).get("employeeName"));
        assertEquals("BLAKE", list.get(1).get("employeeName"));
        assertEquals("JAMES", list.get(2).get("employeeName"));
        assertEquals("MARTIN", list.get(3).get("employeeName"));
        assertEquals("TURNER", list.get(4).get("employeeName"));
        assertEquals("WARD", list.get(5).get("employeeName"));
    }

    /** @throws Exception */
    @Test
    void object_WithoutInverseField_parameter_dto() throws Exception {
        String path = getClass().getName().replace(".", "/") + "_dto2.sql";
        Param param = new Param();
        param.departmentId = 3;
        param.salary = new BigDecimal(1000);
        param.orderBy = "employee_name";
        param.offset = 1;
        param.limit = 3;
        List<Integer> list = jdbcManager.selectBySqlFile(Integer.class, path, param).getResultListWithoutInverseField();
        assertEquals(3, list.size());
        assertEquals(6, list.get(0).intValue());
        assertEquals(5, list.get(1).intValue());
        assertEquals(10, list.get(2).intValue());
    }

    /** @throws Exception */
    @Test
    void object_WithoutInverseField_parameter_none() throws Exception {
        String path = getClass().getName().replace(".", "/") + "_no2.sql";
        List<Integer> list = jdbcManager.selectBySqlFile(Integer.class, path).getResultListWithoutInverseField();
        assertEquals(14, list.size());
    }

    /** @throws Exception */
    @Test
    void object_WithoutInverseField_parameter_simpleType() throws Exception {
        String path = getClass().getName().replace(".", "/") + "_simpleType2.sql";
        List<Integer> list = jdbcManager.selectBySqlFile(Integer.class, path, 3).getResultListWithoutInverseField();
        assertEquals(6, list.size());
        assertEquals(2, list.get(0).intValue());
        assertEquals(6, list.get(1).intValue());
        assertEquals(12, list.get(2).intValue());
        assertEquals(5, list.get(3).intValue());
        assertEquals(10, list.get(4).intValue());
        assertEquals(3, list.get(5).intValue());
    }

    /**
     * 
     * @author taedium
     */
    public static class Param {

        /** */
        public int departmentId;

        /** */
        public BigDecimal salary;

        /** */
        public String orderBy;

        /** */
        public int offset;

        /** */
        public int limit;
    }

    /**
     * 
     * @author taedium
     */
    public static class Param2 {

        /** */
        @Temporal(TemporalType.DATE)
        public Calendar calDate;

        /** */
        @Temporal(TemporalType.TIME)
        public Calendar calTime;

        /** */
        @Temporal(TemporalType.TIMESTAMP)
        public Calendar calTimestamp;
    }

    /**
     * 
     * @author taedium
     */
    public static class Param3 {

        /** */
        @Temporal(TemporalType.DATE)
        public Date dateDate;

        /** */
        @Temporal(TemporalType.TIME)
        public Date dateTime;

        /** */
        @Temporal(TemporalType.TIMESTAMP)
        public Date dateTimestamp;
    }

    public static class Param4 {
        public LocalDate localDate;
        public LocalTime localTime;
        public LocalDateTime localDateTime;
        public OffsetDateTime offsetDateTime;
    }
}
