<?php
    session_start();
    include "connect.php";
    $file = $_FILES['photo']['name'];
    $tmp_name = $_FILES['photo']['tmp_name'];
    move_uploaded_file($tmp_name,"img/slip/".$file);
    echo "<script type='text/javascript'>alert('ชำระเงินเสร็จสิ้น');</script>";
        header("Refresh: 1; show_info.php");
?>