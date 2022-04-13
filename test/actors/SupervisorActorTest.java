package actors;

import java.time.Duration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import actors.SupervisorActor.Data;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.testkit.javadsl.TestKit;

public class SupervisorActorTest {
    public static ActorSystem system;

    @Before
    public  void setup() {
        system = ActorSystem.create();
    }


    @Test
    public void SupervisorActorTest() {

        new TestKit(system) {
            {
                final Props props = SupervisorActor.getProps();
                final ActorRef subject = system.actorOf(props);
                final TestKit probe = new TestKit(system);

                within(
                        Duration.ofSeconds(10),
                        () -> {
                            subject.tell(new SupervisorActor.RegisterMsg(),getRef());
                            expectNoMsg();

                            subject.tell(new SupervisorActor.Tick(),getRef());
                            expectMsgClass(Data.class);

                            subject.tell(new SupervisorActor.DeRegister(),getRef());
                            expectNoMsg();

                            return null;
                        });
            }

        };
    }

    @After
    public  void teardown() {
        TestKit.shutdownActorSystem(system);
        system = null;
    }
}