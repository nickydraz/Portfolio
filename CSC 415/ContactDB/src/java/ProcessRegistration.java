/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.TreeMap;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author nicholasdrazenovic
 */
public class ProcessRegistration extends HttpServlet {

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext sc = request.getServletContext();
        Map<String, Map<String, ContactInfo>> repository = (Map)sc.getAttribute("repository");
        String username = request.getParameter("username");
        if (repository.containsKey(username))
        {
            try {
                PrintWriter writer = response.getWriter();
                writer.print("<!DOCTYPE html>\n" +
"<html>\n" +
"    <head>\n" +
"        <title>Contacts Database | Registration</title>\n" +
"        <meta charset=\"UTF-8\">\n" +
"        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
"    </head>\n" +
"    <body>\n" +
"        <h3>CSC 415/515 Web-based Community Contacts Database</h3>\n" +
"        \n" +
"        <h4>Registration Page</h4>\n" +
"        \n" +
"        <p>A user with that name already exists. Please try again.</p>\n" +
"        <form name=\"registration\" id=\"registration\" action=\"#\">  \n" +
"            Choose a username: <input type=\"text\" name=\"username\" id=\"username\" value=\"\" placeholder=\"johnDoe123\" />\n" +
"            <input type=\"submit\" />\n" +
"          </form>\n" +
"    </body>\n" +
"</html>\n" +
"");
            }
            catch (IOException e) {}
            
            
        }
        else
        {
            sc.setAttribute("username", username );
            repository.put(username, new TreeMap<String, ContactInfo>());
            
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

}//end class
