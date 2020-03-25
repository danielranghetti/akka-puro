package AKKA;


import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.ConfigFactory;

public class StartPing {
    public static void main(String[] args) {
        // Criação de um Actor System, container Akka.
        ActorSystem system = ActorSystem.create("OiSistema", ConfigFactory.load("akka:/OiSistema@10.0.0.1:25520/user/AtorPing"));
        // Criando o ator ping
        ActorRef actorRef = system.actorOf(Props.create(AtorPing.class), "Ping");
        // Enviando a mensagem ao ator
        actorRef.tell(new AtorPing.Iniciar(), null);
        system.getWhenTerminated();


    }
}
