package ru.rsreu.autoauthor.logic;

import java.security.MessageDigest;

public class PasswordEncryption {
    public static String encryptPassword(String password){
        StringBuilder passwordStr = new StringBuilder();
        try{byte[] bytesOfMessage = password.getBytes("UTF-8");
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] theMD5digest = md.digest(bytesOfMessage);
            for (byte b: theMD5digest){
                passwordStr.append(String.valueOf(b)).append(" ");
            }
        } catch (Exception e ){

        }
        return passwordStr.toString();
    }
}
