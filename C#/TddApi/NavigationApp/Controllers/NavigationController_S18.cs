using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using NavigationApp.s18.inject_provider;

namespace NavigationApp.Controllers
{
    [ApiController]
    [Route("navigator18")]
    public class NavigationController_S18 : ControllerBase
    {
        Navigator nav;
        DistanceProvider distanceProvider;
        public NavigationController_S18(DistanceProvider distProvider)
        {
            distanceProvider = distProvider;
        }

        [HttpGet]
        [Route("distance")]
        public ActionResult Distance()
        {
            return Ok(nav.getDistanceFromDestination());
        }

        [HttpPost]
        [Route("destination")]
        public ActionResult Destination(Location loc)
        {
            nav.setDestination(loc);
            return Ok();
        }

        [HttpPost]
        [Route("startpoint")]
        public ActionResult StartPoint(Location loc)
        {
            nav = new Navigator(loc, distanceProvider);
            return Ok();
        }
    }
}
