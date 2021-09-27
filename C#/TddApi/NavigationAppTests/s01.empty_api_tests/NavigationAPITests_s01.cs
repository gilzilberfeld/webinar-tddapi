using Microsoft.VisualStudio.TestTools.UnitTesting;
using NavigationAppTests.s01.empty_api_tests;

namespace NavigationAppTests
{
    [TestClass]
    public class NavigationAPITests_s01
    {
        [TestMethod]
        public void navigate_to_same_location_distance_is_zero()
		{
			Location location = new Location("New York");
			setStartPoint(location);
			setDestination(location);
			Assert.AreEqual(0, distanceToDestination());
		}

		[TestMethod]
		public void navigate_and_drive_to_another_location_distance_is_reduced()
		{
			Location initialLocation = new Location("New York");
			setStartPoint(initialLocation);

			Location destination = new Location("Los Angeles"); ;
			setDestination(destination);

			int initialDistance = distanceToDestination();

			Location midLocation = new Location("Dallas");
			driveTo(midLocation);

			Assert.IsTrue(distanceToDestination() < initialDistance);
		}



		private int distanceToDestination()
		{
			return -1;
		}

		private void setDestination(Location location)
		{
		}

		private void setStartPoint(Location location)
		{
		}

		private void driveTo(Location midLocation)
		{
		}

	}
}
