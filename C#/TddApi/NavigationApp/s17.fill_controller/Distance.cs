using System;

namespace NavigationApp.s17.fill_controller
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