/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.glassfish.soteria.Utils;

/**
 *
 * @author diurno
 */
@WebServlet(urlPatterns = {"/s1"})
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
            
            String nombre = request.getParameter("nombre");
            String valor = request.getParameter("valor");
            
            Cookie[] galletas = request.getCookies();
            
//            // Comprobamos la opción seleccionada por el usuario
//            int opcion = Integer.parseInt(request.getParameter("boton"));
//            
//            switch (opcion) {
//                case 1:
//                    Cookie c = new Cookie(nombre, valor);
//                    response.addCookie(c);
//                    response.sendRedirect("index.jsp");
//                    break;
//                    
//                case 2:
//                    for (Cookie c1 : galletas) {
//                        if (c1.getName().equals(nombre)){
//                            out.println("Nombre: " + c1.getName());
//                            out.println("Valor: " + c1.getValue());
//                            break;
//                        } else {
//                            out.println("No existe ninguna cookie con ese nombre.");
//                            break;
//                        }
//                    }
//                    break;
//                    
//                case 3:
//                    
//                    
//                default:
//                    throw new AssertionError();
//            }
            
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet s1</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet s1 at " + request.getContextPath() + "</h1>");
            
            // Comprobamos la opción seleccionada por el usuario
            int opcion = Integer.parseInt(request.getParameter("boton"));
            
            switch (opcion) {
                case 1:
                    Cookie c = new Cookie(nombre, valor);
                    response.addCookie(c);
                    response.sendRedirect("index.jsp");
                    out.println("Cookie creada");
                    break;
                    
                case 2:
                    for (Cookie c1 : galletas) {
                        if (c1.getName().equalsIgnoreCase(nombre)){
                            out.println("Nombre: " + c1.getName());
                            out.println("Valor: " + c1.getValue());
                            break;
                        } else {
                            out.println("No existe ninguna cookie con ese nombre.");
                            break;
                        }
                    }
                    break;
                    
                case 3:
                    
                    
                default:
                    throw new AssertionError();
            }
            
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
