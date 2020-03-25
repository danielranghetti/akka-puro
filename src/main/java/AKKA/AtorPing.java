package AKKA;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedAbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class AtorPing extends UntypedAbstractActor {
    LoggingAdapter loggingAdapter = Logging.getLogger(getContext().system(), this);

    public static class Iniciar {
    }

    public static class PingMsg {
        private final String mensagem;

        public PingMsg(String mensagem) {
            this.mensagem = mensagem;
        }

        public String getMensagem() {
            return mensagem;
        }
    }

    private int contador = 0;
    private ActorRef atorPong = getContext().actorOf(Props.create(AtorPong.class), "Pong");


    public void onReceive(Object msg) throws Exception {
        if (msg instanceof Iniciar) {
            //setando a msg do AtorPing para a AtorPong
            atorPong.tell(new PingMsg("Ping"), getSelf());
            //verificar ser a msg é do AtorPong
        } else if (msg instanceof AtorPong.PongMsg) {
            //pega a msg do AtorPong
            AtorPong.PongMsg atorPong = (AtorPong.PongMsg) msg;
            //mostra a msg enviada do AtorPong para o AtorPing no console
            loggingAdapter.info("Mensagem do ator ping: {}  ", atorPong.getMensagem());
            contador += 1;
            if (contador == 3) {
                getContext().system().terminate();
            } else {
                getSender().tell(new PingMsg("Ping " + contador), getSelf());
            }
        } else {
            // informa ao actor system que este ator não processa esta mensagem
            unhandled(msg);
        }
    }

}

