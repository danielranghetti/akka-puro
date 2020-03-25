package AKKA;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedAbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class AtorPong extends UntypedAbstractActor {

    LoggingAdapter loggingAdapter = Logging.getLogger(getContext().system(), this);

    public static class Iniciar {

    }

    public static class PongMsg {
        private final String mensagem;

        public PongMsg(String mensagem) {
            this.mensagem = mensagem;
        }

        public String getMensagem() {
            return mensagem;
        }
    }

    private int contador = 0;
    private ActorRef atorPing = getContext().actorOf(Props.create(AtorPing.class), "Ping");

    @Override
    public void onReceive(Object msg) throws Exception {
        if (msg instanceof Iniciar) {
            atorPing.tell(new PongMsg("Pong"), getSelf());
        }
        //verifica ser a msg está vindo do AtorPing
        else if (msg instanceof AtorPing.PingMsg) {
            //pegar a mensagem do AtorPing
            AtorPing.PingMsg atorPing = (AtorPing.PingMsg) msg;
            //mostra a mensagem enviado do AtorPing para o AtorPong no console
            loggingAdapter.info("Mensagem do ator pong: " + atorPing.getMensagem());
            //inforna a mensagem que o AtorPong esta passando
            contador += 1;
            if (contador == 3) {
                getContext().system().terminate();
            } else {
                getSender().tell(new PongMsg("pong" + contador), getSelf());
            }
        } else {
            unhandled(msg);
        }
    }
}
