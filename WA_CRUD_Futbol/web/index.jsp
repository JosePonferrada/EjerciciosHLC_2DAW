<%-- 
    Document   : index
    Created on : 25 nov 2024, 14:41:54
    Author     : diurno
--%>

<%@page import="clases.ConnMysql"%>
<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <title>Clasificación</title>
    </head>
    <body>
        <h1>Clasificación</h1>

        <table class="table">
            <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Escudo</th>
                    <th scope="col">Equipo</th>
                    <th scope="col">Puntos</th>
                    <th scope="col">Victorias</th>
                    <th scope="col">Empates</th>
                    <th scope="col">Derrotas</th>
                    <th scope="col">Goles F</th>
                    <th scope="col">Goles C</th>
                </tr>
            </thead>
            <tbody>

                <%

                    try {

                        Connection miconexion = new ConnMysql().getConnection();

                        // Creamos un objeto Statement con la instrucción
                        Statement instruccion = miconexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

                        int contPos =1;
                        
                        // Creamos la sentencia sql
                        String sql = "select * from equipo order by puntos desc";

                        // Recogemos los datos en un resultset
                        ResultSet rs = instruccion.executeQuery(sql);

                        while (rs.next()) {
                            out.println("<tr>");
                            out.println("<th scope='row'>" + contPos + "</th>");
                            out.println("<td><img src='./assets/" + rs.getInt(1) + ".png'></td>");
                            out.println("<td>" + rs.getString(2) + "</td>");
                            out.println("<td>" + rs.getInt(3) + "</td>");
                            out.println("<td>" + rs.getInt(4) + "</td>");
                            out.println("<td>" + rs.getInt(5) + "</td>");
                            out.println("<td>" + rs.getInt(6) + "</td>");
                            out.println("<td>" + rs.getInt(7) + "</td>");
                            out.println("<td>" + rs.getInt(8) + "</td>");
                            out.println("</tr>");
                            contPos++;
                        }
                        
                        rs.close();
                        instruccion.close();
                        miconexion.close();

                    } catch (Exception ex) {

                        ex.printStackTrace();

                    }

                %>

            </tbody>
        </table>

                <form style="display: flex; flex-direction: row; gap: 10px;" action="s1" method="POST">
                    <input type="password" name="pwd" class="form-control" placeholder="Password" style="width: auto; margin-left: 10px">
                    <button type="submit" name="login" class="btn btn-primary">Administrador</button>
                </form>
                
                <%
                
                // Comprobamos si existe un atributo msg en la sesión.
                String mensaje = (String) session.getAttribute("msg");
                if (mensaje != null) {
                    out.println("<span>" + mensaje + "</span>");
                }
                
                %>

                
    </body>
</html>
