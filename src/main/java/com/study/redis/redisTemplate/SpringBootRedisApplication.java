package com.study.redis.redisTemplate;

import com.study.tomcat.TomcatConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Administrator
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.study.redis.redisTemplate"})
public class SpringBootRedisApplication {

	public static void main(String[] args) {
        new SpringApplicationBuilder()
				.sources(SpringBootRedisApplication.class,
						TomcatConfiguration.class)
				.run(args);

	}
}
