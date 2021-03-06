package com.wavelabs.resource;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.solr.client.solrj.SolrServerException;

import com.wavelabs.model.Job;
import com.wavelabs.service.JobService;
import com.wavelabs.solr.service.SolrJobSearchService;

@Path("job")
public class JobResource {

	@Path("/post")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response persistJob(Job job) {
		Boolean flag = JobService.persistJob(job);
		if (flag) {
			return Response.ok("Successfully inserted").build();
		} else {
			return Response.ok("Record insertion is faild").build();
		}
	}

	@Path("{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getJob(@PathParam("id") int id) {
		Job job = JobService.getJob(id);
		return Response.ok(job).build();
	}

	@Path("{id}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteJob(@PathParam("id") int id) {
		Job job = JobService.deleteJob(id);
		return Response.ok(job).build();
	}

	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateJob(@PathParam("id") int id, Job job) {
		job.setId(id);
		Boolean flag = JobService.updateJob(job);
		if (flag) {
			return Response.ok("Job is upto date").build();
		} else {
			return Response.ok("Updation failed to some reason").build();
		}
	}
	@GET
	@Path("/search")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getJobs(@QueryParam("searchTerm") String searchTerm) throws SolrServerException, IOException {
		Job[] job = SolrJobSearchService.getJobs(searchTerm);
		if (job != null) {
			return Response.ok(job).build();
		} else {
			return Response.ok("Please try again").build();
		}
	}
}
