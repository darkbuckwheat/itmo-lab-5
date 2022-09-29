package Exceptions;

public class InvalidInputException extends Exception{
    public InvalidInputException(String input){
        super("Incorrect input \"" + input + "\"");
    }
}
