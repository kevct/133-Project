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
 * The Drone class defines the attributes of a drone
 */

public class Drone extends Movable{
	
	//Constructor
	public Drone(int size, Point point, int heading, int speed) {
		super(size, point, ColorUtil.MAGENTA); //Sets size, initial location, and color of drone		
		setHeading(heading); //Sets heading of drone to heading
		setSpeed(speed); //Sets speed of drone to speed
	}
	
	/**
	* The setColor() method overrides the superclass' method
	* this method does nothing because Drones cannot change
	* color once created
	* @param Point This is the new color of the Base
	* @return nothing
	*/
	@Override
	public void setColor(int c) {
	}
	
	/**
	 * Draws the object
	 * @param nothing
	 * @return nothing
	 */
	public void draw(Graphics g, Point pCmpRelPrnt) {
		int x = (int)Math.round(pCmpRelPrnt.getX() + getLocation().getX());
		int y = (int)Math.round(pCmpRelPrnt.getY() + getLocation().getY());;
		
		int[] arrx = {x, x+getSize()/2, x-getSize()/2};
		int[] arry = {y+getSize()/2, y-getSize()/2, y-getSize()/2};
		
		g.setColor(getColor());
		g.drawPolygon(arrx, arry, 3);
		//g.setColor(ColorUtil.MAGENTA);
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
			System.out.println("Drone collided with Base " + ((Base)otherObject).getSeqNumber());		
		}else if(otherObject instanceof EnergyStation) {
			System.out.println("Drone collided with EnergyStation");		
		}else if(otherObject instanceof Drone) {
			System.out.println("Drone collided with Drone");		
		}else if(otherObject instanceof NPCyborg) {
			System.out.println("Drone collided with NPC");		
		}else if(otherObject instanceof PlayerCyborg) {
			System.out.println("Drone collided with PlayerCyborg");		
		}
	}
	
	/**
	* The toString method returns the location, color, heading, 
	* speed, and size of the drone
	* @param nothing
	* @return String This is the data about the particular Drone
	*/
	public String toString() {
		//Location of the drone
		String loc = "loc=" + Math.round(getLocation().getX()*10.0)/10.0+ "," + Math.round(getLocation().getY()*10.0)/10.0;
		//Color of the drone [r,g,b]
		String color = "color=[" + ColorUtil.red(getColor()) + "," + ColorUtil.green(getColor()) + "," + ColorUtil.blue(getColor()) + "]";
		
		return "Drone: " + loc + " " + color + " heading=" + getHeading() + " speed=" + getSpeed()+ " size=" + getSize() + "\n";
	}
}
