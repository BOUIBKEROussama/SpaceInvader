package Controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

import Model.*;
import Tools.Direction;

public class GlobalController {
	
	private Timer clock =new Timer(); 
	private ArrayList<Missile> missiles  = new ArrayList<Missile>();
	private ArrayList<Minion> minions =new ArrayList<Minion>();
	private Direction direction = Direction.RIGHT;
	 public GlobalController(){}
	
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
	public boolean collisionDown(Entity e) {
		if(e.getY()+e.getLongueur() >= 10  ){
			return true;
		}
		return false;
	}
	
	public boolean collisionRight(Entity e) {
		if(e.getX()+e.getLargeur() >= 10) return true;
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
		if(d==Direction.LEFT) for(Minion m : minions) moveLeft(m);
		if(d==Direction.RIGHT) for(Minion m : minions) moveRight(m);
		if(d==Direction.DOWN) for(Minion m : minions) moveDown(m);
		if(d==Direction.UP) for(Minion m : minions) moveUp(m);
	}
	
	public void start() {
		clock.schedule(new TimerTask() {
			public void run() {
				Iterator<Minion> it = minions.iterator();
				while(it.hasNext() ) {
					Minion minion = it.next();
					if(collisionDown(minion)) it.remove();
					if(collisionRight(minion)){
						direction = Direction.LEFT;
						moveAllMinions(Direction.DOWN);
						moveAllMinions(direction);
						continue;
					}	
					if(collisionLeft(minion)) { 
						direction = Direction.RIGHT;
						moveAllMinions(Direction.DOWN);
						moveAllMinions(direction);
						continue;
					}
					if(direction == Direction.RIGHT) moveRight(minion);
					if(direction ==  Direction.LEFT) moveLeft(minion);
					System.out.println(minion.getX()+", "+minion.getY());
				}
				System.out.println("------------------------------------");
			}
			
		}, 0, 100);
	}
	
	
}
