package com;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.entity.*;
import com.serviceimpl.*;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        // Board Size
        System.out.print("Enter board size: ");
        int boardSize = scanner.nextInt();

        // Snakes
        System.out.print("Enter number of snakes: ");
        int numSnakes = scanner.nextInt();
        List<Snake> snakes = new ArrayList<>();
        for (int i = 0; i < numSnakes; i++) {
            System.out.print("Enter snake head and tail: ");
            snakes.add(new Snake(scanner.nextInt(), scanner.nextInt()));
        }

        // Ladders
        System.out.print("Enter number of ladders: ");
        int numLadders = scanner.nextInt();
        List<Ladder> ladders = new ArrayList<>();
        for (int i = 0; i < numLadders; i++) {
            System.out.print("Enter ladder start and end: ");
            ladders.add(new Ladder(scanner.nextInt(), scanner.nextInt()));
        }

        // Players
        System.out.print("Enter number of players: ");
        int numPlayers = scanner.nextInt();
        List<String> playerNames = new ArrayList<>();
        for (int i = 0; i < numPlayers; i++) {
            System.out.print("Enter player name: ");
            playerNames.add(scanner.next());
        }

        // Start Game
        GameService gameService = new GameService(boardSize, snakes, ladders, playerNames);
        gameService.startGame();
    }
}