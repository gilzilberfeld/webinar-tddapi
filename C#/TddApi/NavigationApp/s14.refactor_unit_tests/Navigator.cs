using System;

namespace NavigationApp.s14.refactor_unit_tests
{
    public class Navigator
    {
        DistanceProvider distProvider;
        Location startingPoint;

        public Navigator(Location loc, DistanceProvider distService)
        {
            distProvider = distService;
            startingPoint = loc;
        }

        public Distance getDistanceFromDestination()
        {
            return distProvider.getDistance(startingPoint, startingPoint);
        }

        public void setDestination(Location dest)
        {
            
        }
    }
}