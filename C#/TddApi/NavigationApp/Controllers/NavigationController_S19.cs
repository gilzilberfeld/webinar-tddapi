using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using NavigationApp.s19.refactor_navigator;

namespace NavigationApp.Controllers
{
    [ApiController]
    [Route("navigator19")]
    public class NavigationController_S19 : ControllerBase
    {
        Navigator nav;
        DistanceProvider distanceProvider;
        public NavigationController_S19(DistanceProvider distProvider)
        {
            distanceProvider = distProvider;
            nav = new Navigator(distProvider);
        }

        [HttpGet]
        [Route("distance")]
        public Distance Distance()
        {
            return nav.getDistanceFromDestination();
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
            nav.setStartingPoint(loc);
            return Ok();
        }
    }
}
