<%-- 
    Document   : index
    Created on : 7 oct 2024, 13:38:37
    Author     : diurno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Formulario</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <form action="s1" method="post">
            
            Seleccione sus intereses: 
            <br>
            <input type="checkbox" name="intereses" value="Peliculas">Películas<br>
            <input type="checkbox" name="intereses" value="Libros">Libros<br>
            <input type="checkbox" name="intereses" value="Internet" checked="">Internet<br>
            <br>
            
            <p>Seleccione un país:</p>
            <select name="select1">
                <option value="Spain" selected="">España</option>
                <option value="Francia">Francia</option>
                <option value="Portugal">Portugal</option>
            </select>
            <br>
            
            <p>Seleccione un color:</p>
            <input type="radio" name="rd1" value="Rojo">Rojo
            <input type="radio" name="rd1" value="Verde">Verde
            <input type="radio" name="rd1" value="Azul" checked="">Azul
            
            <br><br>
            
            <input type="submit" name="enviar" value="Enviar">
            
        </form>
    </body>
</html>
