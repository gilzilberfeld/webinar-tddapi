package testingil.webinar.tddapi.sfinal.inject_nav;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import testingil.webinar.tddapi.sfinal.DistanceProvider;
import testingil.webinar.tddapi.sfinal.NavigationController;
import testingil.webinar.tddapi.sfinal.Navigator;

@Configuration
public class NavigatorAPITestsConfig {
	
	@Bean
	public Navigator navigator() {
		return new Navigator(distanceProvider());
	}
	
	@Bean
	public NavigationController navigationController() {
		return new NavigationController();
	}
	
	@Bean
	public DistanceProvider distanceProvider() {
		return mock(DistanceProvider.class);
	}
}
