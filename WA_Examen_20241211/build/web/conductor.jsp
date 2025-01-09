<%-- 
    Document   : conductor
    Created on : 11 dic 2024, 13:09:59
    Author     : diurno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%@page import="clases.ConnMysql"%>

<%
    if (session.getAttribute("auth") == null) {
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    // Saco el valor del atributo Login de la sesión.
    Object[] conductor = (Object[]) session.getAttribute("auth");
    String dni = (String) conductor[0];
    String nombre = (String) conductor[1];

    int puntos = (int) conductor[3];
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table>
        <%
            try {
                Connection conn = new ConnMysql().getConnection();

                Statement instruccion = conn.createStatement(
                        ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

                String sql = "SELECT * from coche where propietario = '" + dni + "'";
                ResultSet rs = instruccion.executeQuery(sql);

                while (rs.next()) {
                    out.println("<tr>");
                    out.println("<td>" + rs.getString("nombre") + "</td>");
                    out.println("<td>Puntos: " + rs.getInt("puntos") + "</td>");
                    out.println("</tr>");
                    
                    out.println("<tr><td>Consulta y gestiona los coches asociados a tu cuenta</td></tr>");
                    
                    out.println("<tr>");
                    out.println("<td>" + rs.getString("matricula") + "</td>");
                    out.println("<td>" + rs.getString("modelo") + "</td>");

                    // Form para el borrado
                    out.println("<form action='s3' method='post'");
                    out.println("<td><button type='submit' name='borrar' value='" + rs.getString(1) + "'>Borrar</button></td>");
                    out.println("</form>");
                    
                    out.println("</tr>");
                    
                    out.println("<tr>");
                    out.println("<td colspan='3'>Añadir Nuevo Coche</td>");
                    out.println("</tr>");
                    
                    out.println("<tr>");
                    
                    out.println("<td>Matricula: <input type='text' id='matricula' name='matricula'</td>");
                    
                    out.println("<td>Modelo: <input type='text' id='modelo' name='modelo'</td>");
                    
                    out.println("</tr>");
                    
                    out.println("<tr>");
                    out.println("<form action='s3' method='post'");
                    out.println("<td colspan='2'><button type='submit' name='anadir' value='" + rs.getString(1) + "'>Añadir Coche</button></td>");
                    out.println("</form>");
                    out.println("</tr>");
                }

            } catch (Exception e) {
            }

        %>

    </body>
</html>
