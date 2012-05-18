package models.featureflags;

import org.junit.Test;
import play.Play;
import play.test.UnitTest;

public class FeatureTest extends UnitTest {

    @Test
    public void newFeatureIsDisabledByDefaultInProdMode() {
        Play.mode = Play.Mode.PROD;
        Feature feature = new Feature("someFeatureName");
        assertFalse(feature.enabled);
    }

    @Test
    public void newFeatureIsEnabledByDefaultInDevMode(){
        Play.mode = Play.Mode.DEV;
        Feature feature = new Feature("someFeatureName");
        assertTrue(feature.enabled);
    }


    
}
