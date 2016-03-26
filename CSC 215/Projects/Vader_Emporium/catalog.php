<?php
  session_start();

  if ($_SESSION["loggedIn"] != true)
  {
    header("Location: index.html");
  }
?>

<html>
  <head>
    <link type="text/css" rel="stylesheet" href="emporium.css">
    <script>


      // =======================================
      // set the following variables
      // =======================================

      //Variable to determine slideshow state
      //Default to false so SS plays
      pause = false;
      // Set speed (milliseconds)
      var speed = 3000

      //Array for image objects
      images = { "saber.jpg":0, "rocket.jpg":1, "flechette.jpg":2, "fusion.jpg":3 };

      //Array for product information
      productInfo = ["Red Light Saber: 6000Cr", "Rocket Launcher, slightly used: 1200Cr", "Flechette Gun: 2000Cr", "Fusion Cutter: 800Cr"];

      var Pic = ["images/saber.jpg", "images/rocket.jpg", "images/flechette.jpg", "images/fusion.jpg"];
      // =======================================

      var t
      var j = 0
      var p = Pic.length

      var preLoad = new Array()
      for (i = 0; i < p; i++){
       preLoad[i] = new Image()
       preLoad[i].src = Pic[i]
      }

      function runSlideShow()
      {
        if(!pause)
        {
         document.getElementById("pause").innerHTML = "&#10074;&#10074;"
         document.images.mainImage.src = preLoad[j].src
         document.getElementById("productInfo").innerHTML = productInfo[j]
         j = j + 1
         if (j > (p-1)) j=0
         t = setTimeout('runSlideShow()', speed)
        }
        else
        {
          document.getElementById("pause").innerHTML = "&#9658;"
        }
      }

      function onclickEvent(imageName, imageID)
      {
        j = j + 1
        if (j > (p-1))
        {
          j = 0
        }

        document.images.mainImage.src = preLoad[j].src;
        document.getElementById("productInfo").innerHTML = productInfo[j];
        setPause(true);
      }

      function setPause(isPaused)
      {
        if (isPaused === undefined)
        {
          isPaused = !pause
        }
        pause = isPaused;
        runSlideShow();
      }
    </script>
  </head>

  <body onload="runSlideShow()">
    <div id="container">
      <div id="menu">
        <?php include 'header.php'; ?>
      </div>
      <br />
      <p>Go to our <a href="order.php">orders page</a> to place an order!</p>
      <br />
      <div id="slideshow">
      <img width="500px" height="500px" id="mainImage" >
        <br />
          <button id="prev" onclick="onclickEvent()">&lt;-</button>
          <button id="pause" onclick="setPause()">&#10074;&#10074;</button>
          <button id="next" onclick="onclickEvent()">-&gt;</button>
          <br />
        <p style="font-size: 28;" id="productInfo"></p>
      </div>
    </div>
  </body>

</html>
