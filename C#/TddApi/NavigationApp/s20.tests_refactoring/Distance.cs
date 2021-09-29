using System;

namespace NavigationApp.s20.tests_refactoring
{
    public class Distance
    {
        public int value { get; set; }

        public Distance()
        {

        }
        public int inKm()
        {
            return (int) (value * 1.6); 
        }
    }
}