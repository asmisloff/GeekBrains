<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Личный сайт студента GeekBrains</title>
	<link rel="stylesheet" href="style.css"> 
	<script type="text/javascript">
		
		var score = 0;

		function checkAnswer(inputId, answers){
			var userAnswer = document.getElementById(inputId).value;
			userAnswer = userAnswer.toLowerCase();
			for(var i = 0; i < answers.length; i++){
				if(userAnswer == answers[i]){
					score++;
					break;
				}
			}
		}

		function checkAnswers() {

			score = 0;
			checkAnswer("userAnswer1", ["морковь", "морковка"]);
			checkAnswer("userAnswer2", ["айболит"]);
			checkAnswer("userAnswer3", ["бегемот", "гиппопотам"]);
			checkAnswer("userAnswer4", ["зима"]);

			alert("Вы отгадали " + score + " загадок");
		}

	</script>
</head>
<body>

<div class="content">
	<?php include('header.php'); ?>

<div class="contentWrap">
    <div class="content">
        <div class="center">

			<h1>Игра в загадки</h1>

			<div class="box">

				<p>Сидит девица в темнице, а коса на улице. Что это такое?</p>
				<input type="text" id="userAnswer1">

				<p>Друг зверей и друг детей<br>Добрый доктор ...</p>
				<input type="text" id="userAnswer2">

				<p>У него огромный рот,<br>Он зовется ...</p>
				<input type="text" id="userAnswer3">

				<p>Белым снегом всё одето -<br>Значит, наступает ...</p>
				<input type="text" id=userAnswer4>

				<br>
				<a href="#" onClick="checkAnswers();">Ответить</a>

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