<?php
    $username = "root";
    $password = "";

    try{
        $conn = mysqli_connect("localhost","root","","registration");
        //echo "Connection successfully";
    }catch(PDOException $e){
        echo "Connection failed:". $e->getmessage();
    }
?>