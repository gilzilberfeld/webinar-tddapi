using System;

namespace NavigationApp.s11.refactor_test_with_mock
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