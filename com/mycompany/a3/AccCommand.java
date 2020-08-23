package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/**
 * @author Kevin Tran
 * CSC 133
 * Professor Muyan-Özçelik
 * Assignment 2
 * 
 * The AccCommand class defines the functionality for accleration
 */

public class AccCommand extends Command{
	private GameWorld gw;
	
	//Constructor for AccCommand
	public AccCommand(GameWorld g) {
		super("Accelerate");
		gw = g;
	}
	
	/**
	* The actionPerformed() method is invoked when accelerate is selected
	* @param ActionEvent
	* @return nothing
	*/
	@Override
	public void actionPerformed(ActionEvent e) {
		if(this.isEnabled()) {
			gw.accelerate();
		}
	}
}
