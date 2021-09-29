using System;

namespace NavigationApp.s18.inject_provider
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