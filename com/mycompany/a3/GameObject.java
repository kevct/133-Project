package com.mycompany.a3;

import com.codename1.charts.models.Point;

/**
 * @author Kevin Tran
 * CSC 133
 * Professor Muyan-Özçelik
 * Assignment 2
 * 
 * The GameObject class defines the basic attributes
 * of all game objects
 */

public abstract class GameObject implements IDrawable, ICollider{
	private int size;
	private Point location;
	private int color;
	private GameObjectCollection collisions;
	
	//Constructor for GameObject
	public GameObject(int s, Point p, int c) {
		size = s;
		location = p;
		color = c;
		collisions = new GameObjectCollection();
	}
	
	/**
	* The getSize() method returns the size of a
	* particular object
	* @param nothing
	* @return int This returns the size of the object
	*/
	public int getSize() {
		return size;
	}
	
	/**
	* The setLocation() method sets the location of the object
	* @param Point This is the point which the location will be set to
	* @return nothing
	*/
	public void setLocation(Point p) {
		location = p;
	}
	
	/**
	* The getLocation() method gets the location of the object
	* @param nothing
	* @return Point This returns the location of the object
	*/
	public Point getLocation() {
		return location;
	}
	
	/**
	* The setColor() method sets the color of the object
	* @param int This is the new color of the object
	* @return nothing
	*/
	public void setColor(int c) {
		color = c;
	}
	
	/**
	* The getColor() method gets the color of the object
	* @param nothing
	* @return int This returns the color of the object
	*/
	public int getColor() {
		return color;
	}
	
	/**
	* Checks to see if object collided with another object
	* @param GameObject
	* @return boolean
	*/
	public boolean collidesWith(GameObject otherObject) {
		boolean result = false;
		
		int dx = (int)Math.round(getLocation().getX() - otherObject.getLocation().getX());
		int dy = (int)Math.round(getLocation().getY() - otherObject.getLocation().getY());
		
		int thisR = getSize()/2;
		int otherR = getSize()/2;
		
		int dSquared = (dx*dx) + (dy*dy);
		int rSquared = (thisR + otherR) * (thisR + otherR);
		if(dSquared <= rSquared) {
			result = true;
		}
		return result;
	}
	
	/**
	* Adds object to list of collided objects
	* @param GameObject
	* @return boolean
	*/
	public void addCollision(GameObject otherObject) {
		collisions.add(otherObject);
	}
	
	/**
	* removes object from list of collisions
	* @param GameObject
	* @return boolean
	*/
	public void removeCollision(GameObject otherObject) {
		GameObjectCollection g = new GameObjectCollection(); //Creates temporary GameObjectCollection
		IIterator i = collisions.getIterator();
		while(i.hasNext()) {
			GameObject o = i.getNext();
			if(o != otherObject) { //If the object is not the object to be removed, add it to the list of collisions
				g.add(o);
			}
		}
		collisions = g;
	}
	
	/**
	* Checks to see if collision already occurred
	* @param GameObject
	* @return boolean
	*/
	public boolean hasAlreadyCollided(GameObject otherObject) {
		boolean result = false;
		IIterator i = collisions.getIterator();
		while(i.hasNext()) {
			GameObject g = i.getNext();
			if(g == otherObject) {
				result = true;
				break;
			}
		}
		return result;
	}	
}
