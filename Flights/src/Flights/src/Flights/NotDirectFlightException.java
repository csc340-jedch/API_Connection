package Flights;


public class NotDirectFlightException extends Exception {
    public NotDirectFlightException(String message){
        super(message);
    }
}
