package testingil.webinar.tddapi.work;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.LessThan;

class NavigatorAPITests {

	@Test
	void navigate_to_same_location_distance_is_zero() {
		Location location = new Location("New York");
		setStartPoint(location);
		setDestination(location);
		assertThat(distanceTo(location), is(0));
	}

	@Test
	void navigate_and_drive_to_another_location_distance_is_reduced() {
		Location initialLocation = new Location("New York");
		setStartPoint(initialLocation);
		Location destination = new Location("Los Angeles");;
		setDestination(destination);
		int initialDistance = distanceTo(destination);
		Location midLocation = new Location("Dallas");
		driveTo(midLocation); 
		assertThat(distanceTo(destination), is(lessThan(initialDistance)));
	}



	private int distanceTo(Location location) {
		return -1;
	}

	private void setDestination(Location location) {
	}

	private void setStartPoint(Location location) {
	}

	private void driveTo(Location midLocation) {
	}
}
