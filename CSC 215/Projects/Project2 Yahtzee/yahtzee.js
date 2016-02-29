//Coded by Nicholas Drazenovic

//State variables
inReRoll = false; //Used to determine if user is currently in a reroll phase
rollCount = 0; //Allowed 3 rolls per turn
turnCount = 0; //13 turns in a game. Default 0 before players are entered
               //Will multiply by number of players within a function
               //so that each player has 13 turns
maxTurnCount = 13; //Variable for max number of turns

playersArray = []; //Array to hold all the players
playerCount = 0; //This is a number meant to identify the players,
                 //allowing for indexing into the player array
                 //Once all players are created, this number is used to change turns

currentPlayerID = 0; //Used for changing turns. Defaults to 0 for beginning of game
firstTurn = true;

gameOver = false; //Variable used after game is done to stop players from rolling
                  //once a winner has been chosen

function getRollCount()
{
  document.getElementById("rollCount").innerHTML = "Roll Count: " + rollCount + "/3";
}

function getTurnCount()
{
  document.getElementById("turnCount").innerHTML = "Turn Counter: " + turnCount;
}

function createPlayer()
{
  var numPlayers = prompt("Welcome to Moon Yahtzee!\nPlease enter the number of astronauts: ");

  //input validation
  while (numPlayers > 5 || numPlayers <= 0)
  {
    numPlayers = prompt("Please enter the number of astronauts (between 1 and 5): ");
  }

  //Create all the player objects
  for (var i = 1; i <= numPlayers; i++)
  {
    var name = prompt("Please enter astronaut #" + i + "s name: ");

    //Create a new player object
    playersArray.push(new player(name));

    //Create a hidden button that will be used to view scores post-game
    var playerButton = document.createElement("button");
    playerButton.innerHTML = name;
    playerButton.id = (i - 1);
    playerButton.setAttribute('onclick',
    'resetBoard(), document.getElementById("playerName").innerHTML = "Viewing " + playersArray[this.id].name + "\'s score card", changeScoreboard(playersArray[this.id].scoreCard, playersArray[this.id].scoreAvail)');

    document.getElementById("hiddenButtons").appendChild(playerButton);
  }

  //update max turn count
  maxTurnCount *= numPlayers;

  nextTurn();

  window.addEventListener('keyup', keyUpEvent, false);
}//end function

//Player Class
function player(name)
{

  this.name = name;
  this.pID = playerCount++; //Give player an ID for indexing the array
                          //then increment for the next player

 //Object to hold the player's scores
 this.scoreCard = {
    'ones': null, 'twos': null, 'threes': null,
    'fours': null, 'fives': null, 'sixes': null,
    'bonus': null, '3kind': null, 'fullHouse': null,
    '4kind': null, 'smallStraight': null, 'largeStraight': null,
    'yahtzee': null, 'chance': null, 'sub': null,
    'grandTotal': null
  };

  //Object to hold whether or not a score box has been chosen yet
  //False if not selected, true if selected already
  this.scoreAvail = {
      'ones': false, 'twos': false, 'threes': false,
      'fours': false, 'fives': false, 'sixes': false,
      'bonus': false, '3kind': false, 'fullHouse': false,
      '4kind': false, 'smallStraight': false, 'largeStraight': false,
      'yahtzee': false, 'chance': false, 'sub': false,
      'grandTotal': false
    };

}//end player class

//Function for showing possible scores after a roll
function displayScore()
{
  //Array for basic totals of die rolls ==> sub
  var basicScoreCount = {1: 0, 2: 0, 3: 0, 4: 0, 5: 0, 6: 0};
  var basicScoreTally= {1: 0, 2: 0, 3: 0, 4: 0, 5: 0, 6: 0};
  //Array for complex totals of die rolls ==> sub2
  var complexScoreTally = [0, 0, 0, 0, 0, 0, 0];

  //Bonus only available if sub >= 63
  var bonus = 0;

  //Array for all the dice
  var diceArray = getDiceValues();
  for (var i = 0; i < 5; i++)
  {
    basicScoreCount[diceArray[i]] += diceArray[i];
  }

  //Clear the previous temporary scores from the roll
  resetBoard();

  //Display the basic score combos (left hand side scoring)
  for (var i = 1; i <= 6; i++)
  {
    if(basicScoreCount[i] == 0)
    {
      continue;
    }
    switch (i){
      case 1:
      changeElement("ones", basicScoreCount[1], "red");
      break;
      case 2:
      changeElement("twos", basicScoreCount[2], "red");
      break;
      case 3:
      changeElement("threes", basicScoreCount[3], "red");
      break;
      case 4:
      changeElement("fours", basicScoreCount[4], "red");
      break;
      case 5:
      changeElement("fives", basicScoreCount[5], "red");
      break;
      case 6:
      changeElement("sixes", basicScoreCount[6], "red");
      break;
    }//end switch

  }//end for

  //Begin work on right-hand side of board
  diceArray.sort();
  diceArray = diceArray.join("");

  //Use regular expressions to find possible scores

  /***************** RULES FOR RIGHT HAND SIDE SCORING *****************
  * 3 Of A Kind --> At least 3 of the same value, add total of all dice
  * 4 Of A Kind --> At least 4 of the same value, add total of all dice
  * Small Straight --> 4 consecutive dice values. 30 points.
  * Large Straight --> 5 consecutive dice values. 40 points.
  * Full House --> 3 of a kind and 2 of a kind. 25 points.
  * Yahtzee --> 5 of a kind. 50 points.
  * Chance --> Sum of all dice values. No special rules.
  **********************************************************************/
  //if 3 of a kind
  if (/(.)\1{2}/.test(diceArray))
  {
    changeElement("3kind", getTotalValues(), "red");
  }
  //if 4 of a kind
  if (/(.)\1{3}/.test(diceArray))
  {
    changeElement("4kind", getTotalValues(), "red");
  }
  //if small straight
  if (/1234|2345|3456|12234|12334|23345|23445|34456|34556/.test(diceArray))
  {
    changeElement("smallStraight", 30, "red");
  }
  //if large straight
  if (/12345|23456/.test(diceArray))
  {
    changeElement("largeStraight", 40, "red");
  }
  //if full house
  if (/(.)\1{2}(.)\2|(.)\3(.)\4{2}/.test(diceArray))
  {
    changeElement("fullHouse", 25, "red");
  }
  //if yahtzee
  if (/(.)\1{4}/.test(diceArray))
  {
    changeElement("yahtzee", 50, "red");
  }

  //Chance
  changeElement("chance", getTotalValues(), "red");

  //Loop through and display zeros for all remaining score elements
  var scoreArray = document.getElementsByName("scoring");

  for (var i = 0; i < scoreArray.length - 1; i++)
  {
    if (document.getElementById(scoreArray[i].id).innerHTML == ""
    || document.getElementById(scoreArray[i].id).innerHTML == null)
    {
      changeElement(scoreArray[i].id, 0, "red");
    }
  }//end for
}//end function

//function to clear the previous temporary scores from the roll
function resetBoard()
{
  //Fill an array with the elements of the scoreboard
  var formArray = document.getElementsByName("scoring");

  //Iterate through the array and reset their styles when applicable
  for (var i = 0; i < formArray.length; i++)
  {
    if ((!formArray[i].readonly && !formArray[i].disabled))
    {
      formArray[i].innerHTML = "";
      formArray[i].style.color = "white";
    }
  }
}
//Function for changing individual boxes
function changeElement(eID, scoreValue, textColor)
{

  //If the element is disabled, don't adjust the box's style
  if (document.getElementById(eID).disabled)
  {
    return false;
  }
  document.getElementById(eID).innerHTML = scoreValue;
  document.getElementById(eID).style.color = textColor;
}

//Fucntion for disabling an element
function disableElement(eID, textColor)
{
  document.getElementById(eID).style.color = textColor;
  document.getElementById(eID).disabled = true;
  playersArray[currentPlayerID].scoreAvail[eID] = true;

  //Change the score label
  document.getElementById("label" + eID).style.backgroundImage = "url(" + eID + "1.gif)";

}//end function

//Function for updating scoreboard
function changeScoreboard(playerScore, scoreAvail)
{
  var keys = [];
  for (var key in playerScore)
  {
    if (playerScore.hasOwnProperty(key))
    {
      keys.push(key);
    }
  }//end for

  for (var i = 0; i < keys.length; i++)
  {
    document.getElementById(keys[i]).innerHTML = playerScore[keys[i]];

    //Skip grandTotal key in array
    if (keys[i] == "grandTotal")
    {
      continue;
    }

    //Check if the scoring element in the board should be available to select
    if(playersArray[currentPlayerID].scoreAvail[keys[i]])
    {
      document.getElementById(keys[i]).disabled = true;
      document.getElementById(keys[i]).style.color = "white";
      document.getElementById("label" + keys[i]).style.backgroundImage = "url(" + keys[i] + "1.gif)";
    }
    else if(!playersArray[currentPlayerID].scoreAvail[keys[i]])
    {
      document.getElementById(keys[i]).disabled = false;
      document.getElementById("label" + keys[i]).style.backgroundImage = "url(" + keys[i] + "0.gif)";
    }
  }//end for

  //Update subtotal for left-hand side
  document.getElementById("sub").innerHTML = playersArray[currentPlayerID].scoreCard["sub"];

}//end function

//Function for changing the board for the next turn
function nextTurn()
{
  //Update the roll count, internally and on the webpage
  rollCount = 0;
  document.getElementById("rollCount").innerHTML = "Roll Count: 0/3";
  document.getElementById("roll").disabled = false;

  //If first turn, default to player with ID 0 as current player
  if (firstTurn)
  {
    firstTurn = false;
  }
  else
  {
    //Go to next element in the array.
    //If at end of array, mod to loop to beginning of array
    currentPlayerID = playersArray[(currentPlayerID + 1) % playerCount].pID;
  }

  //Update the display with current player's name
  document.getElementById("playerName").innerHTML = "Current Astronaut: " + playersArray[currentPlayerID].name;

  //Uncheck all the boxes
  document.reRollers.reset();

  //Reset the dice
  for (var i = 1; i < 6; i++)
  {
    document.getElementById("img" + i).src = "d0.jpg";
    document.getElementById("d" + i).value = 0;
  }

  //Change the scoreboard
  changeScoreboard(playersArray[currentPlayerID].scoreCard, playersArray[currentPlayerID].scoreAvail);

  //Update the turn count internally
  turnCount++;

  //If the maximum number of turns is reached, determine a winner
  if (turnCount > maxTurnCount)
  {
    determineWinner();
    document.getElementById("roll").disabled = true;
    return;
  }

  //else
  //update turn count on webpage
  document.getElementById("turnCount").innerHTML = "Turn Count: " + turnCount + "/" + maxTurnCount;

  //Clear the board of all non-permanent values
  resetBoard();
}//end function

//function for determining a winner
function determineWinner()
{
  var winningID = null; //id of the winning player
  var topScoreArray = []; //Array to hold scores in event of a tie during comparison
                          //If top score is a tie, the players will be shown
  var tieScore = 0;

  inTie = false; //boolean to tell if in a tie state

  //get all the scores of the players and determine a winner
  for (var i = 0; i < playersArray.length; i++)
  {
    if (winningID == null || playersArray[i].scoreCard["grandTotal"] > playersArray[winningID].scoreCard["grandTotal"])
    {
      winningID = i;
      inTie = false;
      topScoreArray = [];
    }
    else if (playersArray[i].scoreCard["grandTotal"] == playersArray[winningID].scoreCard["grandTotal"])
    {
      if (!inTie)
      {
        inTie = true;
        topScoreArray.push(i);
        topScoreArray.push(winningID);
        tieScore = playersArray[i].scoreCard["grandTotal"];
      }
      else
        topScoreArray.push(i);
    }
  }//end for

  if (!inTie)
  {
    alert("Game over. \nThe winner is " + playersArray[winningID].name + " with a score of " + playersArray[winningID].scoreCard["grandTotal"]);
  }
  else
  {
    winningNames = "";

    for (var i = 0; i < topScoreArray.length; i++)
    {
      winningNames += playersArray[topScoreArray[i]].name + ", ";
    }

    alert("Game over. There was a tie.\n\nThe winners are " + winningNames + "with a tied score of " + tieScore);
  }

  //show the player buttons
  document.getElementById("hiddenButtons").hidden = false;
  document.getElementById("playerName").innerHTML = "Viewing " + playersArray[currentPlayerID].name + "\'s score card";
  gameOver = true;

}//end function

//Function for rolling
function roll()
{
  //Don't allow the player to roll more than three times
  if (rollCount == 3)
  {
    alert("Cannot roll again. Please choose a score.");
    return false;
  }

  //Make sure that they are only allowing a roll if there are dice to roll
  if (inReRoll && getSelected() == null)
  {
    alert("Please deselect at least one die before rolling.");
    return false;
  }

  //Roll the dice and update the roll count
  rollDice();
  if (++rollCount == 3)
  {
    document.getElementById("roll").disabled = true;
  }

  //Delay execution of this method so that the animation has time to play
  setTimeout(function() {displayScore(); }, 3100);
}//end function

//Function for showing the dice roll animation
//Calls appropriate function based on state of player's turn
function rollDice()
{
  //Make sure the player is not in a reRoll state
  if (!inReRoll)
  {
    //roll the dice
    for (var i = 1; i <= 5; i++)
    {
      getRandomDie(i)
    }
    //Change the state of inReRoll to true
    inReRoll = true;
  }
  else
  {
    //Make sure at least one dice is able to roll
    if (getSelected() == null)
      alert("Please deselect at least one die before rolling.");
    else
      reRoll();
  }
}//end function

//Intermediary function that allows for the
  //dice rolling animation
function getRandomDie(i)
{
  var x = 0;
  var intervalID = setInterval(function() {
    getRoll(i);

    if (++x === 10)
    {
      clearInterval(intervalID);
    }
  }, 300);
}//end function

//Function for using RNG to get die value
function getRoll(i)
{
    //Get a random value between 1 and 6
    var dieValue = Math.floor((Math.random() * 6) + 1);

    //Update the images and value sof the dice on the page
    document.getElementById("img" + i).src = "d" + dieValue + ".jpg";
    document.getElementById("d" + i).value = dieValue;
}//end function

//Function for rolling only selected dice
function reRoll()
{
  //Store the selected dice in an array
  var diceArray = getSelected();

  //Iterate through the dice to reRoll them
  for (var i = 0; i < diceArray.length; i++)
  {
    getRandomDie(diceArray[i].value);
  }
}//end function

//Function for getting which dice are selected
function getSelected()
{
  //Get the checkboxes from the html page
  var checkboxes = document.getElementsByName("reRoll");
  var checked = [];

  //Iterate through the checkboxes
  for (var i = 0; i < checkboxes.length; i++)
  {
    //And stick the checked ones onto an array...
    if (!checkboxes[i].checked)
    {
       checked.push(checkboxes[i]);
    }
  }

  //Return the array if it is non-empty, or null
  return checked.length > 0 ? checked : null;
}//end function

function getDiceValues()
{
  diceArray = [];

  //Put all the values of each of the dice elements into the array
  for (var i = 1; i <= 5; i++)
  {
    diceArray.push(document.getElementById("d" + i).value);
  }

  return diceArray;
}//end function

function getTotalValues()
{
  //Store all the dice values in an array
  diceArray = getDiceValues();
  sum = 0; //sum of the dice values

  //Iterate through the dice and add their values together
  for (var i = 0; i < diceArray.length; i++)
  {
    sum += diceArray[i];
  }
  return sum;
}//end function

//function for saving the chosen score
function saveScore(eID, eScore, isLeftHand)
{

  //Force at least 1 roll before allowing the player to choose a score
  if (rollCount == 0)
  {
    return;
  }

  //If the element is disabled, don't allow them to select it
  if (document.getElementById(eID).disabled)
  {
    return;
  }

  //If they chose a blank space, give it a score of 0
  if (eScore === null ||eScore == "")
  {
    eScore = 0;
  }

  //Store the score in the player's score card
  playersArray[currentPlayerID].scoreCard[eID] = eScore;

  //If the element was on the lefthand side of the board, add it to the subtotal
  if (isLeftHand)
  {
    playersArray[currentPlayerID].scoreCard["sub"] = eval(playersArray[currentPlayerID].scoreCard["sub"] + " + " + eScore);
  }

  //Change the element to be unchangeable
  disableElement(eID, "white");

  //Add to the grand total for the player
  if (playersArray[currentPlayerID].scoreCard["grandTotal"] === null)
  {
    playersArray[currentPlayerID].scoreCard["grandTotal"] = playersArray[currentPlayerID].scoreCard[eID];
  }
  else
  {
    playersArray[currentPlayerID].scoreCard["grandTotal"] = eval(playersArray[currentPlayerID].scoreCard["grandTotal"] + " + " + playersArray[currentPlayerID].scoreCard[eID]);
  }

  //Check if bonus is applicable
  if (playersArray[currentPlayerID].scoreCard["sub"] >= 63
  && !playersArray[currentPlayerID].scoreAvail["sub"])
  {
    saveScore("bonus", 35, false);
  }

  //Start the next turn
  nextTurn();
}//end function

//Key Up Listener
//if 'r' is released, roll the dice.
function keyUpEvent(e)
{
  if (e.keyCode == "82" && !gameOver)
  {
    roll();
    getRollCount();
  }
  else
  {
    return;
  }
}
