<%-- 
    Document   : autenticado
    Created on : 4 oct 2024, 12:39:53
    Author     : 
--%>

<%@page import="model.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Datos de Usuario Conectado</h1>
        
        <%
                        
            Usuario usuario = (Usuario) session.getAttribute("user");
            out.println("Usuario conectado");
            out.println("<p>Usuario: " + usuario.getUsuario() + "</p>");

        %>
        
        <form action="saldo.jsp" method="post"> 
            <input type="submit" name="view" value="Ver saldo"><br>
            
        </form>
        
        <form action="s3" method="post">
            <input type="submit" name="disconnect" value="Salir">
        </form>
        
    </body>
</html>
