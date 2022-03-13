package controllers;

import com.fasterxml.jackson.databind.JsonNode;


import models.Project;
import play.libs.ws.WSBodyReadables;
import play.libs.ws.WSClient;
import play.libs.ws.WSRequest;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.CompletionStage;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {
    private JsonNode json;
    private final WSClient ws;
    String searcKey = "";

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */

    @Inject
    public HomeController(WSClient ws) {
        this.ws = ws;
    }

    HashMap search_list = new HashMap<>();

    //ArrayList<ArrayList<Project>> search_list = new ArrayList<>();
    public CompletionStage<Result> index() {

        WSRequest request = ws.url("https://www.freelancer.com/api/projects/0.1/projects/active");
        request.addHeader("freelancer-oauth-v1", "D7qxDjJNB6KjDtEUcpOUrsfEGLFLPk");
        String search_key = "\"" + searcKey + "\"";
        System.out.println(search_key);

        request.addQueryParameter("query", search_key);
        request.addQueryParameter("job_details", "true");
        request.addQueryParameter("limit", "10");


        return request.setMethod("GET").stream().thenApply(res -> {
            if (res.getStatus() == 200) {
                json = res.getBody(WSBodyReadables.instance.json());
                JsonNode projects = json.get("result").get("projects");
                ArrayList<Project> list_proj = new ArrayList<>();
                for (var proj : projects) {
                    ArrayList<String> skills = new ArrayList<>();
                    JsonNode jobs = proj.get("jobs");
                    for (int i = 0; i < jobs.size(); i++) {
                        skills.add(jobs.get(i).get("name").asText());
                    }
                    list_proj.add(new Project(proj.get("owner_id").asText(), proj.get("time_submitted").asLong(), proj.get("title").asText(), proj.get("type").asText(), skills));
                }

                if (searcKey != "") {
                    search_list.put(searcKey, list_proj);
                }


            }
            return ok(views.html.freelancelot.render(search_list));
        });
    }


    public Result freelancelot() {
        return ok(views.html.freelancelot.render(search_list));
    }

    public Result captureSearchKeyword(String keyword) {
        searcKey = keyword;
        String result = "";
        return ok(views.html.freelancelot.render(search_list));
    }

}
