/* Assignment 3 starter code provided by Prof. D. Houtman
 * for use in CST8284 Assignment 3, due July 26, 2020.
 * This code is for one-time use only during the Summer 2020 semester.
 * (c) D. Houtman.  All rights reserved
 */
/* Course Name: CST 8284-300 Object Oriented Programming (Java)
Student Name:Matineh Behzad
Class name:RegLauncher
Date:8 August 2020
 */
package cst8284.asgmt4.landRegistry;
/**
 * RegLauncher is the place which the whole program is executed.it contains main method of program.
 * @author Matineh Behzad, based on code supplied by Prof.Dave Houtman
 *
 */
public class RegLauncher {
	/**
	 * This is the program main method that is required for executing program.
	 * @param args
	 */
	public static void main(String[] args) {    
		javax.swing.SwingUtilities.invokeLater     
		(new Runnable() { public void run() {new RegViewGUI().launchDialog();} });  
		}
	}