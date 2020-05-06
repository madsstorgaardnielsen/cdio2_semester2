package dao;
import database.DBConnection;
import dto.UserDTO;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAO {
    private DBConnection database;
    PreparedStatement statement;
    private static UserDAO instance;

    static {
        try {
            instance = new UserDAO();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public UserDAO() throws SQLException {
        database = new DBConnection();
    }

    public static UserDAO getInstance()
    {
        return instance;
    }

    public UserDTO getUserById(int id) throws SQLException {
        CallableStatement statement = database.callableStatement("{call getUserByID(" + id + ")}");
        ResultSet rs = statement.executeQuery();
        UserDTO userDTO = new UserDTO();
        try {
            while (rs.next()) {
                getUserInfo(rs, userDTO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("bruger ikke fundet");
        }
        return userDTO;
    }

    public ArrayList<UserDTO> getAllUsers() throws SQLException {
        ArrayList<UserDTO> userList = new ArrayList<>();
        CallableStatement stmt = database.callableStatement("{call getAllUsers}");
        ResultSet rs = stmt.executeQuery();
        UserDTO userDTO;
        try {
            while (rs.next()) {
                userDTO = new UserDTO();
                getUserInfo(rs, userDTO);
                userList.add(userDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public void AddRoleToUser(int id, String role) throws IOException, SQLException {
        String addRoleToUser = "{call addRoleToUser(?,?)}";
        PreparedStatement statement = database.callableStatement(addRoleToUser);
        statement.setInt(1,id);
        statement.setString(2,role);

        try {
            statement.executeUpdate();
            System.out.println("Role successfully added to userid: "+id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException("Something went wrong with createUser()");
        }
    }

    public void deleteUserRole(int id, String role) throws SQLException {
        String deleteUserRole = "{call deleteuserrole(?,?)}";
        PreparedStatement statement = database.prepareStatement(deleteUserRole);
        statement.setInt(1, id);
        statement.setString(2,role);

        try {
            statement.executeUpdate();
            System.out.println("Role: "+role+" successfully removed from user with id: "+id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addUser(UserDTO user) throws IOException, SQLException {
        String addUser = "{call addUser(?,?,?,?,?,?,?)}";
        PreparedStatement statement = database.callableStatement(addUser);
        statement.setInt(1,user.getUserId());
        statement.setString(2,user.getFirstName());
        statement.setString(3,user.getLastName());
        statement.setString(4,user.getInitials());
        statement.setString(5,user.getCpr());
        statement.setString(6,user.getRole());
        statement.setString(7,user.getPassword());
        try {
            statement.executeUpdate();
            System.out.println("User successfully added to database");
        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException("Something went wrong with createUser()");
        }
    }

    public void deleteUser(int id) throws IOException, SQLException {
        String deleteUser = "{call deleteUser(?)}";
        PreparedStatement statement = database.prepareStatement(deleteUser);
        statement.setInt(1, id);

        try {
            statement.executeUpdate();
            System.out.println("Employee successfully deleted from database");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateUserName(int id) throws IOException, SQLException {
        UserDAO userDAO = new UserDAO();
        String updateUserName = "{call updateUserName(?,?,?)}";
        statement = database.prepareStatement(updateUserName);
        statement.setInt(1, userDAO.getUserById(id).getUserId());
        statement.setString(2, userDAO.getUserById(id).getFirstName());
        statement.setString(3, userDAO.getUserById(id).getLastName());

        try {
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException("Something went wrong when updating user");
        }
    }

    public void updateUserCpr(int id) throws IOException, SQLException {
        UserDAO userDAO = new UserDAO();
        String updateUserCpr = "{call updateUserCpr(?,?)}";
        statement = database.prepareStatement(updateUserCpr);
        statement.setInt(1, userDAO.getUserById(id).getUserId());
        statement.setString(2, userDAO.getUserById(id).getCpr());

        try {
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException("Something went wrong when updating user");
        }
    }

    public void updateUserPassword(int id) throws IOException, SQLException {
        UserDAO userDAO = new UserDAO();
        String updateUserPassword = "{call updateUserPassword(?,?)}";
        statement = database.prepareStatement(updateUserPassword);
        statement.setInt(1, userDAO.getUserById(id).getUserId());
        statement.setString(2, userDAO.getUserById(id).getPassword());

        try {
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException("Something went wrong when updating user");
        }
    }

    public void updateUserInitials(int id) throws IOException, SQLException {
        UserDAO userDAO = new UserDAO();
        String updateUserInitials = "{call updateUserInitials(?,?)}";
        statement = database.prepareStatement(updateUserInitials);
        statement.setInt(1, userDAO.getUserById(id).getUserId());
        statement.setString(2, userDAO.getUserById(id).getInitials());

        try {
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException("Something went wrong when updating user");
        }
    }

    private void getUserInfo(ResultSet rs, UserDTO userDTO) throws SQLException {
        userDTO.setUserId(rs.getInt(1));
        userDTO.setFirstName(rs.getString(2));
        userDTO.setLastName(rs.getString(3));
        userDTO.setInitials(rs.getString(4));
        userDTO.setCpr(rs.getString(5));
        userDTO.setPassword(rs.getString(6));
        userDTO.setRole(rs.getString(7));
    }
}

