package org.acme.getting.started;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.vertx.web.Route;
import io.quarkus.vertx.web.RouteBase;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@ApplicationScoped
@RouteBase(path="/api")
public class RouteGreetingService {
    @Route(path = "/compute", methods = HttpMethod.GET, type = Route.HandlerType.BLOCKING)
    public void handle(RoutingContext rc) {
        System.out.println("Received request on Thread: " + Thread.currentThread().getName());
        // Sleep 30 seconds
        // Thread.sleep(30000);
        Pi.computePi(20000);
        // rc.vertx().
        System.out.println("Back from the compute with " + Thread.currentThread().getName());
        rc.response().end("Compute completed from " + new Date().toString());
    }

    @Route(path = "/greeting", methods = HttpMethod.GET)
    public void greetings(RoutingContext rc) {
        String msg = "Greeting with Quarkus on " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS").format(new Date()); 
        System.out.println(msg);

        rc.response().end(msg);
    }
}
