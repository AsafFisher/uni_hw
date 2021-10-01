package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class Controller {
    @FXML
    public Canvas board;
    LifeGame life = null;

    @FXML
    protected void cycle(ActionEvent event) throws InstantiationException, IllegalAccessException {
        boolean[][] game_state;
        if (life == null) {
            // Start a random game
            life = new LifeGame();
            game_state = life.getState();
        } else {
            // Make a new generation
            game_state = life.cycle();
        }

        // Draw the life state on board
        drawState(game_state);
    }

    protected void drawState(boolean[][] state) {
        GraphicsContext gc = board.getGraphicsContext2D();
        // Clear the board
        gc.clearRect(0, 0, board.getWidth(), board.getHeight());

        // Draw the outline
        drawBoardLayout();

        // Mark the living
        markTiles(state);
    }

    protected void drawBoardLayout() {
        GraphicsContext gc = board.getGraphicsContext2D();
        double rectSize = this.board.getWidth() / this.life.TILES_AMOUNT_AXIS;
        for (int i = 0; i < this.life.TILES_AMOUNT_AXIS; i++) {
            for (int j = 0; j < this.life.TILES_AMOUNT_AXIS; j++) {
                gc.strokeRect(i * rectSize, j * rectSize, rectSize, rectSize);
            }
        }
    }

    protected void markTiles(boolean[][] state) {
        GraphicsContext gc = board.getGraphicsContext2D();
        double rectSize = this.board.getWidth() / this.life.TILES_AMOUNT_AXIS;
        for (int i = 0; i < this.life.TILES_AMOUNT_AXIS; i++) {
            for (int j = 0; j < this.life.TILES_AMOUNT_AXIS; j++) {
                if (state[i][j]) {
                    gc.fillRect(i * rectSize, j * rectSize, rectSize, rectSize);
                }
            }
        }

    }
}