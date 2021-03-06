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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.extension.jdbc.it.entity.CompKeyAddress;
import org.seasar.extension.jdbc.it.entity.CompKeyEmployee;
import nos2jdbc.core.it.NoS2JdbcExtension;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author taedium
 * 
 */
@ExtendWith(NoS2JdbcExtension.class)
class CompKeyOneToOneTest {

    private JdbcManager jdbcManager;

    /**
     * 
     * @throws Exception
     */
    @Test
    void testLeftOuterJoin_fromOwnerToInverse() throws Exception {
        List<CompKeyEmployee> list = jdbcManager.from(CompKeyEmployee.class).leftOuterJoin("address").getResultList();
        assertEquals(14, list.size());
        for (CompKeyEmployee e : list) {
            assertNotNull(e.address);
        }
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void testLeftOuterJoin_fromOwnerToInverse_noFetch() throws Exception {
        List<CompKeyEmployee> list = jdbcManager.from(CompKeyEmployee.class).leftOuterJoin("address", false).getResultList();
        assertEquals(14, list.size());
        for (CompKeyEmployee e : list) {
            assertNull(e.address);
        }
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void testInnerJoin_fromOwnerToInverse() throws Exception {
        List<CompKeyEmployee> list = jdbcManager.from(CompKeyEmployee.class).innerJoin("address").getResultList();
        assertEquals(14, list.size());
        for (CompKeyEmployee e : list) {
            assertNotNull(e.address);
        }
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void testInnerJoin_fromOwnerToInverse_noFetch() throws Exception {
        List<CompKeyEmployee> list = jdbcManager.from(CompKeyEmployee.class).innerJoin("address", false).getResultList();
        assertEquals(14, list.size());
        for (CompKeyEmployee e : list) {
            assertNull(e.address);
        }
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void testLeftOuterJoin_fromInverseToOwner() throws Exception {
        List<CompKeyAddress> list = jdbcManager.from(CompKeyAddress.class).leftOuterJoin("employee").getResultList();
        assertEquals(14, list.size());
        for (CompKeyAddress e : list) {
            assertNotNull(e.employee);
        }
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void testLeftOuterJoin_fromInverseToOwner_noFetch() throws Exception {
        List<CompKeyAddress> list = jdbcManager.from(CompKeyAddress.class).leftOuterJoin("employee", false).getResultList();
        assertEquals(14, list.size());
        for (CompKeyAddress e : list) {
            assertNull(e.employee);
        }
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void testInnerJoin_fromInverseToOwner() throws Exception {
        List<CompKeyAddress> list = jdbcManager.from(CompKeyAddress.class).innerJoin("employee").getResultList();
        assertEquals(14, list.size());
        for (CompKeyAddress e : list) {
            assertNotNull(e.employee);
        }
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void testInnerJoin_fromInverseToOwner_noFetch() throws Exception {
        List<CompKeyAddress> list = jdbcManager.from(CompKeyAddress.class).innerJoin("employee", false).getResultList();
        assertEquals(14, list.size());
        for (CompKeyAddress e : list) {
            assertNull(e.employee);
        }
    }
}
