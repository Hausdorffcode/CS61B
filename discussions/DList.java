public class DList<T>{
	public class DListNode{
		T item;
		DListNode prev;
		DListNode next;
		
		public DListNode(T x, DListNode p, DListNode n){
			this.item = x;
			this.prev = p;
			this.next = p;
		}
		
		public DListNode(T x){
			this.item = x;
			this.next = this;
			this.prev = this;
		}
	}
	
	private DListNode setinel;
	private int size;
	
	public DList(){
		setinel = new DListNode(null);
		size = 0;
	}
	
	public DList(T x){
		setinel = new DListNode(null);
		DListNode node = new DListNode(x, setinel, setinel);
		setinel.next = node;
		setinel.prev = node;
		size = 1;
	}
	
	public int size(){
		return size;
	}
	
	public T getFrontNodeItem(){
		return setinel.next.item;
	}
	
	public T getBackNodeItem(){
		return setinel.prev.item;
	}
	
	public void insertFront(T x){
		DListNode node = new DListNode(x, setinel, setinel.next);
		setinel.next.prev = node;
		setinel.next = node;
		size++;
	}
	
	public void insertBack(T x){
		DListNode node = new DListNode(x, setinel.prev, setinel);
		setinel.prev.next = node;
		setinel.prev = node;
		size++;
	}
	
	public void removeFront(){
		if (setinel.next != setinel){    //make the "size" meaningful
			setinel.next = setinel.next.next;
			setinel.next.prev = setinel;
			size--;
		}
	}
	
	public void removeBack(){
		if (setinel.prev != setinel){
			setinel.prev = setinel.prev.prev;
			setinel.prev.next = setinel;
			size--;
		}
	}
}