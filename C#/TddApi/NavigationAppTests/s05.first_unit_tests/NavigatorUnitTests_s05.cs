using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace NavigationApp.s05.first_unit_tests
{
    [TestClass]
    public class NavigatorUnitTests_s05
    {

        [TestMethod]
        public void throw_when_navigating_without_starting_point()
        {
            Navigator nav = new Navigator();
            Assert.ThrowsException<NotInitializedException>(
                () => nav.getDistanceFromDestination());
        }
    }
}
