<?php

class EmailHelper {
    private $sender;
    private $recipient;    

    public function __construct($sender, $recipient) {
        $this->sender = $sender;
        $this->recipient = $recipient;        
    }
    
    public function send_email($subject, $message) {
        $headers = 'From: noreplypls @ company . com';
      
        $sendemail = mail($this->recipient, $subject, $message, $headers);

        if($sendemail){
            $mgm = "Email successfully sent! <br/>";            
        } else {
            $mgm = "Error sending email!";
        }
        echo $mgm;
    }
}