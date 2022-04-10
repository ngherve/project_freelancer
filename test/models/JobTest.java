import models.Job;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * test class for Job model
 * @author Nastaran Naseri
 */
public class JobTest {

    /**
     * variable Job for test
     */
    Job job = new Job("id","name");

    /**
     * test Job constructor
     */
    @Test
    public void Job(){
        assertNotNull(new Job());
    }

    /**
     * test Getsid method.
     */
    @Test
    public void getId() {
        assertEquals("id",job.getId());
    }

    /**
     * test Setsid method.
     */
    @Test
    public void setId() {
        job.setId("id");
        assertEquals("id",job.getId());
    }

    /**
     * test Getsname method.
     */
    @Test
    public void getName() {
        assertEquals("name",job.getName());
    }

    /**
     * test Setsname method.
     */
    @Test
    public void setName() {
        job.setName("name");
        assertEquals("name",job.getName());
    }
}
