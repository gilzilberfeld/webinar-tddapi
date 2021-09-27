package testingil.webinar.tddapi.sfinal.inject_nav;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.lessThan;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import testingil.webinar.tddapi.sfinal.Distance;
import testingil.webinar.tddapi.sfinal.DistanceProvider;
import testingil.webinar.tddapi.sfinal.Location;
import testingil.webinar.tddapi.sfinal.NavigationController;

@WebMvcTest
@ContextConfiguration(classes = NavigatorAPITestsConfig.class)
class NavigatorAPITests {

	MockMvc mockMvc;
	@Autowired NavigationController navController;
	@Autowired DistanceProvider mockProvider;

	@BeforeEach
	public void setup() {
		this.mockMvc = MockMvcBuilders
				.standaloneSetup(navController).build();
	}

	@Test
	void navigate_to_same_location_distance_is_zero() throws Exception {
		set_distance_to_be(0);

		setStartPoint(new Location("New York"));
		setDestination(new Location("New York"));
		
		assertThat(distanceToDestination(), is(0));
	}


	@Test
	void navigate_and_drive_to_another_location_distance_is_reduced() throws Exception {
		set_distances_to_be(2500, 1400);

		setStartPoint(new Location("New York"));
		setDestination(new Location("Los Angeles"));
		int initialDistance = distanceToDestination();
		
		driveTo(new Location("Dallas")); 
		assertThat(distanceToDestination(), is(lessThan(initialDistance)));
	}

	
	private void setDestination(Location location) throws JsonProcessingException, Exception {
		mockMvc.perform(post("/navigator/destination")
				.contentType(MediaType.APPLICATION_JSON)
				.content(toJson(location)))
        .andExpect(status().isOk());
	}

	private void setStartPoint(Location location) throws Exception {
		mockMvc.perform(post("/navigator/startpoint")
				.contentType(MediaType.APPLICATION_JSON)
				.content(toJson(location)))
        .andExpect(status().isOk());
	}

	private void driveTo(Location midLocation) throws Exception {
		setStartPoint(midLocation);
	}
	
	private int distanceToDestination() throws JsonProcessingException, Exception {
		MvcResult result = 
			mockMvc.perform(get("/navigator/distance"))
	        .andExpect(status().isOk())
	        .andReturn();
		
		String content = result.getResponse().getContentAsString();
		Distance distance = distanceFromJson(content);
		return distance.inKm();
	}

	private Distance distanceFromJson(String content) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(content, Distance.class);
	}

	private String toJson(Location loc) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(loc);
	}

	private void set_distance_to_be(int distanceInMiles) {
		Distance distance = new Distance();
		distance.value = distanceInMiles;
		when(mockProvider.getDistance(
				Mockito.any(Location.class), 
				Mockito.any(Location.class)))
		.thenReturn(distance);
	}
	
	private void set_distances_to_be(int distance1, int distance2) {
		Distance initDistance = new Distance();
		initDistance.value = distance1;
		
		Distance midDistance = new Distance();
		midDistance.value = distance2;
		
		when(mockProvider.getDistance(
				Mockito.any(Location.class), 
				Mockito.any(Location.class)))
			.thenReturn(initDistance)
			.thenReturn(midDistance);
	}

}
