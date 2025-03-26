package com;

import java.util.Scanner;

import com.entity.PieceO;
import com.entity.PieceX;
import com.entity.Player;
import com.service.TicTacToeService;

public class App {
    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Player 1 name:");
        Player player1 = new Player(scanner.next(), new PieceX());
        System.out.println("Enter Player 2 name:");
        Player player2 = new Player(scanner.next(), new PieceO());

        TicTacToeService ticTacToeService = new TicTacToeService(3, player1, player2);
        ticTacToeService.playGame();
    }
}
