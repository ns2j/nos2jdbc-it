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

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.extension.jdbc.it.entity.Department;
import org.seasar.extension.jdbc.it.entity.Employee;
import org.seasar.extension.jdbc.where.SimpleWhere;
import nos2jdbc.core.it.NoS2JdbcExtension;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author taedium
 * 
 */
@ExtendWith(NoS2JdbcExtension.class)
public class AutoSelectGetResultTest {

    private JdbcManager jdbcManager;

    /**
     * 
     * @throws Exception
     */
    @Test
    void testGetSingleResult() throws Exception {
        Employee employee = jdbcManager.from(Employee.class).where(new SimpleWhere().eq("employeeId", 1)).getSingleResult();
        assertNotNull(employee);
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void testGetSingleResult_NonUniqueResultException() throws Exception {
	assertThrows(NonUniqueResultException.class, () -> jdbcManager.from(Employee.class).where(new SimpleWhere().eq("departmentId", 1)).getSingleResult());
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void testGetSingleResult_NoResultException() throws Exception {
	assertThrows(NoResultException.class, () -> jdbcManager.from(Employee.class).where(new SimpleWhere().eq("employeeId", 100)).disallowNoResult().getSingleResult());
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void testGetSingleResult_null() throws Exception {
        Employee employee = jdbcManager.from(Employee.class).where(new SimpleWhere().eq("employeeId", 100)).getSingleResult();
        assertNull(employee);
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void testGetSingleResult_oneToMany() throws Exception {
        Department department = jdbcManager.from(Department.class).leftOuterJoin("employees").where(new SimpleWhere().eq("departmentId", 1)).getSingleResult();
        assertNotNull(department);
    }
}
