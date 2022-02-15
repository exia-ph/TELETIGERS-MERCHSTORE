package Errors;

public class AuthenticationException_1 extends RuntimeException
{
    public AuthenticationException_1()
    {
        super("Either Username or Password is Incorrect");
    }
}