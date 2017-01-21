import ug.joshh.animal.Dog;
public class ExternalDogLauncher{
	public static void main(String[] args){
		Dog d = new Dog("bobo", "shrimp dog", 9);
		System.out.println(d);
	}
}

/**another way*/
/**
public class ExternalDogLauncher{
	public static void main(String[] args){
		ug.joshh.animal.Dog d = new ug.joshh.animal.Dog("bobo", "shrimp dog", 9);
		System.out.println(d);
	}
}
*/