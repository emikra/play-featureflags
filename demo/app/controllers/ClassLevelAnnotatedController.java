package controllers;


import featureflags.Feature;
import play.mvc.Controller;

@Feature("classLevelFeature")
public class ClassLevelAnnotatedController extends Controller {

    public static void method1() {
        renderOkPage();
    }


    public static void method2() {
        renderOkPage();
    }

    private static void renderOkPage() {
        String featureName = "classLevelFeature";
        render("featureEnabled.html", featureName);
    }

}
