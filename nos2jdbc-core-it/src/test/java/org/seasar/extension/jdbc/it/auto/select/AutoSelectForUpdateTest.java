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

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfSystemProperty;
import org.junit.jupiter.api.extension.ExtendWith;
import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.extension.jdbc.exception.BaseJoinNotFoundRuntimeException;
import org.seasar.extension.jdbc.exception.PropertyNotFoundRuntimeException;
import org.seasar.extension.jdbc.it.entity.Employee;
import org.seasar.extension.jdbc.manager.JdbcManagerImplementor;
import nos2jdbc.core.it.NoS2JdbcExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.seasar.extension.jdbc.SelectForUpdateType.*;

/**
 * @author taedium
 * 
 */
@ExtendWith(NoS2JdbcExtension.class)
class AutoSelectForUpdateTest {

    private JdbcManager jdbcManager;

    private JdbcManagerImplementor jdbcManagerImplementor;

    /**
     * 
     * @throws Exception
     */
    @Test
    void testForUpdate() throws Exception {
        if (!jdbcManagerImplementor.getDialect().supportsForUpdate(NORMAL, false)) {
            return;
        }
        jdbcManager.from(Employee.class).forUpdate().getResultList();
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    //@Prerequisite("#ENV != 'standard'")
    @DisabledIfSystemProperty(named = "database", matches = "standard")
    void testForUpdate_innerJoin() throws Exception {
        if (!jdbcManagerImplementor.getDialect().supportsForUpdate(NORMAL, false) || !jdbcManagerImplementor.getDialect().supportsInnerJoinForUpdate()) {
            return;
        }
        jdbcManager.from(Employee.class).innerJoin("department").forUpdate().getResultList();
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    //@Prerequisite("#ENV != 'standard'")
    @DisabledIfSystemProperty(named = "database", matches = "standard")
    void testForUpdate_leftOuterJoin() throws Exception {
        if (!jdbcManagerImplementor.getDialect().supportsForUpdate(NORMAL, false) || !jdbcManagerImplementor.getDialect().supportsOuterJoinForUpdate()) {
            return;
        }
        jdbcManager.from(Employee.class).leftOuterJoin("department").forUpdate().getResultList();
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void testForUpdate_paging_UnsupportedOperationException() throws Exception {
        if (!jdbcManagerImplementor.getDialect().supportsForUpdate(NORMAL, false)) {
            return;
        }
        assertThrows(UnsupportedOperationException.class, () -> jdbcManager.from(Employee.class).orderBy("employeeName").offset(5).limit(3).forUpdate().getResultList());
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void testForUpdate_UnsupportedOperationException() throws Exception {
        if (jdbcManagerImplementor.getDialect().supportsForUpdate(NORMAL, false)) {
            return;
        }
        assertThrows(UnsupportedOperationException.class, () -> jdbcManager.from(Employee.class).forUpdate());
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void testForUpdateNowait() throws Exception {
        if (!jdbcManagerImplementor.getDialect().supportsForUpdate(NOWAIT, false)) {
            return;
        }
        jdbcManager.from(Employee.class).forUpdateNowait().getResultList();
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void testForUpdateNowait_innerJoin() throws Exception {
        if (!jdbcManagerImplementor.getDialect().supportsForUpdate(NOWAIT, false)) {
            return;
        }
        jdbcManager.from(Employee.class).innerJoin("department").forUpdateNowait().getResultList();
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void testForUpdateNowait_leftOuterJoin() throws Exception {
        if (!jdbcManagerImplementor.getDialect().supportsForUpdate(NOWAIT, false) || !jdbcManagerImplementor.getDialect().supportsOuterJoinForUpdate()) {
            return;
        }
        jdbcManager.from(Employee.class).leftOuterJoin("department").forUpdateNowait().getResultList();
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void testForUpdateNowait_paging_UnsupportedOperationException() throws Exception {
        if (!jdbcManagerImplementor.getDialect().supportsForUpdate(NOWAIT, false)) {
            return;
        }
        assertThrows(UnsupportedOperationException.class, () -> jdbcManager.from(Employee.class).orderBy("employeeName").offset(5).limit(3).forUpdateNowait().getResultList());
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void testForUpdateNowait_UnsupportedOperationException() throws Exception {
        if (jdbcManagerImplementor.getDialect().supportsForUpdate(NOWAIT, false)) {
            return;
        }
        assertThrows(UnsupportedOperationException.class, () -> jdbcManager.from(Employee.class).forUpdateNowait());
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void testForUpdateNowaitWithTarget() throws Exception {
        if (!jdbcManagerImplementor.getDialect().supportsForUpdate(NOWAIT, true)) {
            return;
        }
        jdbcManager.from(Employee.class).forUpdateNowait("employeeName").getResultList();
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void testForUpdateNowaitWithTarget_innerJoin() throws Exception {
        if (!jdbcManagerImplementor.getDialect().supportsForUpdate(NOWAIT, true)) {
            return;
        }
        jdbcManager.from(Employee.class).innerJoin("department").forUpdateNowait("employeeName").getResultList();
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void testForUpdateNowaitWithTarget_innerJoin2() throws Exception {
        if (!jdbcManagerImplementor.getDialect().supportsForUpdate(NOWAIT, true)) {
            return;
        }
        jdbcManager.from(Employee.class).innerJoin("department").forUpdateNowait("department.location").getResultList();
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void testForUpdateNowaitWithTarget_leftOuterJoin() throws Exception {
        if (!jdbcManagerImplementor.getDialect().supportsForUpdate(NOWAIT, true) || !jdbcManagerImplementor.getDialect().supportsOuterJoinForUpdate()) {
            return;
        }
        jdbcManager.from(Employee.class).leftOuterJoin("department").forUpdateNowait("employeeName").getResultList();
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void testForUpdateNowaitWithTarget_leftOuterJoin2() throws Exception {
        if (!jdbcManagerImplementor.getDialect().supportsForUpdate(NOWAIT, true) || !jdbcManagerImplementor.getDialect().supportsOuterJoinForUpdate()) {
            return;
        }
        jdbcManager.from(Employee.class).leftOuterJoin("department").forUpdateNowait("department.location").getResultList();
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void testForUpdateNowaitWithTarget_paging_UnsupportedOperationException() throws Exception {
        if (!jdbcManagerImplementor.getDialect().supportsForUpdate(NOWAIT, true)) {
            return;
        }
        assertThrows(UnsupportedOperationException.class, () -> jdbcManager.from(Employee.class).orderBy("employeeName").offset(5).limit(3).forUpdateNowait("employeeName").getResultList());
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void testForUpdateNowaitWithTarget_UnsupportedOperationException() throws Exception {
        if (jdbcManagerImplementor.getDialect().supportsForUpdate(NOWAIT, true)) {
            return;
        }
        assertThrows(UnsupportedOperationException.class, () -> jdbcManager.from(Employee.class).forUpdateNowait("employeeName"));
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void testForUpdateNowaitWithTargets() throws Exception {
        if (!jdbcManagerImplementor.getDialect().supportsForUpdate(NOWAIT, true)) {
            return;
        }
        jdbcManager.from(Employee.class).forUpdateNowait("employeeName", "salary").getResultList();
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void testForUpdateNowaitWithTargets_innerJoin() throws Exception {
        if (!jdbcManagerImplementor.getDialect().supportsForUpdate(NOWAIT, true)) {
            return;
        }
        jdbcManager.from(Employee.class).innerJoin("department").forUpdateNowait("employeeName", "department.location").getResultList();
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void testForUpdateNowaitWithTargets_leftOuterJoin() throws Exception {
        if (!jdbcManagerImplementor.getDialect().supportsForUpdate(NOWAIT, true) || !jdbcManagerImplementor.getDialect().supportsOuterJoinForUpdate()) {
            return;
        }
        jdbcManager.from(Employee.class).leftOuterJoin("department").forUpdateNowait("employeeName", "department.location").getResultList();
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void testForUpdateNowaitWithTargets_pagingOffsetLimit() throws Exception {
        if (!jdbcManagerImplementor.getDialect().supportsForUpdate(NOWAIT, true)) {
            return;
        }
        assertThrows(UnsupportedOperationException.class, () -> jdbcManager.from(Employee.class).orderBy("employeeName").offset(5).limit(3).forUpdateNowait("employeeName", "salary").getResultList());
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void testForUpdateNowaitWithTargets_UnsupportedOperationException() throws Exception {
        if (jdbcManagerImplementor.getDialect().supportsForUpdate(NOWAIT, true)) {
            return;
        }
        assertThrows(UnsupportedOperationException.class, () -> jdbcManager.from(Employee.class).forUpdateNowait("employeeName", "salary"));
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void testForUpdateWait() throws Exception {
        if (!jdbcManagerImplementor.getDialect().supportsForUpdate(WAIT, false)) {
            return;
        }
        jdbcManager.from(Employee.class).forUpdateWait(1).getResultList();
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void testForUpdateWait_innerJoin() throws Exception {
        if (!jdbcManagerImplementor.getDialect().supportsForUpdate(WAIT, false)) {
            return;
        }
        jdbcManager.from(Employee.class).innerJoin("department").forUpdateWait(1).getResultList();
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void testForUpdateWait_leftOuterJoin() throws Exception {
        if (!jdbcManagerImplementor.getDialect().supportsForUpdate(WAIT, false) || !jdbcManagerImplementor.getDialect().supportsOuterJoinForUpdate()) {
            return;
        }
        jdbcManager.from(Employee.class).leftOuterJoin("department").forUpdateWait(1).getResultList();
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void testForUpdateWait_paging_UnsupportedOperationException() throws Exception {
        if (!jdbcManagerImplementor.getDialect().supportsForUpdate(WAIT, false)) {
            return;
        }
        assertThrows(UnsupportedOperationException.class, () -> jdbcManager.from(Employee.class).orderBy("employeeName").offset(5).limit(3).forUpdateWait(1).getResultList());
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void testForUpdateWait_UnsupportedOperationException() throws Exception {
        if (jdbcManagerImplementor.getDialect().supportsForUpdate(WAIT, false)) {
            return;
        }
        assertThrows(UnsupportedOperationException.class, () -> jdbcManager.from(Employee.class).forUpdateWait(1));
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void testForUpdateWaitWithTarget() throws Exception {
        if (!jdbcManagerImplementor.getDialect().supportsForUpdate(WAIT, true)) {
            return;
        }
        jdbcManager.from(Employee.class).forUpdateWait(1, "employeeName").getResultList();
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void testForUpdateWaitWithTarget_innerJoin() throws Exception {
        if (!jdbcManagerImplementor.getDialect().supportsForUpdate(WAIT, true)) {
            return;
        }
        jdbcManager.from(Employee.class).innerJoin("department").forUpdateWait(1, "employeeName").getResultList();
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void testForUpdateWaitWithTarget_innerJoin2() throws Exception {
        if (!jdbcManagerImplementor.getDialect().supportsForUpdate(WAIT, true)) {
            return;
        }
        jdbcManager.from(Employee.class).innerJoin("department").forUpdateWait(1, "department.location").getResultList();
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void testForUpdateWaitWithTarget_leftOuterJoin() throws Exception {
        if (!jdbcManagerImplementor.getDialect().supportsForUpdate(WAIT, true)) {
            return;
        }
        jdbcManager.from(Employee.class).leftOuterJoin("department").forUpdateWait(1, "employeeName").getResultList();
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void testForUpdateWaitWithTarget_leftOuterJoin2() throws Exception {
        if (!jdbcManagerImplementor.getDialect().supportsForUpdate(WAIT, true)) {
            return;
        }
        jdbcManager.from(Employee.class).leftOuterJoin("department").forUpdateWait(1, "department.location").getResultList();
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void testForUpdateWaitWithTarget_paging_UnsupportedOperationException() throws Exception {
        if (!jdbcManagerImplementor.getDialect().supportsForUpdate(WAIT, true)) {
            return;
        }
        assertThrows(UnsupportedOperationException.class, () -> jdbcManager.from(Employee.class).orderBy("employeeName").offset(5).limit(3).forUpdateWait(1, "employeeName").getResultList());
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void testForUpdateWaitWithTarget_UnsupportedOperationException() throws Exception {
        if (jdbcManagerImplementor.getDialect().supportsForUpdate(WAIT, true)) {
            return;
        }
        assertThrows(UnsupportedOperationException.class, () -> jdbcManager.from(Employee.class).forUpdateWait(1, "employeeName"));
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void testForUpdateWaitWithTargets() throws Exception {
        if (!jdbcManagerImplementor.getDialect().supportsForUpdate(WAIT, true)) {
            return;
        }
        jdbcManager.from(Employee.class).forUpdateWait(1, "employeeName", "salary").getResultList();
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void testForUpdateWaitWithTargets_innerJoin() throws Exception {
        if (!jdbcManagerImplementor.getDialect().supportsForUpdate(WAIT, true)) {
            return;
        }
        jdbcManager.from(Employee.class).innerJoin("department").forUpdateWait(1, "employeeName", "department.location").getResultList();
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void testForUpdateWaitWithTargets_leftOuterJoin() throws Exception {
        if (!jdbcManagerImplementor.getDialect().supportsForUpdate(WAIT, true)) {
            return;
        }
        jdbcManager.from(Employee.class).leftOuterJoin("department").forUpdateWait(1, "employeeName", "department.location").getResultList();
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void testForUpdateWaitWithTargets_paging_UnsupportedOperationException() throws Exception {
        if (!jdbcManagerImplementor.getDialect().supportsForUpdate(WAIT, true)) {
            return;
        }
        assertThrows(UnsupportedOperationException.class, () -> jdbcManager.from(Employee.class).orderBy("employeeName").offset(5).limit(3).forUpdateWait(1, "employeeName", "salary").getResultList());
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void testForUpdateWaitWithTargets_UnsupportedOperationException() throws Exception {
        if (jdbcManagerImplementor.getDialect().supportsForUpdate(WAIT, true)) {
            return;
        }
        assertThrows(UnsupportedOperationException.class, () -> jdbcManager.from(Employee.class).forUpdateWait(1, "employeeName", "salary"));
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void testForUpdateWithTarget() throws Exception {
        if (!jdbcManagerImplementor.getDialect().supportsForUpdate(NORMAL, true)) {
            return;
        }
        jdbcManager.from(Employee.class).forUpdate("employeeName").getResultList();
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void testForUpdateWithTarget_innerJoin() throws Exception {
        if (!jdbcManagerImplementor.getDialect().supportsForUpdate(NORMAL, true)) {
            return;
        }
        jdbcManager.from(Employee.class).innerJoin("department").forUpdate("employeeName").getResultList();
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void testForUpdateWithTarget_innerJoin2() throws Exception {
        if (!jdbcManagerImplementor.getDialect().supportsForUpdate(NORMAL, true)) {
            return;
        }
        jdbcManager.from(Employee.class).innerJoin("department").forUpdate("department.location").getResultList();
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void testForUpdateWithTarget_leftOuterJoin() throws Exception {
        if (!jdbcManagerImplementor.getDialect().supportsForUpdate(NORMAL, true) || !jdbcManagerImplementor.getDialect().supportsOuterJoinForUpdate()) {
            return;
        }
        jdbcManager.from(Employee.class).leftOuterJoin("department").forUpdate("employeeName").getResultList();
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void testForUpdateWithTarget_leftOuterJoin2() throws Exception {
        if (!jdbcManagerImplementor.getDialect().supportsForUpdate(NORMAL, true) || !jdbcManagerImplementor.getDialect().supportsOuterJoinForUpdate()) {
            return;
        }
        jdbcManager.from(Employee.class).leftOuterJoin("department").forUpdate("department.location").getResultList();
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void testForUpdateWithTarget_paging_UnsupportedOperationException() throws Exception {
        if (!jdbcManagerImplementor.getDialect().supportsForUpdate(NORMAL, true)) {
            return;
        }
        assertThrows(UnsupportedOperationException.class, () -> jdbcManager.from(Employee.class).orderBy("employeeName").offset(5).limit(3).forUpdate("employeeName").getResultList());
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void testForUpdateWithTarget_UnsupportedOperationException() throws Exception {
        if (jdbcManagerImplementor.getDialect().supportsForUpdate(NORMAL, true)) {
            return;
        }
        assertThrows(UnsupportedOperationException.class, () -> jdbcManager.from(Employee.class).forUpdate("employeeName"));
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void testForUpdateWithTargets() throws Exception {
        if (!jdbcManagerImplementor.getDialect().supportsForUpdate(NORMAL, true)) {
            return;
        }
        jdbcManager.from(Employee.class).forUpdate("employeeName", "salary").getResultList();
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void testForUpdateWithTargets_illegalPropertyName() throws Exception {
        if (!jdbcManagerImplementor.getDialect().supportsForUpdate(NORMAL, true)) {
            return;
        }
        assertThrows(PropertyNotFoundRuntimeException.class, () -> jdbcManager.from(Employee.class).forUpdate("illegal").getResultList());
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void testForUpdateWithTargets_illegalPropertyName2() throws Exception {
        if (!jdbcManagerImplementor.getDialect().supportsForUpdate(NORMAL, true)) {
            return;
        }
        assertThrows(BaseJoinNotFoundRuntimeException.class, () -> jdbcManager.from(Employee.class).innerJoin("department").forUpdate("illegal.location").getResultList());
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void testForUpdateWithTargets_illegalPropertyName3() throws Exception {
        if (!jdbcManagerImplementor.getDialect().supportsForUpdate(NORMAL, true)) {
            return;
        }
        try {
            jdbcManager.from(Employee.class).innerJoin("department").forUpdate("department").getResultList();
            assertThrows(PropertyNotFoundRuntimeException.class, () -> jdbcManager.from(Employee.class).innerJoin("department").forUpdate("department").getResultList());
        } catch (PropertyNotFoundRuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void testForUpdateWithTargets_innerJoin() throws Exception {
        if (!jdbcManagerImplementor.getDialect().supportsForUpdate(NORMAL, true)) {
            return;
        }
        jdbcManager.from(Employee.class).innerJoin("department").forUpdate("employeeName", "department.location").getResultList();
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void testForUpdateWithTargets_leftOuterJoin() throws Exception {
        if (!jdbcManagerImplementor.getDialect().supportsForUpdate(NORMAL, true) || !jdbcManagerImplementor.getDialect().supportsOuterJoinForUpdate()) {
            return;
        }
        jdbcManager.from(Employee.class).leftOuterJoin("department").forUpdate("employeeName", "department.location").getResultList();
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void testForUpdateWithTargets_paging_UnsupportedOperationException() throws Exception {
        if (!jdbcManagerImplementor.getDialect().supportsForUpdate(NORMAL, true)) {
            return;
        }
        assertThrows(UnsupportedOperationException.class, () -> jdbcManager.from(Employee.class).orderBy("employeeName").offset(5).limit(3).forUpdate("employeeName", "salary").getResultList());
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void testForUpdateWithTargets_UnsupportedOperationException() throws Exception {
        if (jdbcManagerImplementor.getDialect().supportsForUpdate(NORMAL, true)) {
            return;
        }
        assertThrows(UnsupportedOperationException.class, () -> jdbcManager.from(Employee.class).forUpdate("employeeName", "salary"));
    }
}
