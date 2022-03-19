package models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class JobTest {

    Job job = new Job("id","name");

    @Test
    public void Job(){
        assertNotNull(new Job());
    }

    @Test
    public void getId() {
        assertEquals("id",job.getId());
    }

    @Test
    public void setId() {
        job.setId("id");
        assertEquals("id",job.getId());
    }

    @Test
    public void getName() {
        assertEquals("name",job.getName());
    }

    @Test
    public void setName() {
        job.setName("name");
        assertEquals("name",job.getName());
    }
}
