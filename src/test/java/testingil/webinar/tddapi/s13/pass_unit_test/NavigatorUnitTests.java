package testingil.webinar.tddapi.s13.pass_unit_test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import testingil.webinar.tddapi.s13.Distance;
import testingil.webinar.tddapi.s13.Location;
import testingil.webinar.tddapi.s13.Navigator;
import testingil.webinar.tddapi.s13.DistanceProvider;

class NavigatorUnitTests {

	@Test
	void when_created_distance_is_always_zero() {
		Location loc = new Location("London");
		DistanceProvider mockDistService = 
				mock(DistanceProvider.class, RETURNS_DEEP_STUBS);
		Navigator nav = new Navigator(loc, mockDistService);
		Distance distance = nav.getDistanceFromDestination();
		assertThat(distance.inKm(), is(0));
	}
	
	@Test
	@DisplayName("2500 miles = 4000 kms")
	void distance_is_calculated_in_km() {
		DistanceProvider mockDistService = 
				mock(DistanceProvider.class);

		Distance distanceInMiles = new Distance();
		distanceInMiles.value = 2500;
		when(mockDistService.getDistance(
				Mockito.any(Location.class), 
				Mockito.any(Location.class)))
				.thenReturn(distanceInMiles);
		
		Location startingPoint = new Location("New York City");
		Navigator nav = new Navigator(startingPoint, mockDistService);
		Location dest = new Location("Los Angeles");
		nav.setDestination(dest);
		Distance distance = nav.getDistanceFromDestination();
		assertThat(distance.inKm(), is(4000));
	}

}
