<?php
    session_start();
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style.css">
    <title>Menu</title>
</head>
<body>
    <?php
        if(isset($_SESSION["login"])){
    ?>
    <div class="show">
        <h2 class="title">Menu</h2><br>
        <a href="show_info.php">Room</a><br>
        <a href="logout.php">logout</a>
    </div>
    
    <?php
            }else{
    ?>
        <script>window.location = 'login.html'; </script>
    <?php
            }
    ?>
</body>
</html>
