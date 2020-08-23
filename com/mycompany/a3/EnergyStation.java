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
 * The EnergyStation class defines the attributes of energy stations
 */
public class EnergyStation extends Fixed{
	private int capacity;
	
	//Constructor for EnergyStation
	public EnergyStation(int size, Point point) {
		super(size, point, ColorUtil.GREEN); //Sets size, location, and color of energy station
		capacity = getSize()/3; //Initial capacity of energy station is proportional to its size
		setSelected(false);
	}
	
	/**
	* The setCapacity() method sets the capacity of the energy station
	* @param int This is the capacity of the energy station
	* @return nothing
	*/
	public void setCapacity(int c) {
		capacity = c;
	}
	
	/**
	* The getCapacity() method returns the capacity of the energy station
	* @param nothing
	* @return int This is the capacity of the energy station
	*/
	public int getCapacity() {
		return capacity;
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
		if(isSelected()) {
			g.drawArc(x, y, getSize(), getSize(), 0, 360);
		}else {
			g.fillArc(x, y, getSize(), getSize(), 0, 360);
		}
		g.setColor(ColorUtil.BLACK);
		g.drawString(Integer.toString(capacity), x, y);
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
			System.out.println("ES collided with Base " + ((Base)otherObject).getSeqNumber());		
		}else if(otherObject instanceof EnergyStation) {
			System.out.println("ES collided with EnergyStation");		
		}else if(otherObject instanceof Drone) {
			System.out.println("ES collided with Drone");		
		}else if(otherObject instanceof NPCyborg) {
			System.out.println("ES collided with NPC");		
		}else if(otherObject instanceof PlayerCyborg) {
			System.out.println("ES collided with PlayerCyborg");		
		}
	}
	
	/**
	* The toString method returns the location, color, size, 
	* and capacity of the energy station
	* @param nothing
	* @return String This is the data about the particular energy station
	*/
	public String toString() {
		//Location of the energy station
		String loc = "loc=" + getLocation().getX()+ "," + getLocation().getY();
		//Color of the energy station [r,g,b]
		String color = "color=[" + ColorUtil.red(getColor()) + "," + ColorUtil.green(getColor()) + "," + ColorUtil.blue(getColor()) + "]";
		
		return "Energy Station: " + loc + " " + color + " size=" + getSize() + " capacity=" + capacity + "\n";
	}
}
