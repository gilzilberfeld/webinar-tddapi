using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace NavigationApp.s10.refactor_distance_provider
{
    [TestClass]
    public class NavigatorUnitTests_s10
    {

        [TestMethod]
        public void when_created_distance_is_always_zero()
        {
            Location loc = new Location("London");
            DistanceProvider distService = new DistanceProvider();
            Navigator nav = new Navigator(loc, distService);
            Distance distance = nav.getDistanceFromDestination();
            Assert.AreEqual(0, distance.inKm());
        }


        //[TestMethod]
        //[Description("2500 miles = 4000 kms")]
        //public void distance_is_calculated_in_km()
        //{
        //    Location startingPoint = new Location("New York City");
        //    Navigator nav = new Navigator(startingPoint);

        //    Location dest = new Location("Los Angeles");
        //    nav.setDestination(dest);

        //    Distance distance = nav.getDistanceFromDestination();
        //    Assert.AreEqual(4000, distance.inKm());
        //}
    }
}
