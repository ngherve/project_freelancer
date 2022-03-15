package controllers;

import com.fasterxml.jackson.databind.JsonNode;


import models.Project;

import play.libs.ws.WSBodyReadables;
import play.libs.ws.WSClient;
import play.libs.ws.WSRequest;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.*;
import java.util.concurrent.CompletionStage;
import play.data.DynamicForm;
import play.data.FormFactory;



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

    LinkedHashMap  search_list = new LinkedHashMap <String, ArrayList<Project>>();
    LinkedHashMap  temp = new LinkedHashMap <String, ArrayList<Project>>();

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
                    List<String> alKeys = new ArrayList<String>(search_list.keySet());
                    Collections.reverse(alKeys);
                    System.out.println(alKeys.toString());
                    for(int i = 0; i>alKeys.size();i++){
                        System.out.println(search_list.get(i) + "  i");
                        //Map.Entry<String, ArrayList<Project>> entry = (Map.Entry<String, ArrayList<Project>>) temp.get(i);
                        search_list.put(alKeys.get(i), search_list.get(alKeys.get(i)));
                    }

                }


            }
            return ok(views.html.freelancelot.render(search_list));
        });
    }

    @Inject FormFactory formFactory;
    public Result captureSearchKeyword(Http.Request request) {
        DynamicForm dynamicForm = formFactory.form().bindFromRequest(request);
        searcKey = dynamicForm.get("search");
        System.out.println(searcKey);
        return redirect(routes.HomeController.index());
    }


    public CompletionStage<Result> skills(String theSkill) {
        WSRequest request = ws.url("https://www.freelancer.com/api/projects/0.1/jobs/search");
        request.addHeader("freelancer-oauth-v1", "D7qxDjJNB6KjDtEUcpOUrsfEGLFLPk");
        String theSkillToFind = "\"" + theSkill+ "\"";
        System.out.println(theSkillToFind);

        request.addQueryParameter("job_names[]", theSkill);


        return request.setMethod("GET").stream().thenApply(res -> {
            if (res.getStatus() == 200) {
                json = res.getBody(WSBodyReadables.instance.json());
                //    JsonNode job = json.get("result").get(0);
                JsonNode job = json.get("result").get(0);
                String job_ID = job.get("id").asText();

                System.out.println(job_ID);


                System.out.println(job.toPrettyString());

            }
            return ok(views.html.skill.render(search_list));
        });
    }


    public Result ownerIDSearch(String theOwner) {



        return ok(views.html.owner.render(search_list));
    }

}
