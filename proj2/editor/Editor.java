//package editor;
import com.sun.xml.internal.bind.v2.TODO;
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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class Editor extends Application {
    private static final int WINDOW_WIDTH = 500;
    private static final int WINDOW_HEIGHT = 500;
    private static final int LRMARGIN = 5;  //left and right margin
    private static final int TBMARGIN = 0;  //top and bottom margin
    private static AugmentationLinkedListBuffer buffer = new AugmentationLinkedListBuffer();
    private static Cursor cursor = new Cursor(1, 24);
    private static String filename;
    private static Group root = new Group();
    private static Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT, Color.WHITE);

    @Override
    public void start(Stage primaryStage) {
        root.getChildren().add(cursor);
        for (Node node : buffer) {
            root.getChildren().add(node.text);
        }
        /** set the currentNode to begin*/
        Node begin = buffer.getLinePointer(0);
        buffer.setCurrentNode(begin);

        /**render the buffer and cursor to root according to scene*/
        render();

        EventHandler<KeyEvent> keyEventHandler = new KeyEventHandler();
        // Register the event handler to be called for all KEY_PRESSED and KEY_TYPED events.
        scene.setOnKeyTyped(keyEventHandler);
        scene.setOnKeyPressed(keyEventHandler);

        primaryStage.setTitle("My Editor");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("no filename is provided");
            System.exit(1);
        } else if (args.length == 1) {
            filename = args[0];
            readFromFileToBuffer();
        } else {
            // TODO: 2017/1/28 ;
        }
        launch(args);
    }

    private static void readFromFileToBuffer() {
        try {
            File file = new File(filename);
            if (!file.exists()) {
                // TODO: 2017/1/28
            }
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            int intRead = -1;
            while ((intRead = bufferedReader.read()) != -1) {
                // The integer read can be cast to a char, because we're assuming ASCII.
                char charRead = (char) intRead;
                buffer.addChar(charRead);
            }
            bufferedReader.close();
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("File not found! Exception was: " + fileNotFoundException);
        } catch (IOException ioException) {
            System.out.println("Error when reading; exception was: " + ioException);
        }

    }

    private static void saveFile() {
        try {
            FileWriter writer = new FileWriter(filename);
            for (Node node : buffer) {
                writer.write(node.text.getText());
            }
            writer.close();
        } catch (IOException ioException) {
            System.out.println("Error when writing; exception was: " + ioException);
        }
    }

    private class KeyEventHandler implements EventHandler<KeyEvent> {

        @Override
        public void handle(KeyEvent keyEvent) {
            if (keyEvent.isShortcutDown()) {
                KeyCode code = keyEvent.getCode();
                if (code == KeyCode.Z) {
                    // TODO: 2017/1/28
                } else if (code == KeyCode.S) {
                    saveFile();
                } else if (code == KeyCode.C) {
                    // TODO: 2017/1/28
                } else if (code == KeyCode.V) {
                    // TODO: 2017/1/28
                } else if (code == KeyCode.Y) {
                    // TODO: 2017/1/28
                } else if (code == KeyCode.PLUS || code == KeyCode.EQUALS) {
                    buffer.incFontSizeBy4();
                    cursor.setCursorHeight(getCharHeight());
                } else if (code == KeyCode.MINUS) {
                    buffer.decFontSizeBy4();
                    cursor.setCursorHeight(getCharHeight());
                } else if (code == KeyCode.P) {
                    System.out.println(cursor.getX() + "  " + cursor.getY());
                }
                keyEvent.consume();
            } else if (keyEvent.getEventType() == KeyEvent.KEY_TYPED) {
                String characterTyped = keyEvent.getCharacter();
                if (characterTyped.length() > 0 && characterTyped.charAt(0) != 8) {
                    // Ignore control keys, which have non-zero length, as well as the backspace
                    // key, which is represented as a character of value = 8 on Windows.
                    char toInsert = characterTyped.charAt(0);
                    buffer.addChar(toInsert);
                    root.getChildren().add(buffer.getCurrentNode().text);
                    /**Marks this Event as consumed. This stops its further propagation.*/
                    keyEvent.consume();
                }
            } else if (keyEvent.getEventType() == KeyEvent.KEY_PRESSED) {
                // Arrow keys should be processed using the KEY_PRESSED event, because KEY_PRESSED
                // events have a code that we can check (KEY_TYPED events don't have an associated
                // KeyCode).
                KeyCode code = keyEvent.getCode();
                if (code == KeyCode.BACK_SPACE) {
                    root.getChildren().remove(buffer.deleteChar().text);
                } else if (code == KeyCode.UP) {
                    // TODO: 2017/1/28
                } else if (code == KeyCode.DOWN) {
                    // TODO: 2017/1/28
                } else if (code == KeyCode.LEFT) {
                    buffer.setCurrentNodePrev();
                } else if (code == KeyCode.RIGHT) {
                    buffer.setCurrentNodeNext();
                }
            }
            render();
        }
    }

    private static double[] putAWord (double currentX, double currentY, ArrayList<Node> word) {
        double sceneWidth  = scene.getWidth();
        double[] currentPos = new double[2];

        for (int i = 0; i < word.size(); i++){
            double cWidth = Math.round(word.get(i).text.getLayoutBounds().getWidth());
            if (currentX + cWidth > sceneWidth - LRMARGIN) {
                currentX = LRMARGIN;
                currentY += getCharHeight();
                buffer.addLineList(word.get(i).next);
            }
            word.get(i).text.setX(currentX);
            word.get(i).text.setY(currentY);
            currentX += cWidth;
            word.get(i).text.setTextOrigin(VPos.TOP);
        }
        word.clear();
        currentPos[0] = currentX;
        currentPos[1] = currentY;
        return currentPos;
    }
    public static void render() {
        double sceneWidth  = scene.getWidth();
        double sceneHeight = scene.getHeight();
        double currentX = LRMARGIN;
        double currentY = TBMARGIN;
        double charHeight = getCharHeight();

        buffer.lineListClear();
        Node currentNode = buffer.getCurrentNode();
        buffer.setCurrentNode(buffer.getTail());
        buffer.addChar(' ');  //add the ' ' s.t. the last word can display correctly
        double wordLength = 0;
        ArrayList<Node> word = new ArrayList<>();
        for (Node node : buffer) {
            node.text.setFont(Font.font(buffer.fontName, buffer.fontSize));
            double charWidth = Math.round(node.text.getLayoutBounds().getWidth());
            if (node.text.getText().charAt(0) == '\r' || node.text.getText().charAt(0) == '\n') {
                if (wordLength + currentX> sceneWidth - LRMARGIN) {
                    currentX = LRMARGIN;
                    currentY += getCharHeight();
                    buffer.addLineList(node.next);
                }
                double[] currentPos = putAWord(currentX, currentY, word);
                wordLength = 0;
                currentX = LRMARGIN;
                currentY = currentPos[1] + charHeight;
                node.text.setX(currentX);
                node.text.setY(currentY);
                buffer.addLineList(node.next);
            } else if (node.text.getText().charAt(0) == ' ') {
                if (wordLength + currentX > sceneWidth - LRMARGIN) {
                    currentX = LRMARGIN;
                    currentY += charHeight;
                    buffer.addLineList(node.next);
                }
                double[] currentPos = putAWord(currentX, currentY, word);
                wordLength = 0;
                currentX = currentPos[0];
                currentY = currentPos[1];
                node.text.setX(currentX);
                node.text.setY(currentY);
                currentX += charWidth;
            } else {
                wordLength += charWidth;
                word.add(node);
            }
        }
        buffer.deleteChar();  //remove the ' '
        buffer.setCurrentNode(currentNode);
        /**set the position of cursor*/
        cursor.showCursor(buffer.getCurrentNode(), getCharHeight());
    }

    /**get the char height according to the font size by add a char and then remove a char*/
    private static double getCharHeight(){
        buffer.addChar('a');
        Node currentNode = buffer.getCurrentNode();
        currentNode.text.setFont(Font.font(buffer.fontName, buffer.fontSize));
        double charHeight = Math.round(currentNode.text.getLayoutBounds().getHeight());
        buffer.deleteChar();
        return charHeight;
    }
}