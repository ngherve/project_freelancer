package actors;



import actors.SupervisorActor.Data;
import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import models.*;
import play.Logger;
import play.libs.Json;
import play.libs.ws.WSBodyReadables;
import play.libs.ws.WSClient;
import scala.util.parsing.json.JSONObject;
import services.FreelancerApiService;

import javax.inject.Inject;
import java.util.*;

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

public class GlobalStatsActor extends AbstractActor {

    private final ActorRef ws;
    WSClient wsClient;
    String query;
    Set<String> setWpordFreq = new HashSet<>();

    public GlobalStatsActor(final ActorRef wsOut,WSClient wsClient) {
        ws =  wsOut;
        this.wsClient = wsClient;
        Logger.debug("New Owner Global Stats Actor{} for WebSocket {}", self(), wsOut);
    }


    public static Props props(final ActorRef wsout,WSClient wsClient) {
        return Props.create(GlobalStatsActor.class, wsout,wsClient);
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
        Logger.debug("New Globals Stats Search Actor{} Stopped",self());
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
            Logger.debug("New Global Stats Search Actor Query {}", this.query);
            FreelancerApiService service = new FreelancerApiService(wsClient);
            ObjectNode json = Json.newObject();

            ArrayNode arrNode = json.putArray("stats");

            List<Query> queries = new ArrayList<>();
            queries.add(new Query("query", this.query));
            queries.add(new Query("limit", "250"));
            queries.add(new Query("job_details", "true"));
            queries.add(new Query("full_description", "true"));

            service.getProjects(queries, this.query).thenAccept(globalstatsResult -> {
                System.out.println("GlobalStats ACTOR " + globalstatsResult.toString());
                SearchQueryStats queryStat = new SearchQueryStats(this.query, FreeActor.search_list);
                String stats = SearchQueryStats.combineOutput(queryStat.computeWordLevelStat());
                if(!setWpordFreq.contains(stats)){
                    setWpordFreq.add(stats);
                    ObjectNode node = Json.newObject();
                    node.put("stat",stats);
                    arrNode.add(node);
                    ws.tell(json, self());
                }
            });

        }
    }


}
