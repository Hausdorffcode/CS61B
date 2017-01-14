
/**
 * Tests Planet
 */
public class TestPlanet{
	/**
	 * Tests Planet
	 */
	 public static void main(String[] args){
		 checkPlanet();
	 }
	 
	/**
	 * creates two planets and prints out the pairwise force between them
	 */
	 
	private static void checkPlanet(){
		System.out.println("checking Planet....");
		Planet p1 = new Planet(1.0, 1.0, 3.0, 4.0, 5.0, "jupiter.gif");
        Planet p2 = new Planet(2.0, 1.0, 3.0, 4.0, 5.0, "jupiter.gif");
		System.out.println("The distance between p1 and p2 is: " + p1.calcDistance(p2));
	}
}