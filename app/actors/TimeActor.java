package actors;

import java.util.concurrent.TimeUnit;


import scala.concurrent.duration.Duration;
import akka.actor.AbstractActorWithTimers;
import akka.actor.ActorRef;
import akka.actor.Props;
import play.Logger;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class TimeActor extends AbstractActorWithTimers {
	private Set<ActorRef> userActors;
	
    private static final class Tick {
    }
    
    static public class RegisterMsg {
    }

    static public Props getProps() {
    	return Props.create(TimeActor.class, () -> new TimeActor());
    }	

    private TimeActor() {
    	this.userActors = new HashSet<>();
    }
    
    @Override
    public void preStart() {
    	Logger.info("TimeActor {} started", self());
    	getTimers().startPeriodicTimer("Timer", new Tick(), Duration.create(5, TimeUnit.SECONDS));
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
        		.match(Tick.class, msg -> notifyClients())
                .match(RegisterMsg.class, msg -> userActors.add(sender()))
                .build();
    }

	private void notifyClients() {
    	UserActor.TimeMessage tMsg = new UserActor.TimeMessage(LocalDateTime.now().toString());
    	userActors.forEach(ar -> ar.tell(tMsg, self()));
	}
}
