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
 */
public class HomeController extends Controller {
    @Inject private ActorSystem actorSystem;
    @Inject private Materializer materializer;

    @Inject
    WSClient wsClient;

    @Inject
    public HomeController(  ActorSystem system ) {

        system.actorOf(SupervisorActor.getProps(),"supervisorActor");
    }

    public Result index() {
        return ok(index.render(request()));
    }

    public Result free(){
        return ok(freelanslot.render(request()));
    }
    public Result owner(String id){
        return ok(owner.render(request()));
    }

    public Result skills(String skillId){
        return ok(skill.render(request()));
    }

    public Result globalStat(){
        return ok(globalstats.render(request()));
    }
    public Result getProjectIDStat(String projId){
        return ok(projectIDStats.render(request()));
    }


    public WebSocket freeWS(){
        return WebSocket.Json.accept(request ->ActorFlow.actorRef(ws-> FreeActor.props(ws,wsClient),actorSystem,materializer));
    }
    public WebSocket ownerWS(){
        return WebSocket.Json.accept(request ->ActorFlow.actorRef(ws-> OwnerActor.props(ws),actorSystem,materializer));
    }
    public WebSocket skillsWS(){
        return WebSocket.Json.accept(request ->ActorFlow.actorRef(ws-> SkillActor.props(ws,wsClient),actorSystem,materializer));
    }

    public WebSocket globalStatActorWS(){
        return WebSocket.Json.accept(request ->ActorFlow.actorRef(ws-> GlobalStatsActor.props(ws,wsClient),actorSystem,materializer));
    }

    public WebSocket getProjectIDStatWS() {
        return WebSocket.Json.accept(request -> ActorFlow.actorRef(ws -> ProjectIDStatActor.props(ws, wsClient), actorSystem, materializer));
    }
}
