package dev.elma.services;

import dev.elma.stubs.Game;
import dev.elma.stubs.gameGrpc;
import io.grpc.stub.StreamObserver;

public class Services extends gameGrpc.gameImplBase{

    int number=(int)Math.random()*1000;

    @Override
    public StreamObserver<Game.guessMsg> requestReplay(StreamObserver<Game.repMsg> responseObserver) {
        return new StreamObserver<Game.guessMsg>() {
            @Override
            public void onNext(Game.guessMsg guessMsg) {
                System.out.println(guessMsg.getUsername()+" "+guessMsg.getNumber()+" "+number);

                Game.repMsg repMsg = Game.repMsg.newBuilder().setContent("The number You send is "+guessMsg.getNumber()).build();
                responseObserver.onNext(repMsg);
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {

            }
        };
    }
}
