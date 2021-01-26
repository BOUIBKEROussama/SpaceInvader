package Controller;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import Model.*;

public class GlobalController {
	
	private Timer clock =new Timer(); 
	private ArrayList<Missile> missiles  = new ArrayList<Missile>();
	private ArrayList<Monster> monsters =new ArrayList<Monster>();
	private String direction = "right";
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
	public boolean collision(Entity e) {
		if(e.getY() <= 0 || e.getY()+e.getLongueur() >= 10 || e.getX() <= 0 || e.getX()+e.getLargeur() >= 10) {
			return true;
		}
		return false;
	}
	
	public void addMonster(Monster m) {
		monsters.add(m);
	}
	
	public void start() {
		clock.schedule(new TimerTask() {
			public void run() {
				for(Monster m1 : monsters) {
					if(collision(m1)){
						if(direction == "right") { 
							direction = "left";
							for(Monster m2 : monsters) {
								moveDown(m2);
								moveLeft(m2);
							}
						}
						else{							
							if(direction == "left") { 
								direction = "right";
								for(Monster m2 : monsters) {
									moveDown(m2);
									moveRight(m2);
								}
							}
						}
					}
					else {
						if(direction == "right") {
							moveRight(m1);
						}
						if(direction == "left") {
							moveLeft(m1);
						}
						System.out.println(m1.getX()+", "+m1.getY()+", "+direction);
					}
				}
			}
			
		}, 0, 100);
	}
	
	
}
