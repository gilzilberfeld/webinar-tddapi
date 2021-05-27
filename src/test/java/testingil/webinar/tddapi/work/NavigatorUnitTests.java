package testingil.webinar.tddapi.work;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class NavigatorUnitTests {

	@Test
	void throw_when_navigating_without_starting_point() {
		Navigator nav = new Navigator();
		assertThrows(NotInitializedException.class, ()->
			nav.getDistanceFromDestination());
	}

}
