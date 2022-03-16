package models;


import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

// This is the test for Project
public class ProjectTest {
    private final ArrayList<Job> skills = new ArrayList<Job>(Arrays.asList(new Job("1", "java")));
    private final Project proj = new Project("1", "123", 123456, "title", "type", skills);


    @Test
    public void testGetOwnerId() {
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
    public void getJobs() {
        assertEquals(skills, proj.getJobs());
    }

    @Test
    public void setRequired_skills() {
        proj.setJobs(skills);
        assertEquals(skills, proj.getJobs());
    }

    /***
     *
     */
    @Test
    public void ToString() {

    }
}