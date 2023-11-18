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
    <title>Register</title>
</head>
<body>
    <div class="layout">
        <div class="login">
            <form action="Register_Ex.php" method="post" enctype="multipart/form-data" class="reg">
                <h1>Register</h1>
                <input type="text" name="username" placeholder="Personal ID" required maxlength="13">
                <input type="password" name="password" placeholder="Password" required>
                <input type="password" name="password2" placeholder="Confirm Password" required>
                <input type ="file" name="photo" id="file" onchange="loadfile(event)">
                <label for="file">Upload Picture</label>
                <input type="submit" value="register">
                <input type="reset" value="clear">
            </form>
        </div>

        <div class="pic">
            <h1 class="head">Upload Picture</h1>
            <img class="img" id="output" style="width:300px;">
        </div>
    </div>
    

<script>
            var loadfile = function(event){
                var output = document.getElementById('output');
                output.src = URL.createObjectURL(event.target.files[0])
                output.onload = function(){
                    URL.revokeObjectURL(output.src);
                }
            }
</script>

</body>
</html>
