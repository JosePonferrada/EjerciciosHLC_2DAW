<%-- 
    Document   : index
    Created on : 20 sept 2024, 12:42:36
    Author     : diurno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <h1>Calculamos a buen precio</h1>
        
        <form action="s1" method="post">
            Número 1:<input type="number" name="n1">
            <br>
            Número 2:<input type="number" name="n2">
            <br>
            <button type="submit" value="1" name="button">Sumar</button>
            <button type="submit" value="2" name="button">Restar</button>
            <button type="submit" value="3" name="button">Multiplicar</button>
            <button type="submit" value="4" name="button">Dividir</button>
            <button type="submit">Click me!</button>
        </form>
        
        <br>
        
        <form action="s1" method="post">
            
            <%
                String mensaje = (String) request.getAttribute("mensaje");
                if (mensaje != null) out.println(mensaje + "<br>");
            %>
            
            Usuario: <input type="text" name="user"><br>
            Contraseña: <input type="text" name="pwd"><br>
            <button type="submit">Click me!</button>
            
            
        </form>
        
    </body>
</html>
