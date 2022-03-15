package models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;


/**
 *
 */
public class Project {

    public String owner_id;
    public long time_summited;
    public String date;
    public String title;
    public String proj_type;
    public ArrayList<String> required_skills;

    public Project(){

    }

    public Project(String owner_id, long time_summited, String title, String proj_type, ArrayList<String> required_skills) {
        this.owner_id = owner_id;
        this.time_summited = time_summited;
        this.title = title;
        this.proj_type = proj_type;
        this.required_skills = required_skills;
        Date date = new Date(this.time_summited * 1000);
        DateFormat formatter = new SimpleDateFormat("MMM dd yyyy");
        this.date = formatter.format(date);

    }

    public String getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(String owner_id) {
        this.owner_id = owner_id;
    }

    public long getTime_summited() {
        return time_summited;
    }

    public void setTime_summited(long time_summited) {
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

    public String toString() {
        return (this.owner_id + " " + date + ", " + this.title + ", type: " + this.proj_type + ", skills: " + Arrays.toString(this.required_skills.toArray()));
    }
}
