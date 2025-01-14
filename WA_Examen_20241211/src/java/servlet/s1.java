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
import java.sql.*;

/**
 *
 * @author diurno
 */
@WebServlet(name = "s1", urlPatterns = {"/s1"})
public class s1 extends HttpServlet {

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
            
            // Conseguimos el user
            String user = request.getParameter("user");
            String pass = request.getParameter("pass");
            
            try {

                Connection conn = new ConnMysql().getConnection();

                Statement instruccion = conn.createStatement(
                        ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                
                String sql = "SELECT * from usuario WHERE dni = '" + user 
                        + "' AND pass = '" + pass + "'";
                
                ResultSet rs = instruccion.executeQuery(sql);
                
                if (rs.next()) {
                    Object[] registro = new Object[5];
                    registro[0] = rs.getString("dni"); //DNI
                    registro[1] = rs.getString("nombre"); // Nombre
                    registro[2] = rs.getInt("puntos"); // Puntos
                    registro[3] = rs.getInt("admin"); // Admin (1/0)
                    registro[4] = rs.getString("pass"); // Pass
                    
                   
                    // Habrá que ver si el registro de admin es 1 o 0 para redirigir
                    my_session.setAttribute("auth", registro[3]);
                    
                    if ((int) registro[3] == 1) { // Si es admin
                        request.getRequestDispatcher("admin.jsp").forward(request, response);
                    } else { // Si no lo es
                        request.getRequestDispatcher("conductor.jsp").forward(request, response);
                    }
                    
                    
                }
                
                rs.close();
                instruccion.close();
                conn.close();
                
            } catch (SQLException e) {
                e.printStackTrace();
            }
            out.println("Hola S1");
            
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
