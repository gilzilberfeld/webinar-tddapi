package testingil.webinar.tddapi.s19.refactor_navigator;

import static org.mockito.Mockito.mock;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import testingil.webinar.tddapi.s19.DistanceProvider;
import testingil.webinar.tddapi.s19.NavigationController19;

@Configuration
public class NavigatorAPITestsConfig19 {
	
	@Bean
	public NavigationController19 navigationController19() {
		return new NavigationController19();
	}
	
	@Bean
	public DistanceProvider distanceProvider19() {
		return mock(DistanceProvider.class);
	}
}
