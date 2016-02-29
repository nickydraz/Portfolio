<!-- Hide from Old Browsers
function checkForCookie()
{
	if (getCookie("username") == "")
	{
		createCookie();
	}
	else
		displayCookie();
}//end function

function createCookie()
{
	document.getElementById("main").innerHTML = "<p>Please enter your name: <input type='text' id='username' name = 'username' value = ''/></p>";
	document.getElementById("main").innerHTML += "<br /><button name='submit' id='submit'>Submit</button>";
	document.getElementById("submit").addEventListener("click", function() {setCookie("username", document.getElementById("username").value)});
	document.getElementById("submit").addEventListener("click", displayCookie);
}//end function

function setCookie(cName, value)
{
	var expireDate = new Date();
	expireDate.setMonth(expireDate.getMonth()+6); // lasts for 6 months
	
	document.cookie = cName + "=" + value + ";expires=" + expireDate.toGMTString();
}//end function

function displayCookie()
{
	document.getElementById("main").innerHTML = "<p>Name: " + getCookie("username") + "</p>";
	
	document.getElementById("main").innerHTML += "<p id='showTime'>Current Time: </p>";
	showTheTime();
	
	if (getCookie("lastClosed") != "")
	{
		document.getElementById("main").innerHTML += "<p>Last Visited On: " + getCookie("lastClosed") + "</p>";
		document.getElementById("main").innerHTML += "<p>Time Asleep: " + calcSleep() + " Hours</p>"; 
	}
}//end function

function getCookie(cookName)
{
	var cookie = cookName + "=";
	var cookCheck = document.cookie.split(";");
	for (var i = 0; i < cookCheck.length; i++)
	{
		var c = cookCheck[i];
		while (c.charAt(0) == ' ')
			c = c.substring(1);
		if (c.indexOf(cookie) == 0)
			return c.substring(cookie.length, c.length);
	} 
	
	return "";
}//end function

function showTheTime() 
{
	var now = new Date();  // get current time

	document.getElementById("showTime").innerHTML = "Current Time: " + showTheHours(now.getHours()) + showZeroFilled(now.getMinutes()) + showZeroFilled(now.getSeconds()) + showAmPm();
	setTimeout(showTheTime,1000);  //set a timer for 1 second intervals
	
	function showTheHours(theHour) 
	{	
		if ((theHour > 0 && theHour < 13)) 
		{
			return theHour;
		}
		if (theHour == 0) 
		{
			return 12;
		}
		return theHour-12;
	}
	
	function showZeroFilled(inValue) 
	{
		if (inValue > 9)   // 10 or more
		{
			return ":" + inValue;
		}
		return ":0" + inValue;   // add leading 0
	}

	function showAmPm() // put AM/PM on 12 hr time
	{
		if ((now.getHours() < 12)) 
		{
			return " AM";
		}
		return " PM";
	}
	
	return showTheHours(now.getHours()) + showZeroFilled(now.getMinutes()) + showZeroFilled(now.getSeconds()) + showAmPm();
}//end class

function calcSleep()
{
	sleepTime = getCookie("lastClosed");
	sleepTimeInSeconds = convertToSeconds(sleepTime);
	currentTimeInSeconds = convertToSeconds(showTheTime());
	difference = Math.round((Math.abs(sleepTimeInSeconds - currentTimeInSeconds) / 60 / 60) * 1000000) / 1000000;
	return difference;
}//end function

function convertToSeconds(timeToConvert)
{
	inSeconds = 0;
	positionInString = 0;
	
	currentElement = "";
	for (var i = 0; i < timeToConvert.length; i++)
	{

		if (timeToConvert.charAt(i) !== ":" && timeToConvert.charAt(i) != "P" && timeToConvert.charAt(i) != "M" && timeToConvert.charAt(i) != "A" && timeToConvert.charAt(i) != " ")
		{
			currentElement += timeToConvert.charAt(i);	
		}
		
		if (timeToConvert.charAt(i) == ':')
		{
			switch(positionInString)
			{
				case 0:
				inSeconds += eval(currentElement + "* 60");
				case 1:
				inSeconds += eval(currentElement + "* 60");
				case 2:
				inSeconds += eval(currentElement);
				break;
			}
			currentElement == "";
			positionInString++;
		}
	}
	return inSeconds;
}//end function

function closingTime()
{
	setCookie("lastClosed", showTheTime());
	return "Closing";
}//end function


-->