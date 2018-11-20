package com.cedei.plexus.appusers.utils;

import java.util.Random;

/**
 * passwordGenerator
 */
public class PasswordGenerator {

    private final String letters = "abcdefghijklmnopqrstuvwxyz";
    private final String numbers = "0123456789";
    private final String especials = "@#$%&=-_+*";
    private final String[] characters = new String[] { letters, numbers, especials };
    private Random random = new Random();
    private Integer passwordLength = 15;

    private char getRandomChar(String range) {
        return range.charAt(this.random.nextInt(range.length()));
    }

    public String generatePassword() {
        StringBuilder aux = new StringBuilder();
        for (int i = 0; i < this.passwordLength; i++) {
            Integer range = this.random.nextInt(this.characters.length);
            aux.append(this.getRandomChar(this.characters[range]));
        }
        return aux.toString();
    }

}