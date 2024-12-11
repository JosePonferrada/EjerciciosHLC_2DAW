<%-- 
    Document   : index
    Created on : 7 oct 2024, 14:21:17
    Author     : diurno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Calculadora</title>
    </head>
    <body>
        <h1>Introduce 2 números:</h1>
        
        <form action="operacion.jsp" method="post">

            <input type="text" name="num1">
            <input type="text" name="num2">

            <input type="submit" name="enviar" value="Enviar">
        
        </form>
        
    </body>
</html>
