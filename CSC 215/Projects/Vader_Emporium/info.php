<?php
  session_start();

  if ($_SESSION["loggedIn"] != true)
  {
    header("Location: index.html");
  }

  if ($_SESSION["isEmployee"] != 1)
  {
    header("Location: catalog.php");
  }
?>
<html>
  <head>
    <title>Vader's Emporium | Employee Portal</title>
    <link type="text/css" rel="stylesheet" href="emporium.css">
    <script>
      function validateQuery()
      {
        query = document.getElementById('query').value;

        if (query.substr(0, 6) == "SELECT" || query.substr(0, 6) == "select" || query.substr(0, 6) == "Select")
        {

          return true;
        }

        return false;
      }
    </script>
  </head>
  <body>
    <div id="container">
      <div id="menu">
        <?php include 'header.php'; ?>
      </div>
      <h3>Click the button next to the appropriate action to open the desired form</h3>
      <h4>Alternatively, type your own SELECT statement into the box at the bottom</h4>
      <table>
        <tr>
          <td>Display Products</td>
          <td><input type="button" value="-->" onclick="window.location='displayProducts.php'"/></td>
        </tr>
        <tr>
          <td>Display Orders</td>
          <td><input type="button" value="-->" onclick="window.location='displayOrders.php'"/></td>
        </tr>
        <tr>
          <td>Display Customers</td>
          <td><input type="button" value="-->" onclick="window.location='displayCustomers.php'"/></td>
        </tr>
        <tr>
          <td>Adjust Quantity On Hand</td>
          <td><input type="button" value="-->" onclick="window.location='changeQty.php'"/></td>
        </tr>
        <tr>
          <form id="queryForm" name="queryForm" onsubmit="return validateQuery()" action="processQuery.php" method="post">
          <td>User Query</td>
          <td><input type="text" style="text-align:center; width: 200px;" size="500" value="" name="query" id="query" placeholder="SELECT * FROM customers;" /></td>
          <td><input type="submit" id="sub" value="Submit Query"/></td>
          </form>
        </tr>
      </table>
    </div>
  </body>
</html>
