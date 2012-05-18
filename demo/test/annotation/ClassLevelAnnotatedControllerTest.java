package annotation;

import models.featureflags.Feature;
import org.junit.Before;
import org.junit.Test;
import play.test.Fixtures;
import play.test.FunctionalTest;

public class ClassLevelAnnotatedControllerTest extends FunctionalTest {

    private static final String FEATURE = "classLevelFeature";
    private static final String METHOD1_ROUTE = "/classLevelAnnotation/method1";
    private static final String METHOD2_ROUTE = "/classLevelAnnotation/method2";

    @Before
    public void initData() {
        Fixtures.deleteAll();
        Fixtures.load("annotation/ClassLevelAnnotatedControllerTest.yml");
    }

    @Test
    public void allMethodsInControllerShouldAnswer404WhenFeatureIsDisabled() {
        Feature.disable(FEATURE);
        assertIsNotFound(GET(METHOD1_ROUTE));
        assertIsNotFound(GET(METHOD2_ROUTE));
    }

    @Test
    public void allMethodsInControllerShouldRenderContentWhenFeatureIsEnabled() {
        Feature.enable(FEATURE);
        assertIsOk(GET(METHOD1_ROUTE));
        assertIsOk(GET(METHOD2_ROUTE));
    }


}
