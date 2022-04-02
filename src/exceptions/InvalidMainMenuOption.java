package exceptions;

public class InvalidMainMenuOption extends RuntimeException {
    private final String invalidOption;

    public InvalidMainMenuOption(String invalidOption) {
        this.invalidOption = invalidOption;
    }

    @Override
    public String getMessage() {
        return String.format(
                "%s is not a valid option to choose on main menu!%n" +
                "Must be a number between 1 and 4!",
                invalidOption
        );
    }
}
