package testingil.webinar.tddapi.s14.refactor_test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.lessThan;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import testingil.webinar.tddapi.s14.Distance;
import testingil.webinar.tddapi.s14.Location;
import testingil.webinar.tddapi.s14.NavigationController14;

@WebMvcTest
class NavigatorAPITests {

	@Test
	void navigate_to_same_location_distance_is_zero() throws Exception {
		Location location = new Location("New York");
		setStartPoint(location);
		setDestination(location);
		assertThat(distanceToDestination(), is(0));
	}

	@Test
	void navigate_and_drive_to_another_location_distance_is_reduced() throws Exception {
		Location initialLocation = new Location("New York");
		setStartPoint(initialLocation);
		Location destination = new Location("Los Angeles");;
		setDestination(destination);
		int initialDistance = distanceToDestination();
		Location midLocation = new Location("Dallas");
		driveTo(midLocation); 
		assertThat(distanceToDestination(), is(lessThan(initialDistance)));
	}

	MockMvc mockMvc;
	@BeforeEach
	public void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(new NavigationController14()).build();
	}
	
	private void setDestination(Location location) throws JsonProcessingException, Exception {
		mockMvc.perform(post("/navigator14/destination")
				.contentType(MediaType.APPLICATION_JSON)
				.content(toJson(location)))
        .andExpect(status().isOk());
	}

	private void setStartPoint(Location location) throws Exception {
		mockMvc.perform(post("/navigator14/startpoint")
				.contentType(MediaType.APPLICATION_JSON)
				.content(toJson(location)))
        .andExpect(status().isOk());
	}

	private void driveTo(Location midLocation) throws Exception {
		setStartPoint(midLocation);
	}
	
	private int distanceToDestination() throws JsonProcessingException, Exception {
		MvcResult result = 
			mockMvc.perform(get("/navigator14/distance"))
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
