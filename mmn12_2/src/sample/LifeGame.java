package sample;

import java.util.Random;

public class LifeGame {

    // Change this to change the amount of tiles 30 is beautiful.
    static final int TILES_AMOUNT_AXIS = 100;
    private boolean[][] state;

    public LifeGame() {
        this.state = new boolean[TILES_AMOUNT_AXIS][TILES_AMOUNT_AXIS];
        Random rand = new Random();
        for (int i = 0; i < this.state.length; i++) {
            for (int j = 0; j < this.state[i].length; j++) {
                this.state[i][j] = rand.nextBoolean();
            }
        }

    }

    public boolean[][] cycle() {
        boolean[][] new_generation = new boolean[TILES_AMOUNT_AXIS][TILES_AMOUNT_AXIS];
        for (int i = 0; i < this.state.length; i++) {
            for (int j = 0; j < this.state[i].length; j++) {
                new_generation[i][j] = evaluateCellState(i, j);
            }
        }
        this.state = new_generation;
        return new_generation;
    }

    public boolean[][] getState() {
        return state;
    }

    private boolean evaluateCellState(int i, int j) {
        if (!this.state[i][j]) {
            return getAmountNeighbors(i, j) == 3;
        } else {
            int amount = getAmountNeighbors(i, j);
            if (amount == 0 || amount == 1 || amount >= 4)
                return false;
            if (amount == 2 || amount == 3)
                return true;
        }
        return true;
    }

    private int getAmountNeighbors(int i, int j) {
        // There are 8 neighbors
        int count = 0;
        for (int x = i - 1; x <= i + 1; x++) {
            if (x == -1 || x == TILES_AMOUNT_AXIS)
                continue;
            for (int y = j - 1; y <= j + 1; y++) {
                if (y == -1 || y == TILES_AMOUNT_AXIS)
                    continue;
                if (this.state[x][y]) {
                    count++;
                }
            }
        }
        return this.state[i][j] ? count - 1 : count;
    }
}
