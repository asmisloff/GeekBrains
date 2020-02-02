<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Личный сайт студента GeekBrains</title>
	<link rel="stylesheet" href="style.css"> 
	<script type="text/javascript">

		var answer = parseInt(Math.random() * 100);

		function readInt(){
			var number = document.getElementById("userAnswer").value;
			return parseInt(number);
			// return +document.getElementById("userAnswer").value;
		}

		function write(text,id = "info"){
			document.getElementById(id).innerHTML = text;
		}

		function hide(id){
			document.getElementById(id).style.display = "none";
		}

		var userNumber = 1;

		function guess(){
			var userAnswer = readInt();
			
			if (userAnswer == answer) {
				write("<b>Поздравляю, вы угадали!</b>");
				hide("button");
				hide("userAnswer");
				return;
			}
			else if (userAnswer > answer) {
				write("Введено слишком большое число<br>Переход хода. Введите число от 1 до 100");
			}
			else {
				write("Введено слишком маленькое число<br>Переход хода. Введите число от 1 до 100");				
			}

			userNumber = (userNumber == 1 ? 2 : 1);
			write("Игрок " + userNumber, "userNumber");
		}

	</script>
</head>
<body>

<div class="content">
	<?php include("header.php") ?>

<div class="contentWrap">
    <div class="content">
        <div class="center">

			<h1>Игра угадайка</h1>

			<div class="box">

				<h4 id="userNumber">Игрок 1</h4>
				<p id="info">Угадайте число от 0 до 100</p>
				<input type="text" id="userAnswer">
				<br>
				<a href="#" onClick="guess();" id="button">Начать</a>				
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