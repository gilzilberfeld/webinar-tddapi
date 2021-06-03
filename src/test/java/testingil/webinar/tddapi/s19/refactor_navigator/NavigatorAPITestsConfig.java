package testingil.webinar.tddapi.s19.refactor_navigator;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import testingil.webinar.tddapi.s19.NavigationController19;
import testingil.webinar.tddapi.s19.DistanceProvider;

@Configuration
public class NavigatorAPITestsConfig {
	
	@Bean
	public NavigationController19 navigationController() {
		return new NavigationController19();
	}
	
	@Bean
	public DistanceProvider distanceProvider() {
		return mock(DistanceProvider.class);
	}
}
