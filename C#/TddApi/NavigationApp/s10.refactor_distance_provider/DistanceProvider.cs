using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace NavigationApp.s10.refactor_distance_provider
{
    public class DistanceProvider
    {
        public Distance getDistance(Location startingPoint, Location endPoint)
        {
            Distance distance = new Distance();
            distance.value = 0;
            return distance;
        }
    }
}
