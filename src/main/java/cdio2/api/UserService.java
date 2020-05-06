package cdio2.api;

import dao.UserDAO;
import dto.UserDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.sql.SQLException;


@Path("user")
public class UserService {
    UserDAO userDAO;

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
            UserDAO.getInstance().addUser(userDTO);
            return Response.ok().build();
        } catch (SQLException | IOException throwables) {
            throw new Exception();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteUser(UserDTO userDTO) throws Exception {
        try {
            UserDAO.getInstance().deleteUser(userDTO.getUserId());
            return Response.ok().build();
        } catch (SQLException | IOException throwables) {
            throw new Exception();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUser(UserDTO userDTO) throws Exception {
        try {
            UserDAO.getInstance().updateUserName(userDTO);
            UserDAO.getInstance().updateUserPassword(userDTO);
            UserDAO.getInstance().updateUserCpr(userDTO);
            UserDAO.getInstance().updateUserInitials(userDTO);
            return Response.ok().build();
        } catch (SQLException | IOException throwables) {
            throw new Exception();
        }
    }


}
