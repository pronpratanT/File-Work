<?php
    session_start();
    include "connect.php";
    $username = $_POST['username'];
    $password = $_POST['password'];
    $password2 = $_POST['password2'];
    $file = $_FILES['photo']['name'];
    $tmp_name = $_FILES['photo']['tmp_name'];
    move_uploaded_file($tmp_name,"img/".$file);
    $enc_pass = "";
    if($password === $password2){
        $enc_pass = md5($password);
    }else{
        echo "<script type='text/javascript'>alert('Comfirm Password is not correct.');</script>";
        header("Refresh: 1; Register.php");
    }

    $sql = "SELECT * FROM user where personalID = '$username'";
    $result_count = mysqli_query($conn,$sql);
    $count = mysqli_num_rows($result_count);
    if ($count == 0){
        $sql1 = "INSERT INTO user (personalID,password,pic) values ('$username','$enc_pass','$file')";
        $result = mysqli_query($conn,$sql1);
        header("Refresh: 1; Login.html");
    }else{
        echo "<script type='text/javascript'>alert('Already have username');</script>";
        header("Refresh: 1; Register.php");
    }
?>