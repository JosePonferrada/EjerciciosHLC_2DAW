/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import clases.ConnMysql;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            Connection miconexion = new ConnMysql().getConnection();
            
            // Creamos un objeto Statement con la instrucción
            Statement instruccion = miconexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            
            String nombre = request.getParameter("nombre");
            int nota = Integer.parseInt(request.getParameter("nota"));
            Date fecha = Date.valueOf(request.getParameter("fecha"));
            
            
            // Creamos la sentencia sql
//            String sql = "select id, nombre, nota, fecha_nac from datos";
            
            // Recogemos los datos en un resultset
//            ResultSet rs = instruccion.executeQuery(sql);
            if (request.getParameter("borrar")!=null) {
                
                int idBorrar = Integer.parseInt(request.getParameter("borrar"));
                
                // Creamos la sentencia sql
                String sql = "select * from datos where id = " + idBorrar;

                // Recogemos los datos en un resultset
                ResultSet rs = instruccion.executeQuery(sql);
                //Como solo devuelve una fila, nos situamos en ella y la borramos
                rs.absolute(1);
                rs.deleteRow();
                
                rs.close();
                //Redirigimos al index
                request.getRequestDispatcher("index.jsp").forward(request, response);
                
                
                
            }  
            
            if (request.getParameter("insertar")!= null) {
                
                
                String sql = "select * from datos";
                ResultSet rs = instruccion.executeQuery(sql);
                
                rs.moveToInsertRow();
                rs.updateString(2, nombre);
                rs.updateInt(3, nota);
                rs.updateDate(4, fecha);
                rs.insertRow();
                
                rs.close();
                //Redirigimos al index
                request.getRequestDispatcher("index.jsp").forward(request, response);
                
            }
            
            if (request.getParameter("editar") != null) {
                int idMod = Integer.parseInt(request.getParameter("editar"));
                
                String sql = "select * from datos where id = " + idMod;
                ResultSet rs = instruccion.executeQuery(sql);
                rs.absolute(1); //Nos situamos en la fila devuelta
                rs.updateString(2, nombre);
                rs.updateInt(3, nota);
                rs.updateDate(4, fecha);
                
                rs.updateRow();
                
                rs.close();
                //Redirigimos al index
                request.getRequestDispatcher("index.jsp").forward(request, response);                
            }
            
            
            // Con este id podemos borrar y modificar
            
            
            
//            // Para borrar una fila
//            rs.absolute(2); // Nos situamos en la fila 2
//            rs.deleteRow(); // Borramos
//
//            
//            // Para actualizar un campo
//            rs.updateString("Nombre", "Juana");
//            rs.updateRow();
//            
//            // Para hacer un insert
//            rs.moveToInsertRow(); // Nos movemos a la línea de inserts
//            rs.updateInt(1, 3);   // Actualizamos los campos
//            rs.updateString(2, "Manolo");
//            rs.insertRow(); // Hacemos el insert
//            
            
            
            instruccion.close();
            miconexion.close();
            
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet s1</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Estamos en el servlet 1</h1>");
            
            
            
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(s1.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(s1.class.getName()).log(Level.SEVERE, null, ex);
        }
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
