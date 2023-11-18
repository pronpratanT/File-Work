<?php
    session_start();
    include "connect.php";
?>
<!DOCTYPE html>
<htm lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Prompt:wght@300&display=swap" rel="stylesheet">
    <title>Admin</title>
</head>
<body>
    <?php
        $room = "SELECT * FROM room where status = '1'";
        $showroom = mysqli_query($conn,$room);
        //$fetch = mysqli_fetch_row($showroom);
        $count = mysqli_num_rows($showroom);
        //echo $count;
        //print_r($fetch);
    ?>
    <div class="exportadmin fontthai">
        <div class="show1">
            <h1>หมายเลขห้อง</h1>
        </div>
        <div class="show1">
            <h1>ค่าไฟ</h1>
        </div>
        <div class="show1">
            <h1>ค่าน้ำ</h1>
        </div>
        <div class="show1">
            <h1>สถานะ</h1>
        </div>
        <div class="show1">
            <h1>ราคา</h1>
        </div>
        <div class="show1">
            <h1>แก้ไข</h1>
        </div>
    </div>
    <?php
        for($i=0;$i<$count;$i++){ 
            $fetch = mysqli_fetch_row($showroom);
    ?>
    <div class="adminroom">
        <div class="roomid">
            <h1>
                <?php echo $fetch[0];?>
            </h1>    
        </div>
        <div class="roomelec">
            <h1>
                <?php echo $fetch[2];?>
            </h1>    
        </div>
        <div class="roomwater">
            <h1>
                <?php echo $fetch[3];?>
            </h1>    
        </div>
        <div class="roomtype">
            <h1>
                <?php echo $fetch[1];?>
            </h1>  
        </div>
            <div class="roomprice">
                <h1>
                    <?php echo $fetch[5];?>
                </h1>  
            </div>
            <div class="rent">
                <form action="edit.php" method="POST">
                    <input type="hidden" name="rid" value="<?php echo $fetch[0]; ?>">
                    <button class="btnadmin" type="submit">Edit</button>
                </form>
            </div>
        </div>
    <?php
        };
    ?>
</body>
</htm