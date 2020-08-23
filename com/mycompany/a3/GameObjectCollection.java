package com.mycompany.a3;

import java.util.ArrayList;

/**
 * @author Kevin Tran
 * CSC 133
 * Professor Muyan-Özçelik
 * Assignment 2
 * 
 * The GameObjectCollection contains the collection of game objects
 */

public class GameObjectCollection implements ICollection{
	private ArrayList<GameObject> a;
	
	//Constructor for GameObjectCollection
	public GameObjectCollection() {
		a = new ArrayList<GameObject>();
	}
	
	/**
	* This method allows for the adding of new game objects to the collection
	* @param GameObject the object to be added
	* @return nothing
	*/
	public void add(GameObject o) {
		a.add(o);
	}
	
	/**
	* This method returns an iterator
	* @param nothing
	* @return IIterator this is the iterator
	*/
	public IIterator getIterator(){
		return new GameObjectIterator();
	}
	
	private class GameObjectIterator implements IIterator{
		private int index = -1;
		
		/**
		* This method checks if there is another element to iterate
		* @param nothing
		* @return Boolean returns true if there is a next element otherwise it returns false
		*/
		public boolean hasNext() {
			if(a.size() <= 0) {
				return false;
			}
			if(index == a.size() - 1) {
				return false;
			}
			return true;
		}
		
		/**
		* This method returns the next element
		* @param nothing
		* @return GameObject this is the next index
		*/
		public GameObject getNext() {
			index++;
			return a.get(index);
		}
	}
}
