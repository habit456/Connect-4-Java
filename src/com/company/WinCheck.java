package com.company;

import java.util.ArrayList;

public class WinCheck {

    public boolean checkWin(ArrayList<ArrayList<Integer>> board, int row, int column, int player) {
        ArrayList<Boolean> directionChecks = new ArrayList<Boolean>();

        directionChecks.add(checkHorizontal(board, row, player));
        directionChecks.add(checkVertical(board, column, player));
        directionChecks.add(checkDiagonal1(board, row, column, player));
        directionChecks.add(checkDiagonal2(board, row, column, player));

        return directionChecks.contains(Boolean.TRUE);
    }

    private boolean checkDiagonal1(ArrayList<ArrayList<Integer>> board, int row, int column, int player) {
        String h = getDiagonal1String(board, row, column);
        return checkWinFromString(h, player);
    }

    private String getDiagonal1String(ArrayList<ArrayList<Integer>> board, int row, int column) {
        ArrayList<Integer> al = new ArrayList<Integer>();
        int currentPosRow = row;
        int currentPosCol = column;

        // going UP the diagonal
        // do-while to add the current position to al
        do {
            int boardSlot = board.get(currentPosRow).get(currentPosCol);

            al.add(0, boardSlot);

            currentPosCol--;
            currentPosRow--;
        } while (currentPosCol >= 0 && currentPosRow >= 0);

        // this brings our current pos to the original position but 1 down the diagonal
        currentPosCol = column + 1;
        currentPosRow = row + 1;

        // going DOWN the diagonal
        while (currentPosCol < board.get(0).size() && currentPosRow < board.size()) {
            int boardSlot = board.get(currentPosRow).get(currentPosCol);

            al.add(boardSlot);

            currentPosCol++;
            currentPosRow++;
        }

        return convertArrayToString(al);
    }

    private boolean checkDiagonal2(ArrayList<ArrayList<Integer>> board, int row, int column, int player) {
        String h = getDiagonal2String(board, row, column);
        return checkWinFromString(h, player);
    }

    private String getDiagonal2String(ArrayList<ArrayList<Integer>> board, int row, int column) {
        ArrayList<Integer> al = new ArrayList<Integer>();
        int currentPosRow = row;
        int currentPosCol = column;

        // going UP the diagonal
        // do-while to add the current position to al
        do {
            int boardSlot = board.get(currentPosRow).get(currentPosCol);

            al.add(0, boardSlot);

            currentPosCol++;
            currentPosRow--;
        } while (currentPosCol < board.get(0).size() && currentPosRow >= 0);

        // this brings our current pos to the original position but 1 down the diagonal
        currentPosCol = column - 1;
        currentPosRow = row + 1;

        // going DOWN the diagonal
        while (currentPosCol >= 0 && currentPosRow < board.size()) {
            int boardSlot = board.get(currentPosRow).get(currentPosCol);

            al.add(boardSlot);

            currentPosCol--;
            currentPosRow++;
        }

        return convertArrayToString(al);
    }

    private boolean checkHorizontal(ArrayList<ArrayList<Integer>> board, int row, int player) {
        String h = getHorizontalString(board, row);
        return checkWinFromString(h, player);
    }

    private String getHorizontalString(ArrayList<ArrayList<Integer>> board, int row) {
        ArrayList<Integer> al = board.get(row);
        return convertArrayToString(al);
    }

    private boolean checkVertical(ArrayList<ArrayList<Integer>> board, int column, int player) {
        String h = getVerticalString(board, column);
        return checkWinFromString(h, player);
    }

    private String getVerticalString(ArrayList<ArrayList<Integer>> board, int column) {
        ArrayList<Integer> al = new ArrayList<Integer>();

        for (ArrayList<Integer> boardRow : board ) {
            al.add(boardRow.get(column));
        }

        return convertArrayToString(al);
    }

    private String convertArrayToString(ArrayList<Integer> al) {
        StringBuilder str = new StringBuilder();

        for (Integer i : al) {
            str.append(i);
        }

        return str.toString();
    }

    private boolean checkWinFromString(String str, int player) {
        String check = String.format("%d%d%d%d", player, player, player, player);
        return str.contains(check);
    }
}
