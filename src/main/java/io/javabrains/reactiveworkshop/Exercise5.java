package io.javabrains.reactiveworkshop;

import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;

import java.io.IOException;

public class Exercise5 {

    public static void main(String[] args) throws IOException {

        // Use ReactiveSources.intNumberMono() and ReactiveSources.userMono()

        // Subscribe to a flux using the error and completion hooks
//        Disposable disposal = ReactiveSources.intNumbersFlux().subscribe(
//                number -> System.out.println(number),
//                err -> System.out.println(err.getMessage()),
//                () -> System.out.println("Completed")
//        );

        // Subscribe to a flux using an implementation of BaseSubscriber
        ReactiveSources.intNumbersFlux().subscribe(new MySubscriber<>());

        System.out.println("Press a key to end");
        System.in.read();
    }

}

class MySubscriber<T> extends BaseSubscriber<T> {
    public void hookOnSubscribe(Subscription subscription) {
        System.out.println("Subscription happened");
        request(1);
    }

    public void hookOnNext(T value) {
        System.out.println(value.toString() + " Received.");
        request(3);
    }
}