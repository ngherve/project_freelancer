package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Job class is model for Job which is in the JSON to be represented
 * @author Nastaran Naseri
 */
@JsonIgnoreProperties(ignoreUnknown = true)

public class Job {
    public String id;
    public String name;

    /**
     * Default Constructor for Job
     */
    public Job() {}

    /**
     * Parametrized Constructor for Job
     * @param id String id for storing the Job id
     * @param name  String name for storing the Job name
     */
    public Job(String id, String name) {
        this.id = id;
        this.name = name;
    }


    /**
     * Getter for Job id
     * @return id of the Job
     */
    public String getId() {
        return id;
    }

    /**
     * Setter for Job id
     * @param id id of the Job
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Getter for Job name
     * @return name of the Job
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for Job name
     * @param name  name of the Job
     */
    public void setName(String name) {
        this.name = name;
    }
}
