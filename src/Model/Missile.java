package Model;

import Tools.Direction;

public class Missile extends Entity{

	private double X;
	private double Y;
	private Direction d;

	public Missile(double x, double y, Direction d) {
		super(x,y,5,5);
		this.d = d;
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
	public Direction getDirection() {
		return d;
	}
	public void setDirection(Direction d) {
		this.d = d;
	}

}
