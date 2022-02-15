<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Error Register</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/error_style.css">
        <script src="${pageContext.request.contextPath}/javascripts/fixcss.js"></script>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/css/images/apc.png" type="image/x-icon">
    </head>
    
    <body>
        <%
            String header = getServletContext().getInitParameter("header");
            String footer = getServletContext().getInitParameter("footer");
        %>
        
        <div class="navbar">
            <div class="logo">
                <a href="#"><% out.println(header); %></a>
            </div>
            
            <div class="touhou">
                <img class="sumi" src="${pageContext.request.contextPath}/css/images/sumi.gif">
                <img class="remi" src="${pageContext.request.contextPath}/css/images/remi.gif">
            </div>
	</div>
        
        <div class="box">
            <h1>Registration Error</h1>
            <p>Username is already Taken :(</p>


            <a href="${pageContext.request.contextPath}/register" class="btn-1">Try Again?</a>
        
        </div>
                
        <div class="footer">
            <a href=#><% out.println(footer); %></a>
	</div>

        
    </body>
    
    
</html>