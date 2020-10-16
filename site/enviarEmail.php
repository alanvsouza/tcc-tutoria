<?php

include "classes/EmailHelper.php";

$local = $_POST['local'];
$recipient = $_POST['email'];

$message = "
    <!DOCTYPE html>
    <html lang='en'>
        <p>A sua monitoria foi agendada com sucesso!</p>
        <p>Local da monitoria: $local</p>
    </html>
";

EmailHelper email = new EmailHelper("alanvinicius2003@hotmail.com", $recipient);

email->send("Enviando email com php", $message);
