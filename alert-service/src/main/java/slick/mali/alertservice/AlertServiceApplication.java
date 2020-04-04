package slick.mali.alertservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AlertServiceApplication {

    /**
	 * Main function with arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(AlertServiceApplication.class, args);
	}
}
