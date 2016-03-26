<?php

  session_start();

  if ($_SESSION["loggedIn"] != true)
  {
    header("Location: index.html");
  }

  function updateQty($db, $product, $qty)
  {
    mysqli_query($db, "UPDATE products SET qtyHand = qtyHand -".$qty." where prodName = '".$product."';");
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


  $orderString = "**** BEGIN ORDER ****\n";
  $orderTotal = 0;

  $result = mysqli_query($con, "SELECT * FROM products;");

  foreach ($_POST as $item)
  {
    $row =mysqli_fetch_assoc($result);

    if ($item > 0)
    {
      $orderString .= $row["prodName"]." x".$item."\n";
      $orderTotal += $row["price"] * $item;

      //Update quantity on hand
      updateQty($con, $row["prodName"], $item);

      //Update orders table
      mysqli_query($con, "INSERT INTO orders (orderDate, custNbr, prodNbr, quantity) VALUES (CURRENT_TIMESTAMP, ".$_SESSION['custNbr'].",".$row['prodNbr'].",".$item.")");
    }

  }//end foreach

  //Close connection
  mysqli_close($con);

  $mydate=getdate(date("U"));
  $orderString .= "Total is: ".$orderTotal."Cr. \nOrder placed by ".$_SESSION["username"]." on ".$mydate[weekday].", ".$mydate[month].
  " ".$mydate[mday]." ".$mydate[year].".\n**** END ORDER ****\n";

  mail($_SESSION["email"], "Your Recent Purchase From Darth Vader's Emporium", $orderString);

  echo("<script>alert('Your order has been submitted. We will begin processing your order soon')
  \nwindow.location = 'catalog.php'
  \n</script>");


 ?>
