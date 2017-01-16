public class LinkedListDeque<Item>{
	public class Node{
		public Item item;
		public Node prev;
		public Node next;
		
		public Node(){
			this.item = null;
			this.prev = this;
			this.next = this;
		}
		
		public Node(Item i, Node p, Node n){
			item = i;
			prev = p;
			next = n;
		}
	}
	
	private int size;
	private Node sentinel;
	
	public LinkedListDeque(){
		sentinel = new Node();
		size = 0;
	}
	
	public LinkedListDeque(Item item){
		size = 1;
		sentinel = new Node();
		Node n = new Node(item, sentinel, sentinel);
		sentinel.prev = n;
		sentinel.next = n;
	}
	
	public void addFirst(Item item){
		size++;
		Node n = new Node(item, sentinel, sentinel.next);
		sentinel.next.prev = n;
		sentinel.next = n;
	}
	
	public void addLast(Item item){
		size++;
		Node n = new Node(item, sentinel.prev, sentinel);
		sentinel.prev.next = n;
		sentinel.prev = n;
	}
	
	public boolean isEmpty(){
		return size == 0;
	}
	
	public int size(){
		return size;
	}
	
	public void printDeque(){
		Node ptr = sentinel.next;
		while(ptr != sentinel){
			System.out.print(ptr.item);
			System.out.print(' ');
			ptr = ptr.next;
		}
		System.out.println();
	}
	
	public Item removeFirst(){
		if (sentinel.next == sentinel){
			return null;
		}
		Node n = sentinel.next;
		n.next.prev = sentinel;
		sentinel.next = n.next;
		size--;
		return n.item;
	}
	
	public Item removeLast(){
		if (sentinel.prev == sentinel){
			return null;
		}
		Node n = sentinel.prev;
		n.prev.next = sentinel;
		sentinel.prev = n.prev;
		size--;
		return n.item;
	}
	
	public Item get(int index){
		if (index >= size || index < 0){
			return null;
		}
		Node ptr = sentinel.next;
		for (int i = 0; i < index; i++){
			ptr = ptr.next;
		}
		return ptr.item;
	}
	
	public Item getRecursive(int index){
		if (index >= size || index < 0){
			return null;
		}
		return getRecursiveHelper(index, sentinel.next);
	}
	private Item getRecursiveHelper(int n, Node ptr){
		if(n == 0){
			return ptr.item;
		}
		return getRecursiveHelper(n-1, ptr.next);
	}
}