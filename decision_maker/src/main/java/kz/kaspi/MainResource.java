package kz.kaspi;

import groovy.util.GroovyScriptEngine;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.ResultSet;

@Path("")
public class MainResource {

    @POST
    @Path("exec-proc")
    @Produces(MediaType.TEXT_PLAIN)
    public String execProc(@HeaderParam("proc-code") String procCode, String json) throws Exception {
        return "";
    }
}
