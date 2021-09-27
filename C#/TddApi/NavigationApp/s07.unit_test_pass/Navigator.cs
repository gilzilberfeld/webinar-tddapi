using System;

namespace NavigationApp.s07.unit_test_pass
{
    public class Navigator
    {
        public Navigator(Location loc)
        {
        }

        public Distance getDistanceFromDestination()
        {
            Distance distance = new Distance();
            distance.value = 0;
            return distance;
        }
    }
}