using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace NavigationApp.s07.unit_test_pass
{
    [TestClass]
    public class NavigatorUnitTests_s07
    {

        [TestMethod]
        public void when_created_distance_is_always_zero()
        {
            Location loc = new Location("London");
            Navigator nav = new Navigator(loc);
            Distance distance = nav.getDistanceFromDestination();
            Assert.AreEqual(0, distance.inKm());
        }
    }
}
