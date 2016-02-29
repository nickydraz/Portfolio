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
public class newContactServlet extends HttpServlet {
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
                writer.print(html.addContact);
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
        String fName = request.getParameter("fName");
        String lName = request.getParameter("lName");
        String bMonth = request.getParameter("month");
        String bDay = request.getParameter("day");
        String bYear = request.getParameter("year");
        String phone = request.getParameter("phone");
        
        String fullBirthDay = bMonth + "/" + bDay + "/" + bYear;
        
        if (!phone.contains("-"))
        {
            StringBuilder newPhone = new StringBuilder(phone);
            
            newPhone.insert(3, "-");
            newPhone.insert(7, "-");
            
            phone = newPhone.toString();
        }
        
        System.out.println((String)sc.getAttribute("username"));
        Map<String, ContactInfo> contacts = repository.get((String)sc.getAttribute("username"));
        
        contacts.put((fName + lName), new ContactInfo(fName, lName, bDay, bMonth, bYear, phone));
        
            try {
                PrintWriter writer = response.getWriter();
                writer.print(html.displayNewContact((String)sc.getAttribute("username"), repository, (fName + lName)));
            }
            catch (IOException e) {}
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
