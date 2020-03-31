package com.company;

import java.util.ArrayList;

// 7w x 6h
public class Board {
    private ArrayList<ArrayList<Integer>> board;
    private WinCheck winCheck = new WinCheck();

    private int lastRow;
    private int lastColumn;
    private int lastPlayer;


    public Board() {
        int height = 6;
        int width = 7;
        this.board = setUpBoard(width, height);
    }

    private ArrayList<ArrayList<Integer>> setUpBoard(int width, int height) {
        ArrayList<ArrayList<Integer>> newBoard = new ArrayList<>();

        for (int i = 0; i < height; i++) {
            ArrayList<Integer> row = new ArrayList<>();

            for (int j = 0; j < width; j++) {
                row.add(0);
            }

            newBoard.add(row);
        }

        return newBoard;
    }

    @Override
    public String toString() {
        StringBuilder boardString = new StringBuilder();
        boardString.append("\n");

        for (ArrayList<Integer> row : board) {
            for (Integer slot : row) {
                switch (slot) {
                    case 1:
                        boardString.append(ConsoleColors.ANSI_RED);
                        break;
                    case 2:
                        boardString.append(ConsoleColors.ANSI_BLUE);
                        break;
                }

                boardString.append(slot).append(ConsoleColors.ANSI_RESET).append(" ");
            }

            boardString.append("\n");
        }

        boardString.append("-------------\n");
        boardString.append("A B C D E F G\n");
        boardString.append("-------------");

        return boardString.toString();
    }

    public void drop(String input, int player) {
        int columnNum = letterToInt(input);
        this.lastColumn = columnNum;
        this.lastPlayer = player;

        for (int i = 0; i < board.size(); i++) {
            if (board.get(i).get(columnNum) != 0) {
                this.lastRow = i - 1;
                board.get(this.lastRow).set(columnNum, player);
                break;
            }

            if (i == board.size() - 1) {
                this.lastRow = i;
                board.get(this.lastRow).set(columnNum, player);
                break;
            }
        }
    }

    public int letterToInt(String input) {
        int num;

        switch (input) {
            case "A":
                num = 0;
                break;
            case "B":
                num = 1;
                break;
            case "C":
                num = 2;
                break;
            case "D":
                num = 3;
                break;
            case "E":
                num = 4;
                break;
            case "F":
                num = 5;
                break;
            case "G":
                num = 6;
                break;
            default:
                num = -1;
        }

        return num;
    }

    public boolean checkWin(ArrayList<ArrayList<Integer>> board, int row, int column, int player) {
        return winCheck.checkWin(board, row, column, player);
    }

    public ArrayList<ArrayList<Integer>> getBoard() {
        return board;
    }

    public int getLastRow() {
        return lastRow;
    }

    public int getLastColumn() {
        return lastColumn;
    }

    public int getLastPlayer() {
        return lastPlayer;
    }
}
