<!DOCTYPE html>
<%@page import="java.sql.*"
        import = "ContextListeners.User"
%>
<html>
    <head>
        <title>Store Page</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/storeUser_style.css">
        <script src="${pageContext.request.contextPath}/javascripts/store.js" async></script>
    </head>
    <body class="bg">
        <%
           // Check if User is login
           if( (session.getAttribute("userSes") == null) || (session.getAttribute("passSes") == null))
           {
               response.sendRedirect("../error_session");
           }
           
           //session.invalidate();

           //Remove Cache
           response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // Works for HTTP 1.1 (new)

           response.setHeader("Pragma", "no-cache"); //Works for HTTP 1.0 (new)

           response.setHeader("Expires", "0"); //For Proxies

            
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            String userDB = "app";
            String passDB = "app";
            String url = "jdbc:derby://localhost:1527/ItemDB";
            
            Connection con = DriverManager.getConnection(url, userDB, passDB);
            String query = "SELECT * FROM ITEM_DB";
            PreparedStatement pStmt = con.prepareStatement(query);
            
            ResultSet rs = pStmt.executeQuery();
              
            String header = getServletContext().getInitParameter("header");
            String footer = getServletContext().getInitParameter("footer");
        %>
        
        <div class="menu-bar" id="menu">
            <div class="logo">
                <a href="#"><% out.println(header); %></a>
            </div>
            
            <p id="WellUser">
                <% 
                    ServletContext sc = getServletContext();
                            
                    User Message = (User) sc.getAttribute("user1");
                    out.println(Message);
                %>
            <p>
            <a href="javascript:void(0)" class="opener" onclick="openCart()"><img src="${pageContext.request.contextPath}/css/images/shopping_cart_icon.png" id="icon"></a>
	</div>
        
        
        <div class="cart" id="cart">
            <a href="javascript:void(0)" class="close" onclick="closeCart()">&times;</a>
            <h2 id="cart_header">Cart</h2>
            <div class="cart-actual"></div>
            <div class="cart-total">
                <span class="total-total">Total:</span>
                <span class="total-price">$0.00</span>
            </div>
            <button class="checkout">Proceed to Checkout</button>
        </div>
        
        
        <div class="grid" id="grid">
            <div class="item">
                <a><img src="${pageContext.request.contextPath}/css/images/hoodie.png" id="item_photo"></a>
                <%rs.next();%>
                <span class="shop-item"><%out.print(rs.getString("ITEM_NAME"));%></span>
                <span class="shop-price"><%out.print("$" + String.format("%.2f", Float.parseFloat(rs.getString("ITEM_PRICE"))));%></span>
                <button class="cart-actions add">Add to Cart</button>
            </div>
            <div class="item">
                <a><img src="${pageContext.request.contextPath}/css/images/t_shirt.png" id="item_photo"></a>
                <%rs.next();%>
                <span class="shop-item"><%out.print(rs.getString("ITEM_NAME"));%></span>
                <span class="shop-price"><%out.print("$" + String.format("%.2f", Float.parseFloat(rs.getString("ITEM_PRICE"))));%></span>
                <button class="cart-actions add">Add to Cart</button>
            </div>
            <div class="item">
                <a><img src="${pageContext.request.contextPath}/css/images/mug.png" id="item_photo"></a>
                <%rs.next();%>
                <span class="shop-item"><%out.print(rs.getString("ITEM_NAME"));%></span>
                <span class="shop-price"><%out.print("$" + String.format("%.2f", Float.parseFloat(rs.getString("ITEM_PRICE"))));%></span>
                <button class="cart-actions add">Add to Cart</button>
            </div>
            <div class="item">
                <a><img src="${pageContext.request.contextPath}/css/images/tumbler.png" id="item_photo"></a>
                <%rs.next();%>
                <span class="shop-item"><%out.print(rs.getString("ITEM_NAME"));%></span>
                <span class="shop-price"><%out.print("$" + String.format("%.2f", Float.parseFloat(rs.getString("ITEM_PRICE"))));%></span>
                <button class="cart-actions add">Add to Cart</button>
            </div>
            <div class="item">
                <a><img src="${pageContext.request.contextPath}/css/images/face_mask.png" id="item_photo"></a>
                <%rs.next();%>
                <span class="shop-item"><%out.print(rs.getString("ITEM_NAME"));%></span>
                <span class="shop-price"><%out.print("$" + String.format("%.2f", Float.parseFloat(rs.getString("ITEM_PRICE"))));%></span>
                <button class="cart-actions add">Add to Cart</button>
            </div>
            <div class="item">
                <a><img src="${pageContext.request.contextPath}/css/images/poster.png" id="item_photo"></a>
                <%rs.next();%>
                <span class="shop-item"><%out.print(rs.getString("ITEM_NAME"));%></span>
                <span class="shop-price"><%out.print("$" + String.format("%.2f", Float.parseFloat(rs.getString("ITEM_PRICE"))));%></span>
                <button class="cart-actions add">Add to Cart</button>
            </div>
            <div class="item">
                <a><img src="${pageContext.request.contextPath}/css/images/beanie.png" id="item_photo"></a>
                <%rs.next();%>
                <span class="shop-item"><%out.print(rs.getString("ITEM_NAME"));%></span>
                <span class="shop-price"><%out.print("$" + String.format("%.2f", Float.parseFloat(rs.getString("ITEM_PRICE"))));%></span>
                <button class="cart-actions add">Add to Cart</button>
            </div>
            <div class="item">
                <a><img src="${pageContext.request.contextPath}/css/images/cap.png" id="item_photo"></a>
                <%rs.next();%>
                <span class="shop-item"><%out.print(rs.getString("ITEM_NAME"));%></span>
                <span class="shop-price"><%out.print("$" + String.format("%.2f", Float.parseFloat(rs.getString("ITEM_PRICE"))));%></span>
                <button class="cart-actions add">Add to Cart</button>
            </div>
            <div class="item">
                <a><img src="${pageContext.request.contextPath}/css/images/wristband.png" id="item_photo"></a>
                <%rs.next();%>
                <span class="shop-item"><%out.print(rs.getString("ITEM_NAME"));%></span>
                <span class="shop-price"><%out.print("$" + String.format("%.2f", Float.parseFloat(rs.getString("ITEM_PRICE"))));%></span>
                <button class="cart-actions add">Add to Cart</button>
            </div>
            <div class="item">
                <a><img src="${pageContext.request.contextPath}/css/images/cellphone_case.png" id="item_photo"></a>
                <%rs.next();%>
                <span class="shop-item"><%out.print(rs.getString("ITEM_NAME"));%></span>
                <span class="shop-price"><%out.print("$" + String.format("%.2f", Float.parseFloat(rs.getString("ITEM_PRICE"))));%></span>
                <button class="cart-actions add">Add to Cart</button>
            </div>
        </div>
                
        <div class="footer">
            <a href=#><% out.println(footer); %></a>
	</div>        
                
        <%
            rs.close();
            pStmt.close();
            con.close();
        %>
    </body>
</html>