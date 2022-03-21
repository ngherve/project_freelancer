package controllers;

import akka.Done;
import models.Project;

import models.Query;
import models.SearchQueryStats;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import play.data.DynamicForm;
import play.data.FormFactory;
import services.IApiService;
import play.cache.*;



/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    public static IApiService service;

    //private AsyncCacheApi cache;
    public String searchKey = "";

    public static void setService(IApiService service) {
        HomeController.service = service;
    }

    LinkedHashMap<String, List<Project>> search_list = new LinkedHashMap<>();


    @Inject
    public HomeController( IApiService service) {
        this.service = service;
    }
    String x = Integer.toString(((int)(Math.random() * 100000)) % 1000);

    public CompletionStage<Result> index() {

        List<Query> queries = new ArrayList<>();
        queries.add(new Query("query", "\""+searchKey+"\""));
        queries.add(new Query("job_details", "true"));
        queries.add(new Query("full_description", "true"));
        queries.add(new Query("limit", "10"));

        return service.getProjects(queries, "/active").thenApply(projects -> {
            if (!searchKey.isEmpty()) {
                search_list.put(searchKey, projects);
            }
            return ok(views.html.freelancelot.render(reverseMap(search_list)));
        });

    }

    @Inject FormFactory formFactory;
    /**
     * Capture the Search Keyword from the URL
     * @param request Http.Request request
     * @return redirect to the routes.HomeController.index()
     * @author Seung Hyun Hong, Tamanna Jahin, Nastaran Naseri, Herve Ngomseu Fosting
     */
    public Result captureSearchKeyword(Http.Request request) {
        DynamicForm dynamicForm = formFactory.form().bindFromRequest(request);
        searchKey = dynamicForm.get("search");
        return redirect(routes.HomeController.index());
    }


    /**
     * reverse the Map that contain Search Keyword to Display the new search result above others
     * @param toReverse LinkedHashMap toReverse
     * @param <T> generic for the key
     * @param <Q> generic for the ProjectList
     * @return
     * @author Seung Hyun Hong, Nastaran Naseri, Herve Ngomseu Fosting, Tamanna Jahin
     */
    public static <T,Q> LinkedHashMap<T,Q> reverseMap(LinkedHashMap<T,Q> toReverse){
        LinkedHashMap<T,Q> reverseMap = new LinkedHashMap<>();
        List<T> reverseOrderKeys = new ArrayList<>(toReverse.keySet());
        Collections.reverse(reverseOrderKeys);
        reverseOrderKeys.forEach((key) -> reverseMap.put(key, toReverse.get(key)));
        return reverseMap;
    }

    /**
     * pass the skillId to "https://www.freelancer.com/api/projects/0.1/projects/active?job_details=true&jobs[]=skillId"
     * display 10 latest projects that contain the skill
     * @param skillId String skillId
     * @return completionStage of Results to passing a list of founded projects and skillId to views/skill.scala.html to display
     * @author Nastaran Naseri
     */
    public CompletionStage<Result> skills(String skillId) {
        List<Query> queries = new ArrayList<>();
        queries.add(new Query("job_details", "true"));
        queries.add(new Query("jobs[]", skillId));
        queries.add(new Query("full_description", "true"));
        queries.add(new Query("limit", "10"));

        return service.getProjects(queries, "/active").thenApply(projects -> ok(views.html.skill.render(skillId,projects)));
    }

    /**
     * Receive ownerID and send it to getOwnerResult method in Service.
     * Recieve Json result and parse it into User object and list of Project
     * and send projects and user to view
     * @param ownerId String ownerId
     * @return CompleionStage<Result>
     * @author Seung Hyun Hong
     */
    public CompletionStage<Result> ownerIDSearch(String ownerId) {
        return service.getOwnerResult(ownerId).thenApply(ownerResult -> {
            var user = ownerResult.getUsers().get(ownerId);
            var projects = ownerResult.getProjects();
            return ok(views.html.owner.render(projects, user));
        });
    }

    /**
     * Performs search of the 250 latest projects based on the specified keyword
     * Provides global statistics about the frequency of all unique keywords from
     * the projects' descriptions in descending order of frequency.
     * It then sends the result the the views.globalstats.scala.html
     * @return CompleionStage<Result>
     * @author Herve Ngomseu Fotsing
     */
    public CompletionStage<Result> getGlobalStat() {
        List<Query> queries = new ArrayList<>();
        queries.add(new Query("query", searchKey));
        queries.add(new Query("limit", "250"));
        queries.add(new Query("job_details", "true"));
        queries.add(new Query("full_description", "true"));
        LinkedHashMap<String, List<Project>> projList = new LinkedHashMap<>();

        return service.getProjects(queries, "/active").thenApply(projects -> {
            String stats = "";

            projList.put(searchKey, projects);
            SearchQueryStats queryStat = new SearchQueryStats(searchKey, projList);

            stats = SearchQueryStats.combineOutput(queryStat.computeWordLevelStat());

            return ok(views.html.globalstats.render(stats));
        });
    }

    /**
     * Provides individual statistics about the frequency of all unique keywords from
     * the specified project's projId and description in descending order of frequency.
     * It then sends the result the the views.projectIDStat.scala.html
     * @param projId String projId
     * @return CompletionStage<Result>
     * @author Herve Ngomseu Fotsing
     */
    public CompletionStage<Result> getProjectIDStat(String projId) {
        List<Query> queries = new ArrayList<>();

        // queries.add(new Query("query", searchKey));
        queries.add(new Query("full_description", "true"));
        queries.add(new Query("job_details", "true"));

        return service.getIDProjects(queries, "/"+projId).thenApply(
                project ->  {
                    try {
                        var out = SearchQueryStats.combineOutput(SearchQueryStats.computeWordLevelStatByProject(project.description));
                        return ok(views.html.projectIDStat.render(out));
                    } catch (Exception e) {
                        e.printStackTrace();
                        return badRequest();
                    }
                });
    }
}

