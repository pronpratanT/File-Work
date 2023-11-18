<?php
    session_start();
    include "connect.php";
    $username = $_POST['username'];
    $password = $_POST['password'];
    $enc_pass = md5($password);
    $sql = "SELECT * from user where personalID='$username'";
    $result = mysqli_query($conn,$sql);
    $rows = mysqli_fetch_row($result);
    $count = mysqli_num_rows($result);
    if ($count == 1){
        if ($username == $rows[1]){
            if ($enc_pass === $rows[2]){
                if($rows[5] == 2){
                    header("Refresh: 1; admin.php");
                }else{
                    $_SESSION['login'] = "success";
                    $_SESSION['showuser'] = $rows[0];
                    echo "<script type='text/javascript'>alert('Login Complete');</script>";
                    header("Refresh: 1; menu.php");
                }
            }
            else{
                echo "<script type='text/javascript'>alert('password is wrong');</script>";
                header("Refresh: 1; login.html");
            }
        }
        else{
            echo "<script type='text/javascript'>alert('Username/password is wrong');</script>";
        header("Refresh: 1; login.html");
        }
    }
    else{
        echo "<script type='text/javascript'>alert('Username/password is wrong');</script>";
        header("Refresh: 1; login.html");
    }
?>