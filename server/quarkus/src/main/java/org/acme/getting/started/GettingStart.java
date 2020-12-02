package org.acme.getting.started;

import io.smallrye.mutiny.Multi;

public class GettingStart {

    public static void main(String []args){
    //1 Hello world
    //    Multi.createFrom().items("hello", "world")
    //     .onItem().transform(s->s.toUpperCase() + " ") 
    //     .onCompletion().continueWith("!")
    //     .subscribe().with(System.out::print);

    Multi<String> source = Multi.createFrom().items("a", "b", "c");

    source
        .onItem().invoke(item -> System.out.println("Received item " + item))
        .onFailure().invoke(failure -> System.out.println("Failed with " + failure.getMessage()))
        .onCompletion().invoke(() -> System.out.println("Completed"))
        .onSubscribe().invoke(subscription -> System.out.println("We are subscribed!"))
        .onCancellation().invoke(() -> System.out.println("Downstream has cancelled the interaction"))
        .onRequest().invoke(n -> System.out.println("Downstream requested " + n + " items"))
        .subscribe().with(item -> System.out.println("Subscriber received " + item));
    }
    
}
