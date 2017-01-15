public class SListLauncher{
	public static  void main(String[] args){
		SList<Integer> s1 = new SList<Integer>(5);
		s1.insertFront(10);
		
		SList<String> s2 = new SList<String>("app");
		s2.insertFront("apple");
	}
}