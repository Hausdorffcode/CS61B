//package editor;
import javafx.application.Application;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import sun.util.resources.cldr.om.CurrencyNames_om;

import java.util.LinkedList;

public class Editor extends Application {
	private static final int WINDOW_WIDTH = 500;
    private static final int WINDOW_HEIGHT = 500;
    private static final int LRMARGIN = 5;  //left and right margin
    private static final int TBMARGIN = 0;  //top and bottom margin
    private static final int STARTING_FONT_SIZE = 20;  //default size
    private String fontName = "Verdana";
    private int fontSize = STARTING_FONT_SIZE;
    private AugmentationLinkedListBuffer buffer = new AugmentationLinkedListBuffer();
    private Cursor cursor = new Cursor(1, 20);

    @Override
    public void start(Stage primaryStage) {
		// Create a Node that will be the parent of all things displayed on the screen.
        Group root = new Group();
        // The Scene represents the window: its height and width will be the height and width
        // of the window displayed.
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT, Color.WHITE);

        /**render the buffer and cursor to root according to scene*/
        Render.render(scene, root, buffer, cursor);

        // To get information about what keys the user is pressing, create an EventHandler.
        // EventHandler subclasses must override the "handle" function, which will be called
        // by javafx.
        EventHandler<KeyEvent> keyEventHandler = new KeyEventHandler(scene, root, buffer, cursor);
        // Register the event handler to be called for all KEY_PRESSED and KEY_TYPED events.
        scene.setOnKeyTyped(keyEventHandler);
        scene.setOnKeyPressed(keyEventHandler);

        primaryStage.setTitle("My Editor");
        // This is boilerplate, necessary to setup the window where things are displayed.
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}