package checker;

import Errors.*;
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

//Security
import Encryption.*;

//Captcha
import nl.captcha.Captcha;

@WebServlet(name = "ServletRegister", urlPatterns = {"/ServletRegister"})
public class ServletRegister extends HttpServlet 
{
    //Driver for Database
    String driver;
    
    //StringBuffer
    private static StringBuffer url;
    
    //Username and Password for Database Connection
    private static String userDB;
    private static String passDB;
    
    private static String key_1;
    private static String key_2;
    
    @Override
    public void init(ServletConfig config) throws ServletException 
    {
        super.init(config);

        try 
        {
            Class.forName(config.getInitParameter("driver")); //Driver = "org.apache.derby.jdbc.ClientDriver";

            userDB = config.getInitParameter("userDB");
            passDB = config.getInitParameter("passDB");
            
            url = new StringBuffer(config.getInitParameter("jdbcDriverURL"))
                    .append("://")
                    .append(config.getInitParameter("host"))
                    .append(":")
                    .append(config.getInitParameter("port"))
                    .append("/")
                    .append(config.getInitParameter("DBName"));
            
            /*
            driver = "org.apache.derby.jdbc.ClientDriver";

            //URL, Username and Password
            URL = "jdbc:derby://localhost:1527/UserDB";

                URL
                Driver:     jdbc:derby      ://
                HostName:   localhost       : 
                Port:       1527            / 
                Database:   UserDB          
            */

                     
        } 
        catch (ClassNotFoundException e) //| SQLException e) 
        {
            e.printStackTrace();
        } 
        
        key_1 = config.getInitParameter("key_1");
        key_2 = config.getInitParameter("key_2");

    }


    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        //None here
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        //None here
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        //User Input
        String userRegister = request.getParameter("usernameRegister").trim().toLowerCase();
        String passRegister = request.getParameter("passwordRegister");
        String passRegisterConfirm = request.getParameter("passwordRegisterConfirm");
        String email = request.getParameter("e_mail");
        String address = request.getParameter("address");
        String userRole = "Guest";
        
        //String Username From Database
        String usernameDB = "";
        
        //Captcha
        Captcha captcha = (Captcha) request.getSession().getAttribute(Captcha.NAME);
        String captchaCheck = request.getParameter("captcha");
                
        
        try
        {
            Connection con = DriverManager.getConnection(url.toString(), userDB, passDB);
            
            String query = "SELECT * FROM USER_INFO WHERE username = ?";
            PreparedStatement pStmt = con.prepareStatement(query);
            pStmt.setString(1, userRegister);

            ResultSet rs = pStmt.executeQuery();
            while(rs.next())
            {
                usernameDB = rs.getString("username").trim(); 
            }
            
            
            if(!(captcha.isCorrect(captchaCheck)))
            {
                throw new Register_Error_2();
            }
            
            else if(!(userRegister.trim().equals(usernameDB)) && !(passRegister.isEmpty()))
            {
                if(passRegister.equals(passRegisterConfirm))
                {
                    
                    //First Key
                    Security.key(key_1);
                    passRegister = Security.encrypt(passRegister);

                    //Second Key
                    Security.key(key_2);
                    passRegister = Security.encrypt(passRegister);

                    //Set key to null
                    Security.key("");

                    Statement st = con.createStatement();
                    int x = st.executeUpdate("INSERT into USER_INFO(USERNAME, PASSWORD, ROLE, EMAIL, ADDRESS)values('"+userRegister+"','"+passRegister+"','"+userRole+"','"+email+"','"+address+"')");


                    response.sendRedirect("login");

                    
                }
                
                else
                {
                    throw new Register_Error_1();
                }
            }
            
            else
            {
                throw new Register_Error();
            }
            

            con.close();   
        }
        catch (SQLException e) 
        {
            e.printStackTrace();
        }     
    }
}

