package featureflags;

import play.Play;
import play.PlayPlugin;
import play.mvc.results.NotFound;

import java.lang.reflect.Method;

public class FeatureFlagsPlugin extends PlayPlugin {

    @Override
    public void beforeActionInvocation(Method method) {
        Feature annotation = getAnnotation(method);
        if (annotation != null) {
            if (isFeatureEnabled(annotation)) {
                throw new NotFound("feature not enabled: " + annotation.value());
            }
        }
    }

    private boolean isFeatureEnabled(Feature featureAnnotation) {
        return !models.featureflags.Feature.isEnabled(featureAnnotation.value());
    }


    private Feature getAnnotation(Method method) {
        Feature annotation = method.getAnnotation(Feature.class);
        if (annotation == null) {
            return method.getDeclaringClass().getAnnotation(Feature.class);
        } else {
            return annotation;
        }
    }


}
