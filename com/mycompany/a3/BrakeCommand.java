package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/**
 * @author Kevin Tran
 * CSC 133
 * Professor Muyan-Özçelik
 * Assignment 2
 * 
 * The BrakeCommand class defines the functionality for braking
 */

public class BrakeCommand extends Command{
	private GameWorld gw;
	
	//Constructor for BrakeCommand
	public BrakeCommand(GameWorld g) {
		super("Brake");
		gw = g;
	}
	
	/**
	* The actionPerformed() method is invoked when brake is selected
	* @param ActionEvent
	* @return nothing
	*/
	@Override
	public void actionPerformed(ActionEvent e) {
		gw.brake();
	}
}
