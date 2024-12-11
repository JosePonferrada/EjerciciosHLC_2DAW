<%-- 
    Document   : index
    Created on : Sep 28, 2024, 7:26:54 PM
    Author     : 
--%>

<%@page import="model.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>WA - Lista de Usuarios</title>
        <style>
            p, button {
                font-size: 1.5em;
            }
            input {
                font-size: 1em;
            }
        </style>
    </head>
    <body>
        <h1>Comprobación de credenciales</h1>
        
        <%
            
            Usuario u = null;
            u = (Usuario) session.getAttribute("user");
        
            if (session.isNew() || u == null) {
            
                out.println("<form action=\"s1\" method=\"post\">");
                out.println("<p>Usuario: <input type=\"text\" name=\"user\"></p>");
                out.println("<p>Contraseña: <input type=\"text\" name=\"pwd\"></p>");
                out.println("<button type=\"submit\" name=\"button\">Iniciar Sesión</button>");
                out.println("</form");

                // Si el usuario que se intenta conectar es incorrecto mostramos el mensaje debajo del botón
                
                String mensaje = (String) request.getAttribute("mensaje");
                if (mensaje != null) {
                        out.println("<br>" + mensaje + "<br>");
                }
            
            } else {
            
                out.println("Usuario autenticado: " + u.getUsuario());
            
            }
            

        %>

    </body>
</html>
