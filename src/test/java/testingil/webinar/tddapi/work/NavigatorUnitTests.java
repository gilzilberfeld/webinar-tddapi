package testingil.webinar.tddapi.work;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class NavigatorUnitTests {

	private DistanceProvider mockDistService;
	private Navigator nav;

	@BeforeEach
	public void setup() {
		mockDistService = mock(DistanceProvider.class, RETURNS_DEEP_STUBS);		
	}
	
	@Test
	void when_created_distance_is_always_zero() {
		Location loc = new Location("London");
		nav = new Navigator(loc, mockDistService);
		Distance distance = nav.getDistanceFromDestination();
		assertThat(distance.inKm(), is(0));
	}
	
	@Test
	@DisplayName("2500 miles = 4000 kms")
	void distance_is_calculated_in_km() {
		createMockDistance(2500);
		Location startingPoint = new Location("New York City");
		Location dest = new Location("Los Angeles");

		nav = new Navigator(startingPoint, mockDistService);
		nav.setDestination(dest);
		
		Distance distance = nav.getDistanceFromDestination();
		assertThat(distance.inKm(), is(4000));
	}

	@Test
	void provider_called_correctly() {
		Location startingPoint = new Location("New York City");
		Location dest = new Location("Los Angeles");

		nav = new Navigator(startingPoint, mockDistService);
		nav.setDestination(dest);
		nav.getDistanceFromDestination();

		verify(mockDistService).getDistance(startingPoint, dest);
	}

	private void createMockDistance(int distance) {
		Distance distanceInMiles = new Distance();
		distanceInMiles.value = distance;
		when(mockDistService.getDistance(
				Mockito.any(Location.class), 
				Mockito.any(Location.class)))
				.thenReturn(distanceInMiles);
	}

}
