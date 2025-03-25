package com.serviceimpl;

import com.entity.Board;
import com.entity.Cell;
import com.entity.Player;

public class PlayerService {

    public void movePlayer(Player player, int diceRoll, Board board) {
        int newPos = player.getPosition() + diceRoll;
        if (newPos > board.getSize()) return;
        
        Cell cell = board.getCell(newPos);
        if (cell.getSnake() != null) newPos = cell.getSnake().getTail();
        else if (cell.getLadder() != null) newPos = cell.getLadder().getEnd();
        
        player.setPosition(newPos);
    }

}
