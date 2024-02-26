package org.game.enums;

public enum Orientation {
    N(0,-1)  {
        @Override
        public Orientation getLeft() {
            return Orientation.O;
        }

        @Override
        public Orientation getRight() {
            return Orientation.E;
        }
    },
    S(0, +1)  {
        @Override
        public Orientation getLeft() {
            return Orientation.E;
        }

        @Override
        public Orientation getRight() {
            return Orientation.O;
        }
    },
    E(+1, 0)  {
        @Override
        public Orientation getLeft() {
            return Orientation.N;
        }

        @Override
        public Orientation getRight() {
            return Orientation.S;
        }
    },
    O(-1, 0) {
        @Override
        public Orientation getLeft() {
            return Orientation.S;
        }

        @Override
        public Orientation getRight() {
            return Orientation.N;
        }

    };

    private final int nextX;
    private final int nextY;

    Orientation(int nextX, int nextY) {
        this.nextX = nextX;
        this.nextY = nextY;
    }

    public int getNextX() {
        return nextX;
    }

    public int getNextY() {
        return nextY;
    }

    public abstract Orientation getRight();

    public abstract Orientation getLeft();
}
