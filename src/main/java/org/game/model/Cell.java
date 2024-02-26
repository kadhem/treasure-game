package org.game.model;

public class Cell extends GeoCoordinates {
    private boolean isMountain;
    private boolean isTreasure;
    private int treasureCount;
    private Adventurer adventurer;

    public Cell() {
    }

    public Cell(int xAxis, int yAxis, boolean isMountain, boolean isTreasure, int treasureCount, Adventurer adventurer) {
        super(xAxis, yAxis);
        this.isMountain = isMountain;
        this.isTreasure = isTreasure;
        this.treasureCount = treasureCount;
        this.adventurer = adventurer;
    }

    public boolean isMountain() {
        return isMountain;
    }

    public void setMountain(boolean mountain) {
        isMountain = mountain;
    }

    public boolean isTreasure() {
        return isTreasure;
    }

    public void setTreasure(boolean treasure) {
        isTreasure = treasure;
    }

    public int getTreasureCount() {
        return treasureCount;
    }

    public void setTreasureCount(int treasureCount) {
        this.treasureCount = treasureCount;
    }

    public Adventurer getAdventurer() {
        return adventurer;
    }

    public void setAdventurer(Adventurer adventurer) {
        this.adventurer = adventurer;
    }

    public boolean isAdventurer() {
        return this.getAdventurer() != null;
    }
}
