package controllers;

import database.UserDAO;
import dto.UserDTO;

import java.io.IOException;
import java.sql.SQLException;

public class UserController {
    private UserDAO userDAO;
    private UserDTO userDTO;
    private static UserController instance;

    static {
        try {
            instance = new UserController();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static UserController getInstance()
    {
        return instance;
    }

    private UserController() throws SQLException {
        this.userDAO = new UserDAO();
        this.userDTO = new UserDTO();
    }

    public void updateUserName(int id, String newFirstname, String newLastname) throws SQLException, IOException {
        userDTO = userDAO.getUserById(id);
        userDTO.setFirstName(newFirstname);
        userDTO.setLastName(newLastname);
        userDAO.updateUserName(userDTO);
    }


    public void updateUserInitials(int id,String newInitials) throws SQLException, IOException {
        userDTO = userDAO.getUserById(id);
        userDTO.setInitials(newInitials);
        userDAO.updateUserInitials(userDTO);
    }

    public void updateUserCpr(int id,String newCpr) throws SQLException, IOException {
        userDTO = userDAO.getUserById(id);
        userDTO.setCpr(newCpr);
        userDAO.updateUserCpr(userDTO);
    }

    public void updateUserPassword(int id,String newPassword) throws SQLException, IOException {
        userDTO = userDAO.getUserById(id);
        userDTO.setPassword(newPassword);
        userDAO.updateUserPassword(userDTO);
    }
}
