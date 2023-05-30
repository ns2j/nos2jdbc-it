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

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.extension.jdbc.it.entity.Employee;

import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import nos2jdbc.core.it.NoS2JdbcExtension;

/**
 * @author taedium
 * 
 */
@ExtendWith(NoS2JdbcExtension.class)
class SqlFileSelectGetResultTest {

    private JdbcManager jdbcManager;

    /** @throws Exception */
    @Test
    void bean_getResultList_NoResultException() throws Exception {
        String path = getClass().getName().replace(".", "/") + "_getResultList_NoResultException.sql";
        assertThrows(NoResultException.class, () -> jdbcManager.selectBySqlFile(Employee.class, path).disallowNoResult().getResultList());
    }

    /** @throws Exception */
    @Test
    void bean_getSingleResult() throws Exception {
        String path = getClass().getName().replace(".", "/") + "_getSingleResult.sql";
        Employee employee = jdbcManager.selectBySqlFile(Employee.class, path).getSingleResult();
        assertNotNull(employee);
    }

    /** @throws Exception */
    @Test
    void bean_getSingleResult_NonUniqueResultException() throws Exception {
        String path = getClass().getName().replace(".", "/") + "_getSingleResult_NonUniqueResultException.sql";
        assertThrows(NonUniqueResultException.class, () -> jdbcManager.selectBySqlFile(Employee.class, path).getSingleResult());
    }

    /** @throws Exception */
    @Test
    void bean_getSingleResult_NoResultException() throws Exception {
        String path = getClass().getName().replace(".", "/") + "_getSingleResult_NoResultException.sql";
        assertThrows(NoResultException.class, () -> jdbcManager.selectBySqlFile(Employee.class, path).disallowNoResult().getSingleResult());
    }

    /** @throws Exception */
    @Test
    void bean_getSingleResult_null() throws Exception {
        String path = getClass().getName().replace(".", "/") + "_getSingleResult_null.sql";
        Employee employee = jdbcManager.selectBySqlFile(Employee.class, path).getSingleResult();
        assertNull(employee);
    }

    /** @throws Exception */
    @Test
    void map_getResultList_NoResultException() throws Exception {
        String path = getClass().getName().replace(".", "/") + "_getResultList_NoResultException.sql";
        assertThrows(NoResultException.class, () -> jdbcManager.selectBySqlFile(Map.class, path).disallowNoResult().getResultList());
    }

    /** @throws Exception */
    @Test
    void map_getSingleResult() throws Exception {
        String path = getClass().getName().replace(".", "/") + "_getSingleResult.sql";
        Map<?, ?> employee = jdbcManager.selectBySqlFile(Map.class, path).getSingleResult();
        assertNotNull(employee);
    }

    /** @throws Exception */
    @Test
    void map_getSingleResult_NonUniqueResultException() throws Exception {
        String path = getClass().getName().replace(".", "/") + "_getSingleResult_NonUniqueResultException.sql";
        assertThrows(NonUniqueResultException.class, () -> jdbcManager.selectBySqlFile(Map.class, path).getSingleResult());
    }

    /** @throws Exception */
    @Test
    void map_getSingleResult_NoResultException() throws Exception {
        String path = getClass().getName().replace(".", "/") + "_getSingleResult_NoResultException.sql";
        assertThrows(NoResultException.class, () -> jdbcManager.selectBySqlFile(Map.class, path).disallowNoResult().getSingleResult());
    }

    /** @throws Exception */
    @Test
    void map_getSingleResult_null() throws Exception {
        String path = getClass().getName().replace(".", "/") + "_getSingleResult_null.sql";
        Map<?, ?> employee = jdbcManager.selectBySqlFile(Map.class, path).getSingleResult();
        assertNull(employee);
    }

    /** @throws Exception */
    @Test
    void object_getResultList_NoResultException() throws Exception {
        String path = getClass().getName().replace(".", "/") + "_getResultList_NoResultException2.sql";
        assertThrows(NoResultException.class, () -> jdbcManager.selectBySqlFile(Integer.class, path).disallowNoResult().getResultList());
    }

    /** @throws Exception */
    @Test
    void object_getSingleResult() throws Exception {
        String path = getClass().getName().replace(".", "/") + "_getSingleResult2.sql";
        Integer employeeId = jdbcManager.selectBySqlFile(Integer.class, path).getSingleResult();
        assertNotNull(employeeId);
    }

    /** @throws Exception */
    @Test
    void object_getSingleResult_NonUniqueResultException() throws Exception {
        String path = getClass().getName().replace(".", "/") + "_getSingleResult_NonUniqueResultException2.sql";
        assertThrows(NonUniqueResultException.class, () -> jdbcManager.selectBySqlFile(Integer.class, path).getSingleResult());
    }

    /** @throws Exception */
    @Test
    void object_getSingleResult_NoResultException() throws Exception {
        String path = getClass().getName().replace(".", "/") + "_getSingleResult_NoResultException2.sql";
        assertThrows(NoResultException.class, () -> jdbcManager.selectBySqlFile(Integer.class, path).disallowNoResult().getSingleResult());
    }

    /** @throws Exception */
    @Test
    void object_getSingleResult_null() throws Exception {
        String path = getClass().getName().replace(".", "/") + "_getSingleResult_null2.sql";
        Integer employeeId = jdbcManager.selectBySqlFile(Integer.class, path).getSingleResult();
        assertNull(employeeId);
    }
    /**
     * 
     * @throws Exception
     */
    @Test
    void bean_getResultListWithoutInverseField_NoResultException() throws Exception {
        String path = getClass().getName().replace(".", "/") + "_getResultList_NoResultException.sql";
        assertThrows(NoResultException.class, () -> jdbcManager.selectBySqlFile(Employee.class, path).disallowNoResult().getResultListWithoutInverseField());
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void bean_getSingleResultWithoutInverseField() throws Exception {
        String path = getClass().getName().replace(".", "/") + "_getSingleResult.sql";
        Employee employee = jdbcManager.selectBySqlFile(Employee.class, path).getSingleResultWithoutInverseField();
        assertNotNull(employee);
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void bean_getSingleResultWithoutInverseField_NonUniqueResultException() throws Exception {
        String path = getClass().getName().replace(".", "/") + "_getSingleResult_NonUniqueResultException.sql";
        assertThrows(NonUniqueResultException.class, () -> jdbcManager.selectBySqlFile(Employee.class, path).getSingleResultWithoutInverseField());
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void bean_getSingleResultWithoutInverseField_NoResultException() throws Exception {
        String path = getClass().getName().replace(".", "/") + "_getSingleResult_NoResultException.sql";
        assertThrows(NoResultException.class, () -> jdbcManager.selectBySqlFile(Employee.class, path).disallowNoResult().getSingleResultWithoutInverseField());
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void bean_getSingleResultWithoutInverseField_null() throws Exception {
        String path = getClass().getName().replace(".", "/") + "_getSingleResult_null.sql";
        Employee employee = jdbcManager.selectBySqlFile(Employee.class, path).getSingleResultWithoutInverseField();
        assertNull(employee);
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void map_getResultListWithoutInverseField_NoResultException() throws Exception {
        String path = getClass().getName().replace(".", "/") + "_getResultList_NoResultException.sql";
        assertThrows(NoResultException.class, () -> jdbcManager.selectBySqlFile(Map.class, path).disallowNoResult().getResultListWithoutInverseField());
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void map_getSingleResultWithoutInverseField() throws Exception {
        String path = getClass().getName().replace(".", "/") + "_getSingleResult.sql";
        Map<?, ?> employee = jdbcManager.selectBySqlFile(Map.class, path).getSingleResultWithoutInverseField();
        assertNotNull(employee);
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void map_getSingleResultWithoutInverseField_NonUniqueResultException() throws Exception {
        String path = getClass().getName().replace(".", "/") + "_getSingleResult_NonUniqueResultException.sql";
        assertThrows(NonUniqueResultException.class, () -> jdbcManager.selectBySqlFile(Map.class, path).getSingleResultWithoutInverseField());
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void map_getSingleResultWithoutInverseField_NoResultException() throws Exception {
        String path = getClass().getName().replace(".", "/") + "_getSingleResult_NoResultException.sql";
        assertThrows(NoResultException.class, () -> jdbcManager.selectBySqlFile(Map.class, path).disallowNoResult().getSingleResultWithoutInverseField());
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void map_getSingleResultWithoutInverseField_null() throws Exception {
        String path = getClass().getName().replace(".", "/") + "_getSingleResult_null.sql";
        Map<?, ?> employee = jdbcManager.selectBySqlFile(Map.class, path).getSingleResultWithoutInverseField();
        assertNull(employee);
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void object_getResultListWithoutInverseField_NoResultException() throws Exception {
        String path = getClass().getName().replace(".", "/") + "_getResultList_NoResultException2.sql";
        assertThrows(NoResultException.class, () -> jdbcManager.selectBySqlFile(Integer.class, path).disallowNoResult().getResultListWithoutInverseField());
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void object_getSingleResultWithoutInverseField() throws Exception {
        String path = getClass().getName().replace(".", "/") + "_getSingleResult2.sql";
        Integer employeeId = jdbcManager.selectBySqlFile(Integer.class, path).getSingleResultWithoutInverseField();
        assertNotNull(employeeId);
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void object_getSingleResultWithoutInverseField_NonUniqueResultException() throws Exception {
        String path = getClass().getName().replace(".", "/") + "_getSingleResult_NonUniqueResultException2.sql";
        assertThrows(NonUniqueResultException.class, () -> jdbcManager.selectBySqlFile(Integer.class, path).getSingleResultWithoutInverseField());
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void object_getSingleResultWithoutInverseField_NoResultException() throws Exception {
        String path = getClass().getName().replace(".", "/") + "_getSingleResult_NoResultException2.sql";
        assertThrows(NoResultException.class, () -> jdbcManager.selectBySqlFile(Integer.class, path).disallowNoResult().getSingleResultWithoutInverseField());
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void object_getSingleResultWithoutInverseField_null() throws Exception {
        String path = getClass().getName().replace(".", "/") + "_getSingleResult_null2.sql";
        Integer employeeId = jdbcManager.selectBySqlFile(Integer.class, path).getSingleResultWithoutInverseField();
        assertNull(employeeId);
    }
}
