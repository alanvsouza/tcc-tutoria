<?php

require_once("../classes/EmailHelper.php");

$local = $_POST['local'];
$recipient = $_POST['email'];

$message = "
    Sua monitoria foi agendada no local $local.
";

$email = new EmailHelper("alanvinicius2003@hotmail.com", $recipient);

$email->send_email("Enviando email com php", $message);
