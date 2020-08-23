package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

/**
 * @author Kevin Tran
 * CSC 133
 * Professor Muyan-Özçelik
 * Assignment 2
 * 
 * The PlayerCyborg class defines the attributes of a player cyborg
 */

public class PlayerCyborg extends Cyborg{
	private static PlayerCyborg thePlayerCyborg;
	
	//Constructor for PlayerCyborg
	private PlayerCyborg(int size, Point point, int maxSpeed, int eLevel, int eConsumptionRate) {
		super(size, point, maxSpeed, eLevel, eConsumptionRate);
		// TODO Auto-generated constructor stub
	}
	
	/**
	* The getPlayerCyborg() method checks if an instance of Player Cyborg already exists and if so 
	* returns that instance of Player Cyborg, otherwise it creates a new instance of Player Cyborg
	* @param int, Point, maxSpeed, eLevel, eConsumptionRate these are the initial values of the player cyborg
	* @return PlayerCyborg this is the player cyborg
	*/
	public static PlayerCyborg getPlayerCyborg(int size, Point point, int maxSpeed, int eLevel, int eConsumptionRate) {
		if(thePlayerCyborg == null) {
			thePlayerCyborg = new PlayerCyborg(size, point, maxSpeed, eLevel, eConsumptionRate);
		}
		return thePlayerCyborg;
	}
	
	/**
	 * Draws the object
	 * @param nothing
	 * @return nothing
	 */
	public void draw(Graphics g, Point pCmpRelPrnt) {
		int x = (int)Math.round(pCmpRelPrnt.getX() + getLocation().getX() - (getSize()/2));
		int y = (int)Math.round(pCmpRelPrnt.getY() + getLocation().getY() - (getSize()/2));;
		
		g.setColor(getColor());
		g.fillRect(x, y, getSize(), getSize());
		//g.setColor(ColorUtil.BLACK);
		//g.drawArc((int)Math.round(pCmpRelPrnt.getX() + getLocation().getX()), (int)Math.round(pCmpRelPrnt.getY() + getLocation().getY()), 3, 3, 0, 360);
	}
	
	/**
	 * Handles the collision
	 * @param GameObject
	 * @param GameWorld
	 * @return nothing
	 */
	public void handleCollision(GameObject otherObject, GameWorld gw) {
		if(otherObject instanceof Base) {
			gw.collideBase(this, (Base)otherObject);
		}else if(otherObject instanceof EnergyStation) {
			gw.collideEnergy(this, (EnergyStation)otherObject);
		}else if(otherObject instanceof Drone) {
			gw.collideDrone(this, (Drone)otherObject);
		}else if(otherObject instanceof Cyborg) {
			gw.collideCyborg(this, (Cyborg)otherObject);
		}
	}
	
	/**
	* The toString method returns the location, color, heading, speed, size,
	* max speed, steering direction, energy level, and damage level of the cyborg
	* @param nothing
	* @return String This is the data about the particular cyborg
	*/
	public String toString() {
		//Location of the cyborg
		String loc = "loc=" + Math.round(getLocation().getX()*10.0)/10.0+ "," + Math.round(getLocation().getY()*10.0)/10.0;
		//Color of the cyborg [r,g,b]
		String color = "color=[" + ColorUtil.red(getColor()) + "," + ColorUtil.green(getColor()) + "," + ColorUtil.blue(getColor()) + "]";
		
		return "Player Cyborg: " + loc + " " + color + " heading= " + getHeading() + " speed=" + getSpeed() + " size=" + getSize() + "\n\t" + "maxSpeed=" + getMaxSpeed() + " steeringDirection=" + getSteeringDirection() + " energyLevel=" + getEnergyLevel() + " damageLevel=" + getDamageLevel() + "\n";
	}
}
