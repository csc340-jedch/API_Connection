package Flights;

import org.json.JSONException;
import org.json.JSONObject;

public interface FlightDataInterface {

    public String getPrice(String _departureDate, String _originAirport) throws JSONException, NotDirectFlightException;

    //public String getCarrier(String _departureDate, String _originAirport) throws JSONException, NotDirectFlightException;

    public int getCarrierId(String _departureDate, String _originAirport) throws JSONException, NotDirectFlightException;

    public static JSONObject findFlightInformation(String _departureDate, String _originAirport) throws JSONException, NotDirectFlightException {
        return null;
    }

    public int isDirectFlight(String _departureDate, String _originAirport) throws JSONException, NotDirectFlightException;
}
