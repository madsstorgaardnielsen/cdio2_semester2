package cdio2.api;

import controllers.InputValidation;
import controllers.UserController;
import database.UserDAO;
import dto.UserDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.sql.SQLException;


@Path("user")
public class UserAPI {
    InputValidation helper = new InputValidation(); //helper for check of input in creation and updating

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("id") int id) throws SQLException {
        return Response.ok(new UserDAO().getUserById(id)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers() throws SQLException {
        return Response.ok(new UserDAO().getAllUsers()).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(UserDTO userDTO) throws Exception {
        try {
            if (!helper.checkCPR(userDTO.getCpr())) {
                //if cpr not ok
                return Response.status(400, "CPR must be number of length 10").build();
            } else if (!helper.checkRoles(userDTO.getRole())) {
                //if role not ok
                return Response.status(400, "Role must be Admin, Pharmaceut, Produktionsleder or Laborant").build();
            } else if (!helper.checkUserName(userDTO.getFirstName())) {
                return Response.status(400, "First name must be between 5 and 30 characters").build();
            } else if (!helper.checkUserName(userDTO.getLastName())) {
                Response.status(400, "Last name must be between 5 and 30 character").build();
            }
            //only reached if input is ok.
            UserDAO.getInstance().addUser(userDTO);
            return Response.ok().build();
        } catch (SQLException | IOException throwables) {
            return Response.serverError().build();
        }
    }

    @POST
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteUser(@PathParam("id") int id) throws Exception {
        try {
            UserDAO.getInstance().deleteUser(id);
            return Response.ok().build();
        } catch (SQLException | IOException throwables) {
            throw new Exception();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUser(UserDTO userDTO) throws Exception {
        try {
            //NEED TO FIX METHODD INPUT TO OBTAIN THE NEW DATA FOR THE UPDATED USER FIRST
            if (!helper.checkCPR(userDTO.getCpr())) {
                //if cpr not ok
                return Response.status(400, "CPR must be number of length 10").build();
            } else if (!helper.checkRoles(userDTO.getRole())) {
                //if role not ok
                return Response.status(400, "Role must be Admin, Pharmaceut, Produktionsleder or Laborant").build();
            }
            UserController.getInstance().updateUserName(userDTO.getUserId(), userDTO.getFirstName(), userDTO.getLastName());
            UserController.getInstance().updateUserPassword(userDTO.getUserId(), userDTO.getPassword());
            UserController.getInstance().updateUserCpr(userDTO.getUserId(), userDTO.getCpr());
            UserController.getInstance().updateUserInitials(userDTO.getUserId(), userDTO.getInitials());

            System.out.println("User successfully updated");
            return Response.ok().build();
        } catch (SQLException | IOException throwables) {
            throw new Exception();
        }
    }
}
