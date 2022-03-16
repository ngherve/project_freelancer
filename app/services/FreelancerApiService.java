package services;

import com.fasterxml.jackson.databind.JsonNode;
import models.Project;
import models.Query;
import play.libs.ws.WSBodyReadables;
import play.libs.ws.WSClient;
import play.libs.ws.WSRequest;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletionStage;

public class FreelancerApiService implements IApiService {
    private final String baseURL = "https://www.freelancer.com/api/projects/0.1/projects";
    private final WSClient ws;

    @Inject
    public FreelancerApiService(WSClient ws) {
        this.ws = ws;
    }

    @Override
    public CompletionStage<List<Project>> getProjects(List<Query> queries, String page) {
        WSRequest request = ws.url(baseURL + page);

        for (var query: queries) {
            request.addQueryParameter(query.key, query.value);
        }

        request.addQueryParameter("limit", "10");


        return request.setMethod("GET").stream().thenApply(res -> {
            ArrayList<Project> projects = new ArrayList<>();

            if (res.getStatus() == 200) {
                JsonNode jsonProjects = res.getBody(WSBodyReadables.instance.json()).get("result").get("projects");

                for (var json : jsonProjects) {
                    projects.add(Project.fromJson(json));
                }
            }

            return projects;
        });
    }
}
