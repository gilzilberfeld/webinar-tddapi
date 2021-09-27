using System;

namespace NavigationApp.s08.added_ut
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

        public void setDestination(Location dest)
        {
            
        }
    }
}