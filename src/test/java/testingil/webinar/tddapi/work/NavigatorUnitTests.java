package testingil.webinar.tddapi.work;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.hamcrest.MatcherAssert.assertThat;

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
	
//	@Test
//	@DisplayName("2500 miles = 4000 kms")
//	void distance_is_calculated_in_km() {
//		Location startingPoint = new Location("New York City");
//		Navigator nav = new Navigator(startingPoint);
//		Location dest = new Location("Los Angeles");
//		nav.setDestination(dest);
//		Distance distance = nav.getDistanceFromDestination();
//		assertThat(distance.inKm(), is(4000));
//	}

}
