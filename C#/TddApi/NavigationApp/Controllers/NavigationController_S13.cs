using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using NavigationApp.s13.pass_second_test;

namespace NavigationApp.Controllers
{
    [ApiController]
    [Route("navigator13")]
    public class NavigationController_S13 : ControllerBase
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
