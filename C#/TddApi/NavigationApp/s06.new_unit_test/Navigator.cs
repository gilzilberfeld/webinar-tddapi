using System;

namespace NavigationApp.s06.new_unit_test
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