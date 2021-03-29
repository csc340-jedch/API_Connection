package Flights;

import org.json.JSONException;

public class Main {

    public static void main(String[] args) throws JSONException, NotDirectFlightException {

	GetFlightData test = new GetFlightData();
        System.out.println("Carrier " + test.getCarrier("2021-04-01","ATL"));
        System.out.println("Which quote is direct? " + test.isDirectFlight("2021-04-01","ATL"));
        System.out.println("Price " + test.getPrice("2021-04-01","ATL"));
        System.out.println("Carrier ID " + test.getCarrierId("2021-04-01","ATL"));
        System.out.println("Carrier ID " + test.isDirectFlight("2021-04-01","ATL"));


	//test.getQuote("2019-09-01","SDF");
    }
}
