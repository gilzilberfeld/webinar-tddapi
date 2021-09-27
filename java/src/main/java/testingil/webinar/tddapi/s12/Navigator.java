package testingil.webinar.tddapi.s12;

public class Navigator {

	private DistanceProvider distProvider;
	private Location startingPoint; 

	public Navigator(Location startingPoint, DistanceProvider distProvider) {
		this.distProvider = distProvider;
		this.startingPoint = startingPoint;
	}

	public Distance getDistanceFromDestination()	{
		return distProvider.getDistance(startingPoint, startingPoint);
	}

	public void setDestination(Location dest) {
	}

}
