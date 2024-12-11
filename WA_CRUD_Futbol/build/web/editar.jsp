<%-- 
    Document   : editar
    Created on : 10 dic 2024, 19:14:14
    Author     : Usuario
--%>

<%@page import="clases.ConnMysql"%>
<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    if (session.getAttribute("admin") == null) {
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    Object[] registro = (Object[]) session.getAttribute("registro");
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    </head>
    <body>
        <h1>Editar CRUD</h1>

        <table class="table">
            <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col"></th>
                    <th scope="col">Equipo Local</th>
                    <th scope="col">Goles Local</th>
                    <th scope="col">Goles Visitante</th>
                    <th scope="col">Equipo Visitante</th>
                    <th scope="col"></th>
                    <th scope="col"></th>
                    <th scope="col"></th>
                </tr>
            </thead>
            <tbody>
                <%
                    out.println("<tr>");
                    out.println("<th scope='row'>" + 1 + "</th>");
                    out.println("<td><img src='./assets/" + registro[1] + ".png'></td>");
                    out.println("<td>" + registro[2] + "</td>");
                    out.println("<td>" + registro[3] + "</td>");
                    out.println("<td>" + registro[4] + "</td>");
                    out.println("<td>" + registro[5] + "</td>");
                    out.println("<td><img src='./assets/" + registro[6] + ".png'></td>");
                    out.println("<td></td>");
                    out.println("<td></td>");
                    out.println("</tr>");
                %>

                <!-- Fila Modificar -->
                <tr>
            <form action="s3" method="POST">
                <td></td>
                <td></td>
                <td>
                    <select name="eq_local">
                        <%
                            try {
                                // Creamos el objeto conexion
                                Connection conn = new ConnMysql().getConnection();
                                // Creamos un objeto Statement
                                Statement instruccion = conn.createStatement(
                                        ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                                // Creamos el query
                                String sql = "SELECT * FROM equipo";
                                ResultSet rs = instruccion.executeQuery(sql);
                                while (rs.next()) {
                                    out.println("<option value='" + rs.getInt(1) + "'>");
                                    out.println(rs.getString(2));
                                    out.println("</option>");
                                }
                                // Cerrar cada uno de los objetos utilizados
                                rs.close();
                                instruccion.close();
                                conn.close();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        %>
                    </select>
                </td>
                <td>
                    <input type="number" name="goles_local" placeholder="Goles Equipo Local">
                </td>
                <td>
                    <input type="number" name="goles_visit" placeholder="Goles Equipo Visitante">
                </td>
                <td>
                    <select name="eq_visit">
                        <%
                            try {
                                
                                Connection conn = new ConnMysql().getConnection();
                                
                                Statement instruccion = conn.createStatement(
                                        ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                                
                                String sql = "SELECT id, nombre FROM equipo";
                                ResultSet rs = instruccion.executeQuery(sql);
                                while (rs.next()) {
                                    out.println("<option value='" + rs.getInt(1) + "'>");
                                    out.println(rs.getString(2));
                                    out.println("</option>");
                                }
                                
                                rs.close();
                                instruccion.close();
                                conn.close();
                                
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        %>
                    </select>                            
                </td>
                <td>
                    <button type="submit" class="btn btn-success" name="actualizar" value="<% out.print(registro[0]); %>">Actualizar</button>
                </td>
                <td></td>
                <td>
                    <button type="submit" class="btn btn-dark" name="cancelar">Cancelar</button>
                </td>
            </form>
        </tr>
    </tbody>
</table>
<%
    // Comprobamos si existe un atributo msg en la sesión.
    String mensaje = (String) session.getAttribute("msg2");
    if (mensaje != null) {
        out.println("<span>" + mensaje + "</span>");
    }
%>
</body>
</html>
