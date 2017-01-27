//package editor;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

/**
 * Created by huangqiming on 2017/1/25.
 */
/** the cursor is the subclass of Rectangle, it can do set and get Height, Width. setX and setY*/
public class Cursor extends Rectangle {

    /** the val to store fixed height when blink*/
    private double cursorHeight = 0;

    public Cursor(double width, double height) {
        super(width, height);
        super.setFill(Color.BLACK);
        cursorHeight = height;
        makeCursorBlink();
    }

    public void setCursorHeight(double h) {
        super.setHeight(h);
        cursorHeight = h;
    }

    /** A EventHandler to handle change the height of the cursor */
    private class CursorBlinkEventHandler implements EventHandler<ActionEvent> {
        private Cursor cursor;

        CursorBlinkEventHandler(Cursor cursor){
            this.cursor = cursor;
        }

        @Override
        public void handle(ActionEvent event) {
            changeHeight();
        }

        private void changeHeight(){
            if (cursor.getHeight() > 0) {
                cursor.setHeight(0);
            } else {
                cursor.setHeight(cursorHeight);
            }
        }
    }

    /** make the cursor blink periodically*/
    private void makeCursorBlink() {
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        CursorBlinkEventHandler cursorBlink = new CursorBlinkEventHandler(this);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.5), cursorBlink);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }

}
