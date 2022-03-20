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

import play.routing.RoutingDsl;
import play.server.Server;
import static play.mvc.Controller.*;

/**
 * Mock implementation of the TwitterAPI interface
 */
public class HomeControllerTestImplementation implements IApiService {

    private WSClient ws;

    private Server server;

    private FreelancerApiService freeLancerImplementation;

    /**
     * Constructor
     * First, we setup a server that will return our static files for a search or a profile
     * Then, we get a test instance of the WSClient, existing in Play
     * Then, we inject this instance in the real implementation: this way, the mock server
     * will respond instead of Twitter, giving us the static files
     * Finally, we override the base URL to query the local server which responds on /search and /statuses
     * without any domain name in front of it
     */
    public HomeControllerTestImplementation() {
        // Mock the Twitter's API response

        server =
                Server.forRouter(
                        (components) ->
                                RoutingDsl.fromComponents(components)
                                        .GET("/freelancelot/Project.json")
                                        .routingTo((request) -> ok().sendResource("Project.json"))
                                        .build());

        // Get test instance of WSClient
        ws = play.test.WSTestClient.newClient(server.httpPort());
        CompletionStage<WSResponse> response =
                ws.url("http://localhost:" + server.httpPort() + "/freelancelot/Project.json").get();
        // Here we will use the "real" implementation but with the mock server created above
        // Therefore, we will achieve code coverage but not call the live Twitter API!
        freeLancerImplementation = new FreelancerApiService(ws);
        freeLancerImplementation.setBaseUrl("");
    }

    @Override
    public CompletionStage<List<Project>> getProjects(List<Query> queries, String page){
        return freeLancerImplementation.getProjects(queries, page);
    }
    @Override
    public CompletionStage<Project> getIDProjects(List<Query> queries, String page){
        return freeLancerImplementation.getIDProjects(queries, page);
    }
    @Override
    public CompletionStage<OwnerResult> getOwnerResult(String ownerId){
        return freeLancerImplementation.getOwnerResult(ownerId);
    }

    /**
     * Test the search implementation
     * @param keyword keyword to search
     * @return CompletionStage of a WSResponse
     */
   /* @Override
    public CompletionStage<WSResponse> search(String keyword) {
        return twitterImplementation.search(keyword);
    }*/

    /**
     * Test the profile implementation
     * @param username username to search
     * @return CompletionStage of a WSResponse
     */
    /*@Override
    public CompletionStage<WSResponse> profile(String username) {
        return twitterImplementation.profile(username);
    }
*/
    /**
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
}
