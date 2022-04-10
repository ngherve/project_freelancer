package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * User Class for parse the user data from API call and create user object
 * @author Seunghyun Hong
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    public String id;
    public String username;
    @JsonProperty("registration_date")
    public long registrationDate;
    @JsonProperty("display_name")
    public String displayName;
    @JsonProperty("limited_account")
    public String limitedAccount;
    public JsonNode location;
    public String role;
    @JsonProperty("chosen_role")
    public String chosenRole;
    public JsonNode status;
    @JsonProperty("primary_currency")
    public JsonNode primaryCurrency;
    public String date;
    public String city;
    public String st;
    public String pCurr;

    /**
     * default constructor
     */
    public User() {
    }

    /**
     * getter for User ID
     * @return id User ID in String
     */
    public String getId() {
        return id;
    }

    /**
     * setter for User ID
     * @param id get User ID in String
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * getter for User Name
     * @return username String
     */
    public String getUsername() {
        return username;
    }

    /**
     * setter for User Name
     * @param username User Name in String
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * getter for Registration Date
     * @return registrationDate in Long
     */
    public long getRegistrationDate() {
        return registrationDate;
    }

    /**
     * setter for Registration Date
     * Get Registration Date in long as parameter
     * and re-format it to "MMM dd yyyy" in String and store it in date variable
     * @param registrationDate Registration Date in long
     */
    public void setRegistrationDate(long registrationDate) {
        Date date = new Date(this.registrationDate * 1000);
        DateFormat formatter = new SimpleDateFormat("MMM dd yyyy");
        this.date = formatter.format(date);
        this.registrationDate = registrationDate;
    }

    /**
     * getter for Display Name
     * @return displayName in String
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * setter for Display Name
     * @param displayName in String
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     * getter for Limited Account
     * @return limitedAccount in String
     */
    public String getLimitedAccount() {
        return limitedAccount;
    }

    /**
     * setter for Limited Account
     * @param limitedAccount in String
     */
    public void setLimitedAccount(String limitedAccount) {
        this.limitedAccount = limitedAccount;
    }

    /**
     * getter for Role
     * @return role in String
     */
    public String getRole() {
        return role;
    }

    /**
     * setter for Role
     * @param role in String
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * getter for Chosen Role
     * @return shosenRole in String
     */
    public String getChosenRole() {
        return chosenRole;
    }

    /**
     * setter for Chosen Role
     * @param chosenRole in String
     */
    public void setChosenRole(String chosenRole) {
        this.chosenRole = chosenRole;
    }

    /**
     * setter for Location
     * receive Location information in JsonNode format
     * set it in location variable and get country name and set in city variable
     * @param location in JsonNode
     */
    public void setLocation(JsonNode location){this.location = location;
        this.city = this.location.get("country").get("name").asText();
    }

    /**
     * setter for Status
     * receive Status informtaion in JsonNode format
     * set it in status variable and get email verified information and set in st variable
     * @param status
     */
    public void setStatus(JsonNode status){this.status = status;
        this.st = this.status.get("email_verified").asText();
    }

    /**
     * setter for Primary Currency
     * recieve Primary Currency in Json format
     * set in in primaryCurrency variable and get name information and set it in pCurr variable
     * @param primaryCurrency
     */
    public void setPrimaryCurrency(JsonNode primaryCurrency){this.primaryCurrency = primaryCurrency;
    this.pCurr = this.primaryCurrency.get("name").asText();}

    /**
     * getter for Location
     * @return location in JsonNode
     */
    public JsonNode getLocation() {
        return location;
    }

    /**
     * getter for Status
     * @return status in JsonNode format
     */
    public JsonNode getStatus() {
        return status;
    }

    /**
     * getter for Primary Currency
     * @return primaryCurrency in JsonNode
     */
    public JsonNode getPrimaryCurrency() {
        return primaryCurrency;
    }

    /**
     * Override toString method
     * @return User information in String
     */
    @Override
    public String toString() {
        return  "ID =  " + id + "<br />"+
                "User Name =  " + username + "<br />"+
                "Registration_Date =  " + date +"<br />"+
                "Limited_Account =  " + limitedAccount +"<br />"+
                "Display_Name =  " + displayName +"<br />"+
                "Location =  " + city  +"<br />"+
                "Role =  " + role +"<br />"+
                "Chosen_Role =  " + chosenRole +"<br />"+
                "Status =  " + st +"<br />"+
                "Primary_Currency =  " + pCurr + "<br />";

    }
}
