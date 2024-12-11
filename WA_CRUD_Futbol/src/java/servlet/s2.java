/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.*;
import clases.ConnMysql;

/**
 *
 * @author Usuario
 */
@WebServlet(name = "s2", urlPatterns = {"/s2"})
public class s2 extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            HttpSession my_session = request.getSession();
            
            if (my_session.getAttribute("admin") == null
                    || request.getParameter("cerrar") != null) {
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }

            // Si pulsamos sobre editar
            if (request.getParameter("editar") != null) {
                int id_registro = Integer.parseInt(request.getParameter("editar"));

                try {

                    Connection conn = new ConnMysql().getConnection();

                    Statement instruccion = conn.createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

                    String sql = "SELECT t1.id, t1.id_local, t1.local, t1.g1, t1.g2, eq.nombre 'visitante', eq.id 'id_local' "
                            + "FROM equipo eq JOIN "
                            + "(SELECT p.id, eq.nombre 'local', eq.id 'id_local', p.g1, p.g2, p.e2 'visit' FROM partido p JOIN equipo eq WHERE p.e1 = eq.id) t1 "
                            + "WHERE eq.id = t1.visit AND t1.id = " + id_registro;

                    ResultSet rs = instruccion.executeQuery(sql);
                    if (rs.next()) {
                        // Guardamos los datos en un array
                        Object[] registro = new Object[7];
                        registro[0] = rs.getInt(1);             // ID Registro
                        registro[1] = rs.getInt(2);             // ID Equipo local
                        registro[2] = rs.getString(3);          // Nombre equipo local
                        registro[3] = rs.getInt(4);             // Goles euipo local
                        registro[4] = rs.getInt(5);             // Goles equipo visitante
                        registro[5] = rs.getString(6);          // Nombre equipo visitante
                        registro[6] = rs.getInt(7);             // ID Equipo visitante

                        // Guardamos el array en la sesión
                        my_session.setAttribute("registro", registro);
                    }
                    rs.close();
                    instruccion.close();
                    conn.close();

                    my_session.removeAttribute("msg2");
                    // Redirigimos
                    request.getRequestDispatcher("editar.jsp").forward(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            // Si pulsamos en INSERTAR
            if (request.getParameter("insertar") != null) {

                // Obtenemos lo introducido
                int eq_local = Integer.parseInt(request.getParameter("eq_local"));
                int eq_visit = Integer.parseInt(request.getParameter("eq_visit"));
                int goles_local = Integer.parseInt(request.getParameter("goles_local"));
                int goles_visit = Integer.parseInt(request.getParameter("goles_visit"));

                if (eq_local == eq_visit) {
                    my_session.setAttribute("msg2", "No puede enfrentarse un equipo contra él mismo");
                    request.getRequestDispatcher("admin.jsp").forward(request, response);
                }

                try {
                    
                    Connection conn = new ConnMysql().getConnection();
                    
                    Statement instruccion = conn.createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    
                    String sql = "SELECT * FROM partido";
                    ResultSet rs = instruccion.executeQuery(sql);

                    rs.moveToInsertRow();
                    
                    rs.updateInt(2, eq_local);
                    rs.updateInt(3, eq_visit);
                    rs.updateInt(4, goles_local);
                    rs.updateInt(5, goles_visit);

                    rs.insertRow();
                    
                    rs.close();
                    instruccion.close();
                    conn.close();
                    
                    my_session.removeAttribute("msg2");
                    request.getRequestDispatcher("admin.jsp").forward(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            // Si pulsamos BORRAR
            if (request.getParameter("borrar") != null) {
                // Obtenemos el id del registro seleccionado.
                int id_registro = Integer.parseInt(request.getParameter("borrar"));

                try {

                    Connection conn = new ConnMysql().getConnection();

                    Statement instruccion = conn.createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

                    String sql = "SELECT * FROM partido WHERE ID = " + id_registro;
                    ResultSet rs = instruccion.executeQuery(sql);
                    
                    // Seleccionamos el registro y borramos.
                    rs.absolute(1);
                    rs.deleteRow();

                    rs.close();
                    instruccion.close();
                    conn.close();
                    
                    my_session.removeAttribute("msg2");
                    request.getRequestDispatcher("admin.jsp").forward(request, response);
                    
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
