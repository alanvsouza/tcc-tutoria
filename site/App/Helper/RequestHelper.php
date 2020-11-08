<?php

namespace App\Helper;

class RequestHelper
{
    public static function makeRequest($url, $method, $data)
    {
        $options = array(
            'http' => array(
                'header'  => "Content-type: application/x-www-form-urlencoded\r\n",
                'method'  => $method,
                'content' => http_build_query($data)
            )
        );

        $context  = stream_context_create($options);
        $result = file_get_contents($url, false, $context);


        if ($result === FALSE)
            return false;

        return true;
    }
}
