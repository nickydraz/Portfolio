/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nicholasdrazenovic
 */
public class ContactInfo {
    
    String fName;
    String lName;
    Birthday bday;
    
    String phone;


    public ContactInfo(String fName, String lName, String day, String month, String year, String phone)
    {
        this.fName = fName;
        this.lName = lName;
        this.bday = new Birthday(day, month, year);
        this.phone = phone;
    }
    
    public String getFirst()
    {
        return fName;
    }
    
    public String getLast()
    {
        return lName;
    }
    
    public String getBirthday()
    {
        //Will we want to return the whole thing or just the toString or individual parts?
        return bday.getFullBirthday();
    }
    
    public String getPhone()
    {
        return phone;
    }
    
    public String getBDay()
    {
        return bday.getBDay();
    }
    
    public String getBMonth()
    {
        return bday.getBMonth();
    }
    
    public String getBYear()
    {
        return bday.getBYear();
    }
}

class Birthday {
    String day;
    String month;
    String year;

    Birthday(String day, String month, String year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }
    
    public String getFullBirthday()
    {
        return month + "/" + day + "/" + year;
    }
    
    public String getBDay()
    {
        return day;
    }
    
    public String getBMonth()
    {
        return month;
    }
    
    public String getBYear()
    {
        return year;
    }
    
}
