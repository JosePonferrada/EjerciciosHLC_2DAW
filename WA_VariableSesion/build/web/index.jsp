<%-- 
    Document   : index
    Created on : 14 oct 2024, 13:16:05
    Author     : diurno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        
        <%
            
            if (session.isNew()) { // Primera vez que abres el navegador
                out.println("La primera vez que entras");
                session.setAttribute("nombre", "Pepe");
                
            } 
            else {
                
                out.println("Ya has entrado antes");
                String cadena = (String) session.getAttribute("nombre"); // Para recuperar el atributo
                out.println("Nombre: " + cadena);
                
            }

            %>
        
    </body>
</html>
