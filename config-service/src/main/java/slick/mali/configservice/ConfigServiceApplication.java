package slick.mali.configservice;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigServiceApplication {

	/**
	 * Boot up the configuration service
	 * @param args
	 */
	public static void main(String[] args) {
		new SpringApplicationBuilder(ConfigServiceApplication.class).run(args);
	}
}