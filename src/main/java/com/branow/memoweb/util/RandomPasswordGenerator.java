package com.branow.memoweb.util;

import org.springframework.stereotype.Service;

@Service
public class RandomPasswordGenerator {

    public String generate() {
        int passwordLength = 8;
        String charset = "";
        StringBuilder newPassword = new StringBuilder();
        charset += "0123456789";
        charset += "abcdefghijklmnopqrstuvwxyz";
        charset += "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 0; i < passwordLength; i++) {
            newPassword.append(charset.charAt((int) Math.floor(Math.random() * charset.length())));
        }
        return newPassword.toString();
    }

}
