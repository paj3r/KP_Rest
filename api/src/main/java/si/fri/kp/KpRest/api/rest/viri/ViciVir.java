package si.fri.kp.KpRest.api.rest.viri;


import si.fri.kp.KpRest.api.rest.dtos.Avtor;
import si.fri.kp.KpRest.api.rest.dtos.Vic;
import si.fri.kp.KpRest.api.rest.zrna.PodatkiZrno;

import javax.annotation.PostConstruct;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Path("vici")
@ApplicationScoped
//@Secure
public class ViciVir {

    Logger log = Logger.getLogger(ViciVir.class.getName());
    @Inject
    PodatkiZrno pz;

    @PostConstruct
    public void init(){
       // log.info("Working Directory = " + System.getProperty("user.dir"));
    }

    @GET
    @Produces("application/json")
    @Path("/all")
    //@RolesAllowed({"user","admin"})
    public Response getJson(){
        //log.info("pošiljam json");
        return Response.ok(pz.getVici()).build();
    }


    @GET
    @Produces("application/xml")
    @Path("/all")
    //@RolesAllowed({"user","admin"})
    public Response getXml(){
        GenericEntity<List<Vic>> entity = new GenericEntity<List<Vic>>(pz.getVici()) {};
        //log.info("pošiljam xml");
        return Response.ok(entity).build();
    }

    @POST
    @Consumes({"application/json","application/xml"})
    @Produces({"application/json","application/xml"})
    //@RolesAllowed({"admin","user"})
    public Response postVic(Vic vic){
        Vic resp=pz.addVic(vic);
        if(resp==null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }else{
            return Response.status(Response.Status.CREATED).entity(resp).build();
        }
    }
    @PUT
    @Consumes({"application/json","application/xml"})
    @Produces({"application/json","application/xml"})
    @Path("{idVica}")
    //@RolesAllowed("admin")
    public Response putVic(Vic vic,@PathParam("idVica")int id){
        Vic resp=pz.updateVic(vic,id);
        if(resp==null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }else{
            return Response.status(Response.Status.OK).entity(resp).build();
        }
    }
    @DELETE
    @Produces({"application/json","application/xml"})
    @Path("{idVica}")
    //@RolesAllowed("admin")
    public Response deleteVic(@PathParam("idVica") int id){
        boolean uspeh=pz.deleteVic(id);
        if(!uspeh){
            return Response.status(Response.Status.NOT_FOUND).build();
        }else{
            return Response.status(Response.Status.NO_CONTENT).build();
        }
    }
    @GET
    @Produces({"application/json","application/xml"})
    public Response getRandom(){
        //log.info("pošiljam json");
        Vic nov=pz.dobiNakljucnega();
        if(nov==null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok(nov).build();
    }
    @GET
    @Produces("text/plain")
    public Response getRandomText(){
        //log.info("pošiljam json");
        Vic nov=pz.dobiNakljucnega();
        if(nov==null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok(nov.getBesedilo()).build();
    }
    @GET
    @Produces("image/gif")
    @Path("/gif")
    public Response getGif() throws IOException {
        File file=new File("./images/giphy.gif");
        byte[] imageData = Files.readAllBytes(file.toPath());
        return Response.ok(imageData).build();
    }

}
