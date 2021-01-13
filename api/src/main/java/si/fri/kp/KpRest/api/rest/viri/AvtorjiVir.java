package si.fri.kp.KpRest.api.rest.viri;


import si.fri.kp.KpRest.api.rest.dtos.Avtor;
import si.fri.kp.KpRest.api.rest.dtos.Vic;
import si.fri.kp.KpRest.api.rest.zrna.PodatkiZrno;

import javax.annotation.PostConstruct;
//import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Path("avtorji")
@ApplicationScoped
public class AvtorjiVir {
    Logger log = Logger.getLogger(AvtorjiVir.class.getName());

    @Inject
    PodatkiZrno pz;

    @GET
    @Produces("application/json")
    @Path("/all")
    //@RolesAllowed({"admin","user"})
    public Response getJson(){
        //log.info("pošiljam json");
        return Response.ok(pz.getAvtorji()).build();
    }

    @GET
    @Produces("application/xml")
    @Path("/all")
    //@RolesAllowed({"admin","user"})
    public Response getXml(){
        GenericEntity<List<Avtor>> entity = new GenericEntity<List<Avtor>>(pz.getAvtorji()) {};
        //log.info("pošiljam xml");
        return Response.ok(entity).build();
    }

    @GET
    @Produces("application/json")
    @Path("/{idAvtorja}/vici")
    public Response getAvtVicJs(@PathParam("idAvtorja")int id){
        return Response.ok(pz.getVicFromAvtor(id)).build();
    }

    @GET
    @Produces("application/xml")
    @Path("/{idAvtorja}/vici")
    public Response getAvtVicXML(@PathParam("idAvtorja")int id){
        GenericEntity<List<Vic>> entity = new GenericEntity<List<Vic>>(pz.getVicFromAvtor(id)) {};
        return Response.ok(entity).build();
    }

    @GET
    @Produces({"application/xml","application/json"})
    @Path("{idAvtorja}")
    public Response getavtId(@PathParam("idAvtorja")int id){
        Avtor avt=pz.getAvtorbyId(id);
        if(avt==null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(avt).build();
    }

    @POST
    @Consumes({"application/json","application/xml"})
    @Produces({"application/json","application/xml"})
    //@RolesAllowed({"admin","user"})
    public Response postAvtor(Avtor avtor){
        Avtor resp=pz.addAvtor(avtor);
        if(resp==null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }else{
            return Response.status(Response.Status.CREATED).entity(resp).build();
        }
    }

    @PUT
    @Consumes({"application/json","application/xml"})
    @Produces({"application/json","application/xml"})
    @Path("{idAvtorja}")
    //@RolesAllowed({"admin","user"})
    public Response putAvtor(Avtor avt,@PathParam("idAvtorja")int id){
        Avtor resp=pz.updateAvtor(avt,id);
        if(resp==null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }else{
            return Response.status(Response.Status.OK).entity(resp).build();
        }
    }

    @DELETE
    @Produces({"application/json","application/xml"})
    @Path("{idAvtorja}")
    //@RolesAllowed("admin")
    public Response deleteVic(@PathParam("idAvtorja") int id){
        boolean uspeh=pz.deleteAvtor(id);
        if(!uspeh){
            return Response.status(Response.Status.NOT_FOUND).build();
        }else{
            return Response.status(Response.Status.NO_CONTENT).build();
        }
    }


}
