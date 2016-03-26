<?php
  session_start();

  if ($_SESSION["loggedIn"] != true)
  {
    header("Location: index.html");
  }
?>
<html>
  <head>
    <title>Vader's Emporium | Customer Listing</title>
    <link type="text/css" rel="stylesheet" href="emporium.css">
  </head>
  <body>
    <div id="container">
      <div id="menu">
        <?php include 'header.php'; ?>
      </div>
      <br />
      <h3>Customer Table</h3>
      <br />
      <table id="DBTable">
        <tr>
          <th>Customer#</th>
          <th>Customer First Name</th>
          <th>Customer Last Name</th>
          <th>Username</th>
          <th>Password</th>
          <th>Email</th>
          <th>Address</th>
          <th>Is Employee (0 -> No; 1 -> Yes)</th>
        </tr>
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

          $result = mysqli_query($con, "SELECT * FROM customer");

          while ($row = mysqli_fetch_assoc($result))
          {
            echo "\n<tr>";

            echo "<td style='text-align:center;'>".$row["custNbr"]."</td>";
            echo "<td>".$row["fname"]."</td>";
            echo "<td>".$row["lname"]."</td>";
            echo "<td>".$row["username"]."</td>";
            echo "<td>".$row["password"]."</td>";
            echo "<td>".$row["email"]."</td>";
            echo "<td>".$row["physicalAddress"]."</td>";
            echo "<td style='text-align: center;'>".$row["isEmployee"]."</td>";

            echo "</tr>";
          }

          //Close the connection
          mysqli_close($con);
         ?>
        <tr>
          <td style="text-align:center;" colspan="8"><input style="width: 300px;" type="button" value="Return To Employee Portal" onclick="window.location='info.php'" /></td>
        </tr>
      </table>
    </div>
  </body>
</html>
