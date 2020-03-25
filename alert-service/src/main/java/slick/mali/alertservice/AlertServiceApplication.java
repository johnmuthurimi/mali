package slick.mali.alertservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableSwagger2
@OpenAPIDefinition(info =
	@Info(title = "Alert API", version = "1.0", description = "Documentation Alert API v1.0")
)
public class AlertServiceApplication {

    @Bean
	public Docket swaggerPersonApi10() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
					.apis(RequestHandlerSelectors.basePackage("slick.mali.alertservices.controller"))
					.paths(PathSelectors.any())
				.build()
				.apiInfo(new ApiInfoBuilder().version("1.0").title("Alert API").description("Documentation Alert API v1.0").build());
	}

    /**
	 * Main function with arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(AlertServiceApplication.class, args);
	}
}
