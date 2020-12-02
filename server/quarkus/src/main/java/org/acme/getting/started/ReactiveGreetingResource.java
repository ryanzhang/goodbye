package org.acme.getting.started;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.annotations.SseElementType;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

@Path("/apis")
public class ReactiveGreetingResource {
    @Inject
    ReactiveGreetingService service;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/compute")
    public Uni<String> greeting() {
        return service.compute();
        // return service.abc();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/compute/{count}")
    public Multi<String> greetings(@PathParam int count) {
        return service.multipleCompute(count);
    }

    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
    @SseElementType(MediaType.TEXT_PLAIN)
    @Path("/stream/{count}")
    public Multi<String> greetingsAsStream(@PathParam int count) {
        return service.multipleCompute(count);
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("greeting")
    public Uni<String> reactiveGreeting() {
        return service.greeting();
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("rr/greeting")
    public String requestresponseGreeting() {
        String msg = "Greeting(imperative) with Quarkus on " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS").format(new Date()); 
        System.out.println(msg);
        return msg;
    }
}
