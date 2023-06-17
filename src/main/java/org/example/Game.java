package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Game {
    public int ERRORS_NUMBER_LIMIT = 5;
    public int error_number;
    private Scanner userInput = new Scanner(System.in);

    HangmanDictionary hangmanDictionary = new HangmanDictionary();
    String word = HangmanDictionary.word;

    ArrayList<Integer> revealedLetterIndexList = new ArrayList<>();

    String maskedWord;

    public void gameLoop() {
        boolean keepGoing = true;
        while (keepGoing) {
            System.out.println("Let's play (y/n)");
            error_number = 0;
            String userChoice = userInput.nextLine();
            switch (userChoice) {
                case "y":
                    gameSteps();
                    break;
                case "n":
                    System.out.println("End of Game\n Thank you for playing with us!");
                    keepGoing = false;
                    break;
                default:
                    System.out.println("Not a valid choice!\n Please try again...\n");
            }
        }
    }

    public void gameSteps() {

        System.out.println(HangmanDictionary.word);
        maskedWord = getMaskedWord(word, revealedLetterIndexList);
        System.out.println(maskedWord);
        System.out.println(HangmanDictionary.definition);
        playerMove();
    }

    public void playerMove() {
        boolean keepGoing = true;
        while (keepGoing){
        System.out.println("Enter one letter of Russian alphabet");
        String userLetter = userInput.nextLine();

        if (isRussianLetter(userLetter)) {
             keepGoing = validatePlayerMove(userLetter);
        } else {
            System.out.println("Not a valid letter!\n Please enter one letter of Russian alphabet...\n");
        }

    }
    }

    public boolean validatePlayerMove(String userLetter) {
        revealedLetterIndexList = new ArrayList<>();
        if (word.contains(userLetter)) {
            int index = -1;
            while ((index = word.indexOf(userLetter, index + 1)) != -1) {
                revealedLetterIndexList.add(index);

            }
            maskedWord =getMaskedWord(word,revealedLetterIndexList);
            System.out.println(maskedWord);
            if (Objects.equals(maskedWord, word)){
                System.out.println("You won");
                return false;
            }
            }else{
                error_number++;
            printHangman(error_number);
                if (error_number > ERRORS_NUMBER_LIMIT)
                {System.out.println("You lost");
                    return false;}

        } return true;
    }

    public String getMaskedWord(String word, ArrayList<Integer> indexList ) {
        StringBuilder sb = new StringBuilder();
            if (indexList.size() == 0) {
                for (int i = 0; i < word.length(); i++) {
                sb.append("*");
            } }else {
                int j = 0;
                for (int i = 0; i < word.length() ; i++) {
                    if (i != indexList.get(j) && !isRussianLetter(String.valueOf(maskedWord.charAt(i)))) {
                        sb.append("*");
                    } else {
                        sb.append(word.charAt(i));
                        if (j<indexList.size()-1){j++;
                    }}
            }
        }return sb.toString();
    }


    public static boolean isRussianLetter(String userLetter) {

        for (int i = 0; i < userLetter.length(); i++) {
            if (Character.UnicodeBlock.of(userLetter.charAt(i)).equals(Character.UnicodeBlock.CYRILLIC) && userLetter.length() == 1)
                return true;
        }
        return false;
    }


    public String printHangman(int error_number) {
        switch (error_number){
            case 1:
                System.out.println("""
                    ___________               
                    |   \\    |
                    |  
                    |  
                    |  
                    |
                    ~~~~~~~~~~~
                    """);
                break;
            case 2:
                System.out.println("""
                    ___________                
                    |   \\    |
                    |   ()
                    |   []
                    |  
                    |
                    ~~~~~~~~~~~
                    """);
                break;
            case 3:
                System.out.println("""
                    ___________                
                    |   \\    |
                    |   ()
                    |  /[]
                    |  
                    |
                    ~~~~~~~~~~~
                    """);
                break;
            case 4:
                System.out.println("""
                    ___________                
                    |   \\    |
                    |   ()
                    |  /[]\\
                    |  
                    |
                    ~~~~~~~~~~~
                    """);
                break;
            case 5:
                System.out.println("""
                    ___________                
                    |   \\    |
                    |   ()
                    |  /[]\\
                    |  /   
                    |
                    ~~~~~~~~~~~
                    """);
                break;
            default:
                System.out.println("""
                    ___________                
                    |   \\    |
                    |   ()
                    |  /[]\\
                    |  /  \\
                    |
                    ~~~~~~~~~~~
                    """);

        }
        return "";
    }


}
