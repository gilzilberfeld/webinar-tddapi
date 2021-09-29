using Microsoft.VisualStudio.TestTools.UnitTesting;
using Moq;

namespace NavigationApp.s18.inject_provider
{
    [TestClass]
    public class NavigatorUnitTests_s18
    {
        Mock<DistanceProvider> mockDistanceProvider;
        Navigator nav;

        [TestInitialize]
        public void setup()
        {
            mockDistanceProvider = new Mock<DistanceProvider> { DefaultValue = DefaultValue.Mock };
        }

        [TestMethod]
        public void when_created_distance_is_always_zero()
        {
            Location loc = new Location("London");
            nav = new Navigator(loc, mockDistanceProvider.Object);
            Distance distance = nav.getDistanceFromDestination();
            Assert.AreEqual(0, distance.inKm());
        }

        [TestMethod]
        [Description("2500 miles = 4000 kms")]
        public void distance_is_calculated_in_km()
        {
            createMockDistance(2500);

            Location startingPoint = new Location("New York City");
            nav = new Navigator(startingPoint, mockDistanceProvider.Object);

            Location dest = new Location("Los Angeles");
            nav.setDestination(dest);

            Distance distance = nav.getDistanceFromDestination();
            Assert.AreEqual(4000, distance.inKm());

        }

        [TestMethod]
        public void provider_called_correctly()
        {
            Location startingPoint = new Location("New York City");
            Location dest = new Location("Los Angeles");

            nav = new Navigator(startingPoint, mockDistanceProvider.Object);
            nav.setDestination(dest);
            nav.getDistanceFromDestination();

            mockDistanceProvider.Verify(distProv => distProv.getDistance(startingPoint, dest));
        }

        private void createMockDistance(int newValue)
        {
            Distance distanceInMiles = new Distance();
            distanceInMiles.value = newValue;
            mockDistanceProvider.Setup(distProv =>
               distProv.getDistance(It.IsAny<Location>(), It.IsAny<Location>()))
                .Returns(distanceInMiles);
        }
    }
}
