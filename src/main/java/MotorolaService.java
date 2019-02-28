import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Path("/radios")
public class MotorolaService {
    static HashMap<Integer, Device> motorolaDevices = new HashMap<Integer, Device>();

    @POST
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response storeRadioProfile(@PathParam("id") Integer id,  String payload) {
        JSONObject jsonObject = new JSONObject(payload);
        String alias = jsonObject.getString("alias");
        List<String> allowed_locations = new ArrayList<String>();
        JSONArray jsonArray = (JSONArray)jsonObject.get("allowed_locations");
        for (int i = 0; i < jsonArray.length(); i++){
            allowed_locations.add(jsonArray.getString(i));
        }

        motorolaDevices.put(id, new Device(id, alias, allowed_locations));
        return Response.ok().build();

    }

    @POST
    @Path("/{id}/location")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response setRadioLocation(@PathParam("id") Integer id, String payload) {
        JSONObject jsonObject = new JSONObject(payload);
        String alias = jsonObject.getString("alias");

        Device device = motorolaDevices.get(id);

        if(device.verifyLocation(alias)) {
            device.setLocation(alias);
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @GET
    @Path("/{id}/location")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRadioLocation(@PathParam("id") Integer id) throws JSONException {
        Device device = motorolaDevices.get(id);
        String location = device.getLocation();

        if(location != null) {
            JSONObject jsonResponse =   new JSONObject()
                                    .put("location", location);

            return Response.ok(jsonResponse.toString(), MediaType.APPLICATION_JSON).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

    }

}
