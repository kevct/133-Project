package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.util.MathUtil;

/**
 * @author Kevin Tran
 * CSC 133
 * Professor Muyan-Özçelik
 * Assignment 2
 * 
 * The BaseStrategy class makes NPCs move to the next base
 */

public class BaseStrategy implements IStrategy{
	private Base nextBase; //Location of next base to reach
	private NPCyborg cyborg; //The cyborg that we are updating its location
	
	//Constructor for BaseStrategy
	public BaseStrategy(NPCyborg npcyborg, Base base) {
		cyborg = npcyborg;
		nextBase = base;
	}
	
	/**
	* The apply() method invokes the base strategy
	* @param nothing
	* @return nothing
	*/
	@Override
	public void apply() {		
		//Get ideal heading of next base
		cyborg.setSpeed(cyborg.getMaxSpeed());
		int heading = 90 - (int)Math.round(Math.toDegrees((MathUtil.atan2((double)nextBase.getLocation().getY()-(double)cyborg.getLocation().getY(), (double)nextBase.getLocation().getX()-(double)cyborg.getLocation().getX()))));
		cyborg.setHeading(heading);
	}
	
}
