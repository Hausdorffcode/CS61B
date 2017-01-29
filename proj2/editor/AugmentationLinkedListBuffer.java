//package editor;

import javafx.scene.text.Text;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by huangqiming on 2017/1/25.
 */
public class AugmentationLinkedListBuffer implements Iterable<Node> {

    private Node sentinel;     //sentinel node for the linked list
    private Node currentNode;  //Current node to be inserted after
    private ArrayList<Node> lineList;//the lineList record the AugmentationLinkedListBuffer start line node
    public String fontName = "Verdana";
    public int fontSize = 20;

    /** the constructor */
    public AugmentationLinkedListBuffer(){
        sentinel = new Node();
        currentNode = sentinel;
        lineList = new ArrayList<Node>(10);
        lineList.add(sentinel);   //the 0th line
    }

    public Node getTail () {
        return sentinel.prev;
    }

    /** add the char x behind the currentNode and update the val "currentNode" and "currentPos"*/
    public void addChar(char x){
        Text textToInsert = new Text(String.valueOf(x));
        Node nodeToInsert = new Node(textToInsert);
        Node nextNode = currentNode.next;
        currentNode.next = nodeToInsert;
        nodeToInsert.next = nextNode;
        nextNode.prev = nodeToInsert;
        nodeToInsert.prev = currentNode;
        currentNode = nodeToInsert;
    }

    /** delete the Node of the currentNode ,if the buffer is not empty */
    public Node deleteChar(){
        if (currentNode == sentinel) {
            return null;
        }
        Node nodeToRemove = currentNode;
        currentNode.prev.next = currentNode.next;
        currentNode.next = currentNode.prev;
        currentNode = currentNode.prev;
        return nodeToRemove;
    }


    /** return the currentNode */
    public Node getCurrentNode() {
        return currentNode;
    }

    /**set currentNode*/
    public void setCurrentNode(Node n) {
        currentNode = n;
    }

    /**set currentNode to the next*/
    public void setCurrentNodeNext() {
        if (currentNode.next != sentinel) {
            currentNode = currentNode.next;
        }
    }

    /**set currentNode to the prev*/
    public void setCurrentNodePrev() {
        if (currentNode.prev != sentinel) {
            currentNode = currentNode.prev;
        }
    }

    /** return the pointer of the start node of ith line, the line count start 0*/
    public Node getLinePointer(int i){
        if (i >= lineList.size()) {
            throw new IndexOutOfBoundsException("out of the max line");
        }
        return lineList.get(i);
    }

    /**clear the lineList s.t. only record the 0th line*/
    public void lineListClear() {
        lineList = new ArrayList<Node>(10);
        lineList.add(sentinel);   //the 0th line
    }

    /** add new line start node to the lineList, the node is n.next*/
    public void addLineList(Node n) {
        if (n != sentinel) {
            lineList.add(n);
        }
    }

    public void incFontSizeBy4() {
        fontSize += 4;
    }

    public void decFontSizeBy4() {
        if (fontSize > 4) {
            fontSize -= 4;
        }
    }

    private class myIterator implements Iterator<Node> {
        private Node pointer = sentinel.next;
        public boolean hasNext() {
            return pointer != sentinel;
        }
        public Node next() {
            Node myNode = pointer;
            pointer = pointer.next;
            return myNode;
        }
    }

    public Iterator<Node> iterator(){
        return new myIterator();
    }
}
