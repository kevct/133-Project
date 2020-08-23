package com.mycompany.a3;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

/**
 * @author Kevin Tran
 * CSC 133
 * Professor Muyan-Özçelik
 * Assignment 2
 * 
 * The AboutCommand class defines the functionality for selecting about
 */

public class AboutCommand extends Command{
	
	//Constructor for AboutCommand
	public AboutCommand() {
		super("About");
	}
	
	/**
	* The actionPerformed() method is invoked when about is selected
	* @param ActionEvent
	* @return nothing
	*/
	@Override
	public void actionPerformed(ActionEvent e) {
		Dialog.show("About", "Kevin Tran\nCSC 133\nV3 Sili Game", "OK", null);
	}
}
