<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Личный сайт студента GeekBrains</title>
	<link rel="stylesheet" href="style.css"> 
	<script type="text/javascript">

		var SYMBOLS = [ 1, 2, 3, 4, 5, 6, 7, 8, 9, 0,
					   'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
					   'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
					   'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
					   'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'];

	   function rand(arr) {
	   		var index = Math.round(Math.random() * SYMBOLS.length);
		   	return SYMBOLS[index];
	   }

	   function isFloat(n) {
	   		return (n % 1) != 0;
	   }

	   function write(text) {
			document.getElementById("info").innerHTML = text;
		}

		function genPassword() {
            var n = +document.getElementById("userAnswer").value;

            if (isNaN(n) || n <= 0 || isFloat(n)) {
            	write("Ошибка ввода. Требуется целое положительное число.");
            	return;
            }

            var pwd = "";
            
            for (var i = 0; i < n; ++i) {
                pwd += rand(SYMBOLS);
            }

            write("Ваш пароль: " + pwd);
        }

	</script>
</head>
<body>

<div class="content">
	<?php include("header.php") ?>

<div class="contentWrap">
    <div class="content">
        <div class="center">

			<h1>Генератор паролей</h1>

			<div class="box">

				<p id="info">Введите количество символов в пароле</p>
				<input type="text" id="userAnswer">
				<br>
				<a href="#" onClick="genPassword();" id="button">Начать</a>				
			</div>

        </div>
    </div>
</div>

</div>
<div class="footer">
	Copyright &copy; A. Smisloff
<div>


</body>
</html>