package testingil.webinar.tddapi.s18.inject_provider;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import testingil.webinar.tddapi.s18.DistanceProvider;
import testingil.webinar.tddapi.s18.NavigationController18;

@Configuration
public class NavigatorAPITestsConfig {
	
	@Bean
	public NavigationController18 navigationController() {
		return new NavigationController18();
	}
	
	@Bean
	public DistanceProvider distanceProvider() {
		return mock(DistanceProvider.class);
	}
}
