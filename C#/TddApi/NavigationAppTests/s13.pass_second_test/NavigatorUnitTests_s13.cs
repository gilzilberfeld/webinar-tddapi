using Microsoft.VisualStudio.TestTools.UnitTesting;
using Moq;

namespace NavigationApp.s13.pass_second_test
{
    [TestClass]
    public class NavigatorUnitTests_s13
    {

        [TestMethod]
        public void when_created_distance_is_always_zero()
        {
            Location loc = new Location("London");
            var mockDistanceProvider = new Mock<DistanceProvider> { DefaultValue = DefaultValue.Mock };
            Navigator nav = new Navigator(loc, mockDistanceProvider.Object);
            Distance distance = nav.getDistanceFromDestination();
            Assert.AreEqual(0, distance.inKm());
        }

        [TestMethod]
        [Description("2500 miles = 4000 kms")]
        public void distance_is_calculated_in_km()
        {
            var mockDistanceProvider = new Mock<DistanceProvider> { DefaultValue = DefaultValue.Mock };

            Distance distanceInMiles = new Distance();
            distanceInMiles.value = 2500;
            mockDistanceProvider.Setup(distProv =>
               distProv.getDistance(It.IsAny<Location>(), It.IsAny<Location>()))
                .Returns(distanceInMiles);

            Location startingPoint = new Location("New York City");
            Navigator nav = new Navigator(startingPoint, mockDistanceProvider.Object);
            
            Location dest = new Location("Los Angeles");
            nav.setDestination(dest);
            
            Distance distance = nav.getDistanceFromDestination();
            Assert.AreEqual(4000, distance.inKm());

        }
    }
}
