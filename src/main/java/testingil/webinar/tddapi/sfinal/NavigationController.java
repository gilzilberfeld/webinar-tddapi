package testingil.webinar.tddapi.sfinal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/navigator")
public class NavigationController {

	
	
	@Autowired
	DistanceProvider distProvider;
	@Autowired
	Navigator nav;
	
	@PostMapping("/destination")
	public void setDestination(@RequestBody Location location) {
		nav.setDestination(location);
	}
	
	@PostMapping("/startpoint")
	public void setStartPoint(@RequestBody Location location) {
		nav.setStartingPoint(location);
	}
	
	@GetMapping(value = "/distance", produces = MediaType.APPLICATION_JSON_VALUE)
	public Distance getDistance() {
		return nav.getDistanceFromDestination();
	}
}
