package services;

import com.google.inject.ImplementedBy;
import models.OwnerResult;
import models.Project;
import models.Query;

import java.util.List;
import java.util.concurrent.CompletionStage;

/**
 * Interface of API service
 * @author Seung Hyun Hong, Nastaran Naseri, Herve Ngomseu Fosting, Tamanna Jahin
 */

@ImplementedBy(FreelancerApiService.class)
public interface IApiService {
    CompletionStage<List<Project>> getProjects(List<Query> queries, String page);
    CompletionStage<Project> getIDProjects(List<Query> queries, String page);
    CompletionStage<OwnerResult> getOwnerResult(String ownerId);
}
