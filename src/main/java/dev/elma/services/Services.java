package dev.elma.services;

import dev.elma.stubs.Game;
import dev.elma.stubs.gameGrpc;
import io.grpc.stub.StreamObserver;

import java.util.HashMap;

public class Services extends gameGrpc.gameImplBase{
    //Secret number...
    int number=(int)(Math.random()*1000);
    HashMap<String,StreamObserver<Game.repMsg>> players=new HashMap<>();
    @Override
    public StreamObserver<Game.guessMsg> requestReplay(StreamObserver<Game.repMsg> responseObserver) {

        return new StreamObserver<Game.guessMsg>() {
            @Override
            public void onNext(Game.guessMsg guessMsg) {
                System.out.println(number);

                String player=guessMsg.getUsername();
                if(!players.containsKey(player)){
                    players.put(player,responseObserver);
                }
                int nbr=(int)(guessMsg.getNumber());
                Game.repMsg repMsg;

                if(nbr==number){

                    for(String pl:players.keySet()){
                        if(pl.equals(player)){
                            repMsg=Game.repMsg.newBuilder().setContent("You WIIIN✔✔✔ ").build();
                            players.get(pl).onNext(repMsg);
                            players.get(pl).onCompleted();
                        }
                        else {
                            repMsg=Game.repMsg.newBuilder().setContent(player+" WIIIN✔✔✔ ").build();
                            players.get(pl).onNext(repMsg);
                            players.get(pl).onCompleted();
                        }
                    }
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
