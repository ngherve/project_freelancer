package models;

import com.fasterxml.jackson.databind.JsonNode;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;


/**
 *
 */
public class Project {

    public String projId;
    public String ownerId;
    public long timeSubmitted;
    public String date;
    public String title;
    public String projectType;
    public ArrayList<Job> jobs;

    public Project(){

    }

    public Project(String projId, String ownerId, long timeSubmitted, String title, String projectType, ArrayList<Job> jobs) {
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

    public String getProjId(){return this.projId;}
    public void setProjId(String projId){this.projId = projId;}

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

    public ArrayList<Job> getJobs() {
        return jobs;
    }

    public void setJobs(ArrayList<Job> jobs) {
        this.jobs = jobs;
    }

    public String toString() {
        String skills = "";
        for(var job: this.jobs){
            skills+=job.name+ " ";
        }
        return (this.ownerId + " " + date + ", " + this.title + ", type: " + this.projectType + ", skills: " + skills);
    }

    public static Project fromJson(JsonNode json) {
        var projId = json.get("id").asText();
        var ownerId = json.get("owner_id").asText();
        var timeSubmitted = json.get("time_submitted").asLong();
        var title = json.get("title").asText();
        var type = json.get("type").asText();

        ArrayList<Job> skills = new ArrayList<>();
        JsonNode jobs = json.get("jobs");
        for (var job: jobs) {
            var jobId = job.get("id").asText();
            var jobName = job.get("name").asText();
            skills.add(new Job(jobId, jobName));
        }
        ;return new Project(projId,ownerId, timeSubmitted, title, type, skills);
    }
}
