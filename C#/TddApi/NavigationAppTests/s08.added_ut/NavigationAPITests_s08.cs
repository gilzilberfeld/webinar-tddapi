using Microsoft.AspNetCore.Hosting;
using Microsoft.AspNetCore.TestHost;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Newtonsoft.Json;
using System.Net;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;

namespace NavigationApp.s08.added_ut
{
    [TestClass]
    public class NavigationAPITests_s09
    {
	
        [TestMethod]
        public async Task navigate_to_same_location_distance_is_zero()
		{
			Location location = new Location("New York");
            await setStartPoint(location);
            await setDestination(location);
            Assert.AreEqual(0, await distanceToDestination());
		}

		[TestMethod]
		public async Task navigate_and_drive_to_another_location_distance_is_reduced()
		{
			Location initialLocation = new Location("New York");
			await setStartPoint(initialLocation);

			Location destination = new Location("Los Angeles"); ;
			await setDestination(destination);

			int initialDistance = await distanceToDestination();

			Location midLocation = new Location("Dallas");
			await driveTo(midLocation);

			Assert.IsTrue(await distanceToDestination() < initialDistance);
		}


		private async Task setDestination(Location location)
		{
			var content = new StringContent(JsonConvert.SerializeObject(location),
				Encoding.UTF8, "application/json");
			var response = await client.PostAsync("/navigator08/destination", content);
			Assert.AreEqual(HttpStatusCode.OK, response.StatusCode);
		}

		private async Task setStartPoint(Location location)
		{
			var content = new StringContent(JsonConvert.SerializeObject(location),
				Encoding.UTF8, "application/json");
			var response = await client.PostAsync("/navigator08/startpoint", content);
			Assert.AreEqual(HttpStatusCode.OK, response.StatusCode);
		}

		private async Task driveTo(Location midLocation)
		{
			await setStartPoint(midLocation);
		}

		private async Task<int> distanceToDestination()
		{
			var response = await client.GetAsync("/navigator08/distance");
			Assert.AreEqual(HttpStatusCode.OK, response.StatusCode);
			var body = await response.Content.ReadAsStringAsync();
			Distance distance = JsonConvert.DeserializeObject<Distance>(body);
			return distance.inKm();
		}


		HttpClient client;
		[TestInitialize]
		public void setup()
        {
			var server = new TestServer(new WebHostBuilder()
				.UseEnvironment("Development")
				.UseStartup<Startup>());

			client = server.CreateClient();
		}
	}
}
