using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using NavigationApp.s14.refactor_unit_tests;

namespace NavigationApp.Controllers
{
    [ApiController]
    [Route("navigator14")]
    public class NavigationController_S14 : ControllerBase
    {
      
        [HttpGet]
        [Route("distance")]
        public ActionResult Distance()
        {
            return Ok();
        }

        [HttpPost]
        [Route("destination")]
        public ActionResult Destination(Location loc)
        {
            return Ok();
        }

        [HttpPost]
        [Route("startpoint")]
        public ActionResult StartPoint(Location loc)
        {
            return Ok();
        }
    }
}
