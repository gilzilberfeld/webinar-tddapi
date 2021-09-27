using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace NavigationApp.s16.pass_unit_test
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
