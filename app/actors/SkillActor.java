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
 * The Skills Actor class is used to display
 * 10 results for prvided query by making an API call every 10 seconds.
 * This actor subscribes to Supervisor Actor.
 *
 * @author Nastaran Naseri
 * @version 1.0
 * @since   2022-04-13
 */

public class SkillActor extends AbstractActor {

    private final ActorRef ws;
    WSClient wsClient;
    String query;
    public static LinkedHashMap<String, List<Project>> search_list = new LinkedHashMap<>();


    /**
     * Parametrized Constructor for Skill Actor
     *
     * @param wsOut final ActorRef wsOut
     * @param wsClient WSClient wsClient
     */
    public SkillActor(final ActorRef wsOut,WSClient wsClient) {
        ws =  wsOut;
        this.wsClient = wsClient;
        Logger.debug("New Skill Search Actor{} for WebSocket {}", self(), wsOut);
    }

    /**
     * The method is called to create Skill Actor
     * @param wsout
     * @param wsClient
     * @return
     */
    public static Props props(final ActorRef wsout,WSClient wsClient) {
        return Props.create(SkillActor.class, wsout,wsClient);
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
        Logger.debug("New Skill Search Actor{} Stopped",self());
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

        if (this.query != null && this.query !="") {
            Logger.debug("New Free Search Actor Query {}", this.query);
            List<Query> queries = new ArrayList<>();
           // queries.add(new Query("query", "\"" + this.query + "\""));
            queries.add(new Query("jobs[]", this.query));
            queries.add(new Query("job_details", "true"));
            queries.add(new Query("full_description", "true"));
            queries.add(new Query("limit", "10"));

            FreelancerApiService service = new FreelancerApiService(wsClient);
            ObjectNode json = Json.newObject();
            ArrayNode arrNode = json.putArray("projects");
            service.getProjects(queries, "/active").thenAccept(projects -> {

                if(search_list.containsKey(this.query)){
                    Logger.debug("Subsequent Query:{}",this.query);
                    projects = getDifference(projects);

                }else {
                    Logger.debug("First Query:{}",this.query);
                    search_list.put(this.query,projects);
                }
                if(!projects.isEmpty()) {
                    for (Project p : projects) {
                        ObjectNode node = Json.newObject();
                        node.put("id", p.getProjId());
                        node.put("time", p.getDate());
                        node.put("title", p.getTitle());
                        node.put("type", p.getProjectType());
                        node.put("owner", p.getOwnerId());
                        ArrayNode sNode = node.putArray("skills");

                        for (Job j : p.getJobs()) {
                            ObjectNode node2 = Json.newObject();
                            node2.put("jid", j.getId());
                            node2.put("jname", j.getName());
                            //                        sNode.add(j.getName());
                            //                        //to get the id too
                            //                        sNode.add(j.getId());
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
