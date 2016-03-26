<?php
  session_start();

  //Connect to the database
  $server = "localhost";
  $username = "root";
  $password = "root";
  $database = "Vader";

  //Connect to the database
  $con = mysqli_connect($server, $username, $password, $database);

  if (mysqli_connect_errno())
  {
    echo "Failed to connect to the ".$database." database: ".mysqli_connect_error();
  }

  #Store input in variables for easier access
  $pieces = explode(" ", $_POST["name"]);
  $username = $_POST["username"];
  $password = $_POST["password"];
  $email = $_POST["email"];
  $address = $_POST["address"];
  echo "INSERT INTO Vader.customer (fname, lname, username, password, email, physicalAddress) VALUES ('".$_POST['name']."', '".$_POST['name']."', '".$username."', '".$password."','".$email."', '".$address."');";

  #Insert the user into the DB
  mysqli_query($con, "INSERT INTO Vader.customer (fname, lname, username, password, email, physicalAddress) VALUES ('".$_POST['name']."', '".$_POST['name']."', '".$username."', '".$password."','".$email."', '".$address."');");

  #Pull the user's info from the DB, store in Session variables.
  ## Pulling to ensure all data fields are stored accurately, rather than reusing the input from before
  $result = mysqli_query($con, "SELECT * FROM customer where username = '".$username."'");

  while ($row = mysqli_fetch_assoc($result))
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

  //close the connection
  mysqli_close($con);

  #Send the user to the main store page
  header("Location: catalog.php");
