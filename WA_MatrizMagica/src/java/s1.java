/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<style> table, tr, td {"
                    + "border: 1px solid black; border-collapse: collapse; }"
                    + "td{ font-size: 1em; padding: 2em;};"
                    + "</style>");
            out.println("<title>Matriz mágica</title>");
            out.println("</head>");
            out.println("<body>");
            
            int numero = Integer.parseInt(request.getParameter("numero"));
            if (numero % 2 == 0 || numero < 1) {
                request.getRequestDispatcher("index.jsp").forward(request, response);
            } else {
                
                // Getting the matrix
                int[][] magicMatrix = new mmagica(numero).getMatrix();
            
                out.println("<table border='1px solid black'>");
                
                for (int i = 0; i < magicMatrix[i].length; i++) {
                    out.println("<tr>");
                    for (int j = 0; j < magicMatrix.length; j++) {
                        out.println("<td>");
                        out.println(magicMatrix[i][j] + " ");
                        out.println("</td>");
                    }
                    out.println("</tr>");
                }
                
                out.println("</table>");
                
            }
            
            out.println("<form action=\"\" method=\"post\">");
            
            out.println("<button type=\"submit\">");
            out.println("Hola");
            out.println("</button>");
            out.println("</form>");
            
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
