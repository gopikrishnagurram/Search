package com.wavelabs.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.wavelabs.model.Message;
import com.wavelabs.solr.service.SolrAutoIndexService;
import com.wavelabs.solr.service.SolrJobIndexing;

@Path("/solr")
public class SolrResource {
	@GET
	@Path("reindex")
	@Produces(MediaType.APPLICATION_JSON)
	public Response reIndex() {
		boolean flag = SolrJobIndexing.doIndex();
		Message message = new Message();
		if (flag) {
			message.setMessage("Re indexing completed");
			message.setStatusCode(200);
		} else {
			message.setMessage("Error occured in server");
			message.setStatusCode(500);
		}
		return Response.ok(message).build();
	}

	@GET
	@Path("auto-index")
	@Produces(MediaType.APPLICATION_JSON)
	public Response autoIndex(@QueryParam("time") String timeIntervel) {
		boolean flag = SolrAutoIndexService.getStatus();
		Message message = new Message();
		if (flag) {
			message.setMessage("Auto index is already on, Please stop existing process and try again");
			message.setStatusCode(403);
			return Response.ok(message).build();
		} else {
			boolean status = SolrAutoIndexService.startAutoIndex(timeIntervel);
			if (status) {
				message.setMessage("Auto index is started");
				message.setStatusCode(200);
			} else {
				message.setMessage("Please try after some time");
				message.setStatusCode(500);
			}
			return Response.ok(message).build();
		}
	}

	@GET
	@Path("stop-auto-index")
	@Produces(MediaType.APPLICATION_JSON)
	public Response stopIndex() {
		boolean flag = SolrAutoIndexService.getStatus();
		Message message = new Message();
		if (!flag) {
			message.setMessage("Index is already stopped");
			message.setStatusCode(403);
			return Response.ok(message).build();
		} else {
			flag = SolrAutoIndexService.stopAutoIndex();
			if (flag) {
				message.setMessage("Auto job processing stopped");
				message.setStatusCode(200);
			} else {
				message.setMessage("Something went wrong, Please try again!");
				message.setStatusCode(500);
			}
			return Response.ok(message).build();
		}
	}
}
