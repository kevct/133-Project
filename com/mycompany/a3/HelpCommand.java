package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

/**
 * @author Kevin Tran
 * CSC 133
 * Professor Muyan-Özçelik
 * Assignment 2
 * 
 * The HelpCommand class defines the functionality for selecting help
 */

public class HelpCommand extends Command{
	
	//Constructor for AboutCommand
		public HelpCommand() {
			super("Help");
		}
		
		/**
		* The actionPerformed() method is invoked when help is selected
		* @param ActionEvent
		* @return nothing
		*/
		@Override
		public void actionPerformed(ActionEvent e) {
			Dialog.show("Help", "a = Accelerate\nb = Brake\nl = Turn Left\nr = Turn Right", "OK", null);
		}
}
