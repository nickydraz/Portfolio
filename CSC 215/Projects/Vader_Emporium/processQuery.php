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

   $result = mysqli_query($con, $_POST["query"]);

   if (!$result)
   {
     echo "<script>alert('Query failed. Please try again'); window.history.back();</script>";
   }

 ?>



 <html>
   <head>
     <title>Vader's Emporium | Query Result</title>
     <link type="text/css" rel="stylesheet" href="emporium.css">
   </head>
   <body>
     <div id="container">
       <div id="menu">
         <?php include 'header.php'; ?>
       </div>
       <br />
       <h3>Custom Query</h3>
       <br />
       <table id="DBTable">
         <tr>
           <?php
              $names = mysqli_fetch_fields($result);


              foreach ($names as $tuple)
              {
                echo "<th>".$tuple->name."</th>";
              }
           ?>
         </tr>
         <?php


          while ($row = mysqli_fetch_assoc($result))
          {
            echo "\n<tr>";

            foreach ($row as $tuple)
            {
              echo "\n<td style='text-align:center;'>".$tuple."</td>";
            }

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
