package models.featureflags;

import org.junit.Before;
import org.junit.Test;
import play.Play;
import play.test.Fixtures;
import play.test.UnitTest;

import static util.FeatureTestUtil.*;

public class FeatureIntegrationTest extends UnitTest {

    private static final String ENABLED_FEATURE = "enabledFeature";
    private static final String DISABLED_FEATURE = "disabledFeature";
    private static final String NEW_FEATURE = "newFeature";

    @Before
    public void loadInitialData() {
        Fixtures.deleteAll();
        Fixtures.load("models/featureflags/FeatureIntegrationTest.yml");
    }

    @Test
    public void isEnabledReturnsFalseIfFeatureIsDisabledInDb() {
        assertExistsInDb(DISABLED_FEATURE);
        assertFalse(Feature.isEnabled(DISABLED_FEATURE));
    }

    @Test
    public void isEnabledReturnsTrueIfFeatureIsEnabledInDb() {
        assertExistsInDb(ENABLED_FEATURE);
        assertTrue(Feature.isEnabled(ENABLED_FEATURE));
    }

    @Test
    public void isEnabledCreatesNewFeatureInDbIfItDoesntExistAndThenReturnsFalseInProdMode() {
        Play.mode = Play.Mode.PROD;
        assertDoesntExistInDb(NEW_FEATURE);
        assertFalse(Feature.isEnabled(NEW_FEATURE));
        assertExistsInDb(NEW_FEATURE);
    }

    @Test
    public void isEnabledCreatesNewFeatureInDbIfItDoesntExistAndThenReturnsTrueInDevMode() {
        Play.mode = Play.Mode.DEV;
        assertDoesntExistInDb(NEW_FEATURE);
        assertTrue(Feature.isEnabled(NEW_FEATURE));
        assertExistsInDb(NEW_FEATURE);
    }


    @Test
    public void enablingAnAlreadyEnabledFeatureDoesntChangeAThing() {
        assertEnabledInDb(ENABLED_FEATURE);
        Feature.enable(ENABLED_FEATURE);
        assertEnabledInDb(ENABLED_FEATURE);
    }

    @Test
    public void disablingAnAlreadyDisabledFeatureDoesntChangeAThing() {
        assertDisabledInDb(DISABLED_FEATURE);
        Feature.disable(DISABLED_FEATURE);
        assertDisabledInDb(DISABLED_FEATURE);
    }

    @Test
    public void enablingADisabledFeatureChangesTheFlagInTheDb() {
        assertDisabledInDb(DISABLED_FEATURE);
        Feature.enable(DISABLED_FEATURE);
        assertEnabledInDb(DISABLED_FEATURE);
    }

    @Test
    public void disablingAnEnabledFeatureChangesTheFlagInTheDb() {
        assertEnabledInDb(ENABLED_FEATURE);
        Feature.disable(ENABLED_FEATURE);
        assertDisabledInDb(ENABLED_FEATURE);
    }

    @Test
    public void enablingUnexistingFeatureCreatesItInTheDb(){
        assertDoesntExistInDb(NEW_FEATURE);
        Feature.enable(NEW_FEATURE);
        assertExistsInDb(NEW_FEATURE);
        assertEnabledInDb(NEW_FEATURE);
    }

    @Test
    public void disablingAnUnexistingFeatureCreatesItInTheDb(){
        assertDoesntExistInDb(NEW_FEATURE);
        Feature.disable(NEW_FEATURE);
        assertExistsInDb(NEW_FEATURE);
        assertDisabledInDb(NEW_FEATURE);
    }

}
