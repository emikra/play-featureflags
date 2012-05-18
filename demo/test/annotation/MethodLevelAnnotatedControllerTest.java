package annotation;

import models.featureflags.Feature;
import org.junit.Before;
import org.junit.Test;
import play.test.Fixtures;
import play.test.FunctionalTest;

public class MethodLevelAnnotatedControllerTest extends FunctionalTest {

    private static final String FEATURE1 = "methodLevelFeature1";
    private static final String FEATURE1_ROUTE = "/methodLevelAnnotation/method1";

    private static final String FEATURE2 = "methodLevelFeature2";
    private static final String FEATURE2_ROUTE = "/methodLevelAnnotation/method2";


    @Before
    public void initData() {
        Fixtures.deleteAll();
        Fixtures.load("annotation/MethodLevelAnnotatedControllerTest.yml");
    }

    @Test
    public void method1ShouldAnswerNotFoundWhenFeature1IsDisabled() {
        Feature.disable(FEATURE1);        
        assertIsNotFound(GET(FEATURE1_ROUTE));
    }


    @Test
    public void method2ShouldAnswerNotFoundWhenFeature2IsDisabled() {
        Feature.disable(FEATURE2);
        assertIsNotFound(GET(FEATURE2_ROUTE));
    }


    @Test
    public void method1ShouldRenderContentWhenFeature1IsEnabled() {
        Feature.enable(FEATURE1);
        assertIsOk(GET(FEATURE1_ROUTE));
    }


    @Test
    public void method2ShouldRenderContentWhenFeature2IsEnabled() {
        Feature.enable(FEATURE2);
        assertIsOk(GET(FEATURE2_ROUTE));
    }

}
