package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.plaf.Border;

/**
 * @author Kevin Tran
 * CSC 133
 * Professor Muyan-Özçelik
 * Assignment 2
 * 
 * The MapView class displays the map and updates the objects in the map
 */

public class MapView extends Container implements Observer{
	private static MapView mv;
	private static GameWorld gw;
	
	//Constructor for MapView
	private MapView(GameWorld gw) {	
		this.getUnselectedStyle().setBgTransparency(255); //Set transparency to opaque
		this.getUnselectedStyle().setBgColor(ColorUtil.WHITE); //Set background color
		this.getUnselectedStyle().setBorder(Border.createLineBorder(3, ColorUtil.rgb(255, 0, 0)));
		
		gw.addObserver(this);
		this.gw = gw;
	}
	
	/**
	* This method implements the singleton pattern
	* @param GameWorld this is the observable to add the map view to
	* @return nothing
	*/
	public static MapView getMapView(GameWorld gw) {
		if(mv == null) {
			mv = new MapView(gw);
		}
		return mv;
	}
	
	
	/**
	* This method updates the MapView whenever it is called
	* @param Observable
	* @param Object
	* @return nothing
	*/
	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof GameWorld) {
			((GameWorld) o).showMap();
			repaint();
		}
	}
	
	/**
	* This method updates the components
	* @param Graphics
	* @return nothing
	*/
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Point p = new Point(this.getX(), this.getY());
		IIterator i = gw.getObjects();
		while(i.hasNext()) {
			GameObject gameObject = i.getNext();
			gameObject.draw(g, p);
		}
	}
	
	/**
	* This method handles mouse clicking on the container
	* @param int
	* @param int
	* @return nothing
	*/
	@Override
	public void pointerPressed(int x, int y) {
		if(gw.isPaused()) {
			PositionCommand.moveObject(x - getAbsoluteX(), y - getAbsoluteY());
			
			int newx = x - getParent().getAbsoluteX();
			int newy = y - getParent().getAbsoluteY();
			Point pPtrRelPrnt = new Point(newx, newy);
			Point pCmpRelPrnt = new Point(getX(), getY()); 
			IIterator objects = gw.getObjects();
			while(objects.hasNext()) {
				GameObject obj = objects.getNext();
				if(obj instanceof Fixed) {
					Fixed fixedObj = (Fixed)obj;
					if(fixedObj.contains(pPtrRelPrnt, pCmpRelPrnt)) {
						fixedObj.setSelected(true);
					}else {
						fixedObj.setSelected(false);
					}
				}
			}
			repaint();
		}
	}
}
