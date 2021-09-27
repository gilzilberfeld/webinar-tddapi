package testingil.webinar.tddapi.s08.added_test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import testingil.webinar.tddapi.s08.Distance;
import testingil.webinar.tddapi.s08.Location;
import testingil.webinar.tddapi.s08.Navigator;

import static org.hamcrest.MatcherAssert.assertThat;

class NavigatorUnitTests {

	@Test
	void when_created_distance_is_always_zero() {
		Location loc = new Location("London");
		Navigator nav = new Navigator(loc);
		Distance distance = nav.getDistanceFromDestination();
		assertThat(distance.inKm(), is(0));
	}
	
	@Test
	@DisplayName("2500 miles = 4000 kms")
	void distance_is_calculated_in_km() {
		Location startingPoint = new Location("New York City");
		Navigator nav = new Navigator(startingPoint);
		
		Location dest = new Location("Los Angeles");
		nav.setDestination(dest);
		
		Distance distance = nav.getDistanceFromDestination();
		assertThat(distance.inKm(), is(4000));
	}

}
