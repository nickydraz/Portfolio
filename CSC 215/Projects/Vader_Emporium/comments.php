<?php session_start(); ?>
<html>
  <head>
    <title>Vader's Emporium | Comments</title>
    <link type="text/css" rel="stylesheet" href="emporium.css">
    <script>

      function verifyForm()
      {
        noError = true;
        if (document.getElementById("name").value == "")
        {
          document.getElementById("name").style.border = "solid 1px red";
          noError = false;
        }
        if (document.getElementById("email").value == "")
        {
          document.getElementById("email").style.border = "solid 1px red";
          noError = false;
        }
        if (document.getElementById("feedback").value == "")
        {
          document.getElementById("feedback").style.border = "solid 1px red";
          noError = false;
        }

        return noError;
      }
    </script>
  </head>

  <body>
    <div id="container">
      <?php include "header.php"; ?>
      <h2>Send Us Your Comments</h2>
      <p>Fill out the form below, then hit 'Send Feedback' to transmit your communications to the Sith Lord.</p>
      <div id="form">
        <form name="comments" id="comments" onsubmit="return verifyForm()" action="processComments.php" method="post">
          <p>Your name: <?php echo($_SESSION["fName"]. " ".$_SESSION["lName"]); ?></p>

          <p>Your email address: <?php echo($_SESSION["email"]); ?></p>

          <p>Your feedback:<br/>
          <textarea name="feedback" id="feedback" rows="8" cols="40" wrap="virtual" value=""/></textarea></p>

          <p><input type="submit" value="Send feedback" /></p>
        </form>
      </div>
    </div>
  </body>

</html>
