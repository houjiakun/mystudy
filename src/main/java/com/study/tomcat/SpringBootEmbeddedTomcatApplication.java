package com.study.tomcat;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.study.tomcat.controller"})
public class SpringBootEmbeddedTomcatApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder()
				.sources(SpringBootEmbeddedTomcatApplication.class,
						TomcatConfiguration.class)
				.run(args);

	}
}
