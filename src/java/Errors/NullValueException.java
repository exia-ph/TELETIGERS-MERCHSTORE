package Errors;

public class NullValueException extends RuntimeException
{
    public NullValueException()
    {
        super("Null Value has been passed by User");
    }
}