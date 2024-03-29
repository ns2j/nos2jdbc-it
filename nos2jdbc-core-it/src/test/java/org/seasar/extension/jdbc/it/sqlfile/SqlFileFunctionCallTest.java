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
package org.seasar.extension.jdbc.it.sqlfile;

import static org.junit.jupiter.api.Assertions.*;
import static org.seasar.extension.jdbc.parameter.Parameter.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfSystemProperty;
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
//@Prerequisite("#ENV not in {'hsqldb', 'h2', 'db2', 'standard'}")
@DisabledIfSystemProperty(named = "database", matches = "(hsqldb|h2|db2|standard)")
class SqlFileFunctionCallTest {

    private JdbcManager jdbcManager;

    /**
     * 
     * @throws Exception
     */
    @Test
    void testParameter_none() throws Exception {
        String path = getClass().getName().replace(".", "/") + "_no" + ".sql";
        Integer result = jdbcManager.callBySqlFile(Integer.class, path).getSingleResult();
        assertEquals(Integer.valueOf(10), result);
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void testParameter_simpleType() throws Exception {
        String path = getClass().getName().replace(".", "/") + "_simpleType" + ".sql";
        Integer result = jdbcManager.callBySqlFile(Integer.class, path, 1).getSingleResult();
        assertEquals(Integer.valueOf(20), result);
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void testParameter_simpleType_time() throws Exception {
        String path = getClass().getName().replace(".", "/") + "_simpleType_time" + ".sql";
        Date inparam = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2007-08-08 12:11:10");
        Date result = jdbcManager.callBySqlFile(Date.class, path, time(inparam)).temporal(TemporalType.TIME).getSingleResult();
        long expected = new SimpleDateFormat("HH:mm:ss").parse("12:11:10").getTime();
        assertEquals(expected, result.getTime());
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void testParameter_dto() throws Exception {
        String path = getClass().getName().replace(".", "/") + "_dto" + ".sql";
        MyDto dto = new MyDto();
        dto.param1 = 3;
        dto.param2 = 5;
        Integer result = jdbcManager.callBySqlFile(Integer.class, path, dto).getSingleResult();
        assertEquals(Integer.valueOf(3), dto.param1);
        assertEquals(Integer.valueOf(5), dto.param2);
        assertEquals(Integer.valueOf(8), result);
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void testParameter_dtoTx_time() throws Exception {
        String path = getClass().getName().replace(".", "/") + "_dto_time" + ".sql";
        Date date = new SimpleDateFormat("HH:mm:ss").parse("12:11:10");
        MyDto2 dto = new MyDto2();
        dto.param1 = date;
        dto.param2 = 5;
        Date result = jdbcManager.callBySqlFile(Date.class, path, dto).temporal(TemporalType.TIME).getSingleResult();
        assertEquals(date.getTime(), result.getTime());
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    //@Prerequisite("#ENV not in {'mssql', 'mysql'}")
    @DisabledIfSystemProperty(named = "database", matches = "(mssql|mysql)")
    void testParameter_resultSet() throws Exception {
        String path = getClass().getName().replace(".", "/") + "_resultSet" + ".sql";
        List<Employee> employees = jdbcManager.callBySqlFile(Employee.class, path, 10).getResultList();
        assertNotNull(employees);
        assertEquals(4, employees.size());
        assertEquals("ADAMS", employees.get(0).employeeName);
        assertEquals("JAMES", employees.get(1).employeeName);
        assertEquals("FORD", employees.get(2).employeeName);
        assertEquals("MILLER", employees.get(3).employeeName);
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    //@Prerequisite("#ENV not in {'mssql', 'mysql'}")
    @DisabledIfSystemProperty(named = "database", matches = "(mssql|mysql)")
    void testParameter_resultSetUpdate() throws Exception {
        String path = getClass().getName().replace(".", "/") + "_resultSetUpdate" + ".sql";
        List<Employee> employees = jdbcManager.callBySqlFile(Employee.class, path, 10).getResultList();
        assertNotNull(employees);
        assertEquals(4, employees.size());
        assertEquals("ADAMS", employees.get(0).employeeName);
        assertEquals("JAMES", employees.get(1).employeeName);
        assertEquals("FORD", employees.get(2).employeeName);
        assertEquals("MILLER", employees.get(3).employeeName);
        String departmentName = jdbcManager.selectBySql(String.class, "select department_name from Department where department_id = ?", 1).getSingleResult();
        assertEquals("HOGE", departmentName);
    }

    /**
     * 
     * @author taedium
     * 
     */
    public static class MyDto {

        /** */
        public Integer param1;

        /** */
        public Integer param2;
    }

    /**
     * 
     * @author taedium
     * 
     */
    public static class MyDto2 {

        /** */
        @Temporal(TemporalType.TIME)
        public Date param1;

        /** */
        public Integer param2;
    }
}
