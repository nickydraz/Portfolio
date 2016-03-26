<?php
  session_start();

  $server = "#";
  $username = "#";
  $password = "#";
  $database = "Vader";


  //Connect to the database
  $con = mysqli_connect($server, $username, $password, $database);

  if (mysqli_connect_errno())
  {
    echo "Failed to connect to the ".$database." database: ".mysqli_connect_error();
  }
  else {
    echo "Connected to database";
  }

  //else
  $result = mysqli_query($con, "SELECT * FROM customer where username = '".$_POST["username"]."'");

  while ($row = mysqli_fetch_assoc($result))
  {
    if ($_POST["password"] == $row["password"])
    {
      $_SESSION["custNbr"] = $row["custNbr"];
      $_SESSION["username"] = $row["username"];
      $_SESSION["loggedIn"] = true;
      $_SESSION["fName"] = $row["fname"];
      $_SESSION["lName"] = $row["lname"];
      $_SESSION["email"] = $row["email"];
      $_SESSION["address"] = $row["physicalAddress"];
      $_SESSION["isEmployee"] = $row["isEmployee"];
      echo "<script>window.location = 'catalog.php'</script>";
    }
  }

  if($_SESSION["loggedIn"] == null)
  {
    echo("<script>alert('Incorrect username or password. Returning to log-in page; please try again');");
    echo("\ndocument.location = 'index.html'");
    echo("</script>");
  }

  //Close the connection
  mysqli_close($con);
?>
