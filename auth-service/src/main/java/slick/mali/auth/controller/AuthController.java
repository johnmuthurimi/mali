package slick.mali.auth.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

	@Autowired
    Environment environment;
    @Autowired
    RestTemplate template;

	@GetMapping
	public String call() {
		String url = "http://email-service/email";
		String emailResponse = template.getForObject(url, String.class);
		LOGGER.info("Response: {}", emailResponse);
		return "I'm email running on port " + environment.getProperty("local.server.port")
				+ " calling-> " + emailResponse;
	}

	@GetMapping("/slow")
	public String slow() {
		String url = "http://email-service/email/slow";
		String emailResponse = template.getForObject(url, String.class);
		LOGGER.info("Response: {}", emailResponse);
		return "I'm email running on port " + environment.getProperty("local.server.port")
				+ " calling-> " + emailResponse;
	}
}