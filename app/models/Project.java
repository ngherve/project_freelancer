package models;

import java.util.ArrayList;

/**
 *
 */
public class Project {

    public String owner_id;
    public String time_summited;
    public String title;
    public String type;
    public ArrayList<String> required_skills;


    public Project(String owner_id, String time_summited, String title, String type, ArrayList<String> required_skills) {
        this.owner_id = owner_id;
        this.time_summited = time_summited;
        this.title = title;
        this.type = type;
        this.required_skills = required_skills;
    }

    public String getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(String owner_id) {
        this.owner_id = owner_id;
    }

    public String getTime_summited() {
        return time_summited;
    }

    public void setTime_summited(String time_summited) {
        this.time_summited = time_summited;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<String> getRequired_skills() {
        return required_skills;
    }

    public void setRequired_skills(ArrayList<String> required_skills) {
        this.required_skills = required_skills;
    }
}
