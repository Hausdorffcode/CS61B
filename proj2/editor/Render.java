
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
public class Render {
    private static final int LRMARGIN = 5;  //left and right margin
    private static final int TBMARGIN = 0;  //top and bottom margin

    public static void render(final Scene scene, final Group root, AugmentationLinkedListBuffer allb, Cursor cursor) {
        double sceneWidth  = scene.getWidth();
        double sceneHeight = scene.getHeight();
        double currentX = LRMARGIN;
        double currentY = TBMARGIN;
        //double charHeight = 0;

        root.getChildren().clear();
        allb.lineListClear();

        for (Node node : allb) {
            node.text.setFont(Font.font(allb.fontName, allb.fontSize));
            double charHeight = Math.round(node.text.getLayoutBounds().getHeight());
            double charWidth = Math.round(node.text.getLayoutBounds().getWidth());
            if (currentX + charWidth <= sceneWidth - LRMARGIN) {
                node.text.setX(currentX);
                node.text.setY(currentY);
                currentX += charWidth;
            } else {
                currentX = LRMARGIN;
                currentY += charHeight;
                node.text.setX(currentX);
                node.text.setY(currentY);
                currentX += charWidth;
                allb.addLineList(node);
            }
            node.text.setTextOrigin(VPos.TOP);
            root.getChildren().add(node.text);
        }
        showCursor(scene, root, allb, cursor);
    }

    private static void showCursor(Scene scene, Group root, AugmentationLinkedListBuffer allb, Cursor cursor) {
        Node currentNode = allb.getCurrentNode();
        if (currentNode.text == null) {
            cursor.setX(5);
            cursor.setY(0);
            cursor.setCursorHeight(20);
        } else {
            double charHeight = Math.round(currentNode.text.getLayoutBounds().getHeight());
            double charWidth = Math.round(currentNode.text.getLayoutBounds().getWidth());
            cursor.setX(currentNode.text.getX()+charWidth);
            cursor.setY(currentNode.text.getY());
            cursor.setCursorHeight(charHeight);
        }
        root.getChildren().add(cursor);
    }

}
