package testingil.webinar.tddapi.s17;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/navigator17")
public class NavigationController17 {

	
	Navigator nav;
	
	@PostMapping("/startpoint")
	public void setStartPoint(@RequestBody Location location) {
		DistanceProvider distProvider = new DistanceProvider();
		nav = new Navigator(location, distProvider);
	}
	
	@PostMapping("/destination")
	public void setDestination(@RequestBody Location location) {
		nav.setDestination(location);
	}
	
	@GetMapping(value = "/distance", produces = MediaType.APPLICATION_JSON_VALUE)
	public Distance getDistance() {
		return nav.getDistanceFromDestination();
	}

}
