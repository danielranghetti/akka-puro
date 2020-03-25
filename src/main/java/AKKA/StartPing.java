package AKKA;


import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.ConfigFactory;

public class StartPing {
    public static void main(String[] args) {
        // Criação de um Actor System, container Akka.
        ActorSystem system = ActorSystem.create("ActorSystem");
        // Criando o ator ping
        ActorRef actorRef = system.actorOf(Props.create(AtorPing.class), "Ping");
        // Enviando a mensagem ao ator
        actorRef.tell(new AtorPing.Iniciar(), null);
        system.getWhenTerminated();


    }
}
