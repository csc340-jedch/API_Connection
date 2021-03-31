package Flights;

import org.json.JSONException;

public class Main {

    public static void main(String[] args) throws JSONException, NotDirectFlightException {

	GetFlightData test = new GetFlightData();
        System.out.println("Carrier " + test.getCarrier("2021-04-01","ORD"));
        System.out.println("Which quote is direct? " + test.isDirectFlight("2021-04-01","ORD"));
        System.out.println("Price " + test.getPrice("2021-04-01","ORD"));
        System.out.println("Carrier ID " + test.getCarrierId("2021-04-01","ORD"));
        System.out.println("Is Direct Flight: " + test.isDirectFlight("2021-04-01","ORD"));


	//test.getQuote("2019-09-01","SDF");
    }
}
