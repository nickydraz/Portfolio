<?php
  session_start();

  if ($_SESSION["loggedIn"] != true)
  {
    header("Location: index.html");
  }
?>
<html>
  <head>
    <title>Vader's Emporium | Orders Listing</title>
    <link type="text/css" rel="stylesheet" href="emporium.css">
  </head>
  <body>
    <div id="container">
      <div id="menu">
        <?php include 'header.php'; ?>
      </div>
      <br />
      <h3>Order Table</h3>
      <br />
      <table id="DBTable">
        <tr>
          <th>Order#</th>
          <th>Order Date</th>
          <th>Customer#</th>
          <th>Product#</th>
          <th>Quantity Ordered</th>
        </tr>
        <?php

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

          $result = mysqli_query($con, "SELECT * FROM orders");

          while ($row = mysqli_fetch_assoc($result))
          {
            echo "\n<tr>";

            echo "<td style='text-align:center;'>".$row["orderNbr"]."</td>";
            echo "<td>".$row["orderDate"]."</td>";
            echo "<td style='text-align:center;'>".$row["custNbr"]."</td>";
            echo "<td style='text-align:center;'>".$row["prodNbr"]."</td>";
            echo "<td style='text-align:center;'>".$row["quantity"]."</td>";

            echo "</tr>";
          }

          //Close the connection
          mysqli_close($con);
         ?>
        <tr>
          <td style="text-align:center;" colspan="5"><input style="width: 300px;" type="button" value="Return To Employee Portal" onclick="window.location='info.php'" /></td>
        </tr>
      </table>
    </div>
  </body>
</html>
