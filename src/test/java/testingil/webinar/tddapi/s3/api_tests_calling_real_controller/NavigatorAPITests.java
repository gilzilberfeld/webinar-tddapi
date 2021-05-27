package testingil.webinar.tddapi.s3.api_tests_calling_real_controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import testingil.webinar.tddapi.s3.Distance;
import testingil.webinar.tddapi.s3.Location;
import testingil.webinar.tddapi.s3.NavigationController;

@SpringBootTest
class NavigatorAPITests {

	@Test
	void navigate_to_same_location_distance_is_zero() throws Exception {
		Location location = new Location("New York");
		setStartPoint(location);
		setDestination(location);
		assertThat(distanceTo(location), is(0));
	}

	@Test
	void navigate_and_drive_to_another_location_distance_is_reduced() throws Exception {
		Location initialLocation = new Location("New York");
		setStartPoint(initialLocation);
		Location destination = new Location("Los Angeles");;
		setDestination(destination);
		int initialDistance = distanceTo(destination);
		Location midLocation = new Location("Dallas");
		driveTo(midLocation); 
		assertThat(distanceTo(destination), is(lessThan(initialDistance)));
	}

	MockMvc mockMvc;
	@BeforeEach
	public void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(new NavigationController()).build();
	}
	
	private void setDestination(Location location) throws JsonProcessingException, Exception {
		mockMvc.perform(post("/navigator/destination")
				.content(toJson(location)))
        .andExpect(status().isOk());
	}

	private void setStartPoint(Location location) throws Exception {
		mockMvc.perform(post("/navigator/startpoint")
				.content(toJson(location)))
        .andExpect(status().isOk());
	}

	private void driveTo(Location midLocation) throws Exception {
		setStartPoint(midLocation);
	}
	
	private int distanceTo(Location location) throws JsonProcessingException, Exception {
		MvcResult result = mockMvc.perform(get("/navigator/distance")
				.content(toJson(location)))
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
