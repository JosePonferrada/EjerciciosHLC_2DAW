<%-- 
    Document   : index
    Created on : 4 nov 2024, 14:26:04
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
        <h1>Gestión de Cookies</h1>
        
        <form action="s1" method="post">
            
            <p>Nombre de la cookie: <input type="text" name="nombre"></p>
            <p>Valor de la cookie: <input type="text" name="valor"></p>
            
            <button type="submit" name="boton" value="1">Crear</button>
            <button type="submit" name="boton" value="2">Visualizar</button>
            <button type="submit" name="boton" value="3">Modificar</button>
            <button type="submit" name="boton" value="4">Borrar</button>
            
        </form>
        
    </body>
</html>
