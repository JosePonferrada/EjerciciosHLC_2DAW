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
            out.println("<p>Usuario: " + usuario.getUsuario() + "</p>"
                + "<p>Password: " + usuario.getPassword() + "</p>");

        %>
        
        <!-- Esta es una manera de hacerlo -->
        <form action="index.jsp" method="post"> 
            <input type="submit" name="disconnect" value="Desconectar">
            <% session.invalidate(); %> <!-- Borra todos los atributos de la variable de sesión -->
        </form>

        
        <!-- La otra manera de hacerlo es cerrar la sesión desde el s2 -->
        <form action="s2" method="post"> 
            <input type="submit" name="disconnect" value="Desconectar">
        </form>
        
    </body>
</html>
