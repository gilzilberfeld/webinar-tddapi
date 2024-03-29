﻿using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using NavigationApp.s05.first_unit_tests;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace NavigationApp.Controllers
{
    [ApiController]
    [Route("navigator05")]
    public class NavigationController_S05 : ControllerBase
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
