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
import static org.seasar.extension.jdbc.it.name.DepartmentNames.*;

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
class SingleKeyOneToManyTest {

    private JdbcManager jdbcManager;

    /** @throws Exception */
    @Test
    void leftOuterJoin() throws Exception {
        List<Department> list;

        list = jdbcManager.from(Department.class).leftOuterJoin("employees").orderBy("departmentId").getResultList();
        assertEquals(4, list.size());
        assertNotNull(list.get(0).employees);
        assertNotNull(list.get(1).employees);
        assertNotNull(list.get(2).employees);
        assertNotNull(list.get(3).employees);
        assertTrue(list.get(3).employees.isEmpty());
        assertNotNull(list.get(2).employees.get(0).department);

        list = jdbcManager.from(Department.class).leftOuterJoin("employees").orderBy("departmentId").getResultListWithoutInverseField();
        assertEquals(4, list.size());
        assertNotNull(list.get(0).employees);
        assertNotNull(list.get(1).employees);
        assertNotNull(list.get(2).employees);
        assertNotNull(list.get(3).employees);
        assertTrue(list.get(3).employees.isEmpty());
        assertNull(list.get(2).employees.get(0).department);

    }

    /** @throws Exception */
    @Test
    void leftOuterJoin_names() throws Exception {
        List<Department> list;

        list = jdbcManager.from(Department.class).leftOuterJoin(employees()).orderBy("departmentId").getResultList();
        assertEquals(4, list.size());
        assertNotNull(list.get(0).employees);
        assertNotNull(list.get(1).employees);
        assertNotNull(list.get(2).employees);
        assertNotNull(list.get(3).employees);
        assertTrue(list.get(3).employees.isEmpty());
        assertNotNull(list.get(2).employees.get(0).department);

        list = jdbcManager.from(Department.class).leftOuterJoin(employees()).orderBy("departmentId").getResultListWithoutInverseField();
        assertEquals(4, list.size());
        assertNotNull(list.get(0).employees);
        assertNotNull(list.get(1).employees);
        assertNotNull(list.get(2).employees);
        assertNotNull(list.get(3).employees);
        assertTrue(list.get(3).employees.isEmpty());
        assertNull(list.get(2).employees.get(0).department);
    }

    /** @throws Exception */
    @Test
    void leftOuterJoin_noFetch() throws Exception {
        List<Department> list;

        list = jdbcManager.from(Department.class).leftOuterJoin("employees", false).orderBy("departmentId").getResultList();
        assertEquals(4, list.size());
        assertNull(list.get(0).employees);
        assertNull(list.get(1).employees);
        assertNull(list.get(2).employees);
        assertNull(list.get(3).employees);

        list = jdbcManager.from(Department.class).leftOuterJoin("employees", false).orderBy("departmentId").getResultListWithoutInverseField();
        assertEquals(4, list.size());
        assertNull(list.get(0).employees);
        assertNull(list.get(1).employees);
        assertNull(list.get(2).employees);
        assertNull(list.get(3).employees);
}

    /** @throws Exception */
    @Test
    void innerJoin() throws Exception {
        List<Department> list;

        list = jdbcManager.from(Department.class).innerJoin("employees").orderBy("departmentId").getResultList();
        assertEquals(3, list.size());
        assertNotNull(list.get(0).employees);
        assertNotNull(list.get(1).employees);
        assertNotNull(list.get(2).employees);
        assertNotNull(list.get(2).employees.get(0).department);

        list = jdbcManager.from(Department.class).innerJoin("employees").orderBy("departmentId").getResultListWithoutInverseField();
        assertEquals(3, list.size());
        assertNotNull(list.get(0).employees);
        assertNotNull(list.get(1).employees);
        assertNotNull(list.get(2).employees);
        assertNull(list.get(2).employees.get(0).department);
    }

    /** @throws Exception */
    @Test
    void innerJoin_noFetch() throws Exception {
        List<Department> list;

        list = jdbcManager.from(Department.class).innerJoin("employees", false).orderBy("departmentId").getResultList();
        assertEquals(3, list.size());
        assertNull(list.get(0).employees);
        assertNull(list.get(1).employees);
        assertNull(list.get(2).employees);

        list = jdbcManager.from(Department.class).innerJoin("employees", false).orderBy("departmentId").getResultListWithoutInverseField();
        assertEquals(3, list.size());
        assertNull(list.get(0).employees);
        assertNull(list.get(1).employees);
        assertNull(list.get(2).employees);
    }

    /** @throws Exception */
    @Test
    void innerJoin_manyToOne_oneToMany() throws Exception {
        Department d = jdbcManager.from(Department.class).innerJoin("employees").innerJoin("employees.department").id(1).getSingleResult();
        List<Employee> employees = d.employees;
        Employee employee = employees.get(0);
        
        d = jdbcManager.from(Department.class).innerJoin("employees").innerJoin("employees.department").id(1).getSingleResult();
        assertNotNull(d);
        employees = d.employees;
        assertNotNull(employees);
        assertEquals(3, employees.size());
        employee = employees.get(0);
        assertNotNull(employee);
        assertEquals(d, employee.department);

        d = jdbcManager.from(Department.class).innerJoin("employees").innerJoin("employees.department").id(1).getSingleResultWithoutInverseField();
        assertNotNull(d);
        employees = d.employees;
        assertNotNull(employees);
        assertEquals(3, employees.size());
        employee = employees.get(0);
        assertNotNull(employee);
        assertEquals(d, employee.department);
    }
}
