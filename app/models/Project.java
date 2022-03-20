package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Project class is model for project which is in the JSON to be represented
 * @author Seung Hyun Hong, Tamanna Jahin, Nastaran Naseri, Herve Ngomseu Fosting
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Project {

    @JsonProperty("id")
    public String projId;

    @JsonProperty("owner_id")
    public String ownerId;

    @JsonProperty("submitdate")
    public long timeSubmitted;

    public String date;

    public String title;

    @JsonProperty("type")
    public String projectType;

    public List<Job> jobs;

    @JsonProperty("description")
    public String description;


    /**
     * Default Constructor for Project
     */
    public Project() {}

    /**
     * Parametrized Constructor for Project
     * @param projId String projId for storing the Project id
     * @param ownerId String ownerId for storing the Project owner_id
     * @param timeSubmitted long timeSubmitted for storing the Project submitdate
     * @param title String title for storing the Project title
     * @param projectType String projectType for storing the Project type
     * @param description String description for storing the Project description
     * @param jobs List of Object Job for storing the Project jobs
     */
    public Project(String projId, String ownerId, long timeSubmitted, String title, String projectType,String description, List<Job> jobs) {
        this.projId = projId;
        this.ownerId = ownerId;
        this.timeSubmitted = timeSubmitted;
        this.title = title;
        this.projectType = projectType;
        this.jobs = jobs;
        this.description = description;
        Date date = new Date(this.timeSubmitted * 1000);
        DateFormat formatter = new SimpleDateFormat("MMM dd yyyy");
        this.date = formatter.format(date);
    }

    /**
     * Getter for Project id
     * @return projId id of the Project
     */
    public String getProjId() {
        return this.projId;
    }

    /**
     * Setter for Project id
     * @param projId id of the Project
     */
    public void setProjId(String projId) {
        this.projId = projId;
    }

    /**
     * Getter for Project ownerId
     * @return ownerId of the Project
     */
    public String getOwnerId() {
        return ownerId;
    }

    /**
     * Setter for Project ownerId
     * @param ownerId ownerId of the Project
     */
    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    /**
     * Getter for Project timeSubmitted
     * @return timeSubmitted of the Project
     */
    public long getTimeSubmitted() {
        return timeSubmitted;
    }

    /**
     * Setter for Project timeSubmitted
     * Get Registration Date in long as parameter
     * and re-format it to "MMM dd yyyy" in String and store it in date variable
     * @param timeSubmitted timeSubmitted of the Project
     */
    public void setTimeSubmitted(long timeSubmitted) {
        this.timeSubmitted = timeSubmitted;
        Date date = new Date(this.timeSubmitted * 1000);
        DateFormat formatter = new SimpleDateFormat("MMM dd yyyy");
        this.date = formatter.format(date);
    }

    /**
     * Getter for Project title
     * @return title of the Project
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setter for Project title
     * @param title title of the Project
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Getter for Project jobs
     * @return jobs of the Project
     */
    public List<Job> getJobs() {
        return jobs;
    }

    /**
     * Setter for Project jobs
     * @param jobs jobs of the Project
     */
    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    /**
     * Getter for Project description
     * @return description of the Project
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter for Project description
     * @param description description of the Project
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Override toString method
     * @return Project information in String
     */
    @Override
    public String toString() {
        StringBuilder skills = new StringBuilder();

        for (var job : this.jobs) {
            skills.append(job.name).append(" ");
        }
        return (this.ownerId + " " + date + ", " + this.title + ", type: " + this.projectType + ", skills: " + skills);
    }
}
