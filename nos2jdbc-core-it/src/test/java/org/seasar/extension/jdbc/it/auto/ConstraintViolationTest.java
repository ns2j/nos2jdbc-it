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

import javax.persistence.EntityExistsException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIf;
import org.junit.jupiter.api.extension.ExtendWith;
import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.extension.jdbc.exception.IdPropertyNotAssignedRuntimeException;
import org.seasar.extension.jdbc.it.entity.ConstraintChecking;
import org.seasar.framework.exception.SQLRuntimeException;
import nos2jdbc.core.it.NoS2JdbcExtension;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author taedium
 * 
 */
@ExtendWith(NoS2JdbcExtension.class)
//@Prerequisite("#ENV not in {'standard'}")
@DisabledIf("['standard'].indexOf(systemProperty.get('database')) >= 0")
public class ConstraintViolationTest {

    private JdbcManager jdbcManager;

    /**
     * 
     * @throws Exception
     */
    @Test
    void testUniqueConstraint_primaryKey() throws Exception {
        ConstraintChecking checking = new ConstraintChecking();
        checking.primaryKey = 1;
        checking.uniqueKey = 1;
        checking.foreignKey = 1;
        checking.notNull = 1;
        checking.checkConstraint = 1;
        jdbcManager.insert(checking).execute();
        ConstraintChecking checking2 = new ConstraintChecking();
        checking2.primaryKey = 1;
        checking2.uniqueKey = 2;
        checking2.foreignKey = 1;
        checking2.notNull = 1;
        checking2.checkConstraint = 1;
        assertThrows(EntityExistsException.class, () -> jdbcManager.insert(checking2).execute());
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void testUniqueConstraint_uniqueKey() throws Exception {
        ConstraintChecking checking = new ConstraintChecking();
        checking.primaryKey = 1;
        checking.uniqueKey = 1;
        checking.foreignKey = 1;
        checking.notNull = 1;
        checking.checkConstraint = 1;
        jdbcManager.insert(checking).execute();
        ConstraintChecking checking2 = new ConstraintChecking();
        checking2.primaryKey = 2;
        checking2.uniqueKey = 1;
        checking2.foreignKey = 1;
        checking2.notNull = 1;
        checking2.checkConstraint = 1;
        assertThrows(EntityExistsException.class, () -> jdbcManager.insert(checking2).execute());
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void testReferentialIntegrityConstraint() throws Exception {
        ConstraintChecking checking = new ConstraintChecking();
        checking.primaryKey = 1;
        checking.uniqueKey = 1;
        checking.foreignKey = 99;
        checking.notNull = 1;
        checking.checkConstraint = 1;
        assertThrows(SQLRuntimeException.class, () -> jdbcManager.insert(checking).execute());
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void testNotNullConstraint() throws Exception {
        ConstraintChecking checking = new ConstraintChecking();
        checking.primaryKey = 1;
        checking.uniqueKey = 1;
        checking.foreignKey = 1;
        checking.notNull = null;
        checking.checkConstraint = 1;
        assertThrows(SQLRuntimeException.class, () -> jdbcManager.insert(checking).execute());
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void testNotNullConstraint_primaryKey() throws Exception {
        ConstraintChecking checking = new ConstraintChecking();
        checking.primaryKey = null;
        checking.uniqueKey = 1;
        checking.foreignKey = 1;
        checking.notNull = 1;
        checking.checkConstraint = 1;
        assertThrows(IdPropertyNotAssignedRuntimeException.class, () -> jdbcManager.insert(checking).execute());
    }

    /**
     * 
     * @throws Exception
     */
    //@Prerequisite("#ENV not in {'mysql'}")
    @DisabledIf("['mysql'].indexOf(systemProperty.get('database')) >= 0")
    @Test
    void testCheckConstraint() throws Exception {
        ConstraintChecking checking = new ConstraintChecking();
        checking.primaryKey = 1;
        checking.uniqueKey = 1;
        checking.foreignKey = 1;
        checking.notNull = 1;
        checking.checkConstraint = -1;
        assertThrows(SQLRuntimeException.class, () -> jdbcManager.insert(checking).execute());
    }
}
