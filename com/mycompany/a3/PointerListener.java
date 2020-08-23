package com.mycompany.a3;

import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

public class PointerListener implements ActionListener{
	private MapView mv;
	public PointerListener(MapView mv) {
		this.mv = mv;
	}
	
	/**
	* The actionPerformed() method is invoked when mouse is clicked
	* @param ActionEvent
	* @return nothing
	*/
	@Override
	public void actionPerformed(ActionEvent e) {
		mv.pointerPressed(e.getX(), e.getY());
	}
}