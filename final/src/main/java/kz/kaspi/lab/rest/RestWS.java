package kz.kaspi.lab.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.text.SimpleDateFormat;
import java.util.Date;

@Path("/")
public class RestWS {
    @POST
    @Path("/exec-proc")
    @Produces(MediaType.TEXT_PLAIN)
    public String execProc(@HeaderParam("proc-code") String procCode, String json) { return null; }

    @GET
    @Path("/hello")
    @Produces(MediaType.TEXT_PLAIN)
    public String sayHello() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + ". HELLO FROM KASPI-LAB-REST WEB-SERVICE!!!";
    }

}
