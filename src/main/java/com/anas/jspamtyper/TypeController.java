package com.anas.jspamtyper;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TypeController {
    private ArrayList<String> randomWords;
    private char sendKey;
    private double interval;
    private boolean keyboardInput;
    private int randomWordsLength,
                nubmer;
    private Robot robot;

    public TypeController() throws AWTException {
        init();
    }

    private void init() throws AWTException {
        randomWords = new ArrayList<>();
        sendKey = '\n';
        randomWordsLength = 6;
        interval = 0.2;
        keyboardInput = false;
        nubmer = 6;
        robot = new Robot();
    }

    public void setInputFile(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            randomWords.add(line);
        }
        reader.close();

        this.setNumber(randomWords.size());
    }

    public void setKeyboardInput(boolean keyboardInput) {
        this.keyboardInput = keyboardInput;
    }

    public void setSendKey(char sendKey) {
        this.sendKey = sendKey;
    }

    public void setInterval(double interval) {
        this.interval = interval;
    }

    public void setRandomWordsLength(int randomWordLength) {
        this.randomWordsLength = randomWordLength;
    }

    public void setNumber(int n) {
        this.nubmer = n;
    }

    public void start() {
        for (int i = 0; i < nubmer; i++) {
            type(getWord());
        }
    }

    private void type(String word) {
        for (char c : word.toCharArray()) {
            // Chek if the char is arabic
            if(ArabicHelper.isArabic(c))
                c = ArabicHelper.convertToEnglish(c); // Convert to english key

            // Press Shift if the char is capital
            if(Character.isUpperCase(c))
                robot.keyPress(KeyEvent.VK_SHIFT);
            // Type the char
            robot.keyPress(KeyEvent.getExtendedKeyCodeForChar(c));
//            robot.delay(1);
            robot.keyRelease(KeyEvent.getExtendedKeyCodeForChar(c));

            // Release Shift if the char is capital
            if(Character.isUpperCase(c))
                robot.keyRelease(KeyEvent.VK_SHIFT);

            // Interval between words/lines
            robot.delay((int) (interval * 1000));
        }

        // Send the sendKey
        robot.keyPress(KeyEvent.getExtendedKeyCodeForChar(sendKey));
        robot.delay(1);
        robot.keyRelease(KeyEvent.getExtendedKeyCodeForChar(sendKey));
    }

    private String getWord() {
        if (keyboardInput) {
            return generateRandomWord();
        } else if (randomWords.size() > 0) {
            return randomWords.remove(0);
        } else {
            return null;
        }
    }

    private String generateRandomWord() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < randomWordsLength; i++) {
            sb.append((char) (Math.random() * 26 + 'a'));
        }
        return sb.toString();
    }
}
