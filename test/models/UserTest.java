package models;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UserTest {
    User user = new User();

    @Test
    public void User(){
        assertNotNull(new User());
    }

    @Test
    public void getId() {
        user.setId("1");
        assertEquals("1", user.getId());
    }

    @Test
    public void setId() {
        user.setId("1");
        assertEquals("1", user.getId());
    }

    @Test
    public void getUsername() {
        user.setUsername("name");
        assertEquals("name", user.getUsername());
    }

    @Test
    public void setUsername() {
        user.setUsername("name");
        assertEquals("name", user.getUsername());
    }

    @Test
    public void getRegistrationDate() {
        user.setRegistrationDate(123456);
        assertEquals(123456, user.getRegistrationDate());
    }

    @Test
    public void setRegistrationDate() {
        user.setRegistrationDate(123456);
        assertEquals(123456, user.getRegistrationDate());
    }

    @Test
    public void getDisplayName() {
        user.setDisplayName("name");
        assertEquals("name", user.getDisplayName());
    }

    @Test
    public void setDisplayName() {
        user.setDisplayName("name");
        assertEquals("name", user.getDisplayName());
    }

    @Test
    public void getLimitedAccount() {
        user.setLimitedAccount("false");
        assertEquals("false", user.getLimitedAccount());
    }

    @Test
    public void setLimitedAccount() {
        user.setLimitedAccount("false");
        assertEquals("false", user.getLimitedAccount());
    }

    @Test
    public void getRole() {
        user.setRole("Employer");
        assertEquals("Employer", user.getRole());

    }

    @Test
    public void setRole() {
        user.setRole("Employer");
        assertEquals("Employer", user.getRole());
    }

    @Test
    public void getChosenRole() {
        user.setChosenRole("Employer");
        assertEquals("Employer", user.getChosenRole());
    }

    @Test
    public void setChosenRole() {
        user.setChosenRole("Employer");
        assertEquals("Employer", user.getChosenRole());
    }
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
        String test = "ID =" + user.id + "<br />"+
                "User Name =" + user.username + "<br />"+
                "Registration_Date =" + user.date +"<br />"+
                "Limited_Account =" + user.limitedAccount +"<br />"+
                "Display_Name = " + user.displayName +"<br />"+
                "Location = " + user.city +"<br />"+
                "Role =" + user.role +"<br />"+
                "Chosen_Role =" + user.chosenRole +"<br />"+
                "Status =" + user.st +"<br />"+
                "Primary_Currency=" + user.pCurr+ "<br />";

        assertEquals(test, user.toString());

    }


}
