/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import clases.ConnMysql;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author diurno
 */
@WebServlet(name = "s3", urlPatterns = {"/s3"})
public class s3 extends HttpServlet {

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

            // Obtenemos el nombre (PK) del usuario logueado.
            Object[] registro = (Object[]) my_session.getAttribute("auth");
            String autor = (String) registro[0];
            

            if (request.getParameter("borrar") != null) {
                // Obtenemos el id del registro seleccionado.
                int id_registro = Integer.parseInt(request.getParameter("borrar"));

                try {
                    
                    Connection conn = new ConnMysql().getConnection();
                    
                    Statement instruccion = conn.createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    
                    String sql = "SELECT * FROM coche WHERE propietario = " + id_registro;
                    ResultSet rs = instruccion.executeQuery(sql);
                    
                    rs.absolute(1);
                    rs.deleteRow();
                    
                    rs.close();
                    instruccion.close();
                    conn.close();
                    
                    my_session.removeAttribute("msg2");
                    request.getRequestDispatcher("conductor.jsp").forward(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            
            if (request.getParameter("anadir") != null) {
                // Obtenemos los parámetros introducidos
                String modelo = request.getParameter("modelo");
                String matricula = request.getParameter("matricula");

                try {
                    
                    Connection conn = new ConnMysql().getConnection();
                    
                    Statement instruccion = conn.createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    
                    String sql = "SELECT * FROM coche";
                    ResultSet rs = instruccion.executeQuery(sql);
                    
                    rs.moveToInsertRow();
                    
                    rs.updateString(1, matricula);
                    rs.updateString(3, modelo);
                    
                    rs.insertRow();
                    
                    rs.close();
                    instruccion.close();
                    conn.close();
                    
                    my_session.removeAttribute("msg2");
                    request.getRequestDispatcher("conductor.jsp").forward(request, response);
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
