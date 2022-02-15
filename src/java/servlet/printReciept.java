package servlet;

import PDFWriters.iText;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 *
 * @author Oracle
 */
@WebServlet(name = "printReciept", urlPatterns = {"/printReciept"})
public class printReciept extends HttpServlet {
    String userDB;
    String passDB;
    String urlDB;
    String driverDB;
    String key;

    @Override
    public void init(ServletConfig config) throws ServletException 
    {
        super.init(config);
        userDB = config.getInitParameter("username");
        passDB = config.getInitParameter("password");
        urlDB = config.getInitParameter("urlDB");
        driverDB = config.getInitParameter("driver");
        key = config.getInitParameter("key");
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
            throws ServletException, IOException   {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
            LocalDateTime now = LocalDateTime.now();
            String fileName = format.format(now);
            response.setContentType("application/pdf;charset=UTF-8");
        
            response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".pdf");
            HttpSession session = request.getSession(); //get session from LoginServlet
                String btn = request.getParameter("download");
                String role = "GUEST";
                String name = "USER";
                ServletContext context=getServletContext(); 
                String header=context.getInitParameter("header"); 
                String footer=context.getInitParameter("footer"); 
                
                String [] item = new String[10];
                String [] qty = new String[10];
                String [] size = new String[10];
                String [] price = new String[10];
                String [] total = new String [10];

                for(int i = 0; i<10 || request.getParameter("name" + (i+1))!=null; i++)
                {
                    item[i] = request.getParameter("name" + (i+1));
                    qty[i] = request.getParameter("qty" + (i+1));
                    if (request.getParameter("size" + (i+1))==null)
                    {
                        size[i] = "";
                    }
                    else
                    {
                        size[i] = request.getParameter("size" + (i+1));
                    }
                    price[i] = request.getParameter("price" + (i+1));
                }
                
                iText a = new iText();
                File pdf = a.generatePDF(item, qty, size, price, total, name, role, userDB, passDB, driverDB, urlDB, header, footer);
            try (OutputStream out = response.getOutputStream()) 
            {
                FileInputStream in = new FileInputStream(pdf);
                byte[] buffer = new byte[4096];
                int length;
                while ((length = in.read(buffer)) > 0) {
                    out.write(buffer, 0, length);
                }
                in.close();
                pdf.delete();
            }

            
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
            throws ServletException, IOException {
        processRequest(request, response);
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
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}