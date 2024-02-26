package org.game.model;


import org.game.enums.MoveEnum;
import org.game.enums.Orientation;

import java.util.List;

public class Adventurer extends GeoCoordinates {

    private String name;
    private int priority;
    private Orientation orientation;
    private List<MoveEnum> moves;
    private int treasuresWinned;

    public Adventurer() {
    }

    public Adventurer(String name, int priority, Orientation orientation, List<MoveEnum> moves, int xAxis, int yAxis, int treasuresWinned) {
        super(xAxis, yAxis);
        this.name = name;
        this.priority = priority;
        this.orientation = orientation;
        this.moves = moves;
        this.treasuresWinned = treasuresWinned;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public List<MoveEnum> getMoves() {
        return moves;
    }

    public void setMoves(List<MoveEnum> moves) {
        this.moves = moves;
    }

    public int getTreasuresWinned() {
        return treasuresWinned;
    }

    public void setTreasuresWinned(int treasuresWinned) {
        this.treasuresWinned = treasuresWinned;
    }

    public static class AdventurerBuilder {
        private int xAxis;
        private int yAxis;
        private String name;
        private int priority;
        private Orientation orientation;
        private List<MoveEnum> moves;
        private int treasuresWinned;

        public AdventurerBuilder() {
        }

        public AdventurerBuilder xAxis(int xAxis) {
            this.xAxis = xAxis;
            return this;
        }

        public AdventurerBuilder yAxis(int yAxis) {
            this.yAxis = yAxis;
            return this;
        }

        public AdventurerBuilder name(String name) {
            this.name = name;
            return this;
        }

        public AdventurerBuilder priority(int priority) {
            this.priority = priority;
            return this;
        }

        public AdventurerBuilder orientation(Orientation orientation) {
            this.orientation = orientation;
            return this;
        }

        public AdventurerBuilder moves(List<MoveEnum> moves) {
            this.moves = moves;
            return this;
        }

        public AdventurerBuilder treasureWinned(int treasuresWinned) {
            this.treasuresWinned = treasuresWinned;
            return this;
        }

        public Adventurer build() {
            return new Adventurer(name, priority, orientation, moves, xAxis, yAxis, treasuresWinned);
        }
    }
}
