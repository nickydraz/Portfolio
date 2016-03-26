<?php

  #Build the order string, which will be emailed to user
  $orderString = "";
  $orderTotal = 0;

  #Quantity checks
  if ($_POST["saberQty"] > 0)
  {
    $orderString .= "Light Saber x".$_POST["saberQty"]." ";
    $orderTotal += 6000 * $_POST["saberQty"];
  }
  if ($_POST["rocketQty"] > 0)
  {
    $orderString .= "Rocket Launcher x".$_POST["rocketQty"]." ";
    $orderTotal += 1200 * $_POST["rocketQty"];
  }
  if ($_POST["flechetteQty"] > 0)
  {
    $orderString .= "Flechette Gun x".$_POST["flechetteQty"]." ";
    $orderTotal += 2000 * $_POST["flechetteQty"];
  }
  if ($_POST["fusionQty"] > 0)
  {
    $orderString .= "Fusion Cutter x".$_POST["fusionQty"]." ";
    $orderTotal += 800 * $_POST["fusionQty"];
  }


  #open the orders text file so that the new order can be added
  $fp = fopen("orders.txt", a);

  #Get the current date
  $mydate=getdate(date("U"));

  #Insert the information into the file
  fputs($fp, $orderString." - - - Total is: ".$orderTotal."Cr. Order placed on ".$mydate[weekday].", ".$mydate[month].
  " ".$mydate[mday]." ".$mydate[year].".\n");

  fclose($fp);

  header("Location:catalog.php");



 ?>
