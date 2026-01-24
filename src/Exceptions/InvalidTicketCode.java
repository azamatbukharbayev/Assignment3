package Exceptions;

public class InvalidTicketCode extends RuntimeException {
    public InvalidTicketCode(String message) {
        super(message);
    }
}
