package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/**
 * @author Kevin Tran
 * CSC 133
 * Professor Muyan-Özçelik
 * Assignment 2
 * 
 * The SoundCommand class defines the functionality for selecting sound
 */

public class SoundCommand extends Command{
	private GameWorld gw;
	
	//Constructor for SoundCommand
	public SoundCommand(GameWorld g) {
		super("Sound");
		gw = g;
	}
	
	/**
	* The actionPerformed() method is invoked when sound is selected
	* @param ActionEvent
	* @return nothing
	*/
	@Override
	public void actionPerformed(ActionEvent e) {
		gw.toggleSound();
	}
}
