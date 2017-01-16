public class ArrayDeque<Item>{
	private int size;
	private Item[] items;
	private int nextFirst;
	private int nextLast;
	private static double ratio = 1/4;
	private static int FACTOR = 2;
	
	public ArrayDeque(){
		size = 0;
		items = (Item[]) new Object[8];
		nextFirst = 4;
		nextLast = 5;
	}
	
	private void resize(int capcaticy){
		Item[] a = (Item[]) new Object[capcaticy];
		int startIndex = (capcaticy - size) / 2;
		if (nextFirst < nextLast){
			for(int i = nextFirst + 1; i < nextLast; i++){
				a[startIndex] = items[i];
			}
		}
		else{
			for(int i = nextFirst + 1; i < items.length; i++){
				a[startIndex] = items[i];
			}
			for(int j = 0; j < nextLast; j++){
				a[startIndex + items.length - nextFirst] = items[j];
			}
		}
		items = a;
		nextFirst = startIndex - 1;
		nextLast = nextFirst + size + 1;
	}
	
	public void addFirst(Item x){
		size++;
		if (size >= items.length - 2){
			resize(size * FACTOR);
		}
		items[nextFirst] = x;
		if (nextFirst == 0){
			nextFirst = items.length - 1;
		}
		else{
			nextFirst = nextFirst - 1;
		}		
	}
	
	public void addLast(Item x){
		size++;
		if (size >= items.length - 2){
			resize(size * FACTOR);
		}
		items[nextLast] = x;
		if (nextLast == items.length - 1){
			nextLast = 0;
		}
		else{
			nextLast += 1;
		}
	}
	
	public boolean isEmpty(){
		return size == 0;
	}
	
	public int size(){
		return size;
	}
	
	public void printDeque(){
		if (nextFirst < nextLast){
			for (int i = nextFirst + 1; i < nextLast; i++){
				System.out.print(items[i]);
				System.out.print(' ');
			}
		}
		else{
			for (int i = nextFirst + 1; i < items.length; i++){
				System.out.print(items[i]);
				System.out.print(' ');
			}
			for (int j = 0; j < nextLast; j++){
				System.out.print(items[j]);
				System.out.print(' ');
			}
		}
		System.out.println();
	}
	
	public Item removeFirst(){
		if (size == 0){
			return null;
		}
		if (items.length >= 16 && (double)size / items.length <= ratio){
			resize(size * FACTOR);
		}
		Item res;
		if (nextFirst == items.length - 1){
			res = items[0];
			nextFirst = 0;	
		}
		else{
			res = items[nextFirst + 1];
			nextFirst += 1;
		}
		items[nextFirst] = null;
		size--;
		return res;
	}
	
	public Item removeLast(){
		if (size == 0){
			return null;
		}
		if (items.length >= 16 && (double)size / items.length <= ratio){
			resize(size * FACTOR);
		}
		Item res;
		if (nextLast == 0){
			res = items[items.length - 1];
			nextLast = items.length - 1;
		}
		else{
			res = items[nextLast - 1];
			nextLast -= 1;
		}
		items[nextLast] = null;
		size++;
		return res;
	}
	
	public Item get(int index){
		return items[(index+nextFirst+1)% items.length];
	}
}