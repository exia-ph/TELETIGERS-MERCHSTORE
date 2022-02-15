/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PDFWriters;

import ContextListeners.*;
import Errors.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Oracle
 */
public class iText {

    public File generatePDF(String[] item, String [] qty, String [] size, String [] price, String [] total, String userInput, String userRole, String userDB, String passDB, String driverDB, String urlDB, String header, String footer) throws AuthenticationException_1 
    {
        File pdf = new File("temp");
        try {

            //Register the Driver
            Class.forName(driverDB);
            System.out.println("LOADED DRIVER");

            //Establish DBMS Session
            Connection con = DriverManager.getConnection(urlDB, userDB, passDB);
            System.out.println("CONNECTED");
            String query = "";
            //"GUEST".equals(userRole)
            if ("GUEST".equals(userRole)) {
                PreparedStatement pstmt = con.prepareStatement(query);
                pstmt.setString(1, userInput);
                ResultSet rs = pstmt.executeQuery();
                pdf = createGuest(item, qty, size, price, total, rs, userInput, header,footer);
                rs.close();
                pstmt.close();
            } else {
                PreparedStatement pstmt = con.prepareStatement(query);
                ResultSet rs = pstmt.executeQuery();
                 pdf= createAdmin(item, qty, size, price, total, rs, userInput, header,footer);
                rs.close();
                pstmt.close();
            }

            //Close streams
            con.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return pdf;
    }

    public static File createAdmin(String[] item, String [] qty, String [] size, String [] price, String [] total, ResultSet rs, String userInput,String header, String footer) {
        try 
        {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
            LocalDateTime now = LocalDateTime.now();
            String fileName = format.format(now);
            
            Document docs = new Document(PageSize.LETTER);
            PdfWriter writer = PdfWriter.getInstance(docs, new FileOutputStream("unstamped" + ".pdf"));
            docs.setMargins(40, 40, 40, 40);
            docs.open();
            docs.add(new Phrase(String.format(userInput)));
            PdfPTable table = new PdfPTable(5);
            table.addCell("Item");
            table.addCell("Quantity");
            table.addCell("Size");
            table.addCell("Price");
            table.addCell("Amount");
            for(int i=0; i<10; i++)
            {
                table.addCell(item[i]);
                table.addCell(size[i]);
                table.addCell(qty[i]);
                table.addCell(price[i]);
                table.addCell(total[i]);
            }
            for(int i=1; i<=3; i++)
            {
                table.addCell("");
            }
            table.addCell("Total");
            int sum=0;
            for(int i=0; i<10; i++)
            {
                sum+=Integer.parseInt(total[i]);
            }
            table.addCell(String.valueOf(sum));

            docs.add(table);

            docs.close();
            
           //Adds Current Date and Time
           PdfReader reader = new PdfReader(new FileInputStream("unstamped" + ".pdf"));
            PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(fileName + ".pdf"));
            PdfContentByte pageCont;
            int n = reader.getNumberOfPages();
            DateTimeFormatter formatOnFile = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
            LocalDateTime time = LocalDateTime.now();
            String inside = formatOnFile.format(time);
            for (int i = 1; i <= n;) {
                pageCont = stamper.getOverContent(i);
                ColumnText.showTextAligned(pageCont, Element.ALIGN_RIGHT,
                        new Phrase(String.format(header)), 80, 575, 0);
                ColumnText.showTextAligned(pageCont, Element.ALIGN_RIGHT,
                        new Phrase(String.format(inside)), 800, 575, 0);
                ColumnText.showTextAligned(pageCont, Element.ALIGN_RIGHT,
                        new Phrase(String.format(footer)), 120, 30, 0);
                ColumnText.showTextAligned(pageCont, Element.ALIGN_RIGHT,
                        new Phrase(String.format("page %s of %s", i, n)), 800, 30, 0);
                i++;
            }
            stamper.close();
            reader.close();      
            File pdf = new File(fileName + ".pdf");
            return pdf;
        } 
        catch (DocumentException ex) {
            Logger.getLogger(iText.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(iText.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(iText.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static File  createGuest(String[] item, String [] qty, String [] size, String [] price, String [] total, ResultSet rs, String userInput,String header, String footer) {
        try {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
            LocalDateTime now = LocalDateTime.now();
            String fileName = format.format(now);
            
            System.out.println("fileName");
            Document docs = new Document(PageSize.LETTER);
            PdfWriter writer = PdfWriter.getInstance(docs, new FileOutputStream("unstamped" + ".pdf"));
            docs.setMargins(40, 40, 40, 40);
            docs.open();
            docs.add(new Phrase(String.format(userInput)));
            PdfPTable table = new PdfPTable(5);
            table.addCell("Item");
            table.addCell("Quantity");
            table.addCell("Size");
            table.addCell("Price");
            table.addCell("Amount");
            for(int i=0; i<10; i++)
            {
                table.addCell(item[i]);
                table.addCell(size[i]);
                table.addCell(qty[i]);
                table.addCell(price[i]);
                table.addCell(total[i]);
            }
            for(int i=1; i<=3; i++)
            {
                table.addCell("");
            }
            table.addCell("Total");
            int sum=0;
            for(int i=0; i<10; i++)
            {
                sum+=Double.parseDouble(total[i].replace("$", ""));
            }
            table.addCell(String.valueOf(sum));
            docs.add(table);
            docs.close();
            
            //Adds Total Number of Pages
            PdfReader reader = new PdfReader(new FileInputStream("unstamped" + ".pdf"));
            PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(fileName + ".pdf"));
            PdfContentByte pageCont;
            int n = reader.getNumberOfPages();

            //Adds Current Date and Time
            DateTimeFormatter formatOnFile = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
            LocalDateTime time = LocalDateTime.now();
            String inside = formatOnFile.format(time);
            for (int i = 1; i <= n;) {
                pageCont = stamper.getOverContent(i);
                ColumnText.showTextAligned(pageCont, Element.ALIGN_RIGHT,
                        new Phrase(String.format(header)), 80, 575, 0);
                ColumnText.showTextAligned(pageCont, Element.ALIGN_RIGHT,
                        new Phrase(String.format(inside)), 800, 575, 0);
                ColumnText.showTextAligned(pageCont, Element.ALIGN_RIGHT,
                        new Phrase(String.format(footer)), 120, 30, 0);
                ColumnText.showTextAligned(pageCont, Element.ALIGN_RIGHT,
                        new Phrase(String.format("page %s of %s", i, n)), 800, 30, 0);
                i++;
            }
            stamper.close();
            reader.close();
        File pdf = new File(fileName + ".pdf");
        return pdf;
            
        } 
        catch (DocumentException ex) 
        {
            Logger.getLogger(iText.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (FileNotFoundException ex) 
        {
            Logger.getLogger(iText.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(iText.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
