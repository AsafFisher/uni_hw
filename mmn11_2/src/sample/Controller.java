package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Controller {
    @FXML
    public Canvas board;
    @FXML protected void addShape(ActionEvent event) throws InstantiationException, IllegalAccessException {
        GraphicsContext gc = board.getGraphicsContext2D();
        gc.clearRect(0, 0, board.getWidth(), board.getHeight());
        Random rand = new Random();
        Class[] shapesClasses = {RandomRect.class, RandomLine.class, RandomEllipse.class};
        for(int i=0; i<10; i++){
            RandomShape shape = (RandomShape) shapesClasses[rand.nextInt(shapesClasses.length)].newInstance();
            shape.draw_random(this.board);
        }
    }
}
abstract class RandomShape{
    abstract void draw_random(Canvas board);
    void setRandomColor(Canvas board){
        Random rand = new Random();
        board.getGraphicsContext2D().setFill(
                Color.rgb(rand.nextInt(255),
                          rand.nextInt(255),
                          rand.nextInt(255)));
    }
}

class RandomRect extends RandomShape{

    @Override
    void draw_random(Canvas board) {
        GraphicsContext ctx = board.getGraphicsContext2D();
        Random rand = new Random();
        ctx.setFill(Color.rgb(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255)));
        double x = rand.nextDouble() * board.getWidth();
        double y = rand.nextDouble() * board.getHeight();
        double width = (rand.nextDouble() * (board.getWidth()/4));
        double height = (rand.nextDouble() * (board.getHeight()/4));

        this.setRandomColor(board);
        ctx.fillRect(x, y, width, height);
    }
}
class RandomLine extends RandomShape{

    @Override
    void draw_random(Canvas board) {
        GraphicsContext ctx = board.getGraphicsContext2D();
        Random rand = new Random();
        double x1 = rand.nextDouble() * board.getWidth();
        double delta_x = (rand.nextDouble() * (board.getWidth()/4));
        double y1 = rand.nextDouble() * board.getHeight();
        double delta_y = (rand.nextDouble() * (board.getHeight()/4));

        this.setRandomColor(board);
        ctx.strokeLine(x1,y1,x1 + delta_x,y1 + delta_y);
    }
}
class RandomEllipse extends RandomShape{

    @Override
    void draw_random(Canvas board) {
        GraphicsContext ctx = board.getGraphicsContext2D();
        Random rand = new Random();
        double x = rand.nextDouble() * board.getWidth();
        double y = rand.nextDouble() * board.getHeight();
        double width = (rand.nextDouble() * (board.getWidth()/4));
        double height = (rand.nextDouble() * (board.getHeight()/4));

        this.setRandomColor(board);
        ctx.fillOval(x, y, width, height);
    }
}