using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using NavigationApp.s15.add_unit_test;

namespace NavigationApp.Controllers
{
    [ApiController]
    [Route("navigator15")]
    public class NavigationController_S15 : ControllerBase
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
