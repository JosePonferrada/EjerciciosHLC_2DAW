/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ListaVuelos;
import model.Vuelo;

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
        
        ListaVuelos lista = new ListaVuelos();
        
        Cookie[] arrayCookies = request.getCookies();
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet s1</title>");
            out.println("<style>");
            out.println("table, th, td, tr {border: 1px solid black; border-collapse: collapse}");
            out.println("th, td {padding: 1.5em; text-align: center;}");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet s1 at " + request.getContextPath() + "</h1>");
            
            out.println("<table>");
            
            out.println("<thead>");
            
            out.println("<th>Código</th><th>Origen</th><th>Destino</th><th>Precio</th>");
            
            // Now we fill the table with the data
            
            for (Vuelo vuelo : lista){
                Cookie c = new Cookie("login", "on");
                response.addCookie(c);
                
                for (Cookie cookie : arrayCookies) {
                    if (cookie.getName().equalsIgnoreCase("login")) {
                        vuelo.aumentaPrecio();
                    }
                }
                
                out.println("<tr>");
                
                out.println("<td>" + vuelo.getCode() + "</td>");
                out.println("<td>" + vuelo.getOrigin() + "</td>");
                out.println("<td>" + vuelo.getDestination() + "</td>");
                out.println("<td>" + vuelo.getPrice() + "€</td>");
                
                out.println("<tr>");
                
            }
            
            out.println("<thead>");
            
            out.println("</table>");
                        
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
