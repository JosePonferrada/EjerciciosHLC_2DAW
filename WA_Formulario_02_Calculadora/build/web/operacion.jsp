<%-- 
    Document   : operacion
    Created on : 7 oct 2024, 14:28:54
    Author     : diurno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Elegir operación</title>
    </head>
    <body>
        <h1>Elegir operación:</h1>
        
        <form action="s1" method="post">
            
            <button type="submit" name="button" value="1">+</button>
            <button type="submit" name="button" value="2">-</button>
            <button type="submit" name="button" value="3">*</button>
            <button type="submit" name="button" value="4">/</button>
            
            <%
                
                request.setAttribute("num1", request.getParameter("num1"));
                request.setAttribute("num2", request.getParameter("num2"));

                out.println(request.getParameter("num1")); // Lo muestra bien
                out.println(request.getParameter("num2")); // Lo muestra bien

            %>
            
            <input type="hidden" name="num1" value="<% request.getParameter("num1"); %>"
            <input type="hidden" name="num2" value="<% request.getParameter("num2"); %>"
            
        </form>
        
    </body>
</html>
