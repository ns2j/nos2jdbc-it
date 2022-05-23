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
import org.seasar.extension.jdbc.it.entity.Address;
import org.seasar.extension.jdbc.it.entity.Employee;

import nos2jdbc.core.it.NoS2JdbcExtension;

/**
 * @author taedium
 * 
 */
@ExtendWith(NoS2JdbcExtension.class)
class SingleKeyOneToOneTest {

    private JdbcManager jdbcManager;

    /** @throws Exception */
    @Test
    void leftOuterJoin_fromOwnerToInverse() throws Exception {
        List<Employee> list;

        list = jdbcManager.from(Employee.class).leftOuterJoin("address").getResultList();
        assertEquals(14, list.size());
        for (Employee e : list) {
            assertNotNull(e.address);
            assertNotNull(e.address.employee);
        }

        list = jdbcManager.from(Employee.class).leftOuterJoin("address").getResultListWithoutInverseField();
        assertEquals(14, list.size());
        for (Employee e : list) {
            assertNotNull(e.address);
            assertNull(e.address.employee);
        }
    }

    /** @throws Exception */
    @Test
    void leftOuterJoin_fromOwnerToInverse_names() throws Exception {
        List<Employee> list;

        list = jdbcManager.from(Employee.class).leftOuterJoin(address()).getResultList();
        assertEquals(14, list.size());
        for (Employee e : list) {
            assertNotNull(e.address);
            assertNotNull(e.address.employee);
        }

        list = jdbcManager.from(Employee.class).leftOuterJoin(address()).getResultListWithoutInverseField();
        assertEquals(14, list.size());
        for (Employee e : list) {
            assertNotNull(e.address);
            assertNull(e.address.employee);
        }
    }

    /** @throws Exception */
    @Test
    void leftOuterJoin_fromOwnerToInverse_noFetch() throws Exception {
        List<Employee> list;

        list = jdbcManager.from(Employee.class).leftOuterJoin("address", false).getResultList();
        assertEquals(14, list.size());
        for (Employee e : list) {
            assertNull(e.address);
        }

        list = jdbcManager.from(Employee.class).leftOuterJoin("address", false).getResultListWithoutInverseField();
        assertEquals(14, list.size());
        for (Employee e : list) {
            assertNull(e.address);
        }
    }

    /** @throws Exception */
    @Test
    void innerJoin_fromOwnerToInverse() throws Exception {
        List<Employee> list;

        list = jdbcManager.from(Employee.class).innerJoin("address").getResultList();
        assertEquals(14, list.size());
        for (Employee e : list) {
            assertNotNull(e.address);
            assertNotNull(e.address.employee);
        }

        list = jdbcManager.from(Employee.class).innerJoin("address").getResultListWithoutInverseField();
        assertEquals(14, list.size());
        for (Employee e : list) {
            assertNotNull(e.address);
            assertNull(e.address.employee);
        }
    }

    /** @throws Exception */
    @Test
    void innerJoin_fromOwnerToInverse_noFetch() throws Exception {
        List<Employee> list;

        list = jdbcManager.from(Employee.class).innerJoin("address", false).getResultList();
        assertEquals(14, list.size());
        for (Employee e : list) {
            assertNull(e.address);
        }

        list = jdbcManager.from(Employee.class).innerJoin("address", false).getResultListWithoutInverseField();
        assertEquals(14, list.size());
        for (Employee e : list) {
            assertNull(e.address);
        }
    }

    /** @throws Exception */
    @Test
    void leftOuterJoin_fromInverseToOwner() throws Exception {
        List<Address> list;

        list = jdbcManager.from(Address.class).leftOuterJoin("employee").getResultList();
        assertEquals(14, list.size());
        for (Address a : list) {
            assertNotNull(a.employee);
            assertNotNull(a.employee.address);
        }

        list = jdbcManager.from(Address.class).leftOuterJoin("employee").getResultListWithoutInverseField();
        assertEquals(14, list.size());
        for (Address a : list) {
            assertNotNull(a.employee);
            assertNull(a.employee.address);
        }
    }

    /** @throws Exception */
    @Test
    void leftOuterJoin_fromInverseToOwner_noFetch() throws Exception {
        List<Address> list;

        list = jdbcManager.from(Address.class).leftOuterJoin("employee", false).getResultList();
        assertEquals(14, list.size());
        for (Address e : list) {
            assertNull(e.employee);
        }

        list = jdbcManager.from(Address.class).leftOuterJoin("employee", false).getResultListWithoutInverseField();
        assertEquals(14, list.size());
        for (Address e : list) {
            assertNull(e.employee);
        }
    }

    /** @throws Exception */
    @Test
    void innerJoin_fromInverseToOwner() throws Exception {
        List<Address> list;

        list = jdbcManager.from(Address.class).innerJoin("employee").getResultList();
        assertEquals(14, list.size());
        for (Address a : list) {
            assertNotNull(a.employee);
            assertNotNull(a.employee.address);
        }

        list = jdbcManager.from(Address.class).innerJoin("employee").getResultListWithoutInverseField();
        assertEquals(14, list.size());
        for (Address a : list) {
            assertNotNull(a.employee);
            assertNull(a.employee.address);
        }
    }

    /** @throws Exception */
    @Test
    void innerJoin_fromInverseToOwner_noFetch() throws Exception {
        List<Address> list = jdbcManager.from(Address.class).innerJoin("employee", false).getResultList();
        assertEquals(14, list.size());
        for (Address e : list) {
            assertNull(e.employee);
        }
    }
}
