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

import static org.junit.jupiter.api.Assertions.*;
import static org.seasar.extension.jdbc.it.name.EmployeeNames.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.extension.jdbc.it.entity.Department;
import org.seasar.extension.jdbc.it.entity.Employee;

import nos2jdbc.core.it.NoS2JdbcExtension;

/**
 * @author taedium
 * 
 */
@ExtendWith(NoS2JdbcExtension.class)
class SingleKeyManyToOneTest {

    private JdbcManager jdbcManager;

    /** @throws Exception */
    @Test
    void leftOuterJoin() throws Exception {
        List<Employee> list;

        list = jdbcManager.from(Employee.class).leftOuterJoin("department").getResultList();
        assertEquals(14, list.size());
        for (Employee e : list) {
            assertNotNull(e);
            assertNotNull(e.department);
            assertNotNull(e.department.employees);
        }

        list = jdbcManager.from(Employee.class).leftOuterJoin("department").getResultListWithoutInverseField();
        assertEquals(14, list.size());
        for (Employee e : list) {
            assertNotNull(e);
            assertNotNull(e.department);
            assertNull(e.department.employees);
        }
    }

    /** @throws Exception */
    @Test
    void leftOuterJoin_names() throws Exception {
        List<Employee> list;

        list = jdbcManager.from(Employee.class).leftOuterJoin(department()).getResultList();
        assertEquals(14, list.size());
        for (Employee e : list) {
            assertNotNull(e);
            assertNotNull(e.department);
            assertNotNull(e.department.employees);
        }

        list = jdbcManager.from(Employee.class).leftOuterJoin(department()).getResultListWithoutInverseField();
        assertEquals(14, list.size());
        for (Employee e : list) {
            assertNotNull(e);
            assertNotNull(e.department);
            assertNull(e.department.employees);
        }
    }

    /** @throws Exception */
    @Test
    void leftOuterJoin_noFetch() throws Exception {
        List<Employee> list;

        list = jdbcManager.from(Employee.class).leftOuterJoin("department", false).getResultList();
        assertEquals(14, list.size());
        for (Employee e : list) {
            assertNotNull(e);
            assertNull(e.department);
        }

        list = jdbcManager.from(Employee.class).leftOuterJoin("department", false).getResultListWithoutInverseField();
        assertEquals(14, list.size());
        for (Employee e : list) {
            assertNotNull(e);
            assertNull(e.department);
        }
    }

    /** @throws Exception */
    @Test
    void innerJoin() throws Exception {
        List<Employee> list;

        list = jdbcManager.from(Employee.class).innerJoin("department").getResultList();
        assertEquals(14, list.size());
        for (Employee e : list) {
            assertNotNull(e);
            assertNotNull(e.department);
            assertNotNull(e.department.employees);
        }

        list = jdbcManager.from(Employee.class).innerJoin("department").getResultListWithoutInverseField();
        assertEquals(14, list.size());
        for (Employee e : list) {
            assertNotNull(e);
            assertNotNull(e.department);
            assertNull(e.department.employees);
        }
    }

    /** @throws Exception */
    @Test
    void innerJoin_noFetch() throws Exception {
        List<Employee> list;

        list = jdbcManager.from(Employee.class).innerJoin("department", false).getResultList();
        assertEquals(14, list.size());
        for (Employee e : list) {
            assertNotNull(e);
            assertNull(e.department);
        }

        list = jdbcManager.from(Employee.class).innerJoin("department", false).getResultListWithoutInverseField();
        assertEquals(14, list.size());
        for (Employee e : list) {
            assertNotNull(e);
            assertNull(e.department);
        }
    }

    /** @throws Exception */
    @Test
    void innerJoin_self() throws Exception {
        List<Employee> list;

        list = jdbcManager.from(Employee.class).innerJoin("manager").getResultList();
        assertEquals(13, list.size());
        for (Employee e : list) {
            assertNotNull(e);
            assertNotNull(e.manager);
        }

        list = jdbcManager.from(Employee.class).innerJoin("manager").getResultListWithoutInverseField();
        assertEquals(13, list.size());
        for (Employee e : list) {
            assertNotNull(e);
            assertNotNull(e.manager);
        }
    }

    /** @throws Exception */
    @Test
    void innerJoin_oneToMany_manyToOne() throws Exception {
        Employee e;
        Department d;
        List<Employee> employees;

        e = jdbcManager.from(Employee.class).innerJoin("department").innerJoin("department.employees").id(1).getSingleResult();
        assertNotNull(e);
        d = e.department;
        assertNotNull(d);
        employees = d.employees;
        assertNotNull(employees);
        assertEquals(5, employees.size());
        assertTrue(employees.contains(e));

        e = jdbcManager.from(Employee.class).innerJoin("department").innerJoin("department.employees").id(1).getSingleResultWithoutInverseField();
        assertNotNull(e);
        d = e.department;
        assertNotNull(d);
        employees = d.employees;
        assertNotNull(employees);
        assertEquals(5, employees.size());
        assertTrue(employees.contains(e));
    }
}
