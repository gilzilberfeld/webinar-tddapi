using System;

namespace NavigationApp.s15.add_unit_test
{
    public class Distance
    {
        public int value;

        public int inKm()
        {
            return (int) (value * 1.6); 
        }
    }
}