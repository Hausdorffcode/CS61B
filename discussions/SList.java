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
	
	/**insert a new element at the given position.
	  *If the position is past the end of the list, insert the new node 
	  *at the end of the list.*/
	public void insert(T x, int position){
		size++;
		if (position >= size){
			insertBack(x);
		}
		else{
			Node ptr = sentinel;
			for (int i = 0; i < position; i++){
				ptr = ptr.next;
			}
			Node n = new Node(x, ptr.next);
			ptr.next = n;
		}
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
	
	/*recursively*/
	private Node reverse1Helper(Node x){
		if(x.next == null || x == null){
			return x;
		}
		Node reserved = reverse1Helper(x.next);
		x.next.next = x; //此时x.next已经被换到最后了
		x.next = null;
		return reserved;
	}
	public void reverse1(){
		sentinel.next = reverse1Helper(sentinel.next);
	}
	
	/*iteratively*/
	public void reverse2(){
		Node p = null;		
		Node q = sentinel.next;	
		while(q != null){
			Node r = q.next;
			q.next = p;
			p = q;
			q = r;
		}
		sentinel.next = p;
	}
	
	public void printSList(){
		Node ptr = sentinel.next;
		while(ptr != null){
			System.out.print(ptr.item);
			System.out.print(' ');
			ptr = ptr.next;
		}
		System.out.println();
	}
	
	
	public static void main(String[] args){
		SList<String> s = new SList<String>();
		s.insertBack("biubiu");
		s.insertBack("coco");
		s.insertBack("didi");
		s.insertBack("e");
		s.insert("aiai", 0);
		s.printSList();
		
		s.reverse2();
		s.printSList();
	}
}