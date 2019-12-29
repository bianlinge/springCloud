package com.imodule.annotation;

import org.springframework.web.bind.annotation.*;

/**
 * Created by E1041 on 2019/12/27.
 */
@RestController
public class RequestHeaderController {
	@RequestMapping(value = "/deliver@RequestHeader",method = RequestMethod.GET)
	public String requestHeaderdelivery(@RequestParam String encoding){
		return encoding;
	}
}
