/*
 * 
 * @author nicholasdrazenovic
 * 
 */

function menuListener()
{
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function()
    {
        if (xhr.readyState === 4 && xhr.status === 200)
        {
            var result = JSON.parse(xhr.responseText); 
            
            var options = "<option> </option>";
            for (var i = 0; i < result.length; i++)
            {
                options += "<option>" + result[i] + "</option>";
            }
           
            document.getElementById("availableCars").innerHTML = options;
   
            
        }
    };
    
    xhr.open("GET", "ShowMenuServlet");
    xhr.send();
}

function changeListener()
{
    
    var selectMenu = document.getElementById("availableCars");
    document.getElementById("carImage").src = ("images/" + selectMenu.options[selectMenu.selectedIndex].text + ".png");
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function()
    {
        if (xhr.readyState === 4 && xhr.status === 200)
        {
            var result = JSON.parse(xhr.responseText); 
            document.getElementById("short").innerHTML = result[0];
            document.getElementById("fullDescriptionTextArea").innerHTML = result[1];
            
        }
    };
    xhr.open("GET", "ShowDetailsServlet?carName=" + selectMenu.options[selectMenu.selectedIndex].text);
    xhr.send();
    
}
