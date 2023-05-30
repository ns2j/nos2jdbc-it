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

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.extension.jdbc.it.entity.Employee;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.OneToMany;
import nos2jdbc.annotation.NoFk;
import nos2jdbc.annotation.NonAuto;
import nos2jdbc.core.it.NoS2JdbcExtension;

/**
 * @author taedium
 * 
 */
@ExtendWith(NoS2JdbcExtension.class)
class SqlSelectGetResultWithoutInverseFieldTest {

    private JdbcManager jdbcManager;

    /** @throws Exception */
    @Test
    void bean_getResultList_NoResultException() throws Exception {
        String sql = "SELECT * FROM EMPLOYEE WHERE EMPLOYEE_ID = 100";
        assertThrows(NoResultException.class, () -> jdbcManager.selectBySql(Employee.class, sql).disallowNoResult().getResultListWithoutInverseField());
    }

    /** @throws Exception */
    @Test
    void bean_getSingleResult() throws Exception {
        String sql = "SELECT * FROM EMPLOYEE WHERE EMPLOYEE_ID = 1";
        Employee employee = jdbcManager.selectBySql(Employee.class, sql).getSingleResultWithoutInverseField();
        assertNotNull(employee);
    }

    /** @throws Exception */
    @Test
    void bean_getSingleResult_NonUniqueResultException() throws Exception {
        String sql = "SELECT * FROM EMPLOYEE WHERE DEPARTMENT_ID = 1";
        assertThrows(NonUniqueResultException.class, () -> jdbcManager.selectBySql(Employee.class, sql).getSingleResultWithoutInverseField());
    }

    /** @throws Exception */
    @Test
    void bean_getSingleResult_NoResultException() throws Exception {
        String sql = "SELECT * FROM EMPLOYEE WHERE EMPLOYEE_ID = 100";
        assertThrows(NoResultException.class, () -> jdbcManager.selectBySql(Employee.class, sql).disallowNoResult().getSingleResultWithoutInverseField());
    }

    /** @throws Exception */
    @Test
    void bean_getSingleResult_null() throws Exception {
        String sql = "SELECT * FROM EMPLOYEE WHERE EMPLOYEE_ID = 100";
        Employee employee = jdbcManager.selectBySql(Employee.class, sql).getSingleResultWithoutInverseField();
        assertNull(employee);
    }

    /** @throws Exception */
    @Test
    void map_getResultList_NoResultException() throws Exception {
        String sql = "SELECT * FROM EMPLOYEE WHERE EMPLOYEE_ID = 100";
        assertThrows(NoResultException.class, () -> jdbcManager.selectBySql(Map.class, sql).disallowNoResult().getResultListWithoutInverseField());
    }

    /** @throws Exception */
    @Test
    void map_getSingleResult() throws Exception {
        String sql = "SELECT * FROM EMPLOYEE WHERE EMPLOYEE_ID = 1";
        Map<?, ?> employee = jdbcManager.selectBySql(Map.class, sql).getSingleResultWithoutInverseField();
        assertNotNull(employee);
    }

    /** @throws Exception */
    @Test
    void map_getSingleResult_NonUniqueResultException() throws Exception {
        String sql = "SELECT * FROM EMPLOYEE WHERE DEPARTMENT_ID = 1";
        assertThrows(NonUniqueResultException.class, () -> jdbcManager.selectBySql(Map.class, sql).getSingleResultWithoutInverseField());
    }

    /** @throws Exception */
    @Test
    void map_getSingleResult_NoResultException() throws Exception {
        String sql = "SELECT * FROM EMPLOYEE WHERE EMPLOYEE_ID = 100";
        assertThrows(NoResultException.class, () -> jdbcManager.selectBySql(Map.class, sql).disallowNoResult().getSingleResultWithoutInverseField());
    }

    /** @throws Exception */
    @Test
    void map_getSingleResult_null() throws Exception {
        String sql = "SELECT * FROM EMPLOYEE WHERE EMPLOYEE_ID = 100";
        Map<?, ?> employee = jdbcManager.selectBySql(Map.class, sql).getSingleResultWithoutInverseField();
        assertNull(employee);
    }

    /** @throws Exception */
    @Test
    void map_getSingleResult_AsLinkedHashMap() throws Exception {
        String sql = "SELECT VERSION, EMPLOYEE_NAME, EMPLOYEE_ID FROM EMPLOYEE WHERE EMPLOYEE_ID = 1";
        Map<?, ?> employee = jdbcManager.selectBySql(LinkedHashMap.class, sql).getSingleResultWithoutInverseField();
        Iterator<?> it = employee.keySet().iterator();
        assertEquals("version", it.next());
        assertNotNull(employee.get("version"));
        assertEquals("employeeName", it.next());
        assertNotNull(employee.get("employeeName"));
        assertNotNull(employee.get("employeeId"));
    }

    /** @throws Exception */
    @Test
    void map_getSingleResult_AsTreeMap() throws Exception {
        String sql = "SELECT VERSION, EMPLOYEE_NAME, EMPLOYEE_ID FROM EMPLOYEE WHERE EMPLOYEE_ID = 1";
        Map<?, ?> employee = jdbcManager.selectBySql(TreeMap.class, sql).getSingleResultWithoutInverseField();
        Iterator<?> it = employee.keySet().iterator();
        assertEquals("employeeId", it.next());
        assertNotNull(employee.get("employeeId"));
        assertEquals("employeeName", it.next());
        assertNotNull(employee.get("employeeName"));
        assertEquals("version", it.next());
        assertNotNull(employee.get("version"));
    }

    /** @throws Exception */
    @Test
    void object_getResultList_NoResultException() throws Exception {
        String sql = "SELECT EMPLOYEE_ID FROM EMPLOYEE WHERE EMPLOYEE_ID = 100";
        assertThrows(NoResultException.class, () -> jdbcManager.selectBySql(Integer.class, sql).disallowNoResult().getResultListWithoutInverseField());
    }

    /** @throws Exception */
    @Test
    void object_getSingleResult() throws Exception {
        String sql = "SELECT EMPLOYEE_ID FROM EMPLOYEE WHERE EMPLOYEE_ID = 1";
        Integer employeeId = jdbcManager.selectBySql(Integer.class, sql).getSingleResultWithoutInverseField();
        assertNotNull(employeeId);
    }

    /** @throws Exception */
    @Test
    void object_getSingleResult_NonUniqueResultException() throws Exception {
        String sql = "SELECT EMPLOYEE_ID FROM EMPLOYEE WHERE DEPARTMENT_ID = 1";
        assertThrows(NonUniqueResultException.class, () -> jdbcManager.selectBySql(Integer.class, sql).getSingleResultWithoutInverseField());
    }

    /** @throws Exception */
    @Test
    void object_getSingleResult_NoResultException() throws Exception {
        String sql = "SELECT EMPLOYEE_ID FROM EMPLOYEE WHERE EMPLOYEE_ID = 100";
        assertThrows(NoResultException.class, () -> jdbcManager.selectBySql(Integer.class, sql).disallowNoResult().getSingleResultWithoutInverseField());
    }

    /** @throws Exception */
    @Test
    void object_getSingleResult_null() throws Exception {
        String sql = "SELECT EMPLOYEE_ID FROM EMPLOYEE WHERE EMPLOYEE_ID = 100";
        Integer employeeId = jdbcManager.selectBySql(Integer.class, sql).getSingleResultWithoutInverseField();
        assertNull(employeeId);
    }

    /** @throws Exception */
    @Test
    void beanNonAuto() throws Exception {
    	 String sql = "select d.DEPARTMENT_ID, DEPARTMENT_NAME, EMPLOYEE_ID, EMPLOYEE_NAME from DEPARTMENT d inner join EMPLOYEE e on e.DEPARTMENT_ID = d.DEPARTMENT_ID order by d.DEPARTMENT_ID, EMPLOYEE_ID";
        List<Dep> deps = jdbcManager.selectBySql(Dep.class, sql).getResultListWithoutInverseField();
        deps.forEach(d -> {
            System.out.println(d.departmentName);
            d.emps.forEach(e -> System.out.println(" " + e.employeeName)); 
        });
        assertEquals(3, deps.size());
        assertEquals(3, deps.get(0).emps.size());
        assertEquals(5, deps.get(1).emps.size());
        assertEquals(6, deps.get(2).emps.size());
        assertNull(deps.get(0).emps.get(0).dep);
    }

    @Entity
    @NonAuto
    public static class Dep {
        @Id
        Long departmentId;
        String departmentName;
        @OneToMany(mappedBy = "dep")
        List<Emp> emps;
    }

    @Entity
    @NonAuto
    public static class Emp {
        @Id
        Long employeeId;
        String employeeName;
        @ManyToOne @NoFk
        Dep dep;
    }
}
