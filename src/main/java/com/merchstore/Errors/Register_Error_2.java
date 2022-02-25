package com.merchstore.Errors;

public class Register_Error_2 extends RuntimeException
{
    public Register_Error_2()
    {
        super("Captcha is Wrong");
    }
}