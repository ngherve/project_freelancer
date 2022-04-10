package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Map;

/**
 * A OwnerResult class is for store information for list of projects and user information in map(id and user object)
 *
 * @author Seunghyun Hong
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class OwnerResult {
    List<Project> projects;
    Map<String, User> users;

    /**
     * constructor for OnerResult
     */
    public OwnerResult() {}

    /**
     * getter for Projects
     * @return projects of Projects
     */
    public List<Project> getProjects() {
        return projects;
    }

    /**
     * setter for Projects
     * @param projects list of project
     */
    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    /**
     *
     * @return
     */
    public Map<String, User> getUsers() {
        return users;
    }

    public void setUsers(Map<String, User> users) {
        this.users = users;
    }
}
