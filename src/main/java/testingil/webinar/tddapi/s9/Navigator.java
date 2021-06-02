package testingil.webinar.tddapi.s9;

public class Navigator {

	private DistanceProvider distProvider;

	public Navigator(Location loc, DistanceProvider distProvider) {
		this.distProvider = distProvider;
	}

	public Distance getDistanceFromDestination()	{
		Distance distance = new Distance();
		distance.value = 0;
		return distance;
	}

	public void setDestination(Location dest) {
	}

}
