
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

/**
 * Created by huangqiming on 2017/1/27.
 */
public class Node{
    Text text;
    Node prev, next;

    public Node(){
        this.text = new Text(" ");
        this.next = this;
        this.prev = this;
    }

    public Node(Text t){
        this.text = t;
        this.next = this;
        this.prev = this;
    }
}
