public class Cats extends Animal{
	@Override
	public void greet(){
		System.out.println("Cat " + name + " says: " + makeNoise());
	}
	
	public Cats(String name, int age){
		super(name, age);
		this.noise = "Meow!";
	}
	
}