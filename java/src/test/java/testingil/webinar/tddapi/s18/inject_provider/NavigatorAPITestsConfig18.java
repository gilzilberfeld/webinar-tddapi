package testingil.webinar.tddapi.s18.inject_provider;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import testingil.webinar.tddapi.s18.DistanceProvider;
import testingil.webinar.tddapi.s18.NavigationController18;

@Configuration
public class NavigatorAPITestsConfig18 {
	
	@Bean
	public NavigationController18 navigationController18() {
		return new NavigationController18();
	}
	
	@Bean
	public DistanceProvider distanceProvider18() {
		return mock(DistanceProvider.class);
	}
}
