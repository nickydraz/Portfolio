
import java.util.Map;
import java.util.TreeMap;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Web application lifecycle listener.
 *
 * @author nicholasdrazenovic
 */
public class NewServletListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Map<String, CarInfo> repository = new TreeMap<>();
        sce.getServletContext().setAttribute("repository", repository);
        
        String path = sce.getServletContext().getRealPath("/");
        sce.getServletContext().setAttribute("realPath", path);
        sce.getServletContext().setAttribute("imagesPath", path + "/images");
        sce.getServletContext().setAttribute("WEBINFPath", path + "/WEB-INF");
        
        System.out.println("The file path is: " + path);
    }
       

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        
    }
}
