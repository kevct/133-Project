package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class PositionCommand extends Command{
	private static GameWorld gw;
	private static boolean pressedOnce;
	
	public PositionCommand(GameWorld g) {
		super("Position");
		gw = g;
		pressedOnce = false;
	}
	
	/**
	* The actionPerformed() method is invoked when position is selected
	* @param ActionEvent
	* @return nothing
	*/
	@Override
	public void actionPerformed(ActionEvent e) {
		if(this.isEnabled()) {
			if(pressedOnce) {
				pressedOnce = false;
			}else {
				pressedOnce = true;
			}
		}
	}
	
	public static void moveObject(int x, int y) {
		if(pressedOnce) {
			IIterator i = gw.getObjects();
			while(i.hasNext()) {
				GameObject o = i.getNext();
				if(o instanceof Fixed) {
					Fixed fixedObject = (Fixed)o;
					if(fixedObject.isSelected()) {
						fixedObject.setLocation(new Point(x, y));
						fixedObject.setSelected(false);
					}
				}
				pressedOnce = false;
			}
		}
	}
}
