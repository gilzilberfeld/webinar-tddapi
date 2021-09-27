using System;

namespace NavigationApp.s13.pass_second_test
{
    public class Navigator
    {
        DistanceProvider distProvider;
        Location startingPoint;
        Location destination;

        public Navigator(Location loc, DistanceProvider distService)
        {
            distProvider = distService;
            startingPoint = loc;
        }

        public Distance getDistanceFromDestination()
        {
            return distProvider.getDistance(startingPoint, destination);
        }

        public void setDestination(Location dest)
        {
            this.destination = dest;
        }
    }
}