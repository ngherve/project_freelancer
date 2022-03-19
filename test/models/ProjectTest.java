package models;


import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

// This is the test for Project
public class ProjectTest {

    ArrayList<Job> skills = new ArrayList<Job>(Arrays.asList(new Job("1", "java")));
    Project proj = new Project("1", "123", 123456, "title", "type","description", skills);

    @Test
    public void Project(){
        assertNotNull(new Project());
    }
    @Test
    public void getProjId() {
        assertEquals("1", proj.getProjId());
    }

    @Test
    public void setProj_id() {
        proj.setProjId("1");
        assertEquals("1", proj.getProjId());
    }
    @Test
    public void getOwnerId() {
        assertEquals("123", proj.getOwnerId());
    }

    @Test
    public void setOwner_id() {
        proj.setOwnerId("123");
        assertEquals("123", proj.getOwnerId());
    }

    @Test
    public void getTime_summited() {
        assertEquals(123456, proj.getTimeSubmitted());
    }

    @Test
    public void setTime_summited() {
        proj.setTimeSubmitted(123456);
        assertEquals(123456, proj.getTimeSubmitted());
    }

    @Test
    public void getTitle() {
        assertEquals("title", proj.getTitle());
    }

    @Test
    public void setTitle() {
        proj.setTitle("title");
        assertEquals("title", proj.getTitle());
    }
    @Test
    public void getDescription() {
        assertEquals("description", proj.getDescription());
    }

    @Test
    public void setDescription() {
        proj.setDescription("description");
        assertEquals("description", proj.getDescription());
    }

    @Test
    public void getJobs() {
        assertEquals(skills, proj.getJobs());
    }

    @Test
    public void setRequired_skills() {
        proj.setJobs(skills);
        assertEquals(skills, proj.getJobs());
    }

    @Test
    public void ToString() {
        assertEquals("123 "+proj.date+", title, type: type, skills: java ",proj.toString());
    }
}