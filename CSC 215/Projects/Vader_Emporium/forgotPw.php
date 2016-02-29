<?php


  $username = $_POST["username"];
  $name;
  $email;
  $password;

  $fp = fopen("users.csv", r);

  //Open database file
  while (!feof($fp))
  {
    #Get the next line from the file
    $loginLine = fgetcsv($fp, 1024);

    #Check username
    if ($_POST["username"] == $loginLine[0])
    {
      $password = $loginLine[1];
      $name = $loginLine[2];
      $email = $loginLine[3];
    }
  }

  if ($email != null)
  {
    mail($email, "Vader's Emporium Forgotten Password", $name." (username: )".$username.", your password is ".$password.".");

    fclose($fp);
    header("Location: index.html");
  }


 ?>
