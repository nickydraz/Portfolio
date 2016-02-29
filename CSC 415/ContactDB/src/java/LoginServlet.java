/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author nicholasdrazenovic
 */
public class LoginServlet extends HttpServlet {

    HTMLTemplate html = new HTMLTemplate();
   

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
                PrintWriter writer = response.getWriter();
                writer.print(html.login);
            }
            catch (IOException e) {}
    
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
        ServletContext sc = request.getServletContext();
        Map<String, Map<String, ContactInfo>> repository = (Map)sc.getAttribute("repository");
        String username = request.getParameter("username");
        
        if (!repository.containsKey(username))
        {
            try {
                PrintWriter writer = response.getWriter();
                writer.print(html.loginFail);
            }
            catch (IOException e) {}
    
        }
        else
        {
            sc.setAttribute("username", username );
            try {
                PrintWriter writer = response.getWriter();
                writer.print(HTMLTemplate.displayTable(username, repository));
                
            } catch (IOException e) {}
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
