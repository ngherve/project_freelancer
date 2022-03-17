package services;

import com.google.inject.ImplementedBy;
import models.Project;
import models.Query;

import java.util.List;
import java.util.concurrent.CompletionStage;

@ImplementedBy(FreelancerApiService.class)
public interface IApiService {
    CompletionStage<List<Project>> getProjects(List<Query> queries, String page);
    CompletionStage<Project> getIDProjects(List<Query> queries, String page);
}
