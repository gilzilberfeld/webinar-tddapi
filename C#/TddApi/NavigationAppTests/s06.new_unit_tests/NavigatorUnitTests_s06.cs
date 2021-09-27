using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace NavigationApp.s06.new_unit_test
{
    [TestClass]
    public class NavigatorUnitTests_s06
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
