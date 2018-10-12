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
import org.junit.jupiter.api.condition.DisabledIf;
import org.junit.jupiter.api.extension.ExtendWith;
import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.extension.jdbc.it.entity.Pattern;
import org.seasar.extension.jdbc.where.SimpleWhere;
import nos2jdbc.core.it.NoS2JdbcExtension;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author taedium
 * 
 */
@ExtendWith(NoS2JdbcExtension.class)
public class AutoSelectPatternMachingTest {

    private JdbcManager jdbcManager;

    /**
     * 
     * @throws Exception
     */
    @Test
    //@Prerequisite("#ENV != 'hsqldb'")
    @DisabledIf("['hsqldb'].indexOf(systemProperty.get('database')) >= 0")
    public void testLike_escape() throws Exception {
        Pattern pattern = new Pattern();
        pattern.value = "xABCy%z$";
        jdbcManager.insert(pattern).execute();
        pattern = jdbcManager.from(Pattern.class).where(new SimpleWhere().like("value", "x%y$%z$$", '$')).getSingleResult();
        assertNotNull(pattern);
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    public void testStarts() throws Exception {
        Pattern pattern = new Pattern();
        pattern.value = "%$x";
        jdbcManager.insert(pattern).execute();
        pattern = jdbcManager.from(Pattern.class).where(new SimpleWhere().starts("value", "%$")).getSingleResult();
        assertNotNull(pattern);
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    public void testEnds() throws Exception {
        Pattern pattern = new Pattern();
        pattern.value = "x%$";
        jdbcManager.insert(pattern).execute();
        pattern = jdbcManager.from(Pattern.class).where(new SimpleWhere().ends("value", "%$")).getSingleResult();
        assertNotNull(pattern);
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    public void testContains() throws Exception {
        Pattern pattern = new Pattern();
        pattern.value = "x%$y";
        jdbcManager.insert(pattern).execute();
        pattern = jdbcManager.from(Pattern.class).where(new SimpleWhere().contains("value", "%$")).getSingleResult();
        assertNotNull(pattern);
    }
}
