using System;

namespace NavigationApp.s13.pass_second_test
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