<%-- 
    Document   : index
    Created on : 11 dic 2024, 10:29:57
    Author     : diurno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    if (session.getAttribute("login") != null) {
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Examen - Index</title>
        <style>
            h1 {
                text-align: center;
            }
        </style>
    </head>
    <body>
        <h1>Inicio de Sesión</h1>

        <form action="s1" method="POST">
            
            <label for="user">Usuario:</label><br>
            <input type="text" id="user" name="user" placeholder="Ingresa tu usuario">
            <br><br>
            <label for="pass">Contraseña</label><br>
            <input type="password" id="pass" name="pass" placeholder="Ingresa tu contraseña">
            <br><br>

            <button type="submit">Iniciar sesión</button>
        </form>

    </body>
</html>
