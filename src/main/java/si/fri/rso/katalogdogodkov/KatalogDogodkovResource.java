
package si.fri.rso.katalogdogodkov;

import com.kumuluz.ee.common.runtime.EeRuntime;
import org.eclipse.microprofile.metrics.annotation.Metered;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.List;




@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("katalogDogodkov")
@ApplicationScoped
public class KatalogDogodkovResource {

    @Inject
    private RestProperties restProperties;

    @GET
    @Metered
    public Response getAllDogodeks() {
        List<Dogodek> dogodeks = Database.getDogodeks();
        return Response.ok(dogodeks).build();
    }

    @GET
    @Path("{dogodekId}")
    public Response getDogodek(@PathParam("dogodekId") Integer dogodekId) {
        Dogodek dogodek = Database.getDogodek(dogodekId.toString());
        return dogodek != null
                ? Response.ok(dogodek).build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    public Response addNewDogodek(Dogodek dogodek) {
        List<Dogodek> dogodeks = Database.getDogodeks();
        boolean add = true;
        for(int i=0; i<dogodeks.size(); i++){
            if(dogodek.getId().equals(dogodeks.get(i).getId())){
                dogodeks.get(i).setTitle(dogodek.getTitle());
                dogodeks.get(i).setAbout(dogodek.getAbout());
                add=false;
                break;
            }
        }
        if(add) {
            Database.addDogodek(dogodek);
        }
        return Response.ok(dogodek).build();
    }

    @DELETE
    @Path("{dogodekId}")
    public Response deleteDogodek(@PathParam("dogodekId") String dogodekId) {
        Database.deleteDogodek(dogodekId);
        return Response.ok(Response.Status.OK).build();
    }

    @POST
    @Path("healthy")
    public Response setHealth(Boolean healthy) {
        restProperties.setHealthy(healthy);
        return Response.ok().build();
    }

    @POST
    @Path("load")
    public Response loadOrder(Integer n) {

        for (int i = 1; i <= n; i++) {
            fibonacci(i);
        }

        return Response.status(Response.Status.OK).build();
    }

    private long fibonacci(int n) {
        if (n <= 1) return n;
        else return fibonacci(n - 1) + fibonacci(n - 2);
    }

    @GET
    @Path("instanceid")
    public Response getInstanceId() {

        String instanceId =
                "{\"instanceId\" : \"" + EeRuntime.getInstance().getInstanceId() + "\"}";

        return Response.ok(instanceId).build();
    }


}