package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/**
 * @author Kevin Tran
 * CSC 133
 * Professor Muyan-Özçelik
 * Assignment 2
 * 
 * The RTurnCommand class defines the functionality for turning right
 */

public class RTurnCommand extends Command{
	private GameWorld gw;
	
	//Constructor for RTurnCommand
	public RTurnCommand(GameWorld g) {
		super("Right");
		gw = g;
	}
	
	/**
	* The actionPerformed() method is invoked when turn right is selected
	* @param ActionEvent
	* @return nothing
	*/
	@Override
	public void actionPerformed(ActionEvent e) {
		gw.steerRight();
	}
}
