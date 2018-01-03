package si.fri.rso.katalogdogodkov;

import org.eclipse.microprofile.health.Health;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@Health
@ApplicationScoped
public class KatalogDogodkovHealthCheck implements HealthCheck {

        @Inject
        private RestProperties restProperties;

        @Override
        public HealthCheckResponse call() {

            if (restProperties.isHealthy()) {
                return HealthCheckResponse.named(KatalogDogodkovHealthCheck.class.getSimpleName()).up().build();
            } else {
                return HealthCheckResponse.named(KatalogDogodkovHealthCheck.class.getSimpleName()).down().build();
            }

        }


}
