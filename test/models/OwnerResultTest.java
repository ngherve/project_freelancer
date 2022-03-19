package models;

import org.junit.Test;

import java.security.acl.Owner;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class OwnerResultTest {
    OwnerResult ownerR = new OwnerResult();
    List<Project> projects;
    Map<String, User> users;

    @Test
    public void OwnerResult(){
        assertNotNull(new OwnerResult());
    }

    @Test
    public void getProjects() {
        assertEquals(projects, ownerR.getProjects());
;    }

    @Test
    public void setProjects() {
         ownerR.setProjects(projects);
        assertEquals(projects, ownerR.getProjects());
    }

    @Test
    public void getUsers() {
        assertEquals(users, ownerR.getUsers());
    }

    @Test
    public void setUsers() {
        ownerR.setUsers(users);
        assertEquals(users, ownerR.getUsers());
    }
}
