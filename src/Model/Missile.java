package Model;

import Tools.Direction;

public class Missile extends Entity{

	private Direction d;

	public Missile(double x, double y, Direction d) {
		super(x,y,5,5);
		this.d = d;
	}
	
	public Direction getDirection() {
		return d;
	}
	public void setDirection(Direction d) {
		this.d = d;
	}

}
