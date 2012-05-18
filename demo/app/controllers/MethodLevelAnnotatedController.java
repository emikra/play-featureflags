package controllers;

import featureflags.Feature;
import play.mvc.Controller;

public class MethodLevelAnnotatedController extends Controller {

    private static final String METHOD_LEVEL_FEATURE1 = "methodLevelFeature1";
    private static final String METHOD_LEVEL_FEATURE2 = "methodLevelFeature2";

    @Feature(METHOD_LEVEL_FEATURE1)
    public static void method1() {
        renderOkPage(METHOD_LEVEL_FEATURE1);
    }

    @Feature(METHOD_LEVEL_FEATURE2)
    public static void method2() {
        renderOkPage(METHOD_LEVEL_FEATURE2);
    }

    private static void renderOkPage(String featureName) {
        render("featureEnabled.html", featureName);
    }

}
