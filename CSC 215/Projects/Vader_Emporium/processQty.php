<?php

  session_start();

  if ($_SESSION["loggedIn"] != true)
  {
    header("Location: index.html");
  }

  function updateQty($db, $product, $qty)
  {

    mysqli_query($db, "UPDATE products SET qtyHand = '".$qty."' where prodNbr = '".$product."';");

  }

  //Server credentials
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

  $result = mysqli_query($con, "SELECT * FROM products;");

  foreach ($_POST as $item)
  {
    $row =mysqli_fetch_assoc($result);

    //Update quantity on hand
    updateQty($con, $row["prodNbr"], $item);

  }//end foreach

  //Close connection
  mysqli_close($con);

  echo "<script>
  \nalert('Your change has been processed.');
  \nwindow.location = 'changeQty.php';</script>";

?>
