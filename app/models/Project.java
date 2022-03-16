package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Project {

    @JsonProperty("id")
    public String projId;

    @JsonProperty("owner_id")
    public String ownerId;

    @JsonProperty("time_submitted")
    public long timeSubmitted;

    public String date;

    public String title;

    @JsonProperty("type")
    public String projectType;

    public List<Job> jobs;

    public Project() {
    }

    public Project(String projId, String ownerId, long timeSubmitted, String title, String projectType, List<Job> jobs) {
        this.projId = projId;
        this.ownerId = ownerId;
        this.timeSubmitted = timeSubmitted;
        this.title = title;
        this.projectType = projectType;
        this.jobs = jobs;
        Date date = new Date(this.timeSubmitted * 1000);
        DateFormat formatter = new SimpleDateFormat("MMM dd yyyy");
        this.date = formatter.format(date);

    }

    public String getProjId() {
        return this.projId;
    }

    public void setProjId(String projId) {
        this.projId = projId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public long getTimeSubmitted() {
        return timeSubmitted;
    }

    public void setTimeSubmitted(long timeSubmitted) {
        this.timeSubmitted = timeSubmitted;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    public String toString() {
        StringBuilder skills = new StringBuilder();

        for (var job : this.jobs) {
            skills.append(job.name).append(" ");
        }
        return (this.ownerId + " " + date + ", " + this.title + ", type: " + this.projectType + ", skills: " + skills);
    }
}
