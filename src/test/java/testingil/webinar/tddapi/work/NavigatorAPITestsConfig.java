package testingil.webinar.tddapi.work;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NavigatorAPITestsConfig {
	
	@Bean
	public NavigationController navigationController() {
		return new NavigationController();
	}
	
	@Bean
	public DistanceProvider distanceProvider() {
		return mock(DistanceProvider.class);
	}
}
