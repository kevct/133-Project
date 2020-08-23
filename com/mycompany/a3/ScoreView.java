package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;

/**
 * @author Kevin Tran
 * CSC 133
 * Professor Muyan-Özçelik
 * Assignment 2
 * 
 * The ScoreView class displays data in the top portion of the form
 */

public class ScoreView extends Container implements Observer{
	private int time = 0;
	private int lives = 0;
	private int lastBase = 0;
	private int eLevel = 0;
	private int dLevel = 0;
	private String sound = "OFF";
	
	private Label label = new Label("Time: " + time +  "  Lives Left: " + lives + "  Player Last Base Reached: " + lastBase + "  Player Energy Level: " + eLevel + "  Player Damage Level: " + dLevel + "  Sound: " + sound);
	
	//Constructor for ScoreView
	public ScoreView(GameWorld gw) {
		this.setLayout(new FlowLayout(Component.CENTER));
		label.getUnselectedStyle().setFgColor(ColorUtil.BLUE); //Set text color
		this.getUnselectedStyle().setBorder(Border.createLineBorder(1, ColorUtil.BLACK)); //Create a line border around the buttons
		this.add(label);
		
		gw.addObserver(this);
	}
	
	/**
	* This method updates the ScoreView whenever it is called
	* @param Observable
	* @param Object
	* @return nothing
	*/
	@Override
	public void update(Observable o, Object args) {
		//Updates the values of the PlayerCyborg
		if(o instanceof GameWorld) {
			PlayerCyborg c = PlayerCyborg.getPlayerCyborg(40, new Point(0, 0), 20, 20, 2);
			GameWorld gw = (GameWorld)o;
			time = gw.getTime();
			lives = gw.getLives();
			sound = gw.getSound();
			if(args instanceof PlayerCyborg) {
				c = (PlayerCyborg)args;
				//Update the text 
				lastBase = c.getLastBaseReached();
				eLevel = c.getEnergyLevel();
				dLevel = c.getDamageLevel();
			}
		}
		label.setText("Time: " + time +  "  Lives Left: " + lives + "  Player Last Base Reached: " + lastBase + "  Player Energy Level: " + eLevel + "  Player Damage Level: " + dLevel + "  Sound: " + sound);
		repaint();
	}
}
