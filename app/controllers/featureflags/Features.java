package controllers.featureflags;

import models.featureflags.Feature;
import play.mvc.Controller;

import java.util.List;

public class Features extends Controller {

    public static void adminPage() {
        List<Feature> features = Feature.findAll();
        render(features);
    }

    public static void switchFeature(Long id) {
        Feature feature = Feature.findById(id);
        feature.enabled = !feature.enabled;
        feature.save();
        adminPage();
    }


}
