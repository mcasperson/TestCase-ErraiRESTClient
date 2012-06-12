package com.test.erraiclient.client.local;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.test.restserver.RESTObject;

@Path("/")
public interface RESTTwo
{
	@GET
	@Path("test")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(
	{ "*" })
	public String printMessage();
}
