
public class IntList{
	public int head;
	public IntList tail;
	
	public IntList(int value, IntList tail){
		this.head = value;
		this.tail = tail;
	}
	
	/**Destructively squares each element of the given IntList L.
     * Don't use 'new'; modify the original IntList.
     * Should be written iteratively.*/
	public static IntList SquareDestructive(IntList L){
		IntList pointer = L;
		while(pointer != null){
			pointer.head = pointer.head * pointer.head;
			pointer = pointer.tail;
		}
		return L;
	}
	
	/**Destructively squares each element of the given IntList L.
	 * Don't use 'new'; modify the original IntList.
	 * Should be written recursively*/
	 public static IntList SquareDestructive2(IntList L){
		 if (L == null){
			 return L;
		 }
		 L.head = L.head * L.head;
		 SquareDestructive2(L.tail);
		 return L;
	 }
	
	/**Non-destructively squares each element of the given IntList L.
	 * Don't modify the given IntList.
	 * Should be written recursively*/
	 public static IntList SquareNonDestructive(IntList L){
		 if (L == null){
			 return L;
		 }
		 IntList tail = SquareNonDestructive(L.tail);
		 IntList pointer = new IntList(L.head*L.head, tail);
		 return pointer;
	 }
	 
	/**Non-destructively squares each element of the given IntList L.
	 * Don't modify the given IntList.
	 * Should be written iteratively*/
	 public static IntList SquareNonDestructive2(IntList L){
		 if (L == null){
			 return L;
		 }
		 IntList pointerL = L.tail;
		 IntList B = new IntList(L.head * L.head, null);
		 IntList pointerB = B;
		 while (pointerL != null){
			 pointerB.tail = new IntList(pointerL.head * pointerL.head, null);
			 pointerB = pointerB.tail;
			 pointerL = pointerL.tail;
		 }
		 return B;
	 }
	 
	/*print out the List in order.*/
	public static void printList(IntList L){
		IntList pointer;
		for(pointer = L; pointer != null; pointer = pointer.tail){
			System.out.print(pointer.head + "  ");
		}
		System.out.println();
	}
	
	public static void main(String[] args){
		IntList L = new IntList(3, null);
		L = new IntList(10, L);
		L = new IntList(12, L);
		IntList.printList(L);
		IntList.printList(IntList.SquareNonDestructive2(L));
		IntList.printList(L);
		
	}
}