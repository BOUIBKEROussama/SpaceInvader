package Model;

import java.util.concurrent.TimeUnit;

import Tools.Direction;

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
	
	public Missile shotSpaceship() {
		return new Missile(this.getX()+this.getLargeur()/2,this.getY(),Direction.UP);
	}
	
}
