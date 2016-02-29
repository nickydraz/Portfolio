<html>

  <head>
  </head>

  <body>
    <div id="container">
      <div id="menu">
        <?php include 'header.php'; ?>
      </div>

      <form name="Order" id="Order" action="processOrder.php" method="post">


          <span id="saber" name="orderItem" value="6000">Light Saber -- 6000Cr
          <input style="text-align:right; width:75px;" type="number" min=0 name="saberQty" value="0" /></span><br />
          <span id="rocket" name="orderItem" value="1200">Rocket Launcher -- 1200Cr
          <input style="text-align:right; width:75px;" type="number" min=0 name="rocketQty" value="0" /></span><br />
          <span id="flechette" name="orderItem" value="2000">Flechette Gun -- 2000Cr
          <input style="text-align:right; width:75px;" type="number" min=0 name="flechetteQty" value="0" /></span><br />
          <span id="fusion" name="orderItem" value="800">Fusion Cutter -- 800Cr
          <input style="text-align:right; width:75px;" type="number" min=0 name="fusionQty" value="0" /><span><br />

          <br />
          <br />
          <input type="submit" value="Send Order" />
      </form>
    </div>
  </body>

</html>
