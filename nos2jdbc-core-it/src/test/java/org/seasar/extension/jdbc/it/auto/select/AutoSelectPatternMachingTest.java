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

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.extension.jdbc.it.entity.Pattern;
import org.seasar.extension.jdbc.where.SimpleWhere;

import nos2jdbc.core.it.NoS2JdbcExtension;

/**
 * @author taedium
 * 
 */
@ExtendWith(NoS2JdbcExtension.class)
class AutoSelectPatternMachingTest {

    private JdbcManager jdbcManager;

    /**
     * 
     * @throws Exception
     */
    @Test
    //@Prerequisite("#ENV != 'hsqldb'")
    //@DisabledIfSystemProperty(named = "database", matches = "hsqldb")
    void testLike_escape() throws Exception {
        Pattern pattern = new Pattern();
        pattern.value1 = "xABCy%z$";
        jdbcManager.insert(pattern).execute();
        pattern = jdbcManager.from(Pattern.class).where(new SimpleWhere().like("value1", "x%y$%z$$", '$')).getSingleResult();
        assertNotNull(pattern);
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void testStarts() throws Exception {
        Pattern pattern = new Pattern();
        pattern.value1 = "%$x";
        jdbcManager.insert(pattern).execute();
        pattern = jdbcManager.from(Pattern.class).where(new SimpleWhere().starts("value1", "%$")).getSingleResult();
        assertNotNull(pattern);
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void testEnds() throws Exception {
        Pattern pattern = new Pattern();
        pattern.value1 = "x%$";
        jdbcManager.insert(pattern).execute();
        pattern = jdbcManager.from(Pattern.class).where(new SimpleWhere().ends("value1", "%$")).getSingleResult();
        assertNotNull(pattern);
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    void testContains() throws Exception {
        Pattern pattern = new Pattern();
        pattern.value1 = "x%$y";
        jdbcManager.insert(pattern).execute();
        pattern = jdbcManager.from(Pattern.class).where(new SimpleWhere().contains("value1", "%$")).getSingleResult();
        assertNotNull(pattern);
    }
}
