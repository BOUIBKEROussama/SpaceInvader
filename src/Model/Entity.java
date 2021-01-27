package Model;

public class Entity {
	
	private double X;
	private double Y;
	private double longueur;
	private double largeur;
	
	
	
	public Entity(double x, double y, double longueur, double largeur) {
		X = x;
		Y = y;
		this.longueur = longueur;
		this.largeur = largeur;
	}
	
	public double getX() {
		return X;
	}
	public void setX(double x) {
		X = x;
	}
	public double getY() {
		return Y;
	}
	public void setY(double y) {
		Y = y;
	}
	public double getLongueur() {
		return longueur;
	}
	public void setLongueur(double longueur) {
		this.longueur = longueur;
	}
	public double getLargeur() {
		return largeur;
	}
	public void setLargeur(double largeur) {
		this.largeur = largeur;
	}

}
