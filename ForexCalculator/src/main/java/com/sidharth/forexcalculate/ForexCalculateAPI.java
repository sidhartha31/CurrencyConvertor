package com.sidharth.forexcalculate;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;

@Path("/forex")
public class ForexCalculateAPI {
	
	private static final String GET_FOREX_RATE_URI = "http://localhost:8082/forexrates/rates";
	
	@GET
	@Path("/forexcalculator")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Response calculateForexRate(@HeaderParam("from") String from, @HeaderParam("to") String to, 
			@HeaderParam("amount") Double amount) {
		
		double forexAmount = 0;
		
		try {
			System.out.println("In Forex Calculator..");
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet httpGet = new HttpGet(GET_FOREX_RATE_URI);
			httpGet.addHeader("accept","application/json");
			httpGet.addHeader("from", from);
			httpGet.addHeader("to", to);
			HttpResponse response = client.execute(httpGet);
			
			HttpEntity entity = response.getEntity();
		    String stringOutput = EntityUtils.toString(entity);
		    
		    double forexRate = new Gson().fromJson(stringOutput, Double.class);
		    System.out.println("Forex Rate: "+forexRate);
		    
		    if(from.isEmpty() || to.isEmpty()) {
		    	return Response.status(Status.BAD_REQUEST).entity(forexAmount).build();
			}
			else {
				forexAmount = amount * forexRate;
				return Response.status(Status.OK).entity(forexAmount).build();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(forexAmount).build();
		}
		
	}
}
