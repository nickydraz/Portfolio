/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.*;
import java.util.Map;
import java.util.TreeMap;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author nicholasdrazenovic
 */
@MultipartConfig
public class CarInfoServlet extends HttpServlet {
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
        
        //Get the context
        ServletContext sc = request.getServletContext();
        Map<String, CarInfo> repository = (TreeMap)sc.getAttribute("repository");
        
        Part imageFile = request.getPart("imageFile");
        
        String name = imageFile.getSubmittedFileName();
        name = name.substring(0, name.length() - 4);
        InputStream in = imageFile.getInputStream(); 
        String rPath = sc.getRealPath("/");
        File webDirectory = new File(new File(rPath).getParentFile().getParentFile(), "web");
        File imageDirectory = new File(webDirectory, "images");
        String imagePath = imageDirectory + "/" + name + ".png";       
        Files.copy(in, Paths.get(imagePath), REPLACE_EXISTING);
     
        String shortDescription = request.getParameter("shortDescription");
        CarInfo car = new CarInfo(name, shortDescription);
        repository.put(name, car);
 
        Part fullDesc = request.getPart("descriptionFile");
        String textName = fullDesc.getSubmittedFileName();
        in = fullDesc.getInputStream();
        String textPath = new File(webDirectory, "WEB-INF") + "/" + textName;
        Files.copy(in, Paths.get(textPath), REPLACE_EXISTING);
       
        
        response.sendRedirect("admin.html");
        
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
