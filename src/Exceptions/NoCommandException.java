package Exceptions;

public class NoCommandException extends Exception{
    public NoCommandException(String commandName){
        super(commandName + " is not realised. Try to check your writing");
    }
}
