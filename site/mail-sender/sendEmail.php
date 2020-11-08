<?php

require_once '../vendor/autoload.php';

\App\Model\Session::startSession();

$emailHelper = new \App\Helper\EmailHelper('cotiltutoria@gmail.com', 'R7nz8zYmf[ts');

$subject = $_POST['subject'];
$from = $_POST['from'];
$to = $_POST['to'];
$model = $_POST['model'];
$replace = $_POST['replace'];

$emailHelper->sendEmail($subject, $from, $to, $model, $replace);

header('Location: ../login.php');
