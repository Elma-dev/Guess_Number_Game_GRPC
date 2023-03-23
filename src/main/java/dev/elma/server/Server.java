package dev.elma.server;

import io.grpc.ServerBuilder;

import java.io.IOException;

public class Server {
    public static void main(String[] args) throws Exception {
        ServerBuilder.forPort(2001).build().start().awaitTermination();
    }
}
