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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * The Owner Actor class is used to display
 * 10 results for query by making an API call every 10 seconds.
 * This actor subscribes to Supervisor Actor.
 *
 * @author  Seunghyun Hong
 * @version 1.0
 * @since   2022-04-13
 */

public class OwnerActor extends AbstractActor {

    private final ActorRef ws;
    WSClient wsClient;
    String query;
    public static LinkedHashMap<String, List<Project>> search_list = new LinkedHashMap<>();

    /**
     * Parametrized Constructor for Owner Actor
     *
     * @param wsOut final ActorRef wsOut
     * @param wsClient WSClient wsClient
     */
    public OwnerActor(final ActorRef wsOut,WSClient wsClient) {
        ws =  wsOut;
        this.wsClient = wsClient;
        Logger.debug("New Owner Search Actor{} for WebSocket {}", self(), wsOut);
    }

    /**
     * The method is called to create Owner Actor
     * @param wsout
     * @param wsClient
     * @return
     */
    public static Props props(final ActorRef wsout,WSClient wsClient) {
        return Props.create(OwnerActor.class, wsout,wsClient);
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
        Logger.debug("New Owner Search Actor{} Stopped",self());
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
     * The method to get the difference between search results
     * @param projectList List of repositories
     * @return List<Project> List of updated projects
     */
    private List<Project> getDifference(List<Project> projectList){

        List<Project> actorProjList = this.search_list.get(this.query);
        List<Project> res = projectList.stream()
                .filter(a -> actorProjList.stream().noneMatch(b -> a.projId.equals(b.projId)))
                .collect(Collectors.toList());

        if(!res.isEmpty()) {
            actorProjList.addAll(res);
            this.search_list.replace(this.query,actorProjList);
        }

        return res;

    }



    /**
     * Method to display 10 results for provided query and send it to UI via JsonObject
     * @param d Data
     * @throws Exception
     */
    private void send(Data d) throws Exception {

        if (this.query != null) {
            Logger.debug("New Owner Search Actor Query {}", this.query);
            FreelancerApiService service = new FreelancerApiService(wsClient);
            ObjectNode json = Json.newObject();

            ArrayNode arrNode = json.putArray("projects");
            ArrayNode user = json.putArray("user");

            ObjectNode node_user = Json.newObject();
            node_user.put("search",this.query);

            service.getOwnerResult(this.query).thenAccept(ownerResult -> {
                List<Project> curProjects = ownerResult.getProjects();

                if(search_list.containsKey(this.query)){
                    Logger.debug("Subsequent Query:{}",this.query);
                    curProjects = getDifference(curProjects);

                }else {
                    Logger.debug("First Query:{}",this.query);
                    search_list.put(this.query,curProjects);
                }
                if(!curProjects.isEmpty()) {
                   // System.out.println("ONER ACTOR " + ownerResult.getUsers().get(this.query).toString());
                    node_user.put("info", ownerResult.getUsers().get(this.query).toString());
                    user.add(node_user);
                    for (Project p : curProjects) {
                        ObjectNode node = Json.newObject();
                        node.put("id", p.getProjId());
                        node.put("time", p.getDate());
                        node.put("title", p.getTitle());
                        node.put("type", p.getProjectType());
                        ArrayNode sNode = node.putArray("skills");
                        for (Job j : p.getJobs()) {
                            ObjectNode node2 = Json.newObject();
                            node2.put("jid", j.getId());
                            node2.put("jname", j.getName());
                            sNode.add(node2);
                        }
                        arrNode.add(node);
                    }
                    ws.tell(json, self());
                }
            });



        }
    }


}
