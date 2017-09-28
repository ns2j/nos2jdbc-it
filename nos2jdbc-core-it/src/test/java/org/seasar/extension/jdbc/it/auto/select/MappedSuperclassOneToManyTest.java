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

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.extension.jdbc.it.entity.ConcreteDepartment;

import nos2jdbc.core.it.NoS2Jdbc;

import static org.junit.Assert.*;

/**
 * @author taedium
 * 
 */
@RunWith(NoS2Jdbc.class)
public class MappedSuperclassOneToManyTest {

    private JdbcManager jdbcManager;

    /**
     * 
     * @throws Exception
     */
    @Test
    public void testLeftOuterJoin() throws Exception {
        List<ConcreteDepartment> list =
            jdbcManager.from(ConcreteDepartment.class).leftOuterJoin(
                "employees").getResultList();
        assertEquals(4, list.size());
        assertNotNull(list.get(0).employees);
        assertNotNull(list.get(1).employees);
        assertNotNull(list.get(2).employees);
        assertNotNull(list.get(3).employees);
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    public void testLeftOuterJoin_noFetch() throws Exception {
        List<ConcreteDepartment> list =
            jdbcManager.from(ConcreteDepartment.class).leftOuterJoin(
                "employees",
                false).getResultList();
        assertEquals(4, list.size());
        assertNull(list.get(0).employees);
        assertNull(list.get(1).employees);
        assertNull(list.get(2).employees);
        assertNull(list.get(3).employees);
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    public void testInnerJoin() throws Exception {
        List<ConcreteDepartment> list =
            jdbcManager
                .from(ConcreteDepartment.class)
                .innerJoin("employees")
                .getResultList();
        assertEquals(3, list.size());
        assertNotNull(list.get(0).employees);
        assertNotNull(list.get(1).employees);
        assertNotNull(list.get(2).employees);
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    public void testInnerJoin_noFetch() throws Exception {
        List<ConcreteDepartment> list =
            jdbcManager.from(ConcreteDepartment.class).innerJoin(
                "employees",
                false).getResultList();
        assertEquals(3, list.size());
        assertNull(list.get(0).employees);
        assertNull(list.get(1).employees);
        assertNull(list.get(2).employees);
    }
}