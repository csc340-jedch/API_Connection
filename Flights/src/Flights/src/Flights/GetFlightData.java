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

        String testURL = "https://skyscanner-skyscanner-flight-search-v1.p.rapidapi.com/apiservices/browsequotes/v1.0/US" +
                "/USD/en-US/SFO-sky/JFK-sky/2021-09-01";

        String finalURL = baseURl + airportPiece + datePiece;
        URL url;

        try {
            //URL connection
            url = new URL(finalURL);
            //System.out.println(finalURL);

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("x-rapidapi-key","1c51e09a6dmshbf06f77f423fa6ap14adfdjsne9e38d557ee6");
            con.setRequestProperty("x-rapidapi-host","skyscanner-skyscanner-flight-search-v1.p.rapidapi.com" );
            //Test URL connection
            int status = con.getResponseCode();
            //System.out.println(status);
            if (status != 200) {
                System.out.printf("Error: Could not load");
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

                //System.out.println("Output" + content.toString());

                //Parse JSON
                JSONObject obj = new JSONObject(content.toString());
                String quotes = obj.getString("Quotes");
                String carriers = obj.getString("Carriers");

                String[] output = {carriers,quotes};
                System.out.println("This is quote: " + quotes + "This is carriers: " + carriers);

                return obj;
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        //String[] failed = {"failed"};
        return null;
    }

    //Use all of the data points to return the flight info to the screen and/or a database
    //Make object have parameters and create wrapper classes
    //Dont allow -1 to be passed

    //Usable
    public String getCarrier(String _departureDate, String _originAirport) throws JSONException, NotDirectFlightException {
        JSONObject input = findFlightInformation(_departureDate,_originAirport);
        JSONArray carrierInfoArray = input.getJSONArray("Carriers");
        String carrierId;
           for(int i = 0; i < carrierInfoArray.length(); i++) {
               JSONObject carrierInfoObject = carrierInfoArray.getJSONObject(i);
               String carrierID = carrierInfoObject.getString("CarrierId");
               //int carrierIDInteger = Integer.parseInt(carrierID);
               int carrierIDInteger = Integer.parseInt(carrierID);
              if (getCarrierId(_departureDate,_originAirport) == carrierIDInteger){
                   return carrierInfoObject.getString("Name");
               }

           }
           return "getCarrier failed";
    }

    //Usable
    public int isDirectFlight(String _departureDate, String _originAirport) throws JSONException, NotDirectFlightException {
        JSONObject input = findFlightInformation(_departureDate,_originAirport);
        JSONArray quotesInfoArray = input.getJSONArray("Quotes");
        String direct;
        for(int i = 0; i < quotesInfoArray.length(); i++) {
            JSONObject quotesInfoObject = quotesInfoArray.getJSONObject(i);
            direct = quotesInfoObject.getString("Direct").toLowerCase();
            if (direct == "true"){
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
        int quoteNumberInt = isDirectFlight(_departureDate,_originAirport);
        JSONObject input = findFlightInformation(_departureDate,_originAirport);
        JSONArray quoteInfoArray = input.getJSONArray("Quotes");
        for(int i = 0; i < quoteNumberInt; i++) {
            JSONObject carrierInfoObject = quoteInfoArray.getJSONObject(i);
            return carrierInfoObject.getString("MinPrice");

        }
        return "not work";
    }

    //Usable
    public int getCarrierId(String _departureDate, String _originAirport) throws JSONException, NotDirectFlightException {
        int quoteNumberInt = isDirectFlight(_departureDate,_originAirport);
        JSONObject input = findFlightInformation(_departureDate,_originAirport);
        JSONArray quoteInfoArray = input.getJSONArray("Quotes");
        String idWithoutBrackets = "";
        for(int i = 0; i < quoteNumberInt; i++) {
            JSONObject carrierInfoObject = quoteInfoArray.getJSONObject(i);
            JSONObject outboundLeg = carrierInfoObject.getJSONObject("OutboundLeg");
            String idWithBrackets = outboundLeg.getString("CarrierIds");
             idWithoutBrackets = idWithBrackets.substring(1,idWithBrackets.length() - 1);
           int idAsInteger = Integer.parseInt(idWithoutBrackets);
            return idAsInteger;
        }

        return -1;
    }

    //No Worky
    public void helpMe(String _departureDate, String _originAirport) throws JSONException {
        JSONObject input = findFlightInformation(_departureDate,_originAirport);
        JSONArray quotesInfoArray = input.getJSONArray("Quotes");
        for(int i = 0; i < quotesInfoArray.length(); i++) {
            JSONObject quotesInfoObject = quotesInfoArray.getJSONObject(i);
            System.out.println(quotesInfoObject.getString("Name"));
        }
    }
}