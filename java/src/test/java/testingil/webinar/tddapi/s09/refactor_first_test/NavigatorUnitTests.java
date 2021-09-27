package testingil.webinar.tddapi.s09.refactor_first_test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import testingil.webinar.tddapi.s09.Distance;
import testingil.webinar.tddapi.s09.DistanceProvider;
import testingil.webinar.tddapi.s09.Location;
import testingil.webinar.tddapi.s09.Navigator;

import static org.hamcrest.MatcherAssert.assertThat;


class NavigatorUnitTests {

	@Test
	void when_created_distance_is_always_zero() {
		Location loc = new Location("London");
		DistanceProvider distService = new DistanceProvider();
		Navigator nav = new Navigator(loc, distService);
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
