<%-- 
    Document   : admin
    Created on : 11 dic 2024, 12:35:46
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
    Object[] auth = (Object[]) session.getAttribute("auth");


%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Vista de Administrador DGT</title>
    </head>
    <body>
        <h1>Gestión de Puntos de Conductores</h1>
        <form action="s2" method="POST">
            <button type="submit" name="logout">Cerrar sesión</button>
        </form>

        <table class="table">
            <thead>
                <tr>
                    <th scope="col">DNI</th>
                    <th scope="col">Nombre</th>
                    <th scope="col">Puntos</th>
                    <th scope="col"></th>
                </tr>
            </thead>
            <tbody>
                <%                    try {

                        Connection conn = new ConnMysql().getConnection();

                        Statement instruccion = conn.createStatement(
                                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

                        String sql = "SELECT dni, nombre, puntos from usuario where admin = 0";
                        ResultSet rs = instruccion.executeQuery(sql);

                        while (rs.next()) {
                            out.println("<tr>");
                            out.println("<td>" + rs.getString(1) + "</td>");
                            out.println("<td>" + rs.getString(2) + "</td>");

                            out.println("<form action='s2' method='post'>");
                            out.println("<td>");
                            out.println("<input type='number' value='" + rs.getInt(3) + "'>");
                            out.println("</td>");
                            out.println("<td><button type='submit' name='guardar' value='" + rs.getString(1) + "'>Guardar</button></td>");
                            out.println("</form>");

                            out.println("</tr>");

                        }

                        rs.close();
                        instruccion.close();
                        conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                %>
            </tbody>
        </table>

    </body>
</html>
