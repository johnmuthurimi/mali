package slick.mali.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * Let's turn off the DataSource/JPA autoconfiguration. 
 * As we are going to configure the database related beans explicitly, 
 * we will turn off the DataSource/JPA autoconfiguration by excluding 
 * the AutoConfiguration classes:
 */
@SpringBootApplication
public class UserServiceApplication {

	/**
	 * Main function with arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}
}

