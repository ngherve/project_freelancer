package models;

import org.junit.Test;

import java.security.acl.Owner;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Test for OwnerResultTest model
 * @author Seunghyun Hong
 */
public class OwnerResultTest {
    /**
     * variable ownerResult for test
     */
    OwnerResult ownerR = new OwnerResult();
    /**
     * variable list of projects for test
     */
    List<Project> projects;
    /**
     * variable map for String and USer for test
     */
    Map<String, User> users;

    /**
     * test Ownerresult  constructor.
     */
    @Test
    public void OwnerResult(){
        assertNotNull(new OwnerResult());
    }

    /**
     * test Getsprojects  method.
     */
    @Test
    public void getProjects() {
        assertEquals(projects, ownerR.getProjects());
;    }

    /**
     * test Setsprojects  method.
     */
    @Test
    public void setProjects() {
         ownerR.setProjects(projects);
        assertEquals(projects, ownerR.getProjects());
    }

    /**
     * test Getsusers method.
     */
    @Test
    public void getUsers() {
        assertEquals(users, ownerR.getUsers());
    }

    /**
     * test Setsusers method.
     */
    @Test
    public void setUsers() {
        ownerR.setUsers(users);
        assertEquals(users, ownerR.getUsers());
    }
}
