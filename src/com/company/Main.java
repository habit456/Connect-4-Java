package com.company;

import java.io.Console;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Board board = new Board();
        Scanner scanner = new Scanner(System.in);

        int player = 1;

        introduction(scanner);


        // main loop
        while (true) {
            System.out.println(board);

            // this is to fix if player tries placing into a full column
            while (true) {
                String playerInput = getPlayerInput(board, scanner, player);

                try {
                    board.drop(playerInput, player);
                } catch (Exception e) {
                    System.out.println("You can't go there!");
                    continue;
                }

                break;
            }

            if (board.checkWin(board.getBoard(), board.getLastRow(), board.getLastColumn(), board.getLastPlayer())) {
                System.out.println(board);
                System.out.printf("Congratulations player %d! You win!", player);
                break;
            }

            player = (player == 1 ? 2 : 1);
        }


    }

    private static String getPlayerInput(Board board, Scanner scanner, int player) {
        System.out.printf("PLayer %d: ", player);
        while (true) {
            String playerInput = scanner.nextLine().toUpperCase();

            if (board.letterToInt(playerInput) != -1) {
                return playerInput;
            } else {
                System.out.println("Invalid Input. Try again.");
            }

        }
    }

    private static void introduction(Scanner scanner) {
        System.out.println();
        System.out.println("--------------");
        System.out.println(ConsoleColors.ANSI_CYAN + "  Connect 4" + ConsoleColors.ANSI_RESET);
        System.out.println("--------------");
        System.out.println("Version 1.0");
        System.out.println("By Joshua Kaplan");
        System.out.println();
        System.out.print("Press enter to begin. . .");
        scanner.nextLine();
    }
}
