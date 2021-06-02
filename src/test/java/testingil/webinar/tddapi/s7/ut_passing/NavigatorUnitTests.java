package testingil.webinar.tddapi.s7.ut_passing;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import testingil.webinar.tddapi.s7.Distance;
import testingil.webinar.tddapi.s7.Location;
import testingil.webinar.tddapi.s7.Navigator;

import static org.hamcrest.MatcherAssert.assertThat;

class NavigatorUnitTests {

	@Test
	void when_created_distance_is_always_zero() {
		Location loc = new Location("London");
		Navigator nav = new Navigator(loc);
		Distance distance = nav.getDistanceFromDestination();
		assertThat(distance.inKm(), is(0));
	}

}
