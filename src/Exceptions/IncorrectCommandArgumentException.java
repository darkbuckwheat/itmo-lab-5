package Exceptions;

public class IncorrectCommandArgumentException extends Exception{
    public IncorrectCommandArgumentException(String argument, String command){
        super("Incorrect argument \"" + argument + "\" for command " + command +
                ". Try to use \"help\" to get all commands with their name and description");
    }
}
