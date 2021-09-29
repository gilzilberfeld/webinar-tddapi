using System;

namespace NavigationApp.s19.refactor_navigator
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