<?php
  session_start();

  if ($_SESSION["loggedIn"] != true)
  {
    header("Location: index.html");
  }

?>

<html>

  <head>
    <title>Vader's Emporium | Admin</title>
    <link rel="stylesheet" type="text/css" href="emporium.css">
  </head>

  <body>
    <div id="container">
      <?php include "header.php";?>
      <?php

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

        $result = mysqli_query($con, "SELECT * FROM customer where custNbr = '".$_SESSION["custNbr"]."';");

        $row = mysqli_fetch_assoc($result);

        //Display the user's information
        echo "<p>Username - ".$row["username"]."</p>";
        echo "<p>Password - ".$row["password"]."</p>";
        echo "<p>Name - ".$row["fname"]." ".$row["lname"]."</p>";
        echo "<p>Email Address - ".$row["email"]."</p>";
        echo "<p>Shipping Address - ".$row["physicalAddress"]."</p>";

        //Close connection
        mysqli_close($con);
       ?>

       <br />
       <br />
       <form name="changeInfo" action="processChange.php" method="post">
         <table>
           <tr>
             <td><label for="password">Password: </label></td>
             <td><input type="password" name="password" value="" ></td>
           </tr>
           <tr>
             <td><label for="fname">First Name: </label></td>
             <td><input type="text" name="fname" value="" ></td>
           </tr>
           <tr>
             <td><label for="lname">Last Name: </label></td>
             <td><input type="text" name="lname" value="" ></td>
           </tr>
           <tr>
             <td><label for="email">Email Address: </label></td>
             <td><input type="text" name="email" value="" placeholder="email@domain.com" ></td>
           </tr>
           <tr>
             <td><label for="address">Physical Address: </label></td>
             <td><input type="text" name="address" value="" placeholder="# Street; City; State"></td>
           </tr>
           <tr>
             <td id="sub" colspan="2"><input type="submit"></td>
           </tr>
       </table>
    </div>
  </body>


</html>
