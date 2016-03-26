<?php

  $name;
  $email;
  $password;

  $server = "localhost";
  $dbUser = "root";
  $dbPassword = "pokemon1994";
  $database = "Vader";

  //Connect to the database
  $con = mysqli_connect($server, $dbUser, $dbPassword, $database);

  if (mysqli_connect_errno())
  {
    echo "Failed to connect to the ".$database." database: ".mysqli_connect_error();
  }


  $result = mysqli_query($con, "SELECT * FROM customer where username = '".$_POST["username"]."'");

  $row = mysqli_fetch_assoc($result);

  $email = $row["email"];
  $password = $row["password"];

  /*
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

  */
  if ($email != null)
  {
    mail($email, "Vader's Emporium Forgotten Password", $_POST["username"].", your password is ".$password.".");

    //close connection
    mysqli_close($con);

    echo("<script>alert('An email containing your password has been sent')
    \nwindow.location = 'index.html'
    \n</script>");
  }
  else {

    //close connection
    mysqli_close($con);

    echo("<script>alert('Username entered is not valid. Returning to log-in page.')
    \nwindow.location = 'index.html'
    \n</script>");
  }


 ?>
