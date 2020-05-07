package cdio2.api;


import dao.IUserDAO;
import dto.UserDTO;

import java.util.ArrayList;

public class ServiceHelper {

    public ServiceHelper() {
    }
    /*
    ***
    ALL CHECK-METHODS RETURN TRUE IF THE VALUES ARE GOOD
    ***
     */

    public boolean checkCPR(String cpr) {
        if (cpr.length() != 10) {
            return false; //wrong length
        }
        for (int i = 0; i < cpr.length(); i++) {
            if (cpr.charAt(i) > '9' || cpr.charAt(i) < '0') {
                return false; //cannot contain characters other than numbers
            }
        }
        return true; //CPR good
    }

    public boolean checkUserName(String userName) {
        return userName.length() >= 2 && userName.length() <= 20; //wrong length must be 2-20 characters
    }

    public boolean checkRoles(String role) {
        if (role.equals("")) {
            return false; //user must have a role
        }
        if (!(role.equals("Pharmacist") ||
                role.equals("Admin") ||
                role.equals("Foreman") ||
                role.equals("Operator"))) {

            return false; //atleast one role is not viable
        }
        return true;
    }
}
