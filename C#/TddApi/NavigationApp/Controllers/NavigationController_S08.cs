﻿using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using NavigationApp.s08.added_ut;

namespace NavigationApp.Controllers
{
    [ApiController]
    [Route("navigator08")]
    public class NavigationController_S08 : ControllerBase
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
