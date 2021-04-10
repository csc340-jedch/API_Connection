package Flights;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetFlightData implements FlightDataInterface{

    public static JSONObject findFlightInformation(String _departureDate, String _originAirport) {

        String baseURl = "https://skyscanner-skyscanner-flight-search-v1.p.rapidapi.com/apiservices/browsequotes/v1.0/US" +
                "/USD/en-US/SFO-sky/";

        String airportPiece = _originAirport + "-sky/";
        String datePiece = _departureDate;
        String finalURL = baseURl + airportPiece + datePiece;
        URL url;

        try {
            //URL connection
            url = new URL(finalURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("x-rapidapi-key","1c51e09a6dmshbf06f77f423fa6ap14adfdjsne9e38d557ee6");
            con.setRequestProperty("x-rapidapi-host","skyscanner-skyscanner-flight-search-v1.p.rapidapi.com" );

            //Test URL connection
            int status = con.getResponseCode();

            if (status != 200) {
                System.out.printf("Error: Could not load. Status: " + status);
            } else {
                BufferedReader in = new BufferedReader(new InputStreamReader((con.getInputStream())));
                String inputLine;
                StringBuffer content = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }

                //Close connection
                in.close();
                con.disconnect();

                //Parse JSON
                JSONObject obj = new JSONObject(content.toString());
                String quotes = obj.getString("Quotes");
                String carriers = obj.getString("Carriers");

                //System.out.println("This is quote: " + quotes + "This is carriers: " + carriers);

                return obj;
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    //Make object have parameters and create wrapper classes

    //public String getDate(){
      //  System.out.println("What mon");
    //}

    //Usable
    public String getCarrier(String _departureDate, String _originAirport) throws JSONException, NotDirectFlightException {
        JSONArray carrierInfoArray = getCarriers(_departureDate,_originAirport);
        String carrierId;
        for(int i = 0; i <= carrierInfoArray.length(); i++) {
            JSONObject carrierInfoObject = carrierInfoArray.getJSONObject(i);
            String carrierID = carrierInfoObject.getString("CarrierId");
            int carrierIDInteger = Integer.parseInt(carrierID);
            if (getCarrierId(_departureDate,_originAirport) == carrierIDInteger){
                return carrierInfoObject.getString("Name");
            }

        }
        return "getCarrier failed";
    }

    //Usable
    public int isDirectFlight(String _departureDate, String _originAirport) throws JSONException, NotDirectFlightException {
        JSONArray quotesInfoArray = getQuotes(_departureDate,_originAirport);
        for(int i = 0; i < quotesInfoArray.length(); i++) {
            JSONObject quotesInfoObject = quotesInfoArray.getJSONObject(i);
            if (quotesInfoObject.getString("Direct").toLowerCase().equals("true")){
                int output = Integer.parseInt(quotesInfoObject.getString("QuoteId"));
                return output;
            } else {
                throw new NotDirectFlightException("No direct flights. Contact representative for help");
            }
        }
        throw new NotDirectFlightException("No direct flights. Contact representative for help");
    }

    //Usable
    public String getPrice(String _departureDate, String _originAirport) throws JSONException, NotDirectFlightException {
        JSONArray quoteInfoArray = getQuotes(_departureDate,_originAirport);
            JSONObject carrierInfoObject = quoteInfoArray.getJSONObject(isDirectFlight(_departureDate,_originAirport) - 1);
            return carrierInfoObject.getString("MinPrice");

    }

    //Usable
    public int getCarrierId(String _departureDate, String _originAirport) throws JSONException, NotDirectFlightException {
        JSONArray quoteInfoArray = getQuotes(_departureDate,_originAirport);
            JSONObject carrierInfoObject = quoteInfoArray.getJSONObject(isDirectFlight(_departureDate,_originAirport) - 1);
            JSONObject outboundLeg = carrierInfoObject.getJSONObject("OutboundLeg");
            String idWithBrackets = outboundLeg.getString("CarrierIds");
            String idWithoutBrackets = idWithBrackets.substring(1,idWithBrackets.length() - 1);
            int idAsInteger = Integer.parseInt(idWithoutBrackets);
            return idAsInteger;

    }

    public JSONArray getQuotes(String _departureDate, String _originAirport) throws JSONException {
        JSONObject input = findFlightInformation(_departureDate,_originAirport);
        return input.getJSONArray("Quotes");
    }
    public JSONArray getCarriers(String _departureDate, String _originAirport) throws JSONException {
        JSONObject input = findFlightInformation(_departureDate,_originAirport);
        return input.getJSONArray("Carriers");
    }

}