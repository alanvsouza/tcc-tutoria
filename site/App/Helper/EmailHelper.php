<?php

namespace App\Helper;

require("PHPMailer/PHPMailer/src/PHPMailer.php");
require("PHPMailer/PHPMailer/src/SMTP.php");

class EmailHelper
{
    private $mail;

    public function __construct($username, $password)
    {
        $this->mail = new \PHPMailer;

        $this->mail->isSMTP();
        $this->mail->SMTPDebug = \SMTP::DEBUG_SERVER;
        $this->mail->Host = 'smtp.gmail.com';
        $this->mail->Port = 587;
        $this->mail->SMTPSecure = \PHPMailer::ENCRYPTION_STARTTLS;
        $this->mail->SMTPAuth = true;
        $this->mail->CharSet = 'UTF-8';
        $this->mail->Username = $username;
        $this->mail->Password = $password;
    }

    public function sendEmail($subject, $from, $to, $model, $replace)
    {
        $this->mail->Subject = $subject;
        $this->mail->setFrom($from);
        $this->mail->addAddress($to);

        $message = file_get_contents('modelos/' . $model . '/index.html');

        foreach ($replace as $key => $string) {
            $message = str_replace('%' . $key . '%', $string, $message);
        }

        $this->mail->msgHTML($message);

        if (!$this->mail->send())
            return false;

        return true;
    }
}
