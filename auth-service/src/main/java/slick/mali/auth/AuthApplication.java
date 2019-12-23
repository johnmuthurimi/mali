package slick.mali.auth;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class AuthApplication implements CommandLineRunner {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
	}

	@LoadBalanced
	@Bean
	RestTemplate template() {
		return new RestTemplate();
	}

	@Override
	public void run(String... args) throws Exception {
		rabbitTemplate.convertAndSend("Hello from out first message");
	}

}
