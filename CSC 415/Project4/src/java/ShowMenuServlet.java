/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//import net.sf.json.JSONObject;
import org.json.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSON;

/**
 *
 * @author nicholasdrazenovic
 */
public class ShowMenuServlet extends HttpServlet {

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
        //Get the context
        ServletContext sc = request.getServletContext();
        Map<String, CarInfo> repository = (TreeMap)sc.getAttribute("repository");
        
        //Convert to JSON array
        JsonArrayBuilder jsonBuilder = Json.createArrayBuilder();
        String [] strArray = new String[repository.size()];
        int count = 0;
        for (String key: repository.keySet()) 
        {
            strArray[count] = key;
            count++;
        }
        
     
        
        for (int i = 0; i < strArray.length; i++)
        {
            jsonBuilder.add(strArray[i]);
        }
        JsonArray jsonArray = jsonBuilder.build();
        
         try {
                PrintWriter writer = response.getWriter();
                writer.print(jsonArray);
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
