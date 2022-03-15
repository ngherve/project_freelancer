package models;

import org.junit.Test;
import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;


public class ProjectTest {

    @Test
    public void getOwner_id() {
        Project proj = new Project();
        proj.setOwner_id("100");
        assertEquals("100",proj.getOwner_id());
    }

    @Test
    public void setOwner_id() {
        Project proj = new Project();
        proj.setOwner_id("100");
        assertEquals("100",proj.getOwner_id());
    }

    @Test
    public void getTime_summited() {
        Project proj = new Project();
        proj.setTime_summited(100000);
        assertEquals(100000,proj.getTime_summited());
    }

    @Test
    public void setTime_summited() {
        Project proj = new Project();
        proj.setTime_summited(100000);
        assertEquals(100000,proj.getTime_summited());
    }

    @Test
    public void getTitle() {
        Project proj = new Project();
        proj.setTitle("Title");
        assertEquals("Title",proj.getTitle());
    }

    @Test
    public void setTitle() {
        Project proj = new Project();
        proj.setTitle("Title");
        assertEquals("Title",proj.getTitle());
    }

    @Test
    public void getRequired_skills() {
        Project proj = new Project();
        ArrayList<String> skills = new ArrayList<>();
        skills.add("java");
        skills.add("PHP");
        proj.setRequired_skills(skills);
        assertEquals(Arrays.asList("java", "PHP"),proj.getRequired_skills());
    }

    @Test
    public void setRequired_skills() {
        Project proj = new Project();
        ArrayList<String> skills = new ArrayList<>();
        skills.add("java");
        skills.add("PHP");
        proj.setRequired_skills(skills);
        assertEquals(Arrays.asList("java", "PHP"),proj.getRequired_skills());
    }

    @Test
    public void testToString() {
        ArrayList<String> skills = new ArrayList<>();
        skills.add("java");
        skills.add("PHP");
        Project proj = new Project("123",123456,"title","type",skills);
        Date date = new Date(proj.getTime_summited() * 1000);
        DateFormat formatter = new SimpleDateFormat("MMM dd yyyy");
        String temp = formatter.format(date);
        assertEquals("123 " + temp + ", title, type: type, skills: [java, PHP]",proj.toString());
    }

}

