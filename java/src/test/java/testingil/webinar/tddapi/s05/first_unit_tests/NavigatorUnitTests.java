package testingil.webinar.tddapi.s05.first_unit_tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import testingil.webinar.tddapi.s05.Navigator;
import testingil.webinar.tddapi.s05.NotInitializedException;

class NavigatorUnitTests {

	@Test
	void throw_when_navigating_without_starting_point() {
		Navigator nav = new Navigator();
		assertThrows(NotInitializedException.class, ()->
			nav.getDistanceFromDestination());
	}

}
