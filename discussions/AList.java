public class AList<Item>{
	private Item[] items;
	private int size;
	private static int REACTOR = 2;
	private static double Ratio = 1/4;
	
	public AList(){
		size = 0;
		items = (Item[]) new Object[100];
	}
	
	/**helper method
	  *Resize our backing array so that it is of the given capacity.*/
	private void resize(int capacity){
		Item[] a = (Item[]) new Object[capacity];
		System.arraycopy(items, 0, a, 0, size);
		items = a;
	}
	public void insertBack(Item x){
		if(size == items.length){
			resize(size * REACTOR);
		}
		items[size] = x;
		size++;
	}
	
	public Item getBack(){
		return items[size-1];
	}
	
	public Item get(int i){
		return items[i];
	}
	
	public int size(){
		return size;
	}
	
	public Item deleteBack(){
		Item itemToReturn = getBack();
		items[size-1] = null; //not strictly necessary, but it's a good habit, when the array is of type "Item", if it don't assignment to null, it will waste the space because of the object which it point to will exist still. 
		size--;
		if((double)size/items.length < Ratio){
			resize(size * REACTOR);
		}
		return itemToReturn;
	}
	
	public void printList(){
		for(int i = 0; i < size; i++){
			System.out.print(items[i]);
			System.out.print(" ");
		}
		System.out.println();
	}
	
	public Item[] insert(Item item, int position){
		if(size == items.length){
			resize(size * REACTOR);
		}
		size++;
		if (position >= size){
			items[size] = item;
		}
		else{
			for(int i = size; i > position; i--){
				items[i] = items[i-1];
			}
			items[position] = item;
		}
		return items;
	}
	
	/**exchange the items with index of x and y respectly*/
	private void swap(int x, int y){
		Item temp = items[x];
		items[x] = items[y];
		items[y] = temp;
	}
	public void reserve(){
		int mid = size / 2;
		for (int i = 0; i < mid; i++){
			swap(i, size-i-1);
		}
	}
	
	
	public static void main(String[] args){
		AList<String> a = new AList<String>();
		a.insertBack("biubiu");
		a.insertBack("coco");
		a.insertBack("didi");
		a.insertBack("e");
		
		a.insert("aiai", 0);
		a.printList();
		
		a.reserve();
		a.printList();
	}
}