package Exceptions;

public class FieldNullException extends Exception{
    public FieldNullException(String fieldName){
        super("Field " + fieldName + " can't be null");
    }
}
