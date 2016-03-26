<html>
  <head>
    <title>Vader's Emporium | Change Quantities</title>
    <link type="text/css" rel="stylesheet" href="emporium.css">
  </head>
<body>
  <div id="container">
    <?php include "header.php";?>
    <h3>Adjust inventory quantities below, then hit 'Submit'</h3>
    <form name="changeQty" id="changeQty" action="processQty.php" method="post">
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
              $supNbr = $row["supNbr"];
              $qty = $row["qtyHand"];
              $result2 = mysqli_query($con, "SELECT products.prodFullName, suppliers.supName FROM products, suppliers where products.prodName = '".$prodName."' and products.supNbr = suppliers.supNbr");
              $supplier = mysqli_fetch_row($result2);
              $supName = $supplier[1];
              echo "<tr>";
              echo "\n<td><span id='".$prodName."' name='orderItem'>".$prodFullName."</td>";
              echo "\n<td>Supplied by ".$supName."</td>";

              echo "\n<td><input style='text-align:right; width:75px;' type='number' min=0 id=\"".$prodName."Qty\" name=\"".$prodName."Qty\" value='".$qty."' /></span></td>";
              echo "\n</tr>\n";

            }

            mysqli_close($con);


           ?>
            <tr>
              <td id="sub" colspan="3"><input type="submit" ></td>
            </tr>

            <tr>
              <td style="text-align:center;" colspan="3"><input style="width: 300px;" type="button" value="Return To Employee Portal" onclick="window.location='info.php'" /></td>
            </tr>
        </table>
    </form>
  </div>
</body>
