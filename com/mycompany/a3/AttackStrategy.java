package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.util.MathUtil;

/**
 * @author Kevin Tran
 * CSC 133
 * Professor Muyan-Özçelik
 * Assignment 2
 * 
 * The AttackStrategy class makes NPCs move to the player's current location
 */

public class AttackStrategy implements IStrategy{
	//This is static since all AttackStrategies will be attacking the same player cyborg
	private static PlayerCyborg playerCyborg = PlayerCyborg.getPlayerCyborg(40, new Point(0,0), 25, 20, 2);
	private NPCyborg cyborg; //The cyborg that we are updating its location
	
	//Constructor for AttackStrategy
	public AttackStrategy(NPCyborg npcyborg) {
		cyborg = npcyborg;
	}
	
	/**
	* The apply() method invokes the attack strategy
	* @param nothing
	* @return nothing
	*/
	@Override
	public void apply() {		
		//Get ideal heading of current player location
		cyborg.setSpeed(cyborg.getMaxSpeed());
		int heading = 90 - (int)Math.round(Math.toDegrees((MathUtil.atan2((double)playerCyborg.getLocation().getY()-(double)cyborg.getLocation().getY(), (double)playerCyborg.getLocation().getX()-(double)cyborg.getLocation().getX()))));
		cyborg.setHeading(heading);
	}
}
