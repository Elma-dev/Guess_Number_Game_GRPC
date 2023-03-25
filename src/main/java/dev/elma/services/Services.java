package dev.elma.services;

import dev.elma.stubs.Game;
import dev.elma.stubs.gameGrpc;
import io.grpc.stub.StreamObserver;

public class Services extends gameGrpc.gameImplBase{

    int number=(int)(Math.random()*1000);

    @Override
    public StreamObserver<Game.guessMsg> requestReplay(StreamObserver<Game.repMsg> responseObserver) {
        return new StreamObserver<Game.guessMsg>() {
            @Override
            public void onNext(Game.guessMsg guessMsg) {
                System.out.println(number);

                String player=guessMsg.getUsername();
                int nbr=(int)(guessMsg.getNumber());
                Game.repMsg repMsg;

                if(nbr==number){
                    repMsg=Game.repMsg.newBuilder().setContent("You WIIIN✔✔✔ ").build();
                    responseObserver.onNext(repMsg);
                    responseObserver.onCompleted();
                }
                else if(nbr<number){
                    repMsg=Game.repMsg.newBuilder().setContent("Greater than "+nbr).build();
                    responseObserver.onNext(repMsg);
                }else{
                    repMsg=Game.repMsg.newBuilder().setContent("Less than "+nbr).build();
                    responseObserver.onNext(repMsg);
                }

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
