<?php
    session_start();
    include "connect.php";
    $file = $_FILES['photo']['name'];
    $tmp_name = $_FILES['photo']['tmp_name'];
    move_uploaded_file($tmp_name,"img/slip/".$file);
    $rid = $_POST['rid'];
    $pid = $_POST['pid'];
    $update_room = "UPDATE room SET status = '1' WHERE roomID = '$rid'";
    $update_user = "UPDATE user SET currentroom = '$rid' WHERE personalID = '$pid'";
    mysqli_query($conn, $update_room);
    mysqli_query($conn, $update_user);
    header("Refresh: 1; show_info.php");
?>