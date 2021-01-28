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
	private ArrayList<Missile> spaceshipMissiles  = new ArrayList<Missile>();
	private ArrayList<Missile> minionsMissiles  = new ArrayList<Missile>();
	private ArrayList<Minion> minions =new ArrayList<Minion>();
	private Direction directionMinions = Direction.RIGHT;
	private boolean moveLeft = false;
	private boolean moveRight = false;
	private boolean spaceshipIsShooting = false;
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
	
	// � compl�t� avce la view
	public boolean collisionDown(Entity e) {
		if(e.getY()+e.getLongueur() >= View.height  )return true;
		return false;
	}
	
	public boolean collisionUp(Entity e) {
		if(e.getY() <= 0) return true;
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
	
	public void setSpaceshipShoot() {
		spaceshipIsShooting = true;
	}
	
	public void setSpaceshipShootOnRelease() {
		spaceshipIsShooting = false;
	}
	
	public void updateSpaceshipShoot() {
		if(spaceshipIsShooting == true) spaceshipMissiles.add(spaceship.shotSpaceship());
	}
	
	public void setSpaceshipOnRelease(Direction dir) {
		if(dir == Direction.LEFT ) moveLeft = false;
		if(dir == Direction.RIGHT ) moveRight = false;
	}
	
	private void updateSpaceshipPosition(){
	    if(moveRight  && !collisionRight(spaceship)) moveRight(spaceship);
	    if(moveLeft && !collisionLeft(spaceship)) moveLeft(spaceship);
	  }
	
	public ArrayList<Missile> getSpaceshipMissiles() {
		return spaceshipMissiles;
	}
	
	public ArrayList<Missile> getMinionsMissiles() {
		return minionsMissiles;
	}
	
	public void updateMissilesPositions() {
		System.out.println(spaceshipMissiles.size());
		Iterator<Missile> it1 = spaceshipMissiles.iterator();
		while(it1.hasNext()) {
			Missile m1 = it1.next(); 
			if(collisionUp(m1)) {
				it1.remove();
				continue;
			}
			moveUp(m1);
		}
		Iterator<Missile> it2 = minionsMissiles.iterator();
		while(it2.hasNext()) {
			Missile m2 = it2.next(); 
			if(collisionDown(m2)) {
				it2.remove();
				continue;
			}
			moveDown(m2);
		}
	}
	
	public void randomMinionsShoot() {
		int rand;
		for (Minion m : minions) {
			rand = (int) (Math.random()* 20);
			if(rand == 1) minionsMissiles.add(m.shotMonster());
		}
	}
	
	public void start() {
		clock.schedule(new TimerTask() {
			public void run() {
				updateSpaceshipPosition();
				updateSpaceshipShoot();
				randomMinionsShoot();
				updateMissilesPositions();
				for(Minion m : minions) {
					if(collisionDown(m)) {
						minions.removeAll(minions);
						break;
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

	public void stop() {
		// TODO Auto-generated method stub
		clock.cancel();
	}
}
