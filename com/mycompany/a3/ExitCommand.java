package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.events.ActionEvent;

/**
 * @author Kevin Tran
 * CSC 133
 * Professor Muyan-Özçelik
 * Assignment 2
 * 
 * The ExitCommand class defines the functionality for exiting the game
 */

public class ExitCommand extends Command{
	
	//Constructor for ExitCommand
	public ExitCommand() {
		super("Exit");
	}
	
	/**
	* The actionPerformed() method is invoked when exit is selected
	* @param ActionEvent
	* @return nothing
	*/
	@Override
	public void actionPerformed(ActionEvent e) {
		boolean b = Dialog.show("Exit", "Are you sure you want to exit?", "Yes", "No");
		if(b) {
			Display.getInstance().exitApplication();
		}
	}
}
