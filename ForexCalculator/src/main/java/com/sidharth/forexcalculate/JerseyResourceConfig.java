package com.sidharth.forexcalculate;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyResourceConfig extends ResourceConfig {

	public JerseyResourceConfig() {
		register(ForexCalculateAPI.class);
	}
}
