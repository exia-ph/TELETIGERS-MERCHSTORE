package ContextListeners;

import static java.lang.Character.toLowerCase;

public class User
{
    private static String username;
    private static String password;
    private static String role;
    private static String email;
    private static String address;
    
    //Getters and Setter
    //___Setter___
    public void setUser(String uname, String pword, String roll, String mail, String add)
    {
        username = uname;
        password = pword;
        role = roll;
        email = mail;
        address = add;
    }
    
    
    //____Username Getter____
    //Gets the Username
    public String getUsername()
    {
        return username;
    }
    
    
    //____Passwords Getter____
    //Gets the Password
    public static String getPassword() 
    {
        return password;
    }
    
    //____Role Getter____
    //Gets the Role
    public static String getRole() 
    {
        return role;
    }

    //_____toString to give out the message_____
    public String toString()
    {
        char c = getRole().charAt(0);
        char v = toLowerCase(c);
        
        if(v == 'a' || v == 'e' || v == 'i' || v == 'o' || v == 'u') 
        {
            return "Welcome " + getUsername() + ", you are an " +  getRole();
        }
        
        else
        {
            return "Welcome " + getUsername() + ", you are a " +  getRole();
        }
    }
}
