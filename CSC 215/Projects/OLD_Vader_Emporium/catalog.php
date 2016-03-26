<html>

  <head>
    <script>

      //Array for image objects
      images = { "saber.jpg":0, "rocket.jpg":1, "flechette.jpg":2, "fusion.jpg":3 };

      //Array for product information
      productInfo = ["Red Light Saber: 6000Cr", "Rocket Launcher, slightly used: 1200Cr", "Flechette Gun: 2000Cr", "Fusion Cutter: 800Cr"];

      function onclickEvent(imageName, imageID)
      {
        document.getElementById("mainImage").src = imageName;
        alert(imageName);
        document.getElementById("productInfo").innerHTML = productInfo[imageID];
      }
    </script>
  </head>

  <body>
    <div id="container">
      <div id="menu">
        <?php include 'header.php'; ?>
      </div>

      <div id="slideshow">
        <img src="images\saber.jpg" width="500px" height="500px" id="mainImage" ><br />
        <p id="productInfo">Red Light Saber: 6000Cr</p>

        <img src="images\saber.jpg" width="100px" height="100px" id="0" onclick="onclickEvent(this.src, this.id)" >
        <img src="images\rocket.jpg" width="100px" height="100px" id="1" onclick="onclickEvent(this.src, this.id)" >
        <img src="images\flechette.jpg" width="100px" height="100px" id="2" onclick="onclickEvent(this.src, this.id)" >
        <img src="images\fusion.jpg" width="100px" height="100px" id="3" onclick="onclickEvent(this.src, this.id)" >
      </div>
    </div>
  </body>

</html>
