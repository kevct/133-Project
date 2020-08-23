package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class PlayPauseCommand extends Command {
	private Game g;
	private boolean paused;
	public PlayPauseCommand(Game g) {
		super("Pause");
		this.g = g;
		paused = false;
	}
	
	/**
	* The actionPerformed() method is invoked when play/pause is selected
	* @param ActionEvent
	* @return nothing
	*/
	@Override
	public void actionPerformed(ActionEvent e) {
		if(paused) {
			g.play();
		}else {
			g.pause();
		}
		paused = paused ? false : true;
	}
}
