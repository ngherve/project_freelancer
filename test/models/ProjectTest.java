package models;

import models.Job;
import models.Project;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * test for Project Model
 * @ @author Seung Hyun Hong, Tamanna Jahin, Nastaran Naseri, Herve Ngomseu Fosting
 */
// This is the test for Project
public class ProjectTest {
    private final ArrayList<Job> skills = new ArrayList<Job>(Arrays.asList(new Job("1", "java")));
    private final Project proj = new Project("1", "123", 123456, "title", "type","description", skills);

    /**
     * test Project constructor
     */
    @Test
    public void Project() {
        assertNotNull(new Project());
    }

    /**
     * Test get proj id method.
     */
    @Test
    public void testGetProjId() {
        assertEquals("1", proj.getProjId());
    }

    /**
     * Test Sets proj id method.
     */
    @Test
    public void setProj_id() {
        proj.setProjId("1");
        assertEquals("1", proj.getProjId());
    }

    /**
     * Test Gets owner id method.
     */
    @Test
    public void getOwnerId() {
        assertEquals("123", proj.getOwnerId());
    }

    /**
     * Test Sets owner id method.
     */
    @Test
    public void setOwner_id() {
        proj.setOwnerId("123");
        assertEquals("123", proj.getOwnerId());
    }

    /**
     * Test Gets time summited method.
     */
    @Test
    public void getTime_summited() {
        assertEquals(123456, proj.getTimeSubmitted());
    }

    /**
     * Test Sets time summited method.
     */
    @Test
    public void setTime_summited() {
        proj.setTimeSubmitted(123456);
        assertEquals(123456, proj.getTimeSubmitted());
    }

    /**
     * Test Gets title method.
     */
    @Test
    public void getTitle() {
        assertEquals("title", proj.getTitle());
    }

    /**
     * Test Sets title method.
     */
    @Test
    public void setTitle() {
        proj.setTitle("title");
        assertEquals("title", proj.getTitle());
    }

    /**
     * Test Gets description method.
     */
    @Test
    public void getDescription() {
        assertEquals("description", proj.getDescription());
    }

    /**
     * Test Sets description method.
     */
    @Test
    public void setDescription() {
        proj.setDescription("description");
        assertEquals("description", proj.getDescription());
    }

    /**
     * Test Gets jobs method.
     */
    @Test
    public void getJobs() {
        assertEquals(skills, proj.getJobs());
    }

    /**
     * Test Sets required skills method.
     */
    @Test
    public void setRequired_skills() {
        proj.setJobs(skills);
        assertEquals(skills, proj.getJobs());
    }


    /**
     * Test To string method.
     */
    @Test
    public void ToString() {
        assertEquals("123 "+proj.date+", title, type: type, skills: java ",proj.toString());
    }

    /**
     * Test to getProjId
     */
    @Test
    public void getProjId() {
        assertEquals("1", proj.getProjId());
    }

    @Test
    public void getDate() {
        assertEquals("Jan. 02 1970", proj.getDate());
    }

    @Test
    public void getProjectType() {
        assertEquals("type", proj.getProjectType());
    }
}