package testingil.webinar.tddapi.s19.refactor_navigator;

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

import testingil.webinar.tddapi.s19.Distance;
import testingil.webinar.tddapi.s19.DistanceProvider;
import testingil.webinar.tddapi.s19.Location;
import testingil.webinar.tddapi.s19.NavigationController19;

@WebMvcTest
@ContextConfiguration(classes = NavigatorAPITestsConfig19.class)
class NavigatorAPITests {

	MockMvc mockMvc;
	@Autowired NavigationController19 navController;
	@Autowired DistanceProvider mockProvider;

	@BeforeEach
	public void setup() {
		this.mockMvc = MockMvcBuilders
				.standaloneSetup(navController).build();
	}

	@Test
	void navigate_to_same_location_distance_is_zero() throws Exception {
		Distance distance = new Distance();
		distance.value = 0;
		
		when(mockProvider.getDistance(
				Mockito.any(Location.class), 
				Mockito.any(Location.class)))
			.thenReturn(distance);

		
		Location location = new Location("New York");
		setStartPoint(location);
		setDestination(location);
		assertThat(distanceToDestination(), is(0));
	}

	@Test
	void navigate_and_drive_to_another_location_distance_is_reduced() throws Exception {
		Distance initDistance = new Distance();
		initDistance.value = 2500;
		
		Distance midDistance = new Distance();
		midDistance.value = 1400;
		
		when(mockProvider.getDistance(
				Mockito.any(Location.class), 
				Mockito.any(Location.class)))
			.thenReturn(initDistance)
			.thenReturn(midDistance);

		Location initialLocation = new Location("New York");
		setStartPoint(initialLocation);
		Location destination = new Location("Los Angeles");;
		setDestination(destination);
		int initialDistance = distanceToDestination();
		Location midLocation = new Location("Dallas");
		driveTo(midLocation); 
		assertThat(distanceToDestination(), is(lessThan(initialDistance)));
	}

	
	
	private void setDestination(Location location) throws JsonProcessingException, Exception {
		mockMvc.perform(post("/navigator19/destination")
				.contentType(MediaType.APPLICATION_JSON)
				.content(toJson(location)))
        .andExpect(status().isOk());
	}

	private void setStartPoint(Location location) throws Exception {
		mockMvc.perform(post("/navigator19/startpoint")
				.contentType(MediaType.APPLICATION_JSON)
				.content(toJson(location)))
        .andExpect(status().isOk());
	}

	private void driveTo(Location midLocation) throws Exception {
		setStartPoint(midLocation);
	}
	
	private int distanceToDestination() throws JsonProcessingException, Exception {
		MvcResult result = 
			mockMvc.perform(get("/navigator19/distance"))
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

}
