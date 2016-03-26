<?php
  session_start();

  if ($_SESSION["loggedIn"] != true)
  {
    header("Location: index.html");
  }
?>
<html>
  <head>
    <title>Vader's Emporium | Products Listing</title>
    <link type="text/css" rel="stylesheet" href="emporium.css">
  </head>
  <body>
    <div id="container">
      <div id="menu">
        <?php include 'header.php'; ?>
      </div>
      <br />
      <h3>Product Table</h3>
      <br />
      <table id="DBTable">
        <tr>
          <th>Product#</th>
          <th>Product Name</th>
          <th>Product Full Name</th>
          <th>Supplier#</th>
          <th>Quantity On Hand</th>
          <th>Price</th>
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

          $result = mysqli_query($con, "SELECT * FROM products");

          while ($row = mysqli_fetch_assoc($result))
          {
            echo "\n<tr>";

            echo "<td style='text-align:center;'>".$row["prodNbr"]."</td>";
            echo "<td>".$row["prodName"]."</td>";
            echo "<td>".$row["prodFullName"]."</td>";
            echo "<td style='text-align:center;'>".$row["supNbr"]."</td>";
            echo "<td style='text-align:center;'>".$row["qtyHand"]."</td>";
            echo "<td>".$row["price"]."</td>";

            echo "</tr>";
          }

          //Close the connection
          mysqli_close($con);
         ?>
        <tr>
          <td style="text-align:center;" colspan="6"><input style="width: 300px;" type="button" value="Return To Employee Portal" onclick="window.location='info.php'" /></td>
        </tr>
      </table>
    </div>
  </body>
</html>
