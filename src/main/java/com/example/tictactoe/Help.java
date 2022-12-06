package com.example.tictactoe;
import java.util.Scanner;

public class Help {
    public static void about() {
        Scanner key = new Scanner(System.in);

        // The String will display the text that will allow the user to read the description.
        String aboutText = "This program will display a JavaFX Tic-Tac-Toe game that has been made for your entertainment. Enjoy!";

        // The output command allows the aboutText value to print the text in the String value.
        System.out.println("Help-About:\n"+aboutText+"\nPress enter to continue.");

        key.nextLine();
    }
}
