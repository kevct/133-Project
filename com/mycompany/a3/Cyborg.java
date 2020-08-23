package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;

/**
 * @author Kevin Tran
 * CSC 133
 * Professor Muyan-Özçelik
 * Assignment 2
 * 
 * The Cyborg class defines the attributes of a cyborg
 */

public abstract class Cyborg extends Movable implements ISteerable{	
	private int steeringDirection;
	private int maximumSpeed;
	private int energyLevel;
	private int energyConsumptionRate;
	private int damageLevel;
	private int lastBaseReached;
	
	//Constructor for Cyborg
	public Cyborg(int size, Point point, int maxSpeed, int eLevel, int eConsumptionRate) {
		super(size, point, ColorUtil.CYAN); //Sets size, location, and color of cyborg
		
		setHeading(0); //Direction of cyborg is set to 0 (North) by default
		
		steeringDirection = 0; //The change in direction the player would like to apply, initially 0
		maximumSpeed = maxSpeed; //Maximum speed of cyborg to a random value between 20 and 50
		energyLevel = eLevel; //Initial energy of cyborg, arbitrarily set to 20
		energyConsumptionRate = eConsumptionRate; //Energy consumed per tick, arbitrarily set to 2
		damageLevel = 0; //Damage level set to 0 by default
		lastBaseReached = 1; //Last base reached is default to 1
	}
	
	/**
	* The steer() method allows for the cyborg to change direction
	* @param int This is the direction in degrees (increments of 5)
	* @return nothing
	*/
	@Override
	public void steer() {
		// TODO Auto-generated method stub
		//Sets heading to the current heading + the steering direction
		setHeading(getHeading()+steeringDirection);
		//Resets steering direction
		steeringDirection = 0;
	}
	
	/**
	* The setSteeringDirection() method sets the steering direction to the defined direction
	* @param int This is the degrees the direction will be set to
	* @return nothing
	*/
	public void setSteeringDirection(int d) {
		steeringDirection = d;
	}
	
	/**
	* The getSteeringDirection() method returns the steering direction
	* @param nothing
	* @return int This is the steering direction
	*/
	public int getSteeringDirection() {
		return steeringDirection;
	}
	
	/**
	* The getMaxSpeed() method returns the max speed of the cyborg
	* @param nothing
	* @return int This is the max speed
	*/
	public int getMaxSpeed() {
		return maximumSpeed;
	}
	
	/**
	* The setEnergyLevel() method sets the energy level to the defined energy level
	* @param int This is the new energy level
	* @return nothing
	*/
	public void setEnergyLevel(int e) {
		energyLevel = e;
	}
	
	/**
	* The getEnergyLevel() method returns the energy level
	* @param nothing
	* @return int This is the energy level
	*/
	public int getEnergyLevel() {
		return energyLevel;
	}
	
	/**
	* The get energyConsumptionRate() method returns the energy consumption rate
	* @param nothing
	* @return int This is the energy consumption rate
	*/
	public int getEnergyConsumptionRate() {
		return energyConsumptionRate;
	}
	
	/**
	* The setDamageLevel() method sets the damage level to the defined damage level
	* @param int This is the new damage level
	* @return nothing
	*/
	public void setDamageLevel(int d) {
		damageLevel = d;
	}
	
	/**
	* The getDamageLevel() method returns the damage level
	* @param nothing
	* @return int This is the damage level
	*/
	public int getDamageLevel() {
		return damageLevel;
	}
	
	/**
	* The setLastBaseReached() method sets the last base reached to the defined base
	* @param int This is the new base reached
	* @return nothing
	*/
	public void setLastBaseReached(int b) {
		lastBaseReached = b;
	}
	
	/**
	* The getLastBaseReached() method returns the last base reached
	* @param nothing
	* @return int This is the last base reached
	*/
	public int getLastBaseReached() {
		return lastBaseReached;
	}
	
}
