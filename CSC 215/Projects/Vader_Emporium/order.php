<?php
  session_start();

  if ($_SESSION["loggedIn"] != true)
  {
    header("Location: index.html");
  }
?>

<html>
  <head>
    <link rel="stylesheet" type="text/css" href="emporium.css">

    <script>
      function validate()
      {
        items = document.Order;
        formFilled = false;

        for (var i = 0; i < items.length; i++)
        {
          if (items[i].value > 0)
          {
            formFilled = true;
            break;
          }
        }

        if (formFilled)
        {
          return true;
        }
        else
        {
          alert("Please choose at least one item to place an order");
          return false;
        }
      }//end validate

      function qtyListener(id, max, qty)
      {
        if (parseInt(max) < parseInt(qty))
        {
          //alert('You have entered a quantity that is higher than our current stock. Changing entered quantity.');
          document.getElementById(id).value = max;
        }
      }
    </script>
  </head>

  <body>
    <div id="container">
      <?php include "header.php";?>
      <h3>Please fill out the form below to place your order</h3>
      <form name="Order" id="Order" onsubmit="return validate()"action="processOrder.php" method="post">
          <table>

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
                $prodFullName = $row["prodFullName"];
                $prodName = $row["prodName"];
                $price = $row["price"];
                $supNbr = $row["supNbr"];
                $qty = $row["qtyHand"];
                $result2 = mysqli_query($con, "SELECT products.prodFullName, suppliers.supName FROM products, suppliers where products.prodName = '".$prodName."' and products.supNbr = suppliers.supNbr");
                $supplier = mysqli_fetch_row($result2);
                $supName = $supplier[1];
                echo "<tr>";
                echo "\n<td><span id='".$prodName."' name='orderItem' value='".$price."'>".$prodFullName." -- ".$price."Cr</td>";
                echo "\n<td>Supplied by ".$supName."</td>";
                if ($row["qtyHand"] < 1)
                {
                  echo "\n<td style='color: red;'>ITEM OUT OF STOCK</span></td>";
                }
                else
                {
                  echo "\n<td><input style='text-align:right; width:75px;' type='number' min=0 max=".$qty." id=\"".$prodName."Qty\" name=\"".$prodName."Qty\" value=0 onkeyup=\"qtyListener(this.id, this.max, this.value)\" /></span></td>";
                }

                echo "\n</tr>\n";

              }

              mysqli_close($con);


             ?>
              <tr>
                <td id="sub" colspan="3"><input type="submit" value="Send Order" ></td>
              </tr>


          </table>
      </form>
    </div>
  </body>

</html>
