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
 * The Base class defines the attributes of bases
 */
public class Base extends Fixed{
	private int sequenceNumber;
	
	//Constructor for Base
	public Base(int size, Point point, int seqNum) {
		super(size, point, ColorUtil.BLUE); //Sets size, location, and color of base
		sequenceNumber = seqNum;
		setSelected(false);
	}
	
	/**
	* The setColor() method overrides the superclass' method
	* this method does nothing because Bases cannot change
	* color once created
	* @param Point This is the new color of the Base
	* @return nothing
	*/
	@Override
	public void setColor(int c) {
	}
	
	/**
	* The getSequenceNumber() method returns the base's sequence
	* number
	* @param nothing
	* @return int This is the sequence Number
	*/
	public int getSeqNumber() {
		return sequenceNumber;
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
		if(isSelected()) {
			g.drawPolygon(arrx, arry, 3);
			g.setColor(ColorUtil.BLACK);
		}else {
			g.fillPolygon(arrx, arry, 3);
			g.setColor(ColorUtil.WHITE);
		}
		g.drawString(Integer.toString(sequenceNumber), x-getSize()/10, y-getSize()/4);
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
			System.out.println("Base collided with Base " + ((Base)otherObject).getSeqNumber());		
		}else if(otherObject instanceof EnergyStation) {
			System.out.println("Base collided with EnergyStation");		
		}else if(otherObject instanceof Drone) {
			System.out.println("Base collided with Drone");		
		}else if(otherObject instanceof NPCyborg) {
			System.out.println("Base collided with NPC");		
		}else if(otherObject instanceof PlayerCyborg) {
			System.out.println("Base collided with PlayerCyborg");		
		}
	}
	
	/**
	* The toString method returns the location, color, size, 
	* and sequence number of the base
	* @param nothing
	* @return String This is the data about the particular Base
	*/
	public String toString() {
		//Location of the base
		String loc = "loc=" + getLocation().getX()+ "," + getLocation().getY();
		//Color of the base [r,g,b]
		String color = "color=[" + ColorUtil.red(getColor()) + "," + ColorUtil.green(getColor()) + "," + ColorUtil.blue(getColor()) + "]";
		
		return "Base: " + loc + " " + color + " size=" + getSize() + " seqNum=" + sequenceNumber + "\n";
	}
}
