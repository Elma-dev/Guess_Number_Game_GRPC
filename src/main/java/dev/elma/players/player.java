package dev.elma.players;

import dev.elma.stubs.Game;
import dev.elma.stubs.gameGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

public class player {
    public static void main(String[] args) {
        ManagedChannel localhost = ManagedChannelBuilder.forAddress("localhost", 2001).usePlaintext().build();
        gameGrpc.gameStub gameStub = gameGrpc.newStub(localhost);
        StreamObserver<Game.guessMsg> guessMsgStreamObserver = gameStub.requestReplay()

    }
}
