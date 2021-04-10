package Flights;

import org.json.JSONException;

public class Main {

    public static void main(String[] args) throws JSONException, NotDirectFlightException {
String departureDate = "2021-06-03";
String originAirport = "LAX";
	GetFlightData test = new GetFlightData();
        System.out.println("Carrier " + test.getCarrier(departureDate,originAirport));
        System.out.println("Which quote is direct? " + test.isDirectFlight(departureDate,originAirport));
        System.out.println("Price " + test.getPrice(departureDate,originAirport));
        System.out.println("Carrier ID " + test.getCarrierId(departureDate,originAirport));
        System.out.println("Is Direct Flight: " + test.isDirectFlight(departureDate,originAirport));


	//test.getQuote("2019-09-01","SDF");
    }
}
