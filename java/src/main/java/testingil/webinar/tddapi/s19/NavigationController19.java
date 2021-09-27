package testingil.webinar.tddapi.s19;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/navigator19")
public class NavigationController19 {
	
	@Autowired
	DistanceProvider distProvider;
	Navigator nav;

	public NavigationController19() {
		nav = new Navigator(distProvider) ;
	}
	
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
