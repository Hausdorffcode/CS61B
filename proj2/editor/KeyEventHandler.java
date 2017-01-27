
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

/**
 * Created by huangqiming on 2017/1/26.
 */
public class KeyEventHandler implements EventHandler<KeyEvent> {

    private Scene scene;
    private Group root;
    private AugmentationLinkedListBuffer buffer;
    private Cursor cursor;

    KeyEventHandler(Scene scene, Group root, AugmentationLinkedListBuffer buffer, Cursor cursor) {
        this.scene = scene;
        this.root = root;
        this.buffer = buffer;
        this.cursor = cursor;
    }

    @Override
    public void handle(KeyEvent keyEvent) {
        if (keyEvent.isShortcutDown()) {
            KeyCode code = keyEvent.getCode();
            if (code == KeyCode.Z) {

            } else if (code == KeyCode.S) {

            } else if (code == KeyCode.C) {

            } else if (code == KeyCode.V) {

            } else if (code == KeyCode.Y) {

            } else if (code == KeyCode.PLUS || code == KeyCode.EQUALS) {
                buffer.incFontSizeBy4();
            } else if (code == KeyCode.MINUS) {
                buffer.decFontSizeBy4();
            } else if (code == KeyCode.P) {
                System.out.println(cursor.getX() + " " + cursor.getY());
            }
            keyEvent.consume();
        } else if (keyEvent.getEventType() == KeyEvent.KEY_TYPED) {
            String characterTyped = keyEvent.getCharacter();
            if (characterTyped.length() > 0 && characterTyped.charAt(0) != 8) {
                // Ignore control keys, which have non-zero length, as well as the backspace
                // key, which is represented as a character of value = 8 on Windows.
                char toInert = characterTyped.charAt(0);
                buffer.addChar(toInert);
                if (characterTyped == "\r\n") {
                    System.out.println("jj");
                }
                /**Marks this Event as consumed. This stops its further propagation.*/
                keyEvent.consume();
            }
        } else if (keyEvent.getEventType() == KeyEvent.KEY_PRESSED) {
            // Arrow keys should be processed using the KEY_PRESSED event, because KEY_PRESSED
            // events have a code that we can check (KEY_TYPED events don't have an associated
            // KeyCode).
            KeyCode code = keyEvent.getCode();
            if (code == KeyCode.BACK_SPACE) {
                buffer.deleteChar();
            } else if (code == KeyCode.UP) {

            } else if (code == KeyCode.DOWN) {

            }
        }
        Render.render(scene, root, buffer, cursor);
    }
}
