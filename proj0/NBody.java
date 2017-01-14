public class NBody{
	public static double readRadius(String path){
		In in = new In(path);
		int planetNum = in.readInt();
		double radius = in.readDouble();
		return radius;
	}
	
	public static Planet[] readPlanets(String path){
		In in = new In(path);
		int PlanetNum = in.readInt();
		double radius = in.readDouble();
		
		Planet[] planets = new Planet[PlanetNum];
		int count = 0;
		while(!in.isEmpty() && count < PlanetNum){
			double px = in.readDouble();
			double py = in.readDouble();
			double vx = in.readDouble();
			double vy = in.readDouble();
			double m = in.readDouble();
			String img = in.readString();
			planets[count] = new Planet(px, py, vx, vy, m, img);
			count++;
		}
		return planets;
	}
	
	public static void main(String[] args){
		if(args.length != 3){
			System.out.println("Please supply 2 numbers and a filename");
			/* System.exit ends the program early. */
			System.exit(0);
		}
		double T = Double.valueOf(args[0]);
		double dt = Double.valueOf(args[1]);
		String filename = args[2];
		double radius = readRadius(filename);
		Planet[] planets = readPlanets(filename);
		
		String imageToDraw = "./images/starfield.jpg";
		/** Sets up the universe so it goes from 
		  * -radius, -radius up to radius, radius */
		StdDraw.setScale(-radius, radius);
				
		double[] xForces = new double[planets.length];
		double[] yForces = new double[planets.length];
		
		/* play bgm*/
		//String audioToPlay = "./audio/2001.mid";
		//StdAudio.play(audioToPlay);
		
		for (double t = 0; t < T; t += dt){
			for(int i = 0; i < planets.length; i++){
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
			}
			for (int j = 0; j < planets.length; j++){
				planets[j].update(dt, xForces[j], yForces[j]);
			}
			/* Clears the drawing window. */
			StdDraw.clear();
			StdDraw.picture(0, 0, imageToDraw);
			
			for (Planet p : planets){
				p.draw();
			}
			/* Shows the drawing to the screen*/
			StdDraw.show(15);
			
		}
		
		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
			planets[i].xxPos, planets[i].yyPos, planets[i].xxVel, planets[i].yyVel, planets[i].mass, planets[i].imgFileName);	
		}	
		
	}
}