using System;

namespace NavigationApp.s20.tests_refactoring
{
    public class Navigator
    {
        DistanceProvider distProvider;
        Location startingPoint;
        Location destination;

        public Navigator(DistanceProvider distService)
        {
            distProvider = distService;
        }

        public Distance getDistanceFromDestination()
        {
            return distProvider.getDistance(startingPoint, destination);
        }

        public void setDestination(Location dest)
        {
            destination = dest;
        }

        public void setStartingPoint(Location loc)
        {
            startingPoint = loc;
        }
    }
}