package com.sidharth.forexcalculate;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class ForexCalculatorApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		
		//SpringApplication.run(ForexCalculatorApplication.class, args);
		new ForexCalculatorApplication().configure(new SpringApplicationBuilder(ForexCalculatorApplication.class)).run(args);
	}

}

