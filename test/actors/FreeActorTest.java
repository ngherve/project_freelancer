package actors;


import akka.testkit.TestActorRef;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.google.inject.AbstractModule;
import models.Project;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import org.mockito.Mockito;
import play.Logger;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.stream.Materializer;
import akka.testkit.javadsl.TestKit;
import static play.inject.Bindings.bind;
import play.Application;
import play.api.inject.guice.GuiceableModule;
import play.cache.AsyncCacheApi;
import play.inject.Injector;
import play.inject.guice.GuiceApplicationBuilder;
import actors.SupervisorActor.Data;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.inject.guice.GuiceInjectorBuilder;
import play.libs.Json;
import play.libs.ws.WSClient;
import play.libs.ws.WSRequest;
import services.FreelancerApiService;

import services.IApiService;

import static play.inject.Bindings.bind;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.ExecutionException;

public class FreeActorTest {
    static ActorSystem system;
    private static FreeActor freeActor;
    private static FreelancerApiService freeService;

    private static Injector testApp;
    ActorRef ref;
    @Before
    public void setup() {
        system = ActorSystem.create();
    }

    @After
    public void teardown() {
        TestKit.shutdownActorSystem(system);
        system = null;
    }

    @Test
    public void FreeActorTest() {
        WSClient ws = new WSClient() {
            @Override
            public Object getUnderlying() {
                return null;
            }

            @Override
            public play.api.libs.ws.WSClient asScala() {
                return null;
            }

            @Override
            public WSRequest url(String url) {
                return null;
            }

            @Override
            public void close() throws IOException {

            }
        };
        ActorRef freeActor = system.actorOf(FreeActor.props(ref,ws), "FreeActor");
        JsonNodeFactory factory = JsonNodeFactory.instance;
        ObjectNode root = factory.objectNode();
        root.put("keyword", "java");
        freeActor.tell(root, ActorRef.noSender());

        assertEquals("\"java\"", root.get("keyword").toString());
    }
}