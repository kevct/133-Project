package com.mycompany.a3;

/**
 * @author Kevin Tran
 * CSC 133
 * Professor Muyan-Özçelik
 * Assignment 2
 * 
 * The ICollection interface implements the Iterator pattern
 */

public interface ICollection {
	public void add(GameObject o);
	public IIterator getIterator();
}
