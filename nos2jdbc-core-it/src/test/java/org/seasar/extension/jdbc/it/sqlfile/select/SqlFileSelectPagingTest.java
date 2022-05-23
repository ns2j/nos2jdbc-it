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

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.extension.jdbc.it.entity.Employee;

import nos2jdbc.core.it.NoS2JdbcExtension;

/**
 * @author taedium
 * 
 */
@ExtendWith(NoS2JdbcExtension.class)
class SqlFileSelectPagingTest {

    private static String PATH = SqlFileSelectPagingTest.class.getName().replace(".", "/") + "_paging.sql";

    private static String PATH2 = SqlFileSelectPagingTest.class.getName().replace(".", "/") + "_paging2.sql";

    private JdbcManager jdbcManager;

    /** @throws Exception */
    @Test
    void bean_paging() throws Exception {
        List<Employee> list = jdbcManager.selectBySqlFile(Employee.class, PATH).getResultList();
        assertEquals(14, list.size());
    }

    /** @throws Exception */
    @Test
    void bean_paging_limitOnly() throws Exception {
        List<Employee> list = jdbcManager.selectBySqlFile(Employee.class, PATH).limit(3).getResultList();
        assertEquals(3, list.size());
        assertEquals(1, list.get(0).employeeId);
        assertEquals(3, list.get(2).employeeId);
    }

    /** @throws Exception */
    @Test
    void bean_paging_offset_limit() throws Exception {
        List<Employee> list = jdbcManager.selectBySqlFile(Employee.class, PATH).offset(3).limit(5).getResultList();
        assertEquals(5, list.size());
        assertEquals(4, list.get(0).employeeId);
        assertEquals(8, list.get(4).employeeId);
    }

    /** @throws Exception */
    @Test
    void bean_paging_offset_limitZero() throws Exception {
        List<Employee> list = jdbcManager.selectBySqlFile(Employee.class, PATH).offset(3).limit(0).getResultList();
        assertEquals(11, list.size());
        assertEquals(4, list.get(0).employeeId);
        assertEquals(14, list.get(10).employeeId);
    }

    /** @throws Exception */
    @Test
    void bean_paging_offsetOnly() throws Exception {
        List<Employee> list = jdbcManager.selectBySqlFile(Employee.class, PATH).offset(3).getResultList();
        assertEquals(11, list.size());
        assertEquals(4, list.get(0).employeeId);
        assertEquals(14, list.get(10).employeeId);
    }

    /** @throws Exception */
    @Test
    void bean_paging_offsetZero_limit() throws Exception {
        List<Employee> list = jdbcManager.selectBySqlFile(Employee.class, PATH).offset(0).limit(3).getResultList();
        assertEquals(3, list.size());
        assertEquals(1, list.get(0).employeeId);
        assertEquals(3, list.get(2).employeeId);
    }

    /** @throws Exception */
    @Test
    void bean_paging_offsetZero_limitZero() throws Exception {
        List<Employee> list = jdbcManager.selectBySqlFile(Employee.class, PATH).offset(0).limit(0).getResultList();
        assertEquals(14, list.size());
    }

    /** @throws Exception */
    @Test
    void map_paging() throws Exception {
        @SuppressWarnings("unchecked") List<Map> list = jdbcManager.selectBySqlFile(Map.class, PATH).getResultList();
        assertEquals(14, list.size());
        assertEquals(9, list.get(0).size());
    }

    /** @throws Exception */
    @Test
    void map_paging_limitOnly() throws Exception {
        @SuppressWarnings("unchecked") List<Map> list = jdbcManager.selectBySqlFile(Map.class, PATH).limit(3).getResultList();
        assertEquals(3, list.size());
        assertEquals(9, list.get(0).size());
        assertEquals(1, ((Number) list.get(0).get("employeeId")).intValue());
        assertEquals(3, ((Number) list.get(2).get("employeeId")).intValue());
    }

    /** @throws Exception */
    @Test
    void map_paging_offset_limit() throws Exception {
        @SuppressWarnings("unchecked") List<Map> list = jdbcManager.selectBySqlFile(Map.class, PATH).offset(3).limit(5).getResultList();
        assertEquals(5, list.size());
        assertEquals(9, list.get(0).size());
        assertEquals(4, ((Number) list.get(0).get("employeeId")).intValue());
        assertEquals(8, ((Number) list.get(4).get("employeeId")).intValue());
    }

    /** @throws Exception */
    @Test
    void map_paging_offset_limitZero() throws Exception {
        @SuppressWarnings("unchecked") List<Map> list = jdbcManager.selectBySqlFile(Map.class, PATH).offset(3).limit(0).getResultList();
        assertEquals(11, list.size());
        assertEquals(9, list.get(0).size());
        assertEquals(4, ((Number) list.get(0).get("employeeId")).intValue());
        assertEquals(14, ((Number) list.get(10).get("employeeId")).intValue());
    }

    /** @throws Exception */
    @Test
    void map_paging_offsetOnly() throws Exception {
        @SuppressWarnings("unchecked") List<Map> list = jdbcManager.selectBySqlFile(Map.class, PATH).offset(3).getResultList();
        assertEquals(11, list.size());
        assertEquals(9, list.get(0).size());
        assertEquals(4, ((Number) list.get(0).get("employeeId")).intValue());
        assertEquals(14, ((Number) list.get(10).get("employeeId")).intValue());
    }

    /** @throws Exception */
    @Test
    void map_paging_offsetZero_limit() throws Exception {
        @SuppressWarnings("unchecked") List<Map> list = jdbcManager.selectBySqlFile(Map.class, PATH).offset(0).limit(3).getResultList();
        assertEquals(3, list.size());
        assertEquals(9, list.get(0).size());
        assertEquals(1, ((Number) list.get(0).get("employeeId")).intValue());
        assertEquals(3, ((Number) list.get(2).get("employeeId")).intValue());
    }

    /** @throws Exception */
    @Test
    void map_paging_offsetZero_limitZero() throws Exception {
        @SuppressWarnings("unchecked") List<Map> list = jdbcManager.selectBySqlFile(Map.class, PATH).offset(0).limit(0).getResultList();
        assertEquals(14, list.size());
        assertEquals(9, list.get(0).size());
    }

    /** @throws Exception */
    @Test
    void object_paging() throws Exception {
        List<Integer> list = jdbcManager.selectBySqlFile(Integer.class, PATH2).getResultList();
        assertEquals(14, list.size());
    }

    /** @throws Exception */
    @Test
    void object_paging_limitOnly() throws Exception {
        List<Integer> list = jdbcManager.selectBySqlFile(Integer.class, PATH2).limit(3).getResultList();
        assertEquals(3, list.size());
        assertEquals(1, list.get(0).intValue());
        assertEquals(3, list.get(2).intValue());
    }

    /** @throws Exception */
    @Test
    void object_paging_offset_limit() throws Exception {
        List<Integer> list = jdbcManager.selectBySqlFile(Integer.class, PATH2).offset(3).limit(5).getResultList();
        assertEquals(5, list.size());
        assertEquals(4, list.get(0).intValue());
        assertEquals(8, list.get(4).intValue());
    }

    /** @throws Exception */
    @Test
    void object_paging_offset_limitZero() throws Exception {
        List<Integer> list = jdbcManager.selectBySqlFile(Integer.class, PATH2).offset(3).limit(0).getResultList();
        assertEquals(11, list.size());
        assertEquals(4, list.get(0).intValue());
        assertEquals(14, list.get(10).intValue());
    }

    /** @throws Exception */
    @Test
    void object_paging_offsetOnly() throws Exception {
        List<Integer> list = jdbcManager.selectBySqlFile(Integer.class, PATH2).offset(3).getResultList();
        assertEquals(11, list.size());
        assertEquals(4, list.get(0).intValue());
        assertEquals(14, list.get(10).intValue());
    }

    /** @throws Exception */
    @Test
    void object_paging_offsetZero_limit() throws Exception {
        List<Integer> list = jdbcManager.selectBySqlFile(Integer.class, PATH2).offset(0).limit(3).getResultList();
        assertEquals(3, list.size());
        assertEquals(1, list.get(0).intValue());
        assertEquals(3, list.get(2).intValue());
    }

    /** @throws Exception */
    @Test
    void object_paging_offsetZero_limitZero() throws Exception {
        List<Integer> list = jdbcManager.selectBySqlFile(Integer.class, PATH2).offset(0).limit(0).getResultList();
        assertEquals(14, list.size());
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void bean_WithoutInverseField_paging() throws Exception {
        List<Employee> list = jdbcManager.selectBySqlFile(Employee.class, PATH).getResultListWithoutInverseField();
        assertEquals(14, list.size());
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void bean_WithoutInverseField_paging_limitOnly() throws Exception {
        List<Employee> list = jdbcManager.selectBySqlFile(Employee.class, PATH).limit(3).getResultListWithoutInverseField();
        assertEquals(3, list.size());
        assertEquals(1, list.get(0).employeeId);
        assertEquals(3, list.get(2).employeeId);
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void bean_WithoutInverseField_paging_offset_limit() throws Exception {
        List<Employee> list = jdbcManager.selectBySqlFile(Employee.class, PATH).offset(3).limit(5).getResultListWithoutInverseField();
        assertEquals(5, list.size());
        assertEquals(4, list.get(0).employeeId);
        assertEquals(8, list.get(4).employeeId);
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void bean_WithoutInverseField_paging_offset_limitZero() throws Exception {
        List<Employee> list = jdbcManager.selectBySqlFile(Employee.class, PATH).offset(3).limit(0).getResultListWithoutInverseField();
        assertEquals(11, list.size());
        assertEquals(4, list.get(0).employeeId);
        assertEquals(14, list.get(10).employeeId);
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void bean_WithoutInverseField_paging_offsetOnly() throws Exception {
        List<Employee> list = jdbcManager.selectBySqlFile(Employee.class, PATH).offset(3).getResultListWithoutInverseField();
        assertEquals(11, list.size());
        assertEquals(4, list.get(0).employeeId);
        assertEquals(14, list.get(10).employeeId);
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void bean_WithoutInverseField_paging_offsetZero_limit() throws Exception {
        List<Employee> list = jdbcManager.selectBySqlFile(Employee.class, PATH).offset(0).limit(3).getResultListWithoutInverseField();
        assertEquals(3, list.size());
        assertEquals(1, list.get(0).employeeId);
        assertEquals(3, list.get(2).employeeId);
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void bean_WithoutInverseField_paging_offsetZero_limitZero() throws Exception {
        List<Employee> list = jdbcManager.selectBySqlFile(Employee.class, PATH).offset(0).limit(0).getResultListWithoutInverseField();
        assertEquals(14, list.size());
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void map_WithoutInverseField_paging() throws Exception {
        @SuppressWarnings("unchecked") List<Map> list = jdbcManager.selectBySqlFile(Map.class, PATH).getResultListWithoutInverseField();
        assertEquals(14, list.size());
        assertEquals(9, list.get(0).size());
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void map_WithoutInverseField_paging_limitOnly() throws Exception {
        @SuppressWarnings("unchecked") List<Map> list = jdbcManager.selectBySqlFile(Map.class, PATH).limit(3).getResultListWithoutInverseField();
        assertEquals(3, list.size());
        assertEquals(9, list.get(0).size());
        assertEquals(1, ((Number) list.get(0).get("employeeId")).intValue());
        assertEquals(3, ((Number) list.get(2).get("employeeId")).intValue());
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void map_WithoutInverseField_paging_offset_limit() throws Exception {
        @SuppressWarnings("unchecked") List<Map> list = jdbcManager.selectBySqlFile(Map.class, PATH).offset(3).limit(5).getResultListWithoutInverseField();
        assertEquals(5, list.size());
        assertEquals(9, list.get(0).size());
        assertEquals(4, ((Number) list.get(0).get("employeeId")).intValue());
        assertEquals(8, ((Number) list.get(4).get("employeeId")).intValue());
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void map_WithoutInverseField_paging_offset_limitZero() throws Exception {
        @SuppressWarnings("unchecked") List<Map> list = jdbcManager.selectBySqlFile(Map.class, PATH).offset(3).limit(0).getResultListWithoutInverseField();
        assertEquals(11, list.size());
        assertEquals(9, list.get(0).size());
        assertEquals(4, ((Number) list.get(0).get("employeeId")).intValue());
        assertEquals(14, ((Number) list.get(10).get("employeeId")).intValue());
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void map_WithoutInverseField_paging_offsetOnly() throws Exception {
        @SuppressWarnings("unchecked") List<Map> list = jdbcManager.selectBySqlFile(Map.class, PATH).offset(3).getResultListWithoutInverseField();
        assertEquals(11, list.size());
        assertEquals(9, list.get(0).size());
        assertEquals(4, ((Number) list.get(0).get("employeeId")).intValue());
        assertEquals(14, ((Number) list.get(10).get("employeeId")).intValue());
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void map_WithoutInverseField_paging_offsetZero_limit() throws Exception {
        @SuppressWarnings("unchecked") List<Map> list = jdbcManager.selectBySqlFile(Map.class, PATH).offset(0).limit(3).getResultListWithoutInverseField();
        assertEquals(3, list.size());
        assertEquals(9, list.get(0).size());
        assertEquals(1, ((Number) list.get(0).get("employeeId")).intValue());
        assertEquals(3, ((Number) list.get(2).get("employeeId")).intValue());
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void map_WithoutInverseField_paging_offsetZero_limitZero() throws Exception {
        @SuppressWarnings("unchecked") List<Map> list = jdbcManager.selectBySqlFile(Map.class, PATH).offset(0).limit(0).getResultListWithoutInverseField();
        assertEquals(14, list.size());
        assertEquals(9, list.get(0).size());
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void object_WithoutInverseField_paging() throws Exception {
        List<Integer> list = jdbcManager.selectBySqlFile(Integer.class, PATH2).getResultListWithoutInverseField();
        assertEquals(14, list.size());
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void object_WithoutInverseField_paging_limitOnly() throws Exception {
        List<Integer> list = jdbcManager.selectBySqlFile(Integer.class, PATH2).limit(3).getResultListWithoutInverseField();
        assertEquals(3, list.size());
        assertEquals(1, list.get(0).intValue());
        assertEquals(3, list.get(2).intValue());
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void object_WithoutInverseField_paging_offset_limit() throws Exception {
        List<Integer> list = jdbcManager.selectBySqlFile(Integer.class, PATH2).offset(3).limit(5).getResultListWithoutInverseField();
        assertEquals(5, list.size());
        assertEquals(4, list.get(0).intValue());
        assertEquals(8, list.get(4).intValue());
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void object_WithoutInverseField_paging_offset_limitZero() throws Exception {
        List<Integer> list = jdbcManager.selectBySqlFile(Integer.class, PATH2).offset(3).limit(0).getResultListWithoutInverseField();
        assertEquals(11, list.size());
        assertEquals(4, list.get(0).intValue());
        assertEquals(14, list.get(10).intValue());
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void object_WithoutInverseField_paging_offsetOnly() throws Exception {
        List<Integer> list = jdbcManager.selectBySqlFile(Integer.class, PATH2).offset(3).getResultListWithoutInverseField();
        assertEquals(11, list.size());
        assertEquals(4, list.get(0).intValue());
        assertEquals(14, list.get(10).intValue());
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void object_WithoutInverseField_paging_offsetZero_limit() throws Exception {
        List<Integer> list = jdbcManager.selectBySqlFile(Integer.class, PATH2).offset(0).limit(3).getResultListWithoutInverseField();
        assertEquals(3, list.size());
        assertEquals(1, list.get(0).intValue());
        assertEquals(3, list.get(2).intValue());
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void object_WithoutInverseField_paging_offsetZero_limitZero() throws Exception {
        List<Integer> list = jdbcManager.selectBySqlFile(Integer.class, PATH2).offset(0).limit(0).getResultListWithoutInverseField();
        assertEquals(14, list.size());
    }
}
