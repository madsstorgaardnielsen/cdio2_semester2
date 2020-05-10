package controllers;

public class InitialsGenerator {
    public String generateInitials(String firstname, String lastname, String cpr) {

        return firstname.toUpperCase().substring(0,2)+""+lastname.toUpperCase().substring(0,2)+""+cpr.substring(0,2)+"";
    }
}
