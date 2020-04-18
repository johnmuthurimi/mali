package slick.mali.gatewayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
@RestController
public class GatewayServiceApplication {

	/**
	 * Boot up the gateway service
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(GatewayServiceApplication.class, args);
	}
}