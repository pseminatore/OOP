/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package hangman;

import javax.swing.JOptionPane;

/**
 *
 * @author ddbie
 */


public class Main {

    /**
     * @param args the command line arguments
     */
    final static String[] WORDS = {"Awkward", "Bagpipes", "Banjo", "Bungler", "Croquet",
            "Crypt", "Dwarves", "Fervid", "Fishhook", "Fjord", "Gazebo", "Gypsy", "Haiku",
            "Haphazard", "Hyphen", "Ivory", "Jazzy", "Jiffy", "Jinx", "Jukebox", "Kayak",
            "Kiosk", "Klutz", "Memento", "Mystify", "Numbskull", "Ostracize", "Oxygen",
            "Pajama", "Phlegm", "Pixel", "Polka", "Quad", "Quip", "Rhythmic", "Rogue",
            "Sphinx", "Squawk", "Swivel", "Toady", "Twelfth", "Unzip", "Waxy",
            "Wildebeest", "Yacht", "Zealous", "Zigzag", "Zippy", "Zombie"};

    public static void main(String[] args) {
        while (true) {
            runMenu();
        }
    }

    public static void runMenu(){
        String input = JOptionPane.showInputDialog("1. Play game from a randomly chosen word in a list" +
                "\n2. Play game from a word entered by another user\n3. Exit Game");
        int in = Integer.parseInt(input);
        switch (in) {
            case 1: // play game from random word
                runGame(true);
                break;
            case 2: // play game from specific word
                runGame(false);
                break;
            case 3: // exit the game
                System.exit(1);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Enter a valid number.");
                runMenu();
                break;
        }
    }

    public static void runGame(boolean isRandom){
        String wordToGuess;
        int correctGuesses = 0;
        if(isRandom){
            int rand = (int) (Math.random() * WORDS.length);
            wordToGuess = WORDS[rand];
        }else{
            wordToGuess = getWord();
        }
        JOptionPane.showMessageDialog(null, "your word: " + wordToGuess);
        int strikes = 0;
        char[] blanks = new char[wordToGuess.length()];
        for (int i = 0; i < wordToGuess.length(); i++){
            blanks[i] = '_' ;
        }
        boolean wrong;
        while(strikes < 5){
            wrong = true;
            String displayBlanks = new String(blanks);
            //JOptionPane.showMessageDialog(null, "It works");
            String disp = displayBlanks + "\nStrikes: " + strikes + "\nEnter your guess: ";
            String sGuess = JOptionPane.showInputDialog(disp).trim();
            if ((int)sGuess.charAt(0) < 97){
                sGuess = sGuess.toLowerCase();
            }
            char guess = sGuess.charAt(0);
            int check = guess;
            if ((check < 65 || check > 90) && (check < 97 || check > 122)) {
                JOptionPane.showMessageDialog(null, "Error: Please only enter alphabetic characters.  Try again!");
                break;
            }
            wordToGuess = wordToGuess.toLowerCase();
            for (int j = 0; j < wordToGuess.length(); j++){
                if (guess == wordToGuess.charAt(j)){
                    if (j==0){
                        int ascii = guess - 32;
                        char aGuess = (char)ascii;
                        blanks[j]= aGuess;
                    }
                    blanks[j] = guess;
                    wrong = false;
                    correctGuesses++;
                }
            }
            if (wrong){
                strikes++;
            }
            if (correctGuesses == wordToGuess.length()){
                JOptionPane.showMessageDialog(null, "Congratulations! You won!");
                break;
            }
            if (strikes == 5){
                JOptionPane.showMessageDialog(null, "Sorry, you lost.");
                break;
            }
        }
    }

    public static String getWord(){
        return JOptionPane.showInputDialog("Enter the word to be guessed: ").trim();
    }
}