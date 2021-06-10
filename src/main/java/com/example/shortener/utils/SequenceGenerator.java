package com.example.shortener.utils;

import java.util.Random;

public class SequenceGenerator {

    public String getRandomSequence(int seqLength){
        StringBuilder seqBuilder = new StringBuilder();
        for (int counter = 0; counter<seqLength; counter++){
            int randomChoice = new Random().nextInt(3);
            switch (randomChoice){
                case 0: seqBuilder.append((char)getRandomNumber()); break;
                case 1: seqBuilder.append((char)getRandomUpperCaseLetter()); break;
                case 2: seqBuilder.append((char)getRandomLowerCaseLetter()); break;
            }
        }
        return seqBuilder.toString();
    }

    private int getRandomNumber() {
        final int MIN = 48;
        final int MAX = 57;
        Random random = new Random();
        return random.nextInt(MAX-MIN)+MIN;
    }

    private int getRandomUpperCaseLetter() {
        final int MIN = 65;
        final int MAX = 90;
        Random random = new Random();
        return random.nextInt(MAX-MIN)+MIN;
    }

    private int getRandomLowerCaseLetter() {
        final int MIN = 97;
        final int MAX = 122;
        Random random = new Random();
        return random.nextInt(MAX-MIN)+MIN;
    }
}
