package org.thompson.stlcf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.thompson.stlcf.config.JdbcConnection;

@SpringBootApplication
public class StLcfApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(StLcfApplication.class, args);
	}
}
 