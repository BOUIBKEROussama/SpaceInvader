package Model;

import java.util.ArrayList;

import Tools.Direction;

public class Monster extends Entity{

	private int healthPoint;


	
	public Monster(double x, double y, double longueur, double largeur, int healthPoint) {
		super(x, y, longueur, largeur);
		this.healthPoint = healthPoint;
	}

	public int getHealthPoint() {
		return healthPoint;
	}

	public void setHealthPoint(int healthPoint) {
		this.healthPoint = healthPoint;
	}
	// A modifier pas sur peut etre liste 
	
	public Missile shotMonster() {
		return new Missile(this.getX()+this.getLargeur()/2,this.getY()+this.getLongueur(),Direction.DOWN);
	}
	
}
