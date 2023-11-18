<?php
    include "connect.php";
    $rid = $_POST['rid'];
    $water = $_POST['water'];
    $elec = $_POST['elec'];
    $status = $_POST['status'];
    $sql = "UPDATE room SET electric = '$elec', water = '$water', status = '$status' WHERE RoomID = '$rid'";
    if (mysqli_query($conn,$sql)){
        echo "<script type='text/javascript'>alert('Update Successfully');</script>";
        header("Refresh: 1; admin.php");
    }else{
        echo "<script type='text/javascript'>alert('Update Uncomplete');</script>";
        header("Refresh: 1; admin.php");
    }
?>