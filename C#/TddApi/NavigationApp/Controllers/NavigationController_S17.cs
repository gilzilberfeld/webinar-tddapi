using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using NavigationApp.s17.fill_controller;

namespace NavigationApp.Controllers
{
    [ApiController]
    [Route("navigator17")]
    public class NavigationController_S17 : ControllerBase
    {
        Navigator nav;
      
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
            DistanceProvider distProvider = new DistanceProvider();
            nav = new Navigator(loc, distProvider);
            return Ok();
        }
    }
}
