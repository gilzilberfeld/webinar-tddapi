﻿using System;

namespace NavigationApp.s18.inject_provider
{
    public class Navigator
    {
        DistanceProvider distProvider;
        Location startingPoint;
        Location destination;

        public Navigator(Location loc, DistanceProvider distService)
        {
            distProvider = distService;
            startingPoint = loc;
        }

        public Distance getDistanceFromDestination()
        {
            return distProvider.getDistance(startingPoint, destination);
        }

        public void setDestination(Location dest)
        {
            destination = dest;
        }
    }
}