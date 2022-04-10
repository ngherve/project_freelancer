package actors;


import actors.SupervisorActor.Data;
import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import models.Job;
import models.Project;
import models.Query;
import play.Logger;
import play.libs.Json;
import play.libs.ws.WSClient;
import scala.util.parsing.json.JSONObject;
import services.FreelancerApiService;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The Repo Search Actor class is used to display
 * 10 results for prvided query by making an API call every 10 seconds.
 * This actor subscribes to Supervisor Actor.
 *
 * @author  Kshitij Yerande
 * @version 1.0
 * @since   2021-12-07
 *
 */

public class FreeActor extends AbstractActor {

    private final ActorRef ws;
    WSClient wsClient;
    String query;
    public static LinkedHashMap<String, List<Project>> search_list = new LinkedHashMap<>();


    public FreeActor(final ActorRef wsOut,WSClient wsClient) {
        ws =  wsOut;
        this.wsClient = wsClient;
        Logger.debug("New FreeActor Search Actor{} for WebSocket {}", self(), wsOut);
    }


    public static Props props(final ActorRef wsout,WSClient wsClient) {
        return Props.create(FreeActor.class, wsout,wsClient);
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
        Logger.debug("New FreeActor Search Actor{} Stopped",self());
    }

    /**
     * Method called when Actor receives message
     * @return Receive
     */
    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(Data.class, this::send)
                .match(ObjectNode.class, o -> this.query = String.valueOf(o.get("keyword")))
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
            queries.add(new Query("query", "\"" + this.query + "\""));
            queries.add(new Query("job_details", "true"));
            queries.add(new Query("full_description", "true"));
            queries.add(new Query("limit", "10"));

            FreelancerApiService service = new FreelancerApiService(wsClient);
            ObjectNode json = Json.newObject();
            ArrayNode arrNode = json.putArray("projects");
            ArrayNode arrSearch = json.putArray("search");
            ObjectNode no = Json.newObject();
            no.put("search_key", this.query.toString());
            arrSearch.add(no);
            service.getProjects(queries, "/active").thenAccept(projects -> {
                //search_list.put(this.query, projects);

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
                        node2.put("jname",j.getName());
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
