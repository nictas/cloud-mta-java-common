package org.cloudfoundry.multiapps.common.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.cloudfoundry.multiapps.common.ContentException;
import org.junit.jupiter.api.Test;

class MapUtilTest {

    public static final Map<String, Object> TEST_PARAMETERS = Map.ofEntries(Map.entry("trueFlag", true), Map.entry("falseFlag", false),
                                                                            Map.entry("emptyFlag", null),
                                                                            Map.entry("incorrectTypeFlag1", "false"),
                                                                            Map.entry("incorrectTypeFlag2", "1"));

    @Test
    void testParseBooleanFlag() {
        assertTrue(MapUtil.parseBooleanFlag(TEST_PARAMETERS, "trueFlag", true));
        assertTrue(MapUtil.parseBooleanFlag(TEST_PARAMETERS, "trueFlag", false));
        assertTrue(MapUtil.parseBooleanFlag(TEST_PARAMETERS, "notPresentFlag", true));
        assertTrue(MapUtil.parseBooleanFlag(TEST_PARAMETERS, "emptyFlag", true));

        assertFalse(MapUtil.parseBooleanFlag(TEST_PARAMETERS, "falseFlag", true));
        assertFalse(MapUtil.parseBooleanFlag(TEST_PARAMETERS, "notPresentFlag", false));
        assertFalse(MapUtil.parseBooleanFlag(TEST_PARAMETERS, "emptyFlag", false));

        assertThrows(ContentException.class, () -> MapUtil.parseBooleanFlag(TEST_PARAMETERS, "incorrectTypeFlag1", true));
        assertThrows(ContentException.class, () -> MapUtil.parseBooleanFlag(TEST_PARAMETERS, "incorrectTypeFlag2", true));
    }
}
