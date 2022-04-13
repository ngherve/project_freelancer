package models;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.User;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Test class for User model
 * @author Seunghyun Hong
 */
public class UserTest {
    /**
     * user variable for test.
     */
    User user = new User();

    /**
     * test User.
     */
    @Test
    public void User(){
        assertNotNull(new User());
    }

    /**
     * test Gets id.
     */
    @Test
    public void getId() {
        user.setId("1");
        assertEquals("1", user.getId());
    }

    /**
     * test Sets id.
     */
    @Test
    public void setId() {
        user.setId("1");
        assertEquals("1", user.getId());
    }

    /**
     * test Gets username.
     */
    @Test
    public void getUsername() {
        user.setUsername("name");
        assertEquals("name", user.getUsername());
    }

    /**
     * test Sets username.
     */
    @Test
    public void setUsername() {
        user.setUsername("name");
        assertEquals("name", user.getUsername());
    }

    /**
     * test Gets registration date.
     */
    @Test
    public void getRegistrationDate() {
        user.setRegistrationDate(123456);
        assertEquals(123456, user.getRegistrationDate());
    }

    /**
     * test Sets registration date.
     */
    @Test
    public void setRegistrationDate() {
        user.setRegistrationDate(123456);
        assertEquals(123456, user.getRegistrationDate());
    }

    /**
     * test Gets display name.
     */
    @Test
    public void getDisplayName() {
        user.setDisplayName("name");
        assertEquals("name", user.getDisplayName());
    }

    /**
     * test Sets display name.
     */
    @Test
    public void setDisplayName() {
        user.setDisplayName("name");
        assertEquals("name", user.getDisplayName());
    }

    /**
     * test Gets limited account.
     */
    @Test
    public void getLimitedAccount() {
        user.setLimitedAccount("false");
        assertEquals("false", user.getLimitedAccount());
    }

    /**
     * test Sets limited account.
     */
    @Test
    public void setLimitedAccount() {
        user.setLimitedAccount("false");
        assertEquals("false", user.getLimitedAccount());
    }

    /**
     * test Gets role.
     */
    @Test
    public void getRole() {
        user.setRole("Employer");
        assertEquals("Employer", user.getRole());

    }

    /**
     * test Sets role.
     */
    @Test
    public void setRole() {
        user.setRole("Employer");
        assertEquals("Employer", user.getRole());
    }

    /**
     * test Gets chosen role.
     */
    @Test
    public void getChosenRole() {
        user.setChosenRole("Employer");
        assertEquals("Employer", user.getChosenRole());
    }

    /**
     * test Sets chosen role.
     */
    @Test
    public void setChosenRole() {
        user.setChosenRole("Employer");
        assertEquals("Employer", user.getChosenRole());
    }

    /**
     * test Sets location.
     */
    @Test
    public void setLocation() {
        ObjectMapper mapper = new ObjectMapper();
        String location =  "{\"country\": {\"name\": \"US\"}}";
        JsonNode loc =null;
        try{
           loc = mapper.readTree(location);
            user.setLocation(loc);
        } catch (Exception ex) {
        ex.printStackTrace();
    }
        assertEquals(loc,user.getLocation());

    }

    /**
     * test Sets status.
     */
    @Test
    public void setStatus() {
        ObjectMapper mapper = new ObjectMapper();
        String location =  "{\"email_verified\": \"true\"}";
        JsonNode temp =null;
        try{
            temp = mapper.readTree(location);
            user.setStatus(temp);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        assertEquals(temp,user.getStatus());
    }

    /**
     * test Sets primary currency.
     */
    @Test
    public void setPrimaryCurrency() {
        ObjectMapper mapper = new ObjectMapper();
        String location =   "{\"name\": \"USD\"}";
        JsonNode temp =null;
        try{
            temp = mapper.readTree(location);
            user.setPrimaryCurrency(temp);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        assertEquals(temp,user.getPrimaryCurrency());
    }

    /**
     * test Test to string.
     */
    @Test
    public void testToString() {
        ObjectMapper mapper = new ObjectMapper();
        String location =  "{\"country\": {\"name\": \"US\"}}";
        String status = "{\"email_verified\": \"true\"}";
        String curr = "{\"name\": \"USD\"}";

        JsonNode loc = null;
        JsonNode st = null;
        JsonNode pcurr = null;
         try {
             loc = mapper.readTree(location);
             st = mapper.readTree(status);
             pcurr = mapper.readTree(curr);
        } catch (Exception ex) {
                ex.printStackTrace();
            }
        System.out.println(user.getPrimaryCurrency());
        String test = "ID =  " + user.id + "<br />"+
                "User Name =  " + user.username + "<br />"+
                "Registration_Date =  " + user.date +"<br />"+
                "Limited_Account =  " + user.limitedAccount +"<br />"+
                "Display_Name =  " + user.displayName +"<br />"+
                "Location =  " + user.city +"<br />"+
                "Role =  " + user.role +"<br />"+
                "Chosen_Role =  " + user.chosenRole +"<br />"+
                "Status =  " + user.st +"<br />"+
                "Primary_Currency =  " + user.pCurr+ "<br />";

        assertEquals(test, user.toString());

    }


}
