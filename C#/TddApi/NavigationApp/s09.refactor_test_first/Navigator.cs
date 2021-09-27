using System;

namespace NavigationApp.s09.refactor_test_first
{
    public class Navigator
    {
        DistanceProvider distProvider;
        public Navigator(Location loc, DistanceProvider distService)
        {
            distProvider = distService;
        }

        public Distance getDistanceFromDestination()
        {
            Distance distance = new Distance();
            distance.value = 0;
            return distance;
        }

        public void setDestination(Location dest)
        {
            
        }
    }
}