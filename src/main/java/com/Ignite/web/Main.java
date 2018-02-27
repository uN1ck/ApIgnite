package com.Ignite.web;

import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.ipc.netty.http.server.HttpServer;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        HandlerFunction helloFunc = request -> {
            return ServerResponse.ok().body(fromObject("Hello"));
        };
        HandlerFunction getSegment = request -> {
            return ServerResponse.ok().body(fromObject("Segment"));
        };
        HandlerFunction setResult = request -> {
            return ServerResponse.ok().body(fromObject("Result"));
        };

        RouterFunction router = route(GET("/"), helloFunc)
                .andRoute(GET("/segment"), getSegment)
                .andRoute(GET("/result"), setResult);


        HttpHandler httpHandler = RouterFunctions.toHttpHandler(router);

        HttpServer
                .create("localhost", 8080)
                .newHandler(new ReactorHttpHandlerAdapter(httpHandler))
                .block();

        Thread.currentThread().join();
    }
}
