package services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.OwnerResult;
import models.Project;
import models.Query;
import play.libs.ws.WSClient;
import play.libs.ws.WSRequest;

import javax.inject.Inject;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletionStage;


public class FreeImplimentation implements IApiService {

    String baseUrl = "https://www.freelancer.com/api/projects/0.1/projects";
    private WSClient ws;

    /**
     * Constructor
     * @param ws WSClient provided by Guice
     */
    @Inject
    public FreeImplimentation(WSClient ws) {
        this.ws = ws;
    }


    @Override
    public CompletionStage<List<Project>> getProjects(List<Query> queries, String page) {
        WSRequest request = ws.url(baseUrl + "\"Project.json");
        ObjectMapper objectMapper = new ObjectMapper();

        return request.setMethod("GET").stream().thenApply(res -> {
            ArrayList<Project> projects = new ArrayList<>();
            if (res.getStatus() == 200 ) {
                for (int i = 0; i < 10; i++) {
                    projects.add(new Project());
                }
                return projects;
            }
            return projects;
        });
    }

    @Override
    public CompletionStage<Project> getIDProjects(List<Query> queries, String page) {
        return null;
    }

    @Override
    public CompletionStage<OwnerResult> getOwnerResult(String ownerId) {
        return null;
    }


    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
}
