import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * A Generic heap class. Unlike Java's priority queue, this heap doesn't just
 * store Comparable objects. Instead, it can store any type of object
 * (represented by type T), along with a priority value.
 */
public class ArrayHeap<T> {
	private ArrayList<Node> contents = new ArrayList<Node>();
	private int size = 0;

	/**
	 * Inserts an item with the given priority value. This is enqueue, or offer.
	 */
	public void insert(T item, double priority) {
		Node newNode = new Node(item, priority);
		size++;
		setNode(size, newNode);
		bubbleUp(size);
	}

	/**
	 * Returns the Node with the smallest priority value, but does not remove it
	 * from the heap.
	 */
	public Node peek() {
		return getNode(0);
	}

	/**
	 * Returns the Node with the smallest priority value, and removes it from
	 * the heap. This is dequeue, or poll.
	 */
	public Node removeMin() {
		Node min = getNode(1);
		int lastIndex = size;
		swap(1, lastIndex);
		contents.remove(getNode(lastIndex));
		size--;
		bubbleDown(1);
		return min;
	}

	/**
	 * Change the node in this heap with the given item to have the given
	 * priority. For this method only, you can assume the heap will not have two
	 * nodes with the same item. Check for item equality with .equals(), not ==
	 */
	public void changePriority(T item, double priority) {
		Node n = getNode(1);
		for (Node node : contents) {
			if (node.item().equals(item)) {
				n = node;
			}
		}
		int index = contents.indexOf(n);
		if (priority > n.priority()) {
			n.setMyPriority(priority);
			bubbleDown(index);
		} else {
			n.setMyPriority(priority);
			bubbleUp(index);
		}
	}

	/**
	 * Prints out the heap sideways.
	 */
	@Override
	public String toString() {
		return toStringHelper(1, "");
	}

	/* Recursive helper method for toString. */
	private String toStringHelper(int index, String soFar) {
		if (getNode(index) == null) {
			return "";
		} else {
			String toReturn = "";
			int rightChild = getRightOf(index);
			toReturn += toStringHelper(rightChild, "        " + soFar);
			if (getNode(rightChild) != null) {
				toReturn += soFar + "    /";
			}
			toReturn += "\n" + soFar + getNode(index) + "\n";
			int leftChild = getLeftOf(index);
			if (getNode(leftChild) != null) {
				toReturn += soFar + "    \\";
			}
			toReturn += toStringHelper(leftChild, "        " + soFar);
			return toReturn;
		}
	}

	private Node getNode(int index) {
		if (index >= contents.size()) {
			return null;
		} else {
			return contents.get(index);
		}
	}

	private void setNode(int index, Node n) {
		// In the case that the ArrayList is not big enough
		// add null elements until it is the right size
		while (index + 1 >= contents.size()) {
			contents.add(null);
		}
		contents.set(index, n);
	}

	/**
	 * Swap the nodes at the two indices.
	 */
	private void swap(int index1, int index2) {
		Node node1 = getNode(index1);
		Node node2 = getNode(index2);
		this.contents.set(index1, node2);
		this.contents.set(index2, node1);
	}

	/**
	 * Returns the index of the node to the left of the node at i.
	 */
	private int getLeftOf(int i) {
		int index = 2 * i;
		return index;
	}

	/**
	 * Returns the index of the node to the right of the node at i.
	 */
	private int getRightOf(int i) {
		int index = 2 * i + 1;
		return index;
	}

	/**
	 * Returns the index of the node that is the parent of the node at i.
	 */
	private int getParentOf(int i) {
		int index = i / 2;
		return index;
	}

	/**
	 * Adds the given node as a left child of the node at the given index.
	 */
	private void setLeft(int index, Node n) {
		setNode(getLeftOf(index), n);
	}

	/**
	 * Adds the given node as the right child of the node at the given index.
	 */
	private void setRight(int index, Node n) {
		setNode(getRightOf(index), n);
	}

	/**
	 * Bubbles up the node currently at the given index.
	 */
	private void bubbleUp(int index) {
		while (index > 1 && getNode(getParentOf(index)).priority() > getNode(index).priority()) {
			swap(getParentOf(index), index);
			index = getParentOf(index);
		}
	}

	/**
	 * Bubbles down the node currently at the given index.
	 */
	private void bubbleDown(int index) {
		while (getLeftOf(index) <= size) {
			int j = getLeftOf(index);
			if (j <= size-1 && getNode(j).priority() > getNode(j+1).priority()) {
				j++;
			}
			if (getNode(j).priority() >= getNode(index).priority()) {
				break;
			}
			swap(j, index);
			index = j;
		}
	}

	/**
	 * Returns the index of the node with smaller priority. Precondition: Not
	 * both of the nodes are null.
	 */
	private int min(int index1, int index2) {
		Node node1 = getNode(index1);
		Node node2 = getNode(index2);
		if (node1 == null) {
			return index2;
		} else if (node2 == null) {
			return index1;
		} else if (node1.myPriority < node2.myPriority) {
			return index1;
		} else {
			return index2;
		}
	}

	public class Node {
		private T myItem;
		private double myPriority;

		private Node(T item, double priority) {
			myItem = item;
			myPriority = priority;
		}

		public T item() {
			return myItem;
		}

		public double priority() {
			return myPriority;
		}

		public void setMyPriority(double p) {
			this.myPriority = p;
		}

		@Override
		public String toString() {
			return item().toString() + ", " + priority();
		}
	}

	public static void main(String[] args) {
		ArrayHeap<String> heap = new ArrayHeap<String>();
		heap.insert("c", 3);
		heap.insert("i", 9);
		heap.insert("g", 7);
		heap.insert("d", 4);
		heap.insert("a", 1);
		heap.insert("h", 8);
		heap.insert("e", 5);
		heap.insert("b", 2);
		heap.insert("c", 3);
		heap.insert("d", 4);
		System.out.println(heap);
	}

}
