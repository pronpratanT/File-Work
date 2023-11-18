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
    <title>Document</title>
</head>
<body>
    <?php
        $rid = $_POST['rid'];
        $sql = "SELECT * FROM room WHERE RoomID = '$rid'";
        $result = mysqli_query($conn, $sql);
        $count = mysqli_num_rows($result);
        $rows = mysqli_fetch_array($result);
    ?>
    <div class="layout">
        <div class="login">
            <div class="show">
                <h1>
                    RoomID :
                    <?=$rows['RoomID']; ?>
                </h1>
                <h1>
                    Type :
                    <?=$rows['type']; ?>
                </h1>
                <h1>
                    Price :
                    <?=$rows['price']; ?>
                </h1>
                <form action="slip_upload.php" method="POST" enctype="multipart/form-data">
                    <input type="hidden" name="rid" value="<?php echo $rows['RoomID'];?>">
                    <input type="text" placeholder="Personal ID" name="pid" required maxlength="13">
                    <input type ="file" name="photo" id="file" onchange="loadfile(event)">
                    <label for="file">Upload Picture</label>
                    <button type="submit" class="btsubmit">Submit</button>
                </form>
            </div>
        </div>
        <div class="pic">
            <h1>Upload Slip</h1>
            <img class="img" id="output" style="width:300px;">
            <h1 class="fontthai">โอนเงินมัดจำ 2 เดือน<br>จำนวน
            <?php echo (int)$rows['price'] * 2?>
             บาท</h1>
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