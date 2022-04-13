package controllers;

import javax.inject.Inject;


import actors.*;
import akka.actor.ActorSystem;
import akka.stream.Materializer;
import play.libs.streams.ActorFlow;
import play.libs.ws.WSClient;
import play.mvc.*;

import services.IApiService;
import views.html.*;



/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 * @author  Seunghyun Hong, Nastaran Naseri, Herve Ngomseu Fotsing
 */
public class HomeController extends Controller {
    @Inject private ActorSystem actorSystem;
    @Inject private Materializer materializer;
    @Inject
    WSClient wsClient;

    @Inject
    public HomeController( ActorSystem system ) {
        system.actorOf(SupervisorActor.getProps(),"supervisorActor");
    }

    /**
     * An action that renders an HTML page a welcome message and redirect to free(search) page
     * @return result
     */
    public Result index() {
        return ok(index.render(request()));
    }

    /**
     * An action that renders an HTML page with up to 10 latest projects for the search query
     * @return result
     */
    public Result free(){
        return ok(freelanslot.render(request()));
    }
    /**
     * An action that renders an HTML page with owner's profiles and up to 10 latest projects for owner has
     * @return result
     */
    public Result owner(String id){
        return ok(owner.render(request()));
    }
    /**
     * An action that renders an HTML page with up to 10 latest projects which has given skills
     * @return result
     */
    public Result skills(String skillId){
        return ok(skill.render(request()));
    }
    /**
     * An action that renders an HTML page with list of words and its count in golbal level
     * @return result
     */
    public Result globalStat(){
        return ok(globalstats.render(request()));
    }
    /**
     * An action that renders an HTML page with list of words and its count for specific project
     * @return result
     */
    public Result getProjectIDStat(String projId){
        return ok(projectIDStats.render(request()));
    }

    /**
     * Handles WebSocket for free(search) Page
     * @return
     */
    public WebSocket freeWS(){
        return WebSocket.Json.accept(request ->ActorFlow.actorRef(ws-> FreeActor.props(ws,wsClient),actorSystem,materializer));
    }
    /**
     * Handles WebSocket for ownerPage
     * @return
     */
    public WebSocket ownerWS(){
        return WebSocket.Json.accept(request ->ActorFlow.actorRef(ws-> OwnerActor.props(ws,wsClient),actorSystem,materializer));
    }
    /**
     * Handles WebSocket for skills Page
     * @return
     */
    public WebSocket skillsWS(){
        return WebSocket.Json.accept(request ->ActorFlow.actorRef(ws-> SkillActor.props(ws,wsClient),actorSystem,materializer));
    }
    /**
     * Handles WebSocket for globalStat Page
     * @return
     */
    public WebSocket globalStatActorWS(){
        return WebSocket.Json.accept(request ->ActorFlow.actorRef(ws-> GlobalStatsActor.props(ws,wsClient),actorSystem,materializer));
    }
    /**
     * Handles WebSocket for getProjectIDStat Page
     * @return
     */
    public WebSocket getProjectIDStatWS() {
        return WebSocket.Json.accept(request -> ActorFlow.actorRef(ws -> ProjectIDStatActor.props(ws, wsClient), actorSystem, materializer));
    }
}
