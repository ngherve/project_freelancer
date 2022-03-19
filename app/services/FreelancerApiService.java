package services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.OwnerResult;
import models.Project;
import models.Query;
import play.libs.concurrent.HttpExecutionContext;
import play.libs.ws.WSBodyReadables;
import play.libs.ws.WSClient;
import play.libs.ws.WSRequest;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletionStage;

import static play.mvc.Results.redirect;


public class FreelancerApiService implements IApiService {
    private final String baseURL = "https://www.freelancer.com/api/projects/0.1/projects";
    private final WSClient ws;

    @Inject
    public FreelancerApiService( WSClient ws) {
        this.ws = ws;
    }



    @Override
    public CompletionStage<List<Project>> getProjects(List<Query> queries, String page) {
        WSRequest request = ws.url(baseURL + page);
        ObjectMapper objectMapper = new ObjectMapper();

        for (var query: queries) {
            request.addQueryParameter(query.key, query.value);
        }

        return request.setMethod("GET").stream().thenApply(res -> {
            ArrayList<Project> projects = new ArrayList<>();
            if (res.getStatus() == 200 ) {
                JsonNode jsonProjects = res.getBody(WSBodyReadables.instance.json()).get("result").get("projects");
                for (var json : jsonProjects) {
                    try {
                        var project = objectMapper.treeToValue(json, Project.class);
                        projects.add(project);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                }
            }
            return projects;
      });
    }


    @Override
    public CompletionStage<OwnerResult> getOwnerResult(String ownerId) {
        WSRequest request = ws.url(baseURL);

        request.addQueryParameter("job_details", "true");
        request.addQueryParameter("user_details", "true");
        request.addQueryParameter("full_descriptions", "true");
        request.addQueryParameter("owners[]", ownerId);
        request.addQueryParameter("limit", "10");

        ObjectMapper objectMapper = new ObjectMapper();
        return request.setMethod("GET").stream().thenApply(res -> {
            if (res.getStatus() == 200) {
                JsonNode jsonProjects = res.getBody(WSBodyReadables.instance.json()).get("result");
                try {
                    return objectMapper.treeToValue(jsonProjects, OwnerResult.class);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
            return null;
        });
    }

    @Override
    public CompletionStage<Project> getIDProjects(List<Query> queries, String page) {
        WSRequest request = ws.url(baseURL + page);
        ObjectMapper objectMapper = new ObjectMapper();
        for (var query: queries) {
            request.addQueryParameter(query.key, query.value);
        }
        return request.setMethod("GET").stream().thenApply(res -> {
            if (res.getStatus() == 200) {
                JsonNode jsonProject = res.getBody(WSBodyReadables.instance.json()).get("result");

                try {
                    return objectMapper.treeToValue(jsonProject, Project.class);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
            return null;
        });
    }
}