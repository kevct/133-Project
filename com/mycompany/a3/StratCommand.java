package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/**
 * @author Kevin Tran
 * CSC 133
 * Professor Muyan-Özçelik
 * Assignment 2
 * 
 * The StratCommand class defines the functionality for changing strategies
 */

public class StratCommand extends Command{
	private GameWorld gw;
	
	//Constructor for StratCommand
	public StratCommand(GameWorld g) {
		super("Change Strategies");
		gw = g;
	}
	
	/**
	* The actionPerformed() method is invoked when change strategies is selected
	* @param ActionEvent
	* @return nothing
	*/
	@Override
	public void actionPerformed(ActionEvent e) {
		gw.changeStrategy();
	}
}
