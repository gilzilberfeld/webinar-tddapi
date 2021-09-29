using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using NavigationApp.s20.tests_refactoring;

namespace NavigationApp.Controllers
{
    [ApiController]
    [Route("navigator20")]
    public class NavigationController_S20 : ControllerBase
    {
        Navigator nav;
        DistanceProvider distanceProvider;
        public NavigationController_S20(DistanceProvider distProvider)
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
