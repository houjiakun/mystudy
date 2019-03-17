

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

@org.springframework.boot.autoconfigure.SpringBootApplication
@ComponentScan(basePackages = {"com.study.excel"})
public class SpringBootApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder()
				.sources(SpringBootApplication.class)
				.run(args);

	}
}
