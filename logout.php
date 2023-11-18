<?php
    session_start();
    session_destroy();
?>
<script>
    alert("Logout");
    setTimeout(() => {
        window.location = 'login.html';
    }, 1000);
</script>