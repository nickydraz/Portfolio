/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import static java.nio.file.Files.newInputStream;
import java.util.Collection;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author nicholasdrazenovic
 */
public class ShowDetailsServlet extends HttpServlet {


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
        String [] strArray = new String[2];
        
        strArray[0] = repository.get(request.getParameter("carName")).shortDescription;
        String rPath = sc.getRealPath("/");
        File webDirectory = new File(new File(rPath).getParentFile().getParentFile(), "web");
        File imageDirectory = new File(webDirectory, "images");
       
        
        
        FileReader desc = new FileReader(webDirectory + "/WEB-INF/" + request.getParameter("carName") + ".txt");
        BufferedReader br = new BufferedReader(desc);
        String content = br.readLine();
        strArray[1] = content;
        
     
        
        jsonBuilder.add(strArray[0]);
        jsonBuilder.add(strArray[1]);
        
        JsonArray jsonArray = jsonBuilder.build();
        System.out.println(jsonArray);
        
            
         try {
                PrintWriter writer = response.getWriter();
                writer.print(jsonArray);
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
