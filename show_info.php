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
    <title>Room</title>
</head>
<body>
    <div class="layout">
    <div class="login">
        <?php
            $inuser = $_SESSION['showuser'];
            $sql = "SELECT * FROM user where userID = '$inuser'";
            $result = mysqli_query($conn,$sql);
            $rows = mysqli_fetch_row($result);
            
            if(isset($_SESSION["login"])){
        ?>
            <div class="show">
                <h1>Username  <?php echo $rows[1]; ?></h1>
                <img src="img/<?php echo $rows[3] ?>" style="width: 300px" alt="">
                <a href="logout.php">logout</a>
            </div>
        <?php
            }else{
        ?>
            <script>
                window.location = 'login.html';
            </script>
        <?php
            }
        ?>
    </div>
    <div class="pic">
            <h1 class="head">Information Room</h1>
            <?php
                //echo $rows[4];
                if($rows[4] == 0){ ?>
                    <h1 class="fontthai">ยังไม่ได้เข้าพัก</h1>
                <?php }else{ 
                    $currentroom = $rows[4];
                    $sql = "SELECT * from room where RoomID = '$currentroom'";
                    $result = mysqli_query($conn,$sql);
                    $rows = mysqli_fetch_row($result);
                    ?>
                    <h1 class="fontthai">RoomID <?php echo $rows[0]?></h1>
                    <h1 class="fontthai">ค่าไฟ <?php echo $rows[2]?></h1>
                    <h1 class="fontthai">ค่าน้ำ <?php echo $rows[3]?></h1>
                    <h1 class="fontthai">ค่าห้อง <?php echo $rows[5] ?></h1>
                <?php }?>   
    </div>
    </div>
    <?php
        if($rows[4] == 0){
            ?>
                <div class="room">
                <div class="exportroom">
                    <div class="show1">
                        <h1>หมายเลขห้อง</h1>
                    </div>
                    <div class="show1">
                        <h1>สถานะ</h1>
                    </div>
                    <div class="show1">
                        <h1>ประเภท</h1>
                    </div>
                    <div class="show1">
                        <h1>ราคา</h1>
                    </div>
                    <div class="show1">
                        <h1>จอง</h1>
                    </div>
        </div>
        <?php
                $room = "SELECT * FROM room where status = '0'";
                $showroom = mysqli_query($conn,$room);
                //$fetch = mysqli_fetch_row($showroom);
                $count = mysqli_num_rows($showroom);
                //echo $count;
                //print_r($fetch);
            for($i=0;$i<$count;$i++){ 
                $fetch = mysqli_fetch_row($showroom);
            ?>
                <div class="exportroom">
                    <div class="roomid">
                        <h1>
                            <?php echo $fetch[0];?>
                        </h1>    
                    </div>
                    <div class="roomstatus">
                        <h1>
                            <?php 
                                if($fetch[1] == "0"){
                                    echo "ว่าง";
                                }
                            ?>
                        </h1>  
                    </div>
                    <div class="roomtype">
                        <h1>
                            <?php echo $fetch[4];?>
                        </h1>  
                    </div>
                    <div class="roomprice">
                        <h1>
                            <?php echo $fetch[5];?>
                        </h1>  
                    </div>
                    <div class="rent">
                        <form action="Rent_room.php" method="POST">
                            <input type="hidden" name="rid" value="<?php echo $fetch[0]; ?>">
                            <button class="btnrent" type="submit">จองห้องพัก</button>
                        </form>
                    </div>
                </div>
            <?php
                };
            ?>
        <?php }else{ ?>
            <div class="layout" style="margin: 20px">
            <div class="login">
                <form action="month_slip.php" method="post" enctype="multipart/form-data" class="reg">
                    <h1>Expense / Month</h1>
                    <input type="text" placeholder="Personal ID" name="pid" required maxlength="13">
                    <input type ="file" name="photo" id="file" onchange="loadfile(event)">
                    <label for="file">Upload Picture</label>
                    <button type="submit" class="btsubmit">Submit</button>
                </form>
            </div>
            <?php
                $showuser = $rows[4];
                $sql = "SELECT * FROM room WHERE RoomID = '$showuser'";
                $result = mysqli_query($conn, $sql);
                $count = mysqli_num_rows($result);
                $fetch = mysqli_fetch_array($result);
            ?>
            <div class="pic">
                <h1 class="head">Upload Slip</h1>
                <img class="img" id="output" style="width:300px;">
                <h1 class="fontthai fontthsize">ค่าหอพักประจำเดือน<br>จำนวน
                <?php echo (int)$rows[5] + (int)$rows[2] + (int)$rows[3]?>
                 บาท</h1>
            </div>
        </div>
        <?php }
    ?>
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