package services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import models.OwnerResult;
import models.Project;
import models.Query;
import models.User;
import play.libs.ws.WSClient;
import play.libs.ws.WSRequest;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

/**
 *  Mock class for test FreelancerApiService class in order to avoid real api call
 *  @author Seung Hyun Hong, Nastaran Naseri, Herve Ngomseu Fosting
 */

public class FreelancerApiServiceMock implements IApiService {
    String baseURL ="url";

    /**
     * default constructor
     */
    public FreelancerApiServiceMock(){
    }

    /**
     * This method will return list of Project from json file in resources folder
     * instead of calling real api.
     * @param queries
     * @param page
     * @return ompletionStage<List<Project>>
     */
    @Override
    public CompletionStage<List<Project>> getProjects(List<Query> queries, String page) {
        String testResources = System.getProperty("user.dir") + "/test/resources/Project.json";
        java.io.File jsonFile = new java.io.File(testResources);
        WSClient ws = null;
        WSRequest request = ws.url(baseURL + page);
        for (var query: queries) {
            request.addQueryParameter(query.key, query.value);
        }
        CompletionStage<List<Project>> completProject = CompletableFuture.supplyAsync(() -> {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonProjects =null;
            List<Project> projects =null;

            try {
                JsonNode rootArray = mapper.readTree(jsonFile);
                jsonProjects = rootArray.get("result").get("projects");
                projects = new ArrayList<>();
                for (var json : jsonProjects) {
                    var project = mapper.treeToValue(json, Project.class);
                    projects.add(project);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return projects;
        });
        return completProject;
    }
    /**
     * This method will return list of Project from json file in resources folder
     * instead of calling real api.
     * @param queries
     * @param page
     * @return ompletionStage<List<Project>>
     */
    @Override
    public CompletionStage<Project> getIDProjects(List<Query> queries, String page) {
        String testResources = System.getProperty("user.dir") + "/test/resources/Project.json";
        java.io.File jsonFile = new java.io.File(testResources);

        CompletionStage<Project> completProject = CompletableFuture.supplyAsync(() -> {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonProjects =null;
            JsonNode jsonProjects2 =null;
            Project proj= null;
            try {
                JsonNode rootArray = mapper.readTree(jsonFile);
                jsonProjects = rootArray.get("result").get("projects").get(0);
                proj = mapper.treeToValue(jsonProjects, Project.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return proj;
        });
        return completProject;
    }
    /**
     * This method will return OwnerResult from json file in resources folder
     * instead of calling real api.
     * @param queries
     * @param page
     * @return OwnerResult
     */
    @Override
    public CompletionStage<OwnerResult> getOwnerResult(String ownerId) {
        String testResources = System.getProperty("user.dir") + "/test/resources/User.json";
        java.io.File jsonFile = new java.io.File(testResources);
        OwnerResult os = new OwnerResult();
        CompletionStage<OwnerResult> completOwnerResult = CompletableFuture.supplyAsync(() -> {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonProjects =null;
            JsonNode jsonUser = null;
            List<Project> projects =null;
            User user = null;
            try {
                JsonNode rootArray = mapper.readTree(jsonFile);
                jsonProjects = rootArray.get("result").get("projects");
                projects = new ArrayList<>();
                for (var json : jsonProjects) {
                    var project = mapper.treeToValue(json, Project.class);
                    projects.add(project);
                }
                os.setProjects(projects);
                jsonUser = rootArray.get("result").get("users").get(ownerId);
                Map<String,User> owner = new HashMap<>();
                user = mapper.readValue(jsonUser.toString(), User.class);
                owner.put(ownerId,user);
                os.setUsers(owner);

            } catch (IOException e) {
                e.printStackTrace();
            }
            return os;
        });
        return completOwnerResult;
    }

    /**
     * Test for setBaseUrl method
     * @param s
     */
    @Override
    public void setBaseUrl(String s) {
        this.baseURL = s;
    }

    /**
     * Test for getBaseUrl method
     * @return Stromg baseURL
     */
    @Override
    public String getBaseUrl() {
        return baseURL;
    }

}
