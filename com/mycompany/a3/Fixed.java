package com.mycompany.a3;

import com.codename1.charts.models.Point;

/**
 * @author Kevin Tran
 * CSC 133
 * Professor Muyan-Özçelik
 * Assignment 2
 * 
 * The Fixed class defines the basic attributes of all fixed game objects
 */

public abstract class Fixed extends GameObject implements ISelectable{
	private boolean selected;
	
	//Constructor for Fixed
	public Fixed(int s, Point p, int c){
		super(s, p, c);
	}
	
	/**
	* The setLocation() method overrides the superclass' method
	* this method does nothing because Fixed objects cannot change
	* locations once created
	* @param Point This is the new location of the object
	* @return nothing
	@Override
	public void setLocation(Point p) {
	}
	*/
	
	@Override
	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt) {
		boolean result = false;
		
		int locX = (int) Math.round(this.getLocation().getX() + pCmpRelPrnt.getX());
		int locY = (int) Math.round(this.getLocation().getY() + pCmpRelPrnt.getY());
		int dx = (int)Math.round(locX - pPtrRelPrnt.getX());
		int dy = (int)Math.round(locY - pPtrRelPrnt.getY());
		
		int thisR = getSize()/2;
		int otherR = 1;
		
		int dSquared = (dx*dx) + (dy*dy);
		int rSquared = (thisR + otherR) * (thisR + otherR);
		if(dSquared <= rSquared) {
			result = true;
		}
		return result;
	}
	
	@Override
	public void setSelected(boolean b) {
		selected = b;
	}
	@Override
	public boolean isSelected() {
		return selected;
	}
}
