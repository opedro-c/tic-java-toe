package exceptions;

public class EmptyEntryException extends RuntimeException {

    @Override
    public String getMessage() {
        return "Entry cannot be empty!";
    }
}
