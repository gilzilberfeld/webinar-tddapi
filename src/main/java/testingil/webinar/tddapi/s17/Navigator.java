package testingil.webinar.tddapi.s17;

public class Navigator {

	private DistanceProvider distProvider;
	private Location startingPoint;
	private Location endPoint;

	public Navigator(Location startingPoint, DistanceProvider distProvider) {
		this.distProvider = distProvider;
		this.startingPoint = startingPoint;
	}

	public Distance getDistanceFromDestination()	{
		return distProvider.getDistance(startingPoint, endPoint);
	}

	public void setDestination(Location dest) {
		this.endPoint = dest;
	}

}
