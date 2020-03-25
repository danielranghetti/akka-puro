package AKKA;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.ConfigFactory;

public class StartPong {

    public static void main(String[] args) {
        // Criação de um Actor System, container Akka.
        ActorSystem system = ActorSystem.create("OiSistema", ConfigFactory.load("akka:/OiSistema@10.0.0.1:25520/user/AtorPong"));
        // Criando o ator ping
        ActorRef actorRef = system.actorOf(Props.create(AtorPong.class), "Pong");
        // Enviando a mensagem ao ator
        actorRef.tell(new AtorPong.Iniciar(), null);
        system.getWhenTerminated();
    }
}
