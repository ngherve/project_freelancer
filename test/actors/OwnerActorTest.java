package actors;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.testkit.javadsl.TestKit;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import play.inject.Injector;
import play.libs.ws.WSClient;
import play.libs.ws.WSRequest;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
/**
 * Test Class for OwnerActorTest
 * @author Seung Hyun Hong, Nastaran Naseri, Herve Ngomseu Fosting
 */
public class OwnerActorTest {

    static ActorSystem system;

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
    /**
     * Test method to test performig of OwnerActor
     */
    @Test
    public void OwnerActorTest() {
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
        ActorRef ownerActor = system.actorOf(OwnerActor.props(ref,ws), "OwnerActor");
        JsonNodeFactory factory = JsonNodeFactory.instance;
        ObjectNode root = factory.objectNode();
        root.put("keyword", "java");
        ownerActor.tell(root, ActorRef.noSender());
        assertEquals("\"java\"", root.get("keyword").toString());

    }
}