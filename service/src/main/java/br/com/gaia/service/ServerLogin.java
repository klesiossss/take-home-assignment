package br.com.gaia.service;

import io.grpc.ServerBuilder;
import io.grpc.Server;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public  class ServerLogin {

    public static void server() throws IOException, InterruptedException {

    ServiceLogin service = new ServiceLogin();

    final int port = 9091;
    var server = ServerBuilder.forPort(port)
            .addService(service)
            .build()
            .start(); //Start the server
    System.out.println("Server started on port " + port + "!");
    server.awaitTermination();

    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
        System.err.println("*** shutting down gRPC server since JVM is shutting down");
        try {
            stopServer(server);
        } catch (InterruptedException e) {
            e.printStackTrace(System.err);
        }
        System.err.println("*** server shut down");
    }));

}
    private static void stopServer(Server server) throws InterruptedException {
        if (server != null) {
            server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
        }
    }
}
