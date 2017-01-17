public class Dogs extends Animal{
	public Dogs (String name, int age){
		super(name, age);
		noise = "Woof!";
	}
	
	@Override
	public void greet(){
		System.out.println("Dog " + name + " says: " + makeNoise());
	}
	
	public void playFetch(){
		System.out.println("Fetch, " + name + "!");
	}
}