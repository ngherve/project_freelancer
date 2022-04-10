package actors;


import actors.SupervisorActor.Data;
import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Query;
import models.SearchQueryStats;
import play.Logger;
import play.libs.Json;
import play.libs.ws.WSClient;
import services.FreelancerApiService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The Repo Search Actor class is used to display
 * 10 results for prvided query by making an API call every 10 seconds.
 * This actor subscribes to Supervisor Actor.
 *
 * @author  Herve Ngomseu Fotsing
 * @version 1.0
 * @since   2021-12-07
 *
 */

public class ProjectIDStatActor extends AbstractActor {

    private final ActorRef ws;
    WSClient wsClient;
    String query;
    Set<String> setWpordFreq1 = new HashSet<>();


    public ProjectIDStatActor(final ActorRef wsOut, WSClient wsClient) {
        ws =  wsOut;
        this.wsClient = wsClient;
        Logger.debug("New Project ID Stats Actor{} for WebSocket {}", self(), wsOut);
    }


    public static Props props(final ActorRef wsout,WSClient wsClient) {
        return Props.create(ProjectIDStatActor.class, wsout,wsClient);
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
        Logger.debug("New Project ID Stats Search Actor{} Stopped",self());
    }

    /**
     * Method called when Actor receives message
     * @return Receive
     */
    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(Data.class, this::send)
                .match(ObjectNode.class, o -> this.query = String.valueOf(o.get("keyword").asText()))
                .build();
    }


    /**
     * Method to display 10 results for provided query and send it to UI via JsonObject
     * @param d Data
     * @throws Exception
     */
    private void send(Data d) throws Exception {

        if (this.query != null) {
            Logger.debug("New Project ID Stats Search Actor Query {}", this.query);
            FreelancerApiService service = new FreelancerApiService(wsClient);
            ObjectNode json = Json.newObject();
            ArrayNode arrNode = json.putArray("stats");
            List<Query> queries = new ArrayList<>();

            queries.add(new Query("full_description", "true"));
            queries.add(new Query("job_details", "true"));

            service.getIDProjects(queries, "/"+this.query).thenAccept(projectIDStatsResult -> {
                System.out.println("Project ID stats ACTOR " + projectIDStatsResult.toString());
                String stats = SearchQueryStats.combineOutput(SearchQueryStats.computeWordLevelStatByProject
                                                                (projectIDStatsResult.description));
                if(!setWpordFreq1.contains(stats)) {
                    setWpordFreq1.add(stats);
                    ObjectNode node = Json.newObject();
                    node.put("stat", stats);
                    arrNode.add(node);
                    ws.tell(json, self());
                }
            });

        }
    }


}
