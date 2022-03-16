package models;

import org.junit.Test;
import static org.junit.Assert.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeCreator;
import org.junit.Before;
import org.junit.Test;
import scala.util.parsing.json.JSONObject;


import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

// This is the test for Project
public class ProjectTest {
    private ArrayList<Job> skills = new ArrayList<Job>(Arrays.asList(new Job("1","java")));
    private Project proj= new Project("123",123456,"title","type",skills);


    @Test
    public void getOwner_id() {
        assertEquals("123",proj.getOwnerId());
    }

    @Test
    public void setOwner_id() {
        proj.setOwnerId("123");
        assertEquals("123",proj.getOwnerId());
    }

    @Test
    public void getTime_summited() {
        assertEquals(123456,proj.getTimeSubmitted());
    }

    @Test
    public void setTime_summited() {
        proj.setTimeSubmitted(123456);
        assertEquals(123456,proj.getTimeSubmitted());
    }

    @Test
    public void getTitle() {
        assertEquals("title",proj.getTitle());
    }

    @Test
    public void setTitle() {
        proj.setTitle("title");
        assertEquals("title",proj.getTitle());
    }

    @Test
    public void getJobs() {
        assertEquals(skills,proj.getJobs());
    }

    @Test
    public void setRequired_skills() {
        proj.setJobs(skills);
        assertEquals(skills,proj.getJobs());
    }

    @Test
    public void testToString() {
        Date date = new Date(proj.getTimeSubmitted() * 1000);
        DateFormat formatter = new SimpleDateFormat("MMM dd yyyy");
        String temp = formatter.format(date);
        assertEquals("123 " + temp + ", title, type: type, skills: java ",proj.toString());
    }

    @Test
    public void fromJson() {
        String json = "{ \"owner_id\" : \"123\" , \"time_submitted\" : \"123456\", \"title\" : \"title\", \"type\" : \"type\", \"jobs\" : [{ \"id\" : \"1\", \"name\" : \"java\"}]}";
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = null;
        try {
            jsonNode = objectMapper.readTree(json);
            assertEquals(proj,Project.fromJson(jsonNode));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
  //       assertEquals(proj,Project.fromJson(jsonNode));
    }
}

