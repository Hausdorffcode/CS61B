public class Cat{
	public String name;
	public static String noise;  //note : static
	
	public Cat(String name, String noise){
		this.name = name;
		this.noise = noise;
	}
	
	public void play(){
		System.out.println(noise + "I'm " + name + "the cat!");
	}
	
	public static void anger(){
		noise = noise.toUpperCase();
	}
	
	public static void clam(){
		noise = noise.toLowerCase();
	}
	
	public static void main(String[] args){
		Cat a = new Cat("Cream", "Meow!");
		Cat b = new Cat("Tubbs", "Nyan!");
		a.play();
		b.play();
		Cat.anger();
		a.clam();
		a.play();
		b.play();
	}
}