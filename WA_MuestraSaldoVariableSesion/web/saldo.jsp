<%-- 
    Document   : saldo
    Created on : 28 oct 2024, 14:12:57
    Author     : diurno
--%>

<%@page import="model.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ver saldo</title>
    </head>
    <body>
        <h1>Ver saldo</h1>
        
        <%
                        
            Usuario usuario = (Usuario) session.getAttribute("user");
            out.println("<p>Usuario: " + usuario.getUsuario() + "</p>");
            
            out.println("<p>Saldo: " + usuario.getSaldo() + "€</p>");

        %>
        
        <form action="s2" method="post">
            <button type="submit" name="operacion" value="-">-</button>
            <button type="submit" name="operacion" value="+">+</button>
        </form>
        
        <form action="autenticado.jsp" method="post">
            <input type="submit" name="back" value="Volver">
        </form>
        
    </body>
</html>
