<?php


  #Open the file
  $fp = fopen("users.csv", "r");

  while (!feof($fp))
  {
    #Get the next line from the file
    $loginLine = fgetcsv($fp, 1024);

    #Check username
    if ($_POST["username"] == $loginLine[0])
    {
      #Check password
      if ($_POST["password"] == $loginLine[1])
      {
        header('Location: catalog.php');
      }
    }

  }

  echo "<br />Finished reading file. No match found.<br/>";
  echo "<input type='button' value='Return' onclick='window.location = \"index.html\"' >";

?>
