
import java.util.Collection;
import java.util.Map;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nicholasdrazenovic
 */
public class HTMLTemplate {
    
    String login = "<!DOCTYPE html>\n" +
"<html>\n" +
"    <head>\n" +
"        <title>Contacts Database | Login</title>\n" +
"        <meta charset=\"UTF-8\">\n" +
"        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
"    </head>\n" +
"    <body>\n" +
"        <h3>CSC 415/515 Web-based Community Contacts Database</h3>\n" +
"        \n" +
"        <h4>Login Page</h4>\n" +
"        \n" +
"        <form name=\"login\" id=\"login\" action=\"LoginServlet\" method=\"post\">  \n" +
"            Enter username: <input type=\"text\" name=\"username\" id=\"username\" value=\"\" placeholder=\"johnDoe123\" />\n" +
"            <input type=\"submit\" />\n" +
"          </form>\n" +
"    </body>\n" +
"</html>\n" +
"";
    
    String loginFail = "<!DOCTYPE html>\n" +
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
"        <p>No user with that name found. Register the name below.</p>\n" +
"        <form name=\"registration\" id=\"registration\" action=\"ProcessRegistration\" method=\"post\">  \n" +
"            Choose a username: <input type=\"text\" name=\"username\" id=\"username\" value=\"\" placeholder=\"johnDoe123\" />\n" +
"            <input type=\"submit\" />\n" +
"          </form>\n" +
"    </body>\n" +
"</html>\n" +
"";
    
    String addContact = "<!DOCTYPE html>\n" +
"<html>\n" +
"    <head>\n" +
"        <title>Contacts Database | Add New</title>\n" +
"        <meta charset=\"UTF-8\">\n" +
"        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
"    </head>\n" +
"    <body>\n" +
"        <h3>CSC 415/515 Web-based Community Contacts Database</h3>\n" +
"        \n" +
"        <h4>Enter Data for New Contact</h4>\n" +
"        \n" +
"        <form name=\"login\" id=\"login\" action=\"newContactServlet\" method=\"post\">  \n" +
"        \n" +
"            <table>\n" +
"                <tr>\n" +
"                    <td>First Name</td>\n" +
"                    <td><input type=\"text\" value=\"\" id=\"fName\" name=\"fName\"/></td>\n" +
"                </tr>\n" +
"                <tr>\n" +
"                    <td>Last Name</td>\n" +
"                    <td><input type=\"text\" value=\"\" id=\"lName\" name=\"lName\"/></td>\n" +
"                </tr>\n" +
"                <tr>\n" +
"                    <td>Birthday</td>\n" +
"                    <td>Month<input type=\"number\" value=\"1\" max=\"12\" min=\"1\" id=\"month\" name=\"month\" /> Day<input type=\"number\" value=\"1\" max=\"31\" min=\"1\" id=\"day\" name=\"day\" /> Year<input type=\"number\" value=\"1950\" max=\"2020\" min=\"1\" id=\"year\" name=\"year\" /></td>\n" +
"                </tr>\n" +
"                <tr>\n" +
"                    <td>Phone</td>\n" +
"                    <td><input type=\"text\" value=\"\" id=\"phone\" name=\"phone\"/></td>\n" +
"                </tr>\n" +
"            </table>\n" +
"            \n" +
"            <input type=\"submit\" />\n" +
"            \n" +
"        </form>\n" +
"    </body>\n" +
"</html>\n" +
"";
    
    public static String displayNewContact(String username, Map<String, Map<String, ContactInfo>> repository, String contactName)
    {
        
        Map<String, ContactInfo> contacts = repository.get(username);
     
        
        ContactInfo contact = contacts.get(contactName);
        
        
        String html = "\n" +
"<!DOCTYPE html>\n" +
"<html>\n" +
"    <head>\n" +
"        <title>Contacts Database | Login</title>\n" +
"        <meta charset=\"UTF-8\">\n" +
"        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
"    </head>\n" +
"    <body>\n" +
"        <h3>CSC 415/515 Web-based Community Contacts Database</h3>\n" +
"        \n" +
"        <h4>Contact information for " + contact.getFirst() + " " + contact.getLast() + " has been added.</h4>\n" +
"        \n" +
"        <p>" + contact.getFirst() + " " + contact.getLast() + "</p>\n" +
"        <p>Phone: " + contact.getPhone() +"</p>\n" +
"        <p>Birthday: " + contact.getBirthday() + "</p>\n" +
"        \n" +
"        <p><a href=\"MainViewServlet\">See your list of contacts</a></p>\n" +
"    </body>\n" +
"</html>";
        
        return html;
    }
    
    
    public static String displayContact(String username, Map<String, Map<String, ContactInfo>> repository, String contactName)
    {
        
        Map<String, ContactInfo> contacts = repository.get(username);
     
        
        ContactInfo contact = contacts.get(contactName);
        
        
        String html = "\n" +
"<!DOCTYPE html>\n" +
"<html>\n" +
"    <head>\n" +
"        <title>Contacts Database | Login</title>\n" +
"        <meta charset=\"UTF-8\">\n" +
"        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
"    </head>\n" +
"    <body>\n" +
"        <h3>CSC 415/515 Web-based Community Contacts Database</h3>\n" +
"        \n" +
"        <p>" + contact.getFirst() + " " + contact.getLast() + "</p>\n" +
"        <p>Phone: " + contact.getPhone() +"</p>\n" +
"        <p>Birthday: " + contact.getBirthday() + "</p>\n" +
"        \n" +
"        <p><a href=\"MainViewServlet\">See your list of contacts</a></p>\n" +
"    </body>\n" +
"</html>";
        
        return html;
    }
    
    public static String displayTable(String username, Map<String, Map<String, ContactInfo>> repository)
    {
        Map<String, ContactInfo> contacts = repository.get(username);
        
        String table = "<!DOCTYPE html>\n" +
"<html>\n" +
"    <head>\n" +
"        <title>Contacts Database | Registration</title>\n" +
"        <meta charset=\"UTF-8\">\n" +
"        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
"    </head>\n" +
"    <body>\n" +
"        <h3>CSC 415/515 Web-based Community Contacts Database</h3>\n" +
"        \n";
       if (contacts.isEmpty())
       {
           table += "You have no contacts.";
       }
       else
       {
           
           table += "<table>";
           
           for(ContactInfo part: contacts.values())
           {
                  table += "<tr><td>" + part.getFirst() + "</td>"
                       + "<td>" + part.getLast() + "</td>"
                          + "<td><a href='detailsServlet?Contact=" + part.getFirst() + part.getLast() + "'>See Details</a></td>"
                          + "<td><a href='EditServlet?Contact=" + part.getFirst() + part.getLast() + "'>Edit</a></td>"
                          + "<td><a href='DeleteServlet?Contact=" + part.getFirst() + part.getLast() + "'>Delete</a></td></tr>";
           }
           
           table += "</table>";
                     
       }
        
        table += "<br /><br /><a href=\"newContactServlet\">Add new contact</a></body></html>";
        
        return table;
    }
    
    public static String editContact(String username, Map<String, Map<String, ContactInfo>> repository, String contactName)
    {
        
        Map<String, ContactInfo> contacts = repository.get(username);
     
        
        ContactInfo contact = contacts.get(contactName);
        
        String editContact = "<!DOCTYPE html>\n" +
"<html>\n" +
"    <head>\n" +
"        <title>Contacts Database | Add New</title>\n" +
"        <meta charset=\"UTF-8\">\n" +
"        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
"    </head>\n" +
"    <body>\n" +
"        <h3>CSC 415/515 Web-based Community Contacts Database</h3>\n" +
"        \n" +
"        <h4>Edit Data for Contact</h4>\n" +
"        \n" +
"        <form name=\"login\" id=\"login\" action=\"EditServlet\" method=\"post\">  \n" +
"           <input type=\"text\" id=\"old\" name=\"old\" value=\"" + (contact.getFirst() + contact.getLast()) + "\" hidden/>\n" +
"            <table>\n" +
"                <tr>\n" +
"                    <td>First Name</td>\n" +
"                    <td><input type=\"text\" value=\"" + contact.getFirst() + "\" id=\"fName\" name=\"fName\"/></td>\n" +
"                </tr>\n" +
"                <tr>\n" +
"                    <td>Last Name</td>\n" +
"                    <td><input type=\"text\" value=\"" + contact.getLast() + "\" id=\"lName\" name=\"lName\"/></td>\n" +
"                </tr>\n" +
"                <tr>\n" +
"                    <td>Birthday</td>\n" +
"                    <td>Month<input type=\"number\" value=\"" + contact.getBMonth() + "\" max=\"12\" min=\"1\" id=\"month\" name=\"month\" /> Day<input type=\"number\" value=\"" + contact.getBDay() + "\" max=\"31\" min=\"1\" id=\"day\" name=\"day\" /> Year<input type=\"number\" value=\"" + contact.getBYear() + "\" max=\"2020\" min=\"1\" id=\"year\" name=\"year\" /></td>\n" +
"                </tr>\n" +
"                <tr>\n" +
"                    <td>Phone</td>\n" +
"                    <td><input type=\"text\" value=\"" + contact.getPhone() + "\" id=\"phone\" name=\"phone\"/></td>\n" +
"                </tr>\n" +
"            </table>\n" +
"            \n" +
"            <input type=\"submit\" />\n" +
"            \n" +
"        </form>\n" +
"    </body>\n" +
"</html>\n" +
"";
        return editContact;
    }
    
    public static String deletePrompt(String username, Map<String, Map<String, ContactInfo>> repository, String contactName)
    {
        Map<String, ContactInfo> contacts = repository.get(username);
        ContactInfo contact = contacts.get(contactName);
        
                String html = "\n" +
"<!DOCTYPE html>\n" +
"<html>\n" +
"    <head>\n" +
"        <title>Contacts Database | Login</title>\n" +
"        <meta charset=\"UTF-8\">\n" +
"        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
"    </head>\n" +
"    <body>\n" +
"        <h3>CSC 415/515 Web-based Community Contacts Database</h3>\n" +
"        \n" +
"        <h4>Are you sure you want to delete this contact?</h4>\n" +                        
"        <p>" + contact.getFirst() + " " + contact.getLast() + "</p>\n" +
"        <p>Phone: " + contact.getPhone() +"</p>\n" +
"        <p>Birthday: " + contact.getBirthday() + "</p>\n" +
"        \n" +
"        <form name=\"delete\" id=\"delete\" action=\"DeleteServlet?Contact=" + (contact.getFirst() + contact.getLast()) + "\" method=\"post\" >\n" +                        
"        <input type=\"submit\" value=\"Delete\" />\n" +     
"        </form>\n" +                        
"        <p><a href=\"MainViewServlet\">See your list of contacts</a></p>\n" +
"    </body>\n" +
"</html>";
      
                return html;
    }
}
