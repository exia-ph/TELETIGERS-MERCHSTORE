package Errors;

public class Register_Error_1 extends RuntimeException
{
    public Register_Error_1()
    {
        super("Passwords not the same");
    }
}