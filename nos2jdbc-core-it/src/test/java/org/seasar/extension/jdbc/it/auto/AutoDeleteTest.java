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
package org.seasar.extension.jdbc.it.auto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.extension.jdbc.exception.NoIdPropertyRuntimeException;
import org.seasar.extension.jdbc.it.entity.CompKeyEmployee;
import org.seasar.extension.jdbc.it.entity.ConcreteEmployee;
import org.seasar.extension.jdbc.it.entity.Employee;
import org.seasar.extension.jdbc.it.entity.NoId;
import org.seasar.extension.jdbc.where.SimpleWhere;

import jakarta.persistence.OptimisticLockException;
import nos2jdbc.core.it.NoS2JdbcExtension;

/**
 * @author taedium
 * 
 */
@ExtendWith(NoS2JdbcExtension.class)
class AutoDeleteTest {

    private JdbcManager jdbcManager;

    /**
     * 
     * @throws Exception
     */
    @Test
    void testExecute() throws Exception {
        Employee employee = new Employee();
        employee.employeeId = 1;
        employee.version = 1;
        int result = jdbcManager.delete(employee).execute();
        assertEquals(1, result);
        employee = jdbcManager.from(Employee.class).where(new SimpleWhere().eq("employeeId", 1)).getSingleResult();
        assertNull(employee);
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void testExecute_ignoreVersion() throws Exception {
        Employee employee = new Employee();
        employee.employeeId = 1;
        employee.version = 99;
        int result = jdbcManager.delete(employee).ignoreVersion().execute();
        assertEquals(1, result);
        employee = jdbcManager.from(Employee.class).where(new SimpleWhere().eq("employeeId", 1)).getSingleResult();
        assertNull(employee);
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void testExecute_compKey() throws Exception {
        CompKeyEmployee employee = new CompKeyEmployee();
        employee.employeeId1 = 1;
        employee.employeeId2 = 1;
        employee.version = 1;
        int result = jdbcManager.delete(employee).execute();
        assertEquals(1, result);
        employee = jdbcManager.from(CompKeyEmployee.class).where(new SimpleWhere().eq("employeeId1", 1).eq("employeeId2", 1)).getSingleResult();
        assertNull(employee);
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void testExecute_mappedSuperclass() throws Exception {
        ConcreteEmployee employee = new ConcreteEmployee();
        employee.employeeId = 1;
        employee.version = 1;
        int result = jdbcManager.delete(employee).execute();
        assertEquals(1, result);
        employee = jdbcManager.from(ConcreteEmployee.class).where(new SimpleWhere().eq("employeeId", 1)).getSingleResult();
        assertNull(employee);
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void testOptimisticLockException() throws Exception {
        Employee employee1 = jdbcManager.from(Employee.class).where("employeeId = ?", 1).getSingleResult();
        Employee employee2 = jdbcManager.from(Employee.class).where("employeeId = ?", 1).getSingleResult();
        jdbcManager.delete(employee1).execute();
        assertThrows(OptimisticLockException.class, () -> jdbcManager.delete(employee2).execute());
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void testSuppresOptimisticLockException() throws Exception {
        Employee employee1 = jdbcManager.from(Employee.class).where("employeeId = ?", 1).getSingleResult();
        Employee employee2 = jdbcManager.from(Employee.class).where("employeeId = ?", 1).getSingleResult();
        jdbcManager.delete(employee1).execute();
        int result = jdbcManager.delete(employee2).suppresOptimisticLockException().execute();
        assertEquals(0, result);
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void testNoId() throws Exception {
        NoId noId = new NoId();
        noId.value1 = 1;
        noId.value1 = 2;
        assertThrows(NoIdPropertyRuntimeException.class, () -> jdbcManager.delete(noId).execute());
    }
}
