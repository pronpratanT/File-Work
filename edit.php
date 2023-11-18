<?php
    session_start();
    include "connect.php";
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Prompt:wght@300&display=swap" rel="stylesheet">
    <title>Document</title>
</head>
<body>
    <div class="login">
        <?php
            $rid = $_POST['rid'];
            $sql = "SELECT * FROM room WHERE RoomID = '$rid'";
            $result = mysqli_query($conn, $sql);
            $rows = mysqli_fetch_array($result);
        ?>
        <form action="update.php" method="post">
            <input type="hidden" name="rid" value="<?php echo $rows['RoomID'];?>">
            <input type="text" placeholder="Electric" name="elec">
            <input type="text" placeholder="Water" name="water">
            <input type="text" placeholder="Status" name="status" value="1">
            <button type="submit" class="btnadmin">Submit</button>
        </form>
    </div>
</body>
</html>