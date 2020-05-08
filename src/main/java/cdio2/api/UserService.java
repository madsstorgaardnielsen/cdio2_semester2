package cdio2.api;

import controllers.UserController;
import dao.UserDAO;
import dto.UserDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.sql.SQLException;


@Path("user")
public class UserService {
    ServiceHelper helper = new ServiceHelper(); //helper for check of input in creation and updating
    UserController userController;

    {
        try {
            userController = new UserController();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

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
/*            if(!helper.checkCPR(userDTO.getCpr())){
                //if cpr not ok
                return Response.status(400, "CPR must be number of length 10").build();
            }
            else if(!helper.checkRoles(userDTO.getRole())){
                //if role not ok
                return Response.status(400, "Role must be Admin, Pharmaceut, Produktionsleder or Laborant").build();
            }*/
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
/*        try {
             //NEED TO FIX METHODD INPUT TO OBTAIN THE NEW DATA FOR THE UPDATED USER FIRST
            if(!helper.checkCPR(userDTO.getCpr())){
                //if cpr not ok
                return Response.status(400, "CPR must be number of length 10").build();
            }
            else if(!helper.checkRoles(userDTO.getRole())){
                //if role not ok
                return Response.status(400, "Role must be Admin, Pharmaceut, Produktionsleder or Laborant").build();
            }*/
        userController.updateUserName(userDTO.getUserId(),userDTO.getFirstName(),userDTO.getLastName());
        userController.updateUserPassword(userDTO.getUserId(),userDTO.getPassword());
        userController.updateUserCpr(userDTO.getUserId(),userDTO.getCpr());
        userController.updateUserInitials(userDTO.getUserId(),userDTO.getInitials());

/*        UserDAO.getInstance().updateUserName(userDTO.getUserId());
        UserDAO.getInstance().updateUserPassword(userDTO.getUserId());
        UserDAO.getInstance().updateUserCpr(userDTO.getUserId());
        UserDAO.getInstance().updateUserInitials(userDTO.getUserId());*/
        System.out.println("User successfully updated");
        return Response.ok().build();
/*        } catch (SQLException | IOException throwables) {
            throw new Exception();
        }*/
    }
}
