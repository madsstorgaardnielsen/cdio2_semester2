package controllers;


public class InputValidation {

    public InputValidation() {
    }
    /*
    ***
    ALL CHECK-METHODS RETURN TRUE IF THE VALUES ARE GOOD
    ***
     */

    public boolean checkCPR(String cpr) {

        if (cpr.length() != 10) {
            System.out.println("damn it went wrong!");
            return false; //wrong length
        }
        for (int i = 0; i < cpr.length(); i++) {
            if (cpr.charAt(i) > '9' || cpr.charAt(i) < '0') {
                System.out.println("damn it went wrong!");
                return false; //cannot contain characters other than numbers
            }
        }
        return true; //CPR good
    }

    public boolean checkUserName(String userName) {
        return userName.length() >= 2 && userName.length() <= 30; //wrong length must be 2-20 characters
    }

    public boolean checkRoles(String role) {
        if (role.equals("")) {
            return false; //user must have a role
        }
        if (!(role.equals("Pharmaceut") ||
                role.equals("Admin") ||
                role.equals("Produktionsleder") ||
                role.equals("Laborant"))) {

            return false; //atleast one role is not viable
        }
        return true;
    }
}
