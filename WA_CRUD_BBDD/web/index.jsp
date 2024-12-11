<%-- 
    Document   : index
    Created on : 18 nov 2024, 14:22:16
    Author     : diurno
--%>

<%@page import="clases.ConnMysql"%>
<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Index</title>
        <style>
            table, th, tr, td {
                border: 2px solid black;
                border-collapse: collapse;
                padding: 5px;
            }
            
            th, td {
                text-align: center;
            }
            
        </style>
    </head>
    <body>
        <h1>Clientes</h1>
        <form action="s1" method="post">
            <table>
                <th>ID</th>
                <th>Nombre</th>
                <th>Nota</th>
                <th>Fecha de Nacimiento</th>
                <th>Acción
            
        <%
            try {

                Connection miconexion = new ConnMysql().getConnection();

                // Creamos un objeto Statement con la instrucción
                Statement instruccion = miconexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

                // Creamos la sentencia sql
                String sql = "select id, nombre, nota, fecha_nac from datos";

                // Recogemos los datos en un resultset
                ResultSet rs = instruccion.executeQuery(sql);

                while (rs.next()) {
                    out.println("<tr>");
                    out.println("<form action='s1' method='post'>");
                    out.println("<td>" + rs.getInt(1) + "</td>");
                    out.println("<td><input type='text' name='nombre' value='" + rs.getString(2) + "'></td>");
                    out.println("<td><input type='number' name='nota' value='" + rs.getInt(3) + "'></td>");
                    out.println("<td><input type='date' name='fecha' value='" + rs.getDate(4) + "'></td>");
                    out.println("<td><button name='editar' value='" + rs.getInt(1) + "'>Editar</button></td>");
                    out.println("<td><button name='borrar' value='" + rs.getInt(1) + "'>Borrar</button></td>");
                    out.println("</form>");
                    out.println("</tr>");
                }
                out.println("<tr>");
                out.println("<td></td>");
                out.println("<td><input type='text' name='nombre'></td>");
                out.println("<td><input type='number' name='nota'></td>");
                out.println("<td><input type='date' name='fecha'></td>");
                out.println("<td><button name='insertar'>Insertar</button></td>");
                out.println("</tr>");

                rs.close();
                instruccion.close();
                miconexion.close();

            } catch (Exception ex) {

                ex.printStackTrace();

            }
        %>
            </table>
        </form>

    </body>
</html>
