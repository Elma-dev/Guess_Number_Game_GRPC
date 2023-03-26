package dev.elma.players;

import dev.elma.stubs.Game;
import dev.elma.stubs.gameGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import java.util.Scanner;

import static java.lang.System.exit;

public class player {
    public static void main(String[] args) {
        ManagedChannel localhost = ManagedChannelBuilder.forAddress("localhost", 2001).usePlaintext().build();
        gameGrpc.gameStub gameStub = gameGrpc.newStub(localhost);
        StreamObserver<Game.guessMsg> guessMsgStreamObserver = gameStub.requestReplay(new StreamObserver<Game.repMsg>() {
            @Override
            public void onNext(Game.repMsg repMsg) {
                String content = repMsg.getContent();
                System.out.println(content);
            }
            @Override
            public void onError(Throwable throwable) {}

            @Override
            public void onCompleted() {
                exit(0);
            }
        });

        String username;
        System.out.print("Username:");
        Scanner scanner = new Scanner(System.in);
        username=scanner.next();

        while(true) {
            System.out.println("Guess Number: ");
            int number=new Scanner(System.in).nextInt();
            Game.guessMsg build = Game.guessMsg.newBuilder().setUsername(username).setNumber((double) number).build();
            guessMsgStreamObserver.onNext(build);
        }
    }
}
