<?php
  session_start();

  $name = $_SESSION["fName"]. " ".$_SESSION["lName"];
  $email = $_SESSION["email"];
  $com = $_POST["feedback"];

  mail("vader@darkside.com", "Feedback from ".$name, $com, "From: ".$email."\r\n");

  echo ("<script>alert('Thank you for your feedback, returning you to the catalog page.');
  \ndocument.location = 'catalog.php'</script>");

?>
