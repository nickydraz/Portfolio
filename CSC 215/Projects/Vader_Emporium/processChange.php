<?php
  session_start();

  function updateRow($db, $column, $value)
  {
    mysqli_query($db, "UPDATE customer SET ".$column." = '".$value."' where custNbr = '".$_SESSION["custNbr"]."';");
  }

  //Connect to the database
  $server = "localhost";
  $username = "root";
  $password = "pokemon1994";
  $database = "Vader";

  //Connect to the database
  $con = mysqli_connect($server, $username, $password, $database);

  if (mysqli_connect_errno())
  {
    echo "Failed to connect to the ".$database." database: ".mysqli_connect_error();
  }

  if ($_POST["password"] != "")
  {
    updateRow($con, "password", $_POST["password"]);
  }

  if ($_POST["fname"] != "")
  {
    updateRow($con, "fname", $_POST["fname"]);
  }

  if ($_POST["lname"] != "")
  {
    updateRow($con, "lname", $_POST["lname"]);
  }

  if ($_POST["email"] != "")
  {
    updateRow($con, "email", $_POST["email"]);
  }

  if ($_POST["address"] != "")
  {
    updateRow($con, "physicalAddress", $_POST["address"]);
  }

  //close the connection
  mysqli_close($con);

  header("Location: admin.php");
