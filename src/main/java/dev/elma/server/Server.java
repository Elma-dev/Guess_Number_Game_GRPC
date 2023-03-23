package dev.elma.server;

import dev.elma.services.Services;
import io.grpc.ServerBuilder;
import io.grpc.internal.ServerImplBuilder;

import java.io.IOException;

public class Server {
    public static void main(String[] args) throws Exception {
        ServerBuilder.forPort(2001).addService(new Services()).build().start().awaitTermination();

    }
}
