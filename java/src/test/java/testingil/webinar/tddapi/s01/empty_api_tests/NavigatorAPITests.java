package testingil.webinar.tddapi.s01.empty_api_tests;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;

class NavigatorAPITests {

	@Test
	void navigate_to_same_location_distance_is_zero() {
		Location location = new Location("New York");
		setStartPoint(location);
		setDestination(location);
		assertThat(distanceToDestination(), is(0));
	}

	@Test
	void navigate_and_drive_to_another_location_distance_is_reduced() {
		Location initialLocation = new Location("New York");
		setStartPoint(initialLocation);
		
		Location destination = new Location("Los Angeles");;
		setDestination(destination);
		
		int initialDistance = distanceToDestination();
		
		Location midLocation = new Location("Dallas");
		driveTo(midLocation); 
		
		assertThat(distanceToDestination(), is(lessThan(initialDistance)));
	}



	private int distanceToDestination() {
		return -1;
	}

	private void setDestination(Location location) {
	}

	private void setStartPoint(Location location) {
	}

	private void driveTo(Location midLocation) {
	}
}
