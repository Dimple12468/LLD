package com.service;

import com.entity.Board;
import com.entity.Player;

public class TicTacToeService {

    private Board board;
    private Player[] players;
    private int currentPlayerIndex;

    public TicTacToeService(int size, Player player1, Player player2) {
        this.board = new Board(size);
        this.players = new Player[]{player1, player2};
        this.currentPlayerIndex = 0;
    }

    public void playGame() {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        while (true) {
            board.printBoard();
            Player currentPlayer = players[currentPlayerIndex];
            System.out.println(currentPlayer.getName() + "'s turn and has piece of: " + currentPlayer.getPiece().getPieceType());
            int row = scanner.nextInt();
            int col = scanner.nextInt();

            if (board.placePiece(row, col, currentPlayer.getPiece())) {
                if (board.checkWin(currentPlayer.getPiece())) {
                    board.printBoard();
                    System.out.println(currentPlayer.getName() + " wins!");
                    break;
                } else if (board.isBoardFull()) {
                    board.printBoard();
                    System.out.println("It's a draw! No winner.");
                    break;
                }
                currentPlayerIndex = 1 - currentPlayerIndex;
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }
        scanner.close();
    }

}
