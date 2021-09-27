package testingil.webinar.tddapi.s05;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/navigator5")
public class NavigationController5 {

	@PostMapping("/destination")
	public void setDestination(@RequestBody Location location) {
	}
	
	@PostMapping("/startpoint")
	public void setStartPoint(@RequestBody Location location) {
	}
	
	@GetMapping(value = "/distance", produces = MediaType.APPLICATION_JSON_VALUE)
	public Distance getDistance() {
		return new Distance();
	}

}
