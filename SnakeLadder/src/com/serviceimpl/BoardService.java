package com.serviceimpl;

import java.util.List;

import com.entity.Board;
import com.entity.Ladder;
import com.entity.Snake;

public class BoardService {

    private Board board;

    public BoardService(int size, List<Snake> snakes, List<Ladder> ladders) {
        board = new Board(size);
        for (Snake s : snakes) board.getCell(s.getHead()).setSnake(s);
        for (Ladder l : ladders) board.getCell(l.getStart()).setLadder(l);
    }

    public Board getBoard() { return board; }

}
