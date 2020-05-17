package com.svceindore.minor.exception;

public class FCMKeyNotFoundException extends Exception{
    @Override
    public String getMessage() {
        return "specify the valid server key in project.properties file as \nSERVER_KEY = your_fcm_server_key";
    }
}
