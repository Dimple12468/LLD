package com.entity;

import com.util.PieceType;

public class Board {

    private Integer size;
    private final Piece[][] board;

    public Board(int size) {
        this.size = size;
        this.board = new Piece[size][size];
    }

    public Integer getSize() {
        return size;
    }

    public Piece[][] getBoard() {
        return board;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public boolean placePiece(int row, int col, Piece piece) {
        if (row < 0 || row >= size || col < 0 || col >= size || board[row][col] != null) {
            return false;
        }
        board[row][col] = piece;
        return true;
    }

    public boolean checkWin(Piece piece) {
        PieceType type = piece.getPieceType();
        // Check rows, columns, and diagonals
        for (int i = 0; i < size; i++) {
            if (checkRow(i, type) || checkCol(i, type)) return true;
        }
        return checkDiagonals(type);
    }

    private boolean checkRow(int row, PieceType type) {
        for (int i = 0; i < size; i++) {
            if (board[row][i] == null || board[row][i].getPieceType() != type) return false;
        }
        return true;
    }
    
    private boolean checkCol(int col, PieceType type) {
        for (int i = 0; i < size; i++) {
            if (board[i][col] == null || board[i][col].getPieceType() != type) return false;
        }
        return true;
    }

    private boolean checkDiagonals(PieceType type) {
        boolean leftDiagonal = true, rightDiagonal = true;
        for (int i = 0; i < size; i++) {
            if (board[i][i] == null || board[i][i].getPieceType() != type) leftDiagonal = false;
            if (board[i][size - i - 1] == null || board[i][size - i - 1].getPieceType() != type) rightDiagonal = false;
        }
        return leftDiagonal || rightDiagonal;
    }

    public void printBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(board[i][j] == null ? "- " : board[i][j].getPieceType() + " ");
            }
            System.out.println();
        }
    }

    public boolean isBoardFull() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == null) {
                    return false;
                }
            }
        }
        return true;
    }
}