package models;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    public String id;
    public String username;

    @JsonProperty("registration_date")
    public long registrationDate;

    @JsonProperty("display_name")
    public String displayName;

    public User() {}

    public User(String id, String username, long registrationDate, String displayName) {
        this.id = id;
        this.username = username;
        this.registrationDate = registrationDate;
        this.displayName = displayName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(long registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", registration_date=" + registrationDate +
                ", display_name='" + displayName + '\'' +
                '}';
    }
}
