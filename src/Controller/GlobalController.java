package Controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;


import Model.*;
import Tools.Direction;
import View.View;

public class GlobalController {
	
	private Timer clock =new Timer(); 
	private ArrayList<Missile> missiles  = new ArrayList<Missile>();
	private ArrayList<Minion> minions =new ArrayList<Minion>();
	private Direction directionMinions = Direction.RIGHT;
	private boolean moveLeft = false;
	private boolean moveRight = false;
	private Spaceship spaceship;
	
	
	public GlobalController(){}
	
	public void bindSpaceship(Spaceship spaceship) {
		this.spaceship = spaceship;
	}
	
	public void moveLeft(Entity e) {
		e.setX(e.getX()-10);
	}

	public void moveRight(Entity e) {
		e.setX(e.getX()+10);
	}

	public void moveUp(Entity e) {
		e.setY(e.getY()-10);
	}

	public void moveDown(Entity e) {
		e.setY(e.getY()+10);
	}
	
	// à complété avce la view
	public boolean collisionDown(Entity e) {
		if(e.getY()+e.getLongueur() >= View.height  ){
			return true;
		}
		return false;
	}
	
	public boolean collisionRight(Entity e) {
		if(e.getX()+e.getLargeur() >= View.width) return true;
		return false;
	}
	
public boolean collisionLeft(Entity e) {
		if(e.getX() <= 0) return true;
		return false;
	}
	
	public void addMinion(Minion m) {
		minions.add(m);
	}
	
	public void moveAllMinions(Direction d) {
		
		if(d==Direction.LEFT) for(Minion m : minions) {
			moveLeft(m);
		}
		if(d==Direction.RIGHT) for(Minion m : minions) moveRight(m);
		if(d==Direction.DOWN) for(Minion m : minions) moveDown(m);
		if(d==Direction.UP) for(Minion m : minions) moveUp(m);
	}
	
	public ArrayList<Minion> getMinions(){
		return minions;
	}
	
	public void setSpaceshipDirection(Direction dir) {
		if(dir == Direction.LEFT ) moveLeft = true;
		if(dir == Direction.RIGHT ) moveRight = true;
	}
	
	public void addSpaceshipShot() {
		missiles.add(spaceship.shotSpaceship());
	}
	
	public void setSpaceshipOnRelease(Direction dir) {
		if(dir == Direction.LEFT ) moveLeft = false;
		if(dir == Direction.RIGHT ) moveRight = false;
	}
	
	private void updateSpaceshipPosition(){
	    if(moveRight  && !collisionRight(spaceship)) moveRight(spaceship);
	    if(moveLeft && !collisionLeft(spaceship)) moveLeft(spaceship);
	  }
	
	public void start() {
		clock.schedule(new TimerTask() {
			public void run() {
				updateSpaceshipPosition();
				
				/*if(collisionRight(minion)){
					
					Collections.reverse(minions);
					
					directionMinions = Direction.LEFT;
					moveAllMinions(Direction.DOWN);
					moveAllMinions(directionMinions);
					continue;
				}	
				if(collisionLeft(minion)) {
					Collections.reverse(minions);
					directionMinions = Direction.RIGHT;
					moveAllMinions(Direction.DOWN);
					moveAllMinions(directionMinions);
					continue;
				}							
					//System.out.println(minion.getX()+", "+minion.getY());
				}*/
				
				for(Minion m : minions) {
					if(collisionDown(m)) {
						minions.removeAll(minions);
					}
					if(collisionRight(m)) {
						directionMinions = Direction.LEFT;
						moveAllMinions(Direction.DOWN);
						break;
					}
					if(collisionLeft(m)) {
						directionMinions = Direction.RIGHT;
						moveAllMinions(Direction.DOWN);
						break;
					}
					
				}
				moveAllMinions(directionMinions);
				
				
			}
			
		}, 0, 100);
	}

	public ArrayList<Missile> getMissiles() {
		// TODO Auto-generated method stub
		return missiles;
	}
	
	public void updateMissilesPositions() {
		for(Missile m : missiles) {
			if(m.getDirection() == Direction.UP) {
				moveUp(m);
			}
				
		}
	}
}
