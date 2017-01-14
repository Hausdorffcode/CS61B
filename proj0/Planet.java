public class Planet{
	public double xxPos;   //it's current x position
	public double yyPos;   //it's current y position
	public double xxVel;   //it's current velocity in the x direction
	public double yyVel;   //it's current velocity in the y direction
	public double mass;    //it's mass
	public String imgFileName;  //The name of an image in the images directory that depicts the planet
	
	public Planet(double xP, double yP, double xV, double yV, double m, String img){
		this.xxPos = xP;
		this.yyPos = yP;
		this.xxVel = xV;
		this.yyVel = yV;
		this.mass = m;
		this.imgFileName = img;
	}
	
	public Planet(Planet P){
		this.xxPos = P.xxPos;
		this.yyPos = P.yyPos;
		this.xxVel = P.xxVel;
		this.yyVel = P.yyVel;
		this.mass = P.mass;
		this.imgFileName = P.imgFileName;
	}
	
	public double calcDistance(Planet P){
		double dx = P.xxPos - this.xxPos;
		double dy = P.yyPos - this.yyPos;
		double r = Math.sqrt(dx * dx + dy * dy);
		return r;
	}
	
	public double calcForceExertedBy(Planet P){
		double G = 6.67E-11;
		double r = this.calcDistance(P);
		double F = (G * P.mass * this.mass) / (r*r);
		return F;
	}
	
	public double calcForceExertedByX(Planet P){
		double F = this.calcForceExertedBy(P);
		double dx = P.xxPos - this.xxPos;
		double r = this.calcDistance(P);
		double Fx = F * dx / r;
		return Fx;
	}
	
	public double calcForceExertedByY(Planet P){
		double F = this.calcForceExertedBy(P);
		double dy = P.yyPos - this.yyPos;
		double r = this.calcDistance(P);
		double Fy = F * dy / r;
		return Fy;
	}
	
	public double calcNetForceExertedByX(Planet[] Ps){
		double NetX = 0;
		for(int i = 0; i < Ps.length; i++){
			if(!Ps[i].equals(this)){
				NetX += this.calcForceExertedByX(Ps[i]);
			}
		}
		return NetX;
	}
	
	public double calcNetForceExertedByY(Planet[] Ps){
		double NetY = 0;
		for (int i = 0; i < Ps.length; i++){
			if(!this.equals(Ps[i])){
				NetY += this.calcForceExertedByY(Ps[i]);
			}
		}
		return NetY;
	}
	
	public void update(double dt, double Netx, double Nety){
		double ax = Netx / this.mass;
		double ay = Nety / this.mass;
		double vx = this.xxVel + dt * ax;
		double vy = this.yyVel + dt * ay;
		this.xxVel = vx;
		this.yyVel = vy;
		double px = this.xxPos + dt * vx;
		double py = this.yyPos + dt * vy;
		this.xxPos = px;
		this.yyPos = py;
	}
	
	public void draw(){
		String perPath = "./images/";
		StdDraw.picture(this.xxPos, this.yyPos, perPath + this.imgFileName);
	}
}
