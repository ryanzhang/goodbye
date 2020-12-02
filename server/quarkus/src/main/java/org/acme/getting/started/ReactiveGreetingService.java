package org.acme.getting.started;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import javax.enterprise.context.ApplicationScoped;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

@ApplicationScoped
public class ReactiveGreetingService {
    public Uni<String> compute() {
        return Uni.createFrom().voidItem()
                .onItem().transform(n -> {
                    System.out.println("Received request on Thread: " + Thread.currentThread().getName());
                    // Sleep 30 seconds
                    // Thread.sleep(30000);
                    Pi.computePi(20000);
                    System.out.println("Back from the compute with " + Thread.currentThread().getName());
                    return "Compute completed from " + new Date().toString() ;
                });
    }


    public Uni<String> greeting() {
        return Uni.createFrom().voidItem()
                .onItem().transform(n -> {
                    String msg = "Greeting(Reactive) with Quarkus on " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS").format(new Date()); 
                    System.out.println(msg);
                    return msg;
                });
    }

    public Multi<String> multipleCompute(int count) {
        return Multi.createFrom().ticks().every(Duration.ofSeconds(1))
                .onItem().transform(n -> {
                    System.out.println("Received request on Thread: " + Thread.currentThread().getName());
                    // Sleep 30 seconds
                    // Thread.sleep(30000);
                    Pi.computePi(20000);
                    System.out.println("Back from the compute with " + Thread.currentThread().getName());
                    return "Compute completed from " + new Date().toString() ;
                })
                .transform().byTakingFirstItems(count);

    }

}
