/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package cacjava2022.rivera.tpfinal;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.SQLException;

/**
 *
 * @author Ana
 */
@WebServlet(name = "LoginAuth", urlPatterns = {"/LoginAuth"})
public class LoginAuth extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter() ) {
            Persistence base = new Persistence();
            ResultSet rs = base.consultaSQL("SELECT * FROM users WHERE username= '" + request.getParameter("login-username") + "' AND password = '" + request.getParameter("login-pass") + "'");
            while (rs.next()) {
                out.println("Bienvenido " + rs.getString("username")+"</br>");
                out.println("nombre completo:" +rs.getString("fullname")+"</br>");
                out.println("<a href=\"./index.html\">Volver a la página principal</a>");
            }
            if ( rs.first() == false ) {
                out.println("El usuario ingresado no existe");
                out.println("<a href=\"./login.html\">Inicie sesión</a>");
            }/* else {
                out.println("Servlet checkuser at "+ request.getContextPath());
                out.println("El usuario ingresado es "+ request.getParameter("login-username"));
            }*/
        } catch (SQLException ex) {
            Logger.getLogger(LoginAuth.class.getName()).log(Level.SEVERE, null, ex);
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
