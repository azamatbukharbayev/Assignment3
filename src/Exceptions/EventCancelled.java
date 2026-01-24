package Exceptions;

public class EventCancelled extends RuntimeException {
    public EventCancelled(String message) {
        super(message);
    }
}
