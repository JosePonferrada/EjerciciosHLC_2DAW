/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import clases.ConnMysql;
import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

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
            
            // Abrimos una sesión.
            HttpSession my_session = request.getSession();
            
            if (request.getParameter("logout") != null
                    && my_session.getAttribute("admin") != null) {
                // Borro los atributos de la sesión.
                my_session.removeAttribute("admin");
                my_session.removeAttribute("msg");
                my_session.removeAttribute("msg2");
                // Redirijo a index.
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
            
            
            // Si hemos pulsado sobre Administrador.
            if (request.getParameter("login") != null) {

                // Obtenemos la clave introducida.
                String pwd = request.getParameter("pwd");

                try {
                    // Creamos el objeto conexion
                    Connection conn = new ConnMysql().getConnection();

                    Statement instruccion = conn.createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

                    String sql = "SELECT pass FROM clave";
                    ResultSet rs = instruccion.executeQuery(sql);
                    
                    // Seleccionamos el único registro
                    rs.absolute(1);
                    
                    // Guardamos la clave.
                    String clave = rs.getString(1);
                    
                    rs.close();
                    instruccion.close();
                    conn.close();
                    
                    // Comprobamos si la clave introducida coincide con la clave en BD.
                    if (clave.equalsIgnoreCase(pwd)) {
                        my_session.setAttribute("admin", 1);
                        my_session.removeAttribute("msg");
                        request.getRequestDispatcher("admin.jsp").forward(request, response);
                    } else {
                        my_session.setAttribute("msg", "Clave incorrecta!");
                        request.getRequestDispatcher("index.jsp").forward(request, response);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            else {
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet s1</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet s1 at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
