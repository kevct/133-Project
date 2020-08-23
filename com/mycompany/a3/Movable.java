package com.mycompany.a3;

import com.codename1.charts.models.Point;

/**
 * @author Kevin Tran
 * CSC 133
 * Professor Muyan-Özçelik
 * Assignment 2
 * 
 * This abstract class defines the basic attributes 
 * of movable objects
 */

public abstract class Movable extends GameObject{
	private int heading;
	private int speed;
	
	//Constructor for Movable
	public Movable(int s, Point p, int c){
		super(s, p, c);
	}
	
	/**
	* This method updates the object's location based
	* on its current heading and speed
	* @param nothing
	* @return nothing
	*/
	public void move(int msec) {
		if(this instanceof NPCyborg) {
			Cyborg cyborg = (NPCyborg)this;
			if(cyborg.getDamageLevel() > 100) {
				cyborg.setSpeed(0);
			}else {
				cyborg.setSpeed((int)(((100-cyborg.getDamageLevel())/100.0)*cyborg.getMaxSpeed()));

			}
		}
		
		//Tries to create a new MapView but will just return the same reference
		//since the Singleton pattern is used
		MapView mv = MapView.getMapView(new GameWorld()); 
		//Gets the change in X and Y directions of the movable object
		//deltaX = cos(θ) * speed where θ = 90 ˗ heading converted to radians
		float deltaX = (float)(Math.cos(Math.toRadians(90 - heading)) * speed * (msec/20));
		//deltaY = sin(θ) * speed where θ = 90 ˗ heading converted to radians
		float deltaY = (float)(Math.sin(Math.toRadians(90 - heading)) * speed * (msec/20));
		
		//Sets the new location to (x + deltaX, y + deltaY)
		setLocation(new Point(getLocation().getX() + deltaX, getLocation().getY() + deltaY));
		
		//If the new location is past the map width then set its location to have x = map width
		if(getLocation().getX() > mv.getWidth()) {
			setLocation(new Point((float)mv.getWidth(), getLocation().getY()));
			if(this instanceof Drone) {
				this.setHeading(this.getHeading()-180);
			}
		//If the new location is past x = 0 then set its location to have x = 0
		}else if(getLocation().getX() < 0) {
			setLocation(new Point((float)0, getLocation().getY()));
			if(this instanceof Drone) {
				this.setHeading(this.getHeading()-180);
			}
		}
		
		//If the new location is past the map height then set its location to have y = map height
		if(getLocation().getY() > mv.getHeight()) {
			setLocation(new Point(getLocation().getX(), (float)mv.getHeight()));
			if(this instanceof Drone) {
				this.setHeading(this.getHeading()-180);
			}
		//If the new location is past y = 0 then set its location to have y = 0
		}else if(getLocation().getY() < 0) {
			setLocation(new Point(getLocation().getX(), (float)0));
			if(this instanceof Drone) {
				this.setHeading(this.getHeading()-180);
			}
		}
	}
	
	/**
	* This method sets the speed of the object to the new specified speed
	* @param int This is the new speed which the object will now be set to
	* @return nothing
	*/
	public void setSpeed(int s) {
		speed = s;
		if(this instanceof Cyborg) {
			if(((Cyborg)this).getMaxSpeed() < speed) {
				speed = ((Cyborg)this).getMaxSpeed();
			}
		}
	}
	
	/**
	* This method gets the speed of the object
	* @param nothing
	* @return int This returns the speed of the object
	*/
	public int getSpeed() {
		return speed;
	}
	
	/**
	* This method sets the heading of the object to the new specified heading
	* @param int This is the new heading which the object will now be set to
	* @return nothing
	*/
	public void setHeading(int h) {
		heading = h;
		/* If the heading is a negative value or greater than 359, 
		 * then set the heading back to a number between 0 and 359 */
		if(heading < 0) {
			heading = 360 + heading;
		}else if(getHeading() > 359) {
			heading = heading - 360;
		}
	}
	
	/**
	* This method gets the heading of the object
	* @param nothing
	* @return int This returns the heading of the object
	*/
	public int getHeading() {
		return heading;
	}
}
