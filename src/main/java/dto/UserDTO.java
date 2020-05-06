package dto;

import java.io.Serializable;

public class UserDTO implements Serializable {
    int userId;
    String firstName;
    String lastName;
    String initials;
    String cpr;
    String password;
    String role;
    private static final long serialVersionUID = 4545864587995944260L;

    public UserDTO(int userId, String firstName, String lastName, String initials, String cpr, String password) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.initials = initials;
        this.cpr = cpr;
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public UserDTO() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public String getCpr() {
        return cpr;
    }

    public void setCpr(String cpr) {
        this.cpr = cpr;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String toString() {
        return userId+" "+firstName+" "+lastName;
    }
}
