package actors;


import actors.SupervisorActor.Data;
import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;

import com.fasterxml.jackson.databind.node.ObjectNode;

import play.Logger;
import play.libs.Json;
import scala.util.parsing.json.JSONObject;


public class TestActor extends AbstractActor {

    private final ActorRef ws;



    String query;



    public TestActor(final ActorRef wsOut) {
        ws =  wsOut;

        Logger.debug("New Repo Search Actor{} for WebSocket {}", self(), wsOut);
    }


    public static Props props(final ActorRef wsout) {
        return Props.create(TestActor.class, wsout);
    }


    /**
     * Method Call before Actor is created and it registers with Supervisor Actor
     */
    @Override
    public void preStart() {
        context().actorSelection("/user/supervisorActor/")
                .tell(new SupervisorActor.RegisterMsg(), self());
    }
    /**
     * Method Call before Actor is stopped
     */
    @Override
    public void postStop() {
        Logger.debug("New Repo Search Actor{} Stopped",self());
    }

    /**
     * Method called when Actor receives message
     * @return Receive
     */
    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(Data.class, this::send)
                .match(ObjectNode.class, o -> this.query ="Test_createReceive")
                .build();
    }


    /**
     * Method to display 10 results for provided query and send it to UI via JsonObject
     * @param d Data
     * @throws Exception
     */
    private void send(Data d) throws Exception {
        Logger.debug("New Repo Search Actor Query {}",this.query);
        final ObjectNode response = Json.newObject();
        response.put("msg", "Hello");
        ws.tell(response, self());


    }



}
