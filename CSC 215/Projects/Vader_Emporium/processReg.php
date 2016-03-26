<?php

#Open the file
$fp = fopen("users.csv", "r");
#get the column header row
$loginLine = fgetcsv($fp, 1024);
$exists = false;
while (!feof($fp))
{
  #Get the next line from the file
  $loginLine = fgetcsv($fp, 1024);

  #Check username
  if ($_POST["username"] == $loginLine[0])
  {
    echo("<script>alert('User already exists; returning to registration page.');");
    echo("\nwindow.history.back();");
    echo("</script>");
    $exists = true;


  }

}

  if(!$exists)
  {
    $fp = fopen("users.csv", "a");

    fputcsv($fp, array($_POST["username"], $_POST["password"], $_POST["name"],
      $_POST["email"], $_POST["address"]));
    fclose($fp);

    header('Location: catalog.php');
  }

?>
