package Controller;

import java.util.ArrayList;

import Model.*;

public class GlobalController {

	private ArrayList<Missile> missiles;
	

	
	public void moveLeft(Entity e) {
		e.setX(e.getX()-1);
	}

	public void moveRight(Entity e) {
		e.setX(e.getX()+1);
	}

	public void moveUp(Entity e) {
		e.setY(e.getY()-1);
	}

	public void moveDown(Entity e) {
		e.setY(e.getY()+1);
	}
	
	// à complété avce la view
	public boolean collision(Entity e) {
		if(e.getY() <= 0 || e.getY()+e.getLongueur() >= 666 || e.getX() <= 0 || e.getX()+e.getLargeur() >= 666) {
			return true;
		}
		return false;
	}
}
