package Flights;

import org.json.JSONException;
import org.json.JSONObject;

public interface FlightDataInterface {

    public String getPrice() throws JSONException, NotDirectFlightException;

    public String getCarrier() throws JSONException, NotDirectFlightException;

    public int getCarrierId() throws JSONException, NotDirectFlightException;

    public static JSONObject findFlightInformation() throws JSONException, NotDirectFlightException {
        return null;
    }

    public int isDirectFlight() throws JSONException, NotDirectFlightException;
}
