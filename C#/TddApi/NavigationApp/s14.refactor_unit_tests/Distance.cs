using System;

namespace NavigationApp.s14.refactor_unit_tests
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