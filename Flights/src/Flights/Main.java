package Flights;

import org.json.JSONException;

public class Main {

    public static void main(String[] args) throws JSONException, NotDirectFlightException {
String departureDate = "2021-06-03";
String originAirport = "LAX";
	GetFlightData test = new GetFlightData(departureDate,originAirport);
        System.out.println("Carrier " + test.getCarrier());
        System.out.println("Which quote is direct? " + test.isDirectFlight());
        System.out.println("Price " + test.getPrice());
        System.out.println("Carrier ID " + test.getCarrierId());
        System.out.println("Is Direct Flight: " + test.isDirectFlight());


	//test.getQuote("2019-09-01","SDF");
    }
}
