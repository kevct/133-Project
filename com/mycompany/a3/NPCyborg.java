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
 * The NPCyborg class defines the attributes of a Non Player Cyborg (NPC)
 */

public class NPCyborg extends Cyborg{
	private IStrategy curStrategy;

	//Constructor for NPC
	public NPCyborg(int size, Point point, int maxSpeed, int eLevel, int eConsumptionRate, int stratNum, Base nextBase) {
		super(size, point, maxSpeed, eLevel, eConsumptionRate);
		if(stratNum == 0) {
			curStrategy = new AttackStrategy(this);
		}else {
			curStrategy = new BaseStrategy(this, nextBase);
		}
	}
	
	/**
	* The invokeStrategy() method invokes the current strategy
	* @param nothing
	* @return nothing
	*/
	public void invokeStrategy() {
		curStrategy.apply();
	}
	
	/**
	* The setStrategy() method sets the current strategy to the specified strategy
	* @param IStrategy this is the new strategy to be set
	* @return nothing
	*/
	public void setStrategy(IStrategy s) {
		curStrategy = s;
	}
	
	/**
	* The getStrategy() method gets the current strategy
	* @param nothing
	* @return IStrategy this is the current strategy
	*/
	public IStrategy getStrategy() {
		return curStrategy;
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
		g.drawRect(x, y, getSize(), getSize());
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
	* max speed, steering direction, energy level, damage level, and 
	* current strategy of the NPC
	* @param nothing
	* @return String This is the data about the particular NPC
	*/
	public String toString() {
		//Determines which strategy the NPC currently is
		String strat = curStrategy instanceof BaseStrategy ? "BaseStrategy" : "AttackStrategy";
		//Location of the cyborg
		String loc = "loc=" + Math.round(getLocation().getX()*10.0)/10.0+ "," + Math.round(getLocation().getY()*10.0)/10.0;
		//Color of the cyborg [r,g,b]
		String color = "color=[" + ColorUtil.red(getColor()) + "," + ColorUtil.green(getColor()) + "," + ColorUtil.blue(getColor()) + "]";
		
		return "NPC: " + loc + " " + color + " heading= " + getHeading() + " speed=" + getSpeed() + " size=" + getSize() + "\n\t" + "maxSpeed=" + getMaxSpeed() + " steeringDirection=" + getSteeringDirection() + " energyLevel=" + getEnergyLevel() + " damageLevel=" + getDamageLevel() + " currentStrategy=" + strat + "\n";
	}
}
