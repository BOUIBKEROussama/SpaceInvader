package Model;

import java.util.ArrayList;

public class Spaceship extends Entity{
	
	private int nbLife;

	public int getNbLife() {
		return nbLife;
	}


	public Spaceship(double x, double y, double longueur, double largeur, int nbLife) {
		super(x, y, longueur, largeur);
		this.nbLife = nbLife;
	}
	
	public void setNbLife(int nbLife) {
		this.nbLife = nbLife;
	}	
	
	
	public void shotSpaceship(ArrayList<Missile> m) {
		m.add(new Missile(this.getY(),this.getX(),0,0));
	}
	
}
