package Errors;

public class Register_Error extends RuntimeException
{
    public Register_Error()
    {
        super("Username is already taken");
    }
}