using Microsoft.VisualStudio.TestTools.UnitTesting;
using Moq;

namespace NavigationApp.s19.refactor_navigator
{
    [TestClass]
    public class NavigatorUnitTests_s19
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
            nav = new Navigator(mockDistanceProvider.Object);
            nav.setStartingPoint(loc);
            Distance distance = nav.getDistanceFromDestination();
            Assert.AreEqual(0, distance.inKm());
        }

        [TestMethod]
        [Description("2500 miles = 4000 kms")]
        public void distance_is_calculated_in_km()
        {
            createMockDistance(2500);

            Location startingPoint = new Location("New York City");
            Location dest = new Location("Los Angeles");

            nav = new Navigator(mockDistanceProvider.Object);
            nav.setStartingPoint(startingPoint);
            nav.setDestination(dest);

            Distance distance = nav.getDistanceFromDestination();
            Assert.AreEqual(4000, distance.inKm());

        }

        [TestMethod]
        public void provider_called_correctly()
        {
            Location startingPoint = new Location("New York City");
            Location dest = new Location("Los Angeles");

            nav = new Navigator(mockDistanceProvider.Object);
            nav.setStartingPoint(startingPoint);
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
