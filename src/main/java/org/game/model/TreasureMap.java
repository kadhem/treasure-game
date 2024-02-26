package org.game.model;

public class TreasureMap {

    private int lengthX;
    private int lengthY;

    private Cell[][] map = new Cell[0][];

    public TreasureMap() {
    }

    public Cell[][] getMap() {
        return map;
    }

    public void setMap(Cell[][] map) {
        this.map = map;
    }

    public int getLengthX() {
        return lengthX;
    }

    public void setLengthX(int lengthX) {
        this.lengthX = lengthX;
    }

    public int getLengthY() {
        return lengthY;
    }

    public void setLengthY(int lengthY) {
        this.lengthY = lengthY;
    }

    public int getAdventurersCount() {
        int count = 0;
        for (Cell[] cells : map) {
            for (Cell cell : cells) {
                if(cell.isAdventurer()) {
                    count++;
                }
            }
        }
        return count;
    }

    public int getMountainsCount() {
        int count = 0;
        for (Cell[] cells : map) {
            for (Cell cell : cells) {
                if(cell.isMountain()) {
                    count++;
                }
            }
        }
        return count;
    }

    public int getTreasuresCount() {
        int count = 0;
        for (Cell[] cells : map) {
            for (Cell cell : cells) {
                if(cell.isTreasure()) {
                    count++;
                }
            }
        }
        return count;
    }
}
