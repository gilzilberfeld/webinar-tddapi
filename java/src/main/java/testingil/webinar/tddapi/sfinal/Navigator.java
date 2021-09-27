package testingil.webinar.tddapi.sfinal;

public class Navigator {

	private DistanceProvider distProvider;
	private Location startingPoint;
	private Location endPoint;

	public Navigator(DistanceProvider distProvider) {
		this.distProvider = distProvider;
	}

	public Distance getDistanceFromDestination()	{
		return distProvider.getDistance(startingPoint, endPoint);
	}

	public void setDestination(Location dest) {
		this.endPoint = dest;
	}

	public void setStartingPoint(Location startingPoint) {
		this.startingPoint = startingPoint;
	}

}
