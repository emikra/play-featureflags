package util;

import models.featureflags.Feature;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FeatureTestUtil {


    public static void assertExistsInDb(String name) {
        assertExistsInDb(name, true);
    }

    public static void assertDoesntExistInDb(String name) {
        assertExistsInDb(name, false);
    }

    private static void assertExistsInDb(String name, boolean exists) {
        List<Feature> features = Feature.find("byName", name).fetch();
        if (exists) {
            assertFalse(features.isEmpty());
            assertTrue(features.size() == 1);
        } else {
            assertTrue(features.isEmpty());
        }
    }


    public static void assertEnabledInDb(String name) {
        assertEnabledInDb(name, true);
    }

    public static void assertDisabledInDb(String name) {
        assertEnabledInDb(name, false);
    }

    private static void assertEnabledInDb(String name, boolean enabled) {
        Feature f = Feature.find("byName", name).first();
        if (enabled) {
            assertTrue(f.enabled);
        } else {
            assertFalse(f.enabled);
        }
    }
}
