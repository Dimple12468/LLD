package com.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import com.entity.*;

public class GameService {
    private BoardService boardService;
    private PlayerService playerService = new PlayerService();
    private List<Player> players;
    private Dice dice = new Dice();

    public GameService(int boardSize, List<Snake> snakes, List<Ladder> ladders, List<String> playerNames) {
        boardService = new BoardService(boardSize, snakes, ladders);
        players = new ArrayList<>();
        for (String name : playerNames) players.add(new Player(name));
    }

    public void startGame() {
        boolean gameWon = false;
        while (!gameWon) {
            for (Player player : players) {
                int roll = dice.roll();
                playerService.movePlayer(player, roll, boardService.getBoard());
                System.out.println(player.getName() + " rolled " + roll + " and moved to " + player.getPosition());
                if (player.getPosition() == boardService.getBoard().getSize()) {
                    System.out.println(player.getName() + " wins!");
                    gameWon = true;
                    break;
                }
            }
        }
    }
}