package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/**
 * @author Kevin Tran
 * CSC 133
 * Professor Muyan-Özçelik
 * Assignment 2
 * 
 * The LTurnCommand class defines the functionality for turning left
 */

public class LTurnCommand extends Command{
	private GameWorld gw;
	
	//Constructor for Left Turn
	public LTurnCommand(GameWorld g) {
		super("Left");
		gw = g;
	}
	
	/**
	* The actionPerformed() method is invoked when turn left is selected
	* @param ActionEvent
	* @return nothing
	*/
	@Override
	public void actionPerformed(ActionEvent e) {
		gw.steerLeft();
	}
}
