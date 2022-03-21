package controllers;

import models.OwnerResult;
import models.Project;
import models.Query;

import play.libs.ws.WSResponse;
import services.FreelancerApiService;
import services.IApiService;
import org.junit.After;
import play.libs.ws.WSClient;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletionStage;

import play.server.Server;

/**
 * Implementation to test home controller
 * @author Seung Hyun Hong, Tamanna Jahin, Nastaran Naseri, Herve Ngomseu Fosting
 */
public class HomeControllerTestImplementation implements IApiService {

    private WSClient ws;

    private Server server;

    private FreelancerApiService freeLancerImplementation;

   // public HomeControllerTestImplementation() {}
        //
//
//        server =
//                Server.forRouter(
//                        (components) ->
//                                RoutingDsl.fromComponents(components)
//                                        .GET("/Project.json")
//                                        .routingTo((request) -> ok().sendResource("Project.json"))
//                                        .build());
//
//        // Get test instance of WSClient
//        ws = play.test.WSTestClient.newClient(server.httpPort());
//        CompletionStage<WSResponse> response =
//                ws.url("http://localhost:" + server.httpPort() + "/freelancelot/Project.json").get();
//        // Here we will use the "real" implementation but with the mock server created above
//        // Therefore, we will achieve code coverage but not call the live Twitter API!
//        freeLancerImplementation = new FreelancerApiService(ws);
//        freeLancerImplementation.setBaseUrl("");
 //   }


    /**
     * Cleaning the ws client
     * Close the WSClient, stop the server
     * @throws IOException exception
     */
    @After
    public void tearDown() throws IOException {
        try {
            ws.close();
        }
        finally {
            server.stop();
        }
    }

    @Override
    public CompletionStage<List<Project>> getProjects(List<Query> queries, String page) {
        return null;
    }

    @Override
    public CompletionStage<Project> getIDProjects(List<Query> queries, String page) {
        return null;
    }

    @Override
    public CompletionStage<OwnerResult> getOwnerResult(String ownerId) {
        return null;
    }
}
