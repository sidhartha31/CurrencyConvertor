package com.sidharth.forexapp;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

public class ForexApplication {

	public static void main(String[] args) {
		
		final String FOREX_CALC_URI = "http://localhost:8081";
		
		String fromCurrency = "USD";
		String toCurrency = "INR";
		Double amountToConvert = 1500.00;
		Double convertedAmount = 0.0;
		
		System.out.println("From "+fromCurrency+" to "+toCurrency);
		System.out.println("Amount to Convert: "+amountToConvert);
		
		try {
			ResteasyClient client = new ResteasyClientBuilder().build();
			ResteasyWebTarget target = client.target(FOREX_CALC_URI);
			target.request().accept(MediaType.APPLICATION_JSON);
			
			ForexApplicationService forexApplicationService = target.proxy(ForexApplicationService.class);
			Response response = forexApplicationService.calculateForexRate(fromCurrency, toCurrency, amountToConvert);
			
			convertedAmount = response.readEntity(Double.class);
			System.out.println("Converted Amount: "+convertedAmount);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
