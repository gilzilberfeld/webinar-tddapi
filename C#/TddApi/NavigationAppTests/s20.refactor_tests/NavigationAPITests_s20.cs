using Microsoft.AspNetCore.Hosting;
using Microsoft.AspNetCore.TestHost;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Moq;
using Newtonsoft.Json;
using System.Net;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;
using Microsoft.Extensions.DependencyInjection;

namespace NavigationApp.s20.tests_refactoring
{
    [TestClass]
    public class NavigationAPITests_s20
    {

        [TestMethod]
        public async Task navigate_to_same_location_distance_is_zero()
        {
            set_distance_to(0);
            Location location = new Location("New York");
            await setStartPoint(location);
            await setDestination(location);
            Assert.AreEqual(0, await distanceToDestination());
        }


        [TestMethod]
        public async Task navigate_and_drive_to_another_location_distance_is_reduced()
        {
            set_distances_to(2500, 1400);

            Location initialLocation = new Location("New York");
            await setStartPoint(initialLocation);

            Location destination = new Location("Los Angeles"); ;
            await setDestination(destination);

            int initialDistance = await distanceToDestination();

            Location midLocation = new Location("Dallas");
            await driveTo(midLocation);

            Assert.IsTrue(await distanceToDestination() < initialDistance);
        }

        private void set_distances_to(int dist1, int dist2)
        {
            Distance initDistance = new Distance();
            initDistance.value = dist1;

            Distance midDistance = new Distance();
            midDistance.value = dist2;

            mockDistanceProvider
                .SetupSequence(distProv => distProv.getDistance(It.IsAny<Location>(), It.IsAny<Location>()))
                .Returns(initDistance)
                .Returns(midDistance);
        }

        private async Task setDestination(Location location)
        {
            var content = new StringContent(JsonConvert.SerializeObject(location),
                Encoding.UTF8, "application/json");
            var response = await client.PostAsync("/navigator20/destination", content);
            Assert.AreEqual(HttpStatusCode.OK, response.StatusCode);
        }

        private async Task setStartPoint(Location location)
        {
            var content = new StringContent(JsonConvert.SerializeObject(location),
                Encoding.UTF8, "application/json");
            var response = await client.PostAsync("/navigator20/startpoint", content);
            Assert.AreEqual(HttpStatusCode.OK, response.StatusCode);
        }

        private async Task driveTo(Location midLocation)
        {
            await setStartPoint(midLocation);
        }

        private async Task<int> distanceToDestination()
        {
            var response = await client.GetAsync("/navigator20/distance");
            Assert.AreEqual(HttpStatusCode.OK, response.StatusCode);
            var body = await response.Content.ReadAsStringAsync();
            Distance distance = JsonConvert.DeserializeObject<Distance>(body);
            return distance.inKm();
        }

        private void set_distance_to(int newDistance)
        {
            Distance distance = new Distance();
            distance.value = newDistance;
            mockDistanceProvider
                .Setup(distProv => distProv.getDistance(It.IsAny<Location>(), It.IsAny<Location>()))
                .Returns(distance);
        }

        HttpClient client;
        Mock<DistanceProvider> mockDistanceProvider;
        [TestInitialize]
        public void setup()
        {
            mockDistanceProvider = new Mock<DistanceProvider> { DefaultValue = DefaultValue.Mock };
            var server = new TestServer(new WebHostBuilder()
                .ConfigureTestServices(services =>
                {
                    services.AddSingleton<DistanceProvider>(mockDistanceProvider.Object);
                })
                .UseEnvironment("Development")
                .UseStartup<Startup>());

            client = server.CreateClient();
        }
    }
}
