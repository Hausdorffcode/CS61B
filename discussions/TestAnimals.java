public class TestAnimals{
	public static void main(String[] args){
		Animal a = new Animal("Pluto", 10);
		Cats c = new Cats("Garfield", 6);
		Dogs d = new Dogs("Fido", 4);
		
		a.greet();
		c.greet();
		d.greet();
		
		a = c;
		((Cats) a).greet();
		a.greet();
	}
}