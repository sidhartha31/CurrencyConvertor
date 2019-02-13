package com.sidharth.forexapp;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public interface ForexApplicationService {

	@GET
	@Path("/forex/forexcalculator")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response calculateForexRate(@HeaderParam("from") String from, @HeaderParam("to") String to, 
			@HeaderParam("amount") Double amount);
}
