//Nicholas Drazenovic

//Global variables
var tiles = []; //array of tiles
var length = 28; //length of the array
var id1 = ""; //ID for first picture being checked
var id2 = ""; //ID for second picture being checked
var nbrSelected = 1; //Counter for number of images clicked for current attempt
var clearedPairs = 0; //Counter for the number of matches found
var numGuesses = 0; //Counter for the number of guesses made
var cheatShown = false; //Boolean that tells whether the user is viewing the cheat sheet

//Counters for time elapsed
var secondsCounter = 0;
var minutesCounter = 0;
var hoursCounter = 0;
var timerOn = false;
function timer()
{
  timerOn = true;
  secondsCounter++;

  if (secondsCounter == 60)
  {
    minutesCounter++;
    secondsCounter = 0;
  }
  if (minutesCounter == 60)
  {
    hoursCounter++;
    minutesCounter = 0;
  }

  var timeText = "";

  //formatting
  if (hoursCounter < 10)
    timeText += "0" + hoursCounter + ":";
  else {
    timeText += hoursCounter + ":";
  }
  if (minutesCounter < 10)
    timeText += "0" + minutesCounter + ":";
  else
    timeText += minutesCounter + ":";
  if (secondsCounter < 10)
    timeText += "0" + secondsCounter;
  else {
    timeText += secondsCounter;
  }

  //Add string to the HTML field
  get("timeElapsed").innerHTML = timeText;
}
//Shorthand for retrieving elements from the DOM
function get(id)
{
  return document.getElementById(id);
}
//Function to populate the array with images
function fillArray()
{

  if (!timerOn)
  {
    setInterval(timer,1000);  //set a timer for 1 second intervals
  }
  //Fill array with 2 copies of each image
  for (var i = 0; i < length; i+=2)
  {
    tiles[i] = "images/car" + (i) + ".png";
    tiles[i + 1] = "images/car" + (i) + ".png";
  }

  //Shuffle once filled
  shuffle(tiles);
}
//Fisher-Yates Array Shuffle Method
//Found on https://bost.ocks.org/mike/shuffle/
function shuffle(array) {
  var m = array.length, t, i;

  // While there remain elements to shuffle…
  while (m) {

    // Pick a remaining element…
    i = Math.floor(Math.random() * m--);

    // And swap it with the current element.
    t = array[m];
    array[m] = array[i];
    array[i] = t;
  }

  return array;
}

//Function to reveal picture
function showPic(id)
{
  //Check if they are clicking on the same spot twice
  if (id == id1)
    return;

  //Save the id
  if (nbrSelected == 1)
  {
    id1 = id;
    get(id1).src = tiles[id1]; //reveal the picture

    //Increment
    nbrSelected++;
  }
  else if (nbrSelected == 2) {
    nbrSelected ++;
    id2 = id;
    get(id2).src = tiles[id2];
    compPics(); //Compare the pictures of the two tiles
    //Reset for the next turn
    setTimeout(function() { id1 = ""; id2 = "";nbrSelected = 1;}, 800)
    numGuesses++;
    get("numberOfGuesses").innerHTML = numGuesses;
  }
}

//Function to compare the pictures
function compPics()
{
  //If they match
  if (tiles[id1] === tiles[id2])
  {
    //Disable the pictures' onclick methods
    get(id1).onclick = "";
    get(id2).onclick = "";

    //Set their images to the cleared image
    get(id1).src = "images/cleared.png";
    get(id2).src = "images/cleared.png";
    clearedPairs++;
  }
  else
    {
      //If they don't match, hide the images again
      setTimeout(function() {get(id1).src = "images/hidden.png";}, 800);
      setTimeout(function() {get(id2).src = "images/hidden.png";}, 800);
    }

    //If all pairs have been found
    if (clearedPairs == 14)
    {
      setTimeout(function(){alert("Congratulations, you have won!"); reset();}, 800);
    }
}

//Function to reset the game
function reset()
{
  secondsCounter = 0;
  minutesCounter = 0;
  hoursCounter = 0;
  clearedPairs = 0;
  id1 = "";
  id2 = "";
  fillArray();
  get("cheat").innerHTML = "Stuck? Click here!";
  cheatShown = false;
  nbrSelected = 0;
  numGuesses = 0;

}
//Function for the cheat sheet
function cheat()
{
  //Hide the sheet if it is already open
  if (cheatShown)
  {
    get("cheat").innerHTML = "Stuck? Click here!";
    cheatShown = false;
    return;
  }

  //Loop through array
  for (var i = 0; i < length; i++)
  {
    var carFull = tiles[i]; //Full string at that index
    var dot = carFull.indexOf("."); //Position of file extension
    var car = carFull.substring(10, dot); //get just the number from the image name

    //Formatting, to start numbers on new row if 7 have been displayed
    if (i%7 == 0)
    {
      get("cheat").innerHTML+= "\n<br />";
    }
    get("cheat").innerHTML += car + ", ";
  }
  get("cheat").innerHTML += "<br /><br />";
  cheatShown = true;
}
