public class SList<T>{
	public class Node{
	public T item;
	public Node next;
	
	public Node(T x, Node next){
		this.item = x;
		this.next = next;
	}
}
	
	private Node sentinel; //this sentinel is in front of the list
	private int size;
	
	public SList(){
		sentinel = new Node(null, null);
		size = 0;
	}
	
	public SList(T x){
		size = 1;
		sentinel = new Node(null, null);
		sentinel.next = new Node(x, null);
	}
	
	public void insertFront(T x){
		size++;
		Node oldActualFrontNode = sentinel.next;
		sentinel.next = new Node(x, oldActualFrontNode);
	}
	
	/*helper method*/
	private Node getBackNode(){
		Node p = sentinel;  //do not worry about p == null any more!
		while(p.next != null){
			p = p.next;
		}
		return p;
	}
	public void insertBack(T x){
		size++;
		Node p = getBackNode();
		p.next = new Node(x, null);
	}
	
	public T getFrontNodeItem(){
		return sentinel.next.item;
	}
	
	public int Size(){
		return this.size;
	}
}