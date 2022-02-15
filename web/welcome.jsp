<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Success</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/welcome_style.css">
        <script src="${pageContext.request.contextPath}/javascripts/fixcss.js"></script>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/css/images/apc.png" type="image/x-icon">
    </head>
    <body>
        
        <%
            String header = getServletContext().getInitParameter("header");
            String footer = getServletContext().getInitParameter("footer");
        %>

        
        <a href="store.jsp" id="welcome"><% out.println(header); %></a>
    </body>
</html>
