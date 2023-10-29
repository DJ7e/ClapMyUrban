package com.dbms.UrbanClaps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;

@SpringBootApplication
@EnableJdbcHttpSession
public class UrbanClapsApplication {

	public static void main(String[] args) {
		SpringApplication.run(UrbanClapsApplication.class, args);
	}

}
