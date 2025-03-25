package com.entity;

public class Board {

    private int size;
    private Cell[] cells;

    public Board(int size) {
        this.size = size;
        cells = new Cell[size + 1];
        for (int i = 1; i <= size; i++) cells[i] = new Cell();
    }

    public Cell getCell(int position) { return cells[position]; }
    public int getSize() { return size; }

}
