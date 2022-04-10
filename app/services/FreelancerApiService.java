package services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.*;
import play.libs.ws.WSBodyReadables;
import play.libs.ws.WSClient;
import play.libs.ws.WSRequest;

import javax.inject.Inject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletionStage;

/**
 * FreelancerApiService implements IApiService class
 *
 */
public class FreelancerApiService implements IApiService {
    String baseURL = "https://www.freelancer.com/api/projects/0.1/projects";
    private final WSClient ws;

    /**
     * Parametrized Constructor for FreelancerApiService
     * @param ws WSClient
     */
    @Inject
    public FreelancerApiService( WSClient ws) {
        this.ws = ws;
    }


    /**
     * Parse the Projects
     * @param queries List of Query
     * @param page String page
     * @return CompletionStage of a List of Projects
     */
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


    /**
     * Parse the owner and owner's Projects
     * @param ownerId String ownerId
     * @return CompletionStage of a List of OwnerResult
     * @author Seung Hyun Hong
     */
    @Override
    public CompletionStage<OwnerResult> getOwnerResult( String ownerId) {
        WSRequest request = ws.url(baseURL);

        request.addQueryParameter("job_details", "true");
        request.addQueryParameter("user_details", "true");
        request.addQueryParameter("full_descriptions", "true");
        request.addQueryParameter("owners[]", ownerId);
        request.addQueryParameter("limit", "10");

        ObjectMapper objectMapper = new ObjectMapper();

        OwnerResult os = new OwnerResult();
        return request.setMethod("GET").stream().thenApply(res -> {
            ArrayList<Project> projects = new ArrayList<>();
            if (res.getStatus() == 200) {
                JsonNode jsonResult = res.getBody(WSBodyReadables.instance.json()).get("result");
                JsonNode jsonProjects = jsonResult.get("projects");
                JsonNode jsonUser = jsonResult.get("users").get(ownerId);
                for (var json : jsonProjects) {
                    try {
                        var project = objectMapper.treeToValue(json, Project.class);
                        projects.add(project);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                }

                try {
                    User users = objectMapper.readValue(jsonUser.toString(), User.class);
                    Map<String, User> owner = new HashMap<>();
                    owner.put(ownerId, users);
                    os.setUsers(owner);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                os.setProjects(projects);

            }

            return os ;
        });

    }


    /**
     * Parse the skills and find Projects that contain the skill
     * @param queries List of Query
     * @param page String page
     * @return CompletionStage of a List of Project
     * @author Nastaran Naseri
     */
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

    public void setBaseUrl(String s) {
        baseURL = s;
    }
    public String getBaseUrl() {
        return baseURL;
    }
}