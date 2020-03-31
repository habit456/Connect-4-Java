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
                    System.out.println(addColorToString("You can't go there!", ConsoleColors.ANSI_PURPLE));
                    continue;
                }

                break;
            }

            if (board.checkWin(board.getBoard(), board.getLastRow(), board.getLastColumn(), board.getLastPlayer())) {
                System.out.println(board);

                String winMessage = String.format("Congratulations Player %d! You win!", player);
                System.out.print(addColorToString(winMessage, ConsoleColors.ANSI_YELLOW));

                break;
            }

            player = (player == 1 ? 2 : 1);
        }


    }

    private static String getPlayerInput(Board board, Scanner scanner, int player) {

        while (true) {
            System.out.print(makePlayerString(player) + ": ");

            String playerInput = scanner.nextLine().toUpperCase();
            if (board.letterToInt(playerInput) != -1) {
                return playerInput;
            } else {
                System.out.println(addColorToString("Invalid Input. Try again.", ConsoleColors.ANSI_PURPLE));
            }

        }
    }

    private static String makePlayerString(int player) {
        String playerString = "Player " + player;
        String color = (player == 1 ? ConsoleColors.ANSI_RED : ConsoleColors.ANSI_BLUE);
        return addColorToString(playerString, color);
    }

    private static String addColorToString(String string, String consoleColor) {
        return consoleColor + string + ConsoleColors.ANSI_RESET;
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
