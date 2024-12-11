<%-- 
    Document   : admin
    Created on : 2 dic 2024, 14:12:12
    Author     : diurno
--%>

<%@page import="clases.ConnMysql"%>
<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    if (session.getAttribute("admin") == null) {
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <title>Admin Page</title>
    </head>
    <body>
        <h1 style="text-align: center;">Admin CRUD</h1>
        
        <h1>Clasificación</h1>

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

                    try {

                        Connection miconexion = new ConnMysql().getConnection();

                        // Creamos un objeto Statement con la instrucción
                        Statement instruccion = miconexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

                        int contPos =1;
                        
                        // Creamos la sentencia sql
                        String sql = "SELECT t1.id, t1.id_local, t1.local, t1.g1, t1.g2, eq.nombre 'visitante', eq.id 'id_local' "
                                        + "FROM equipo eq JOIN "
                                        + "(SELECT p.id, eq.nombre 'local', eq.id 'id_local', p.g1, p.g2, p.e2 'visit' FROM partido p JOIN equipo eq WHERE p.e1 = eq.id) t1 "
                                        + "WHERE eq.id = t1.visit";

                        // Recogemos los datos en un resultset
                        ResultSet rs = instruccion.executeQuery(sql);

                        while (rs.next()) {
                            out.println("<tr>");
                            out.println("<form action='s2' method='post'>");
                            out.println("<th scope='row'>" + contPos + "</th>");
                            out.println("<td><img src='./assets/" + rs.getInt(2) + ".png'></td>");
                            out.println("<td>" + rs.getString(3) + "</td>");
                            out.println("<td>" + rs.getInt(4) + "</td>");
                            out.println("<td>" + rs.getInt(5) + "</td>");
                            out.println("<td>" + rs.getString(6) + "</td>");
                            out.println("<td><img src='./assets/" + rs.getInt(7) + ".png'></td>");
                            out.println("<td><button class='btn btn-warning' type='submit' name='editar' value='" + rs.getInt(1) + "'>Editar</td>");
                            out.println("<td><button class='btn btn-danger' type='submit' name='borrar' value='" + rs.getInt(1) + "'>Borrar</td>");
                            out.println("</form>");
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
                
                
                <!-- Para la fila de INSERTAR -->
                
                <tr>
                    
            <form action="s2" method="POST">
                <td></td>
                <td></td>
                <td>
                    <select name="eq_local">
                        
                        <%

                        try {
                            
                            Connection conn = new ConnMysql().getConnection();
                            
                            Statement instruccion = conn.createStatement(
                                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                            
                            String sql = "SELECT * FROM equipo";
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
                    <input type="number" name="goles_local" placeholder="Goles Local">
                </td>
                <td>
                    <input type="number" name="goles_visit" placeholder="Goles Visitante">
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
                    <button type="submit" class="btn btn-success" name="insertar">Insertar</button>
                </td>
                <td></td>
                <td>
                    <button type="submit" class="btn btn-dark" name="cerrar">Cerrar</button>
                </td>

            </form>
                    
                </tr>
                

            </tbody>
        </table>
        
        <%
                
        // Comprobamos si existe un atributo msg en la sesión.
        String mensaje = (String) session.getAttribute("msg");
        if (mensaje != null) {
            out.println("<span>" + mensaje + "</span>");
        }

        %>            
                        
    </body>
</html>
