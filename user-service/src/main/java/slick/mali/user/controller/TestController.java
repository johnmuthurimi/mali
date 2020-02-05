package slick.mali.user.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController extends BaseController {

	@RequestMapping("/")
	public String index() {
		return "Service is running.";
	}

}