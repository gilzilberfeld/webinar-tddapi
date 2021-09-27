using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace NavigationApp.s14.refactor_unit_tests
{
    public class DistanceProvider
    {
        public virtual Distance getDistance(Location startingPoint, Location endPoint)
        {
            Distance distance = new Distance();
            distance.value = 0;
            return distance;
        }
    }
}
