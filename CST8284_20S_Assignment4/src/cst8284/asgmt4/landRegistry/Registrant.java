/* Assignment 3 starter code provided by Prof. D. Houtman
 * for use in CST8284 Assignment 3, due July 26, 2020.
 * This code is for one-time use only during the Summer 2020 semester.
 * (c) D. Houtman.  All rights reserved
 */
/* Course Name: CST 8284-300 Object Oriented Programming (Java)
Student Name:Matineh Behzad
Class name:Registrant
Date:8 August 2020
 */
package cst8284.asgmt4.landRegistry;

import java.io.Serializable;
/**
 * The Registrant class stores information associated with each Registrant, which includes:the firstName and lastName of the Registrant, 
 * and the currentREGNUM of that any Registrant.
 * @author Matineh Behzad, based on code supplied by Prof.Dave Houtman 
 *
 */
public class Registrant implements Serializable{
	
	private static final int REGNUM_START = 1000;
	private static int currentRegNum = REGNUM_START;
	private final int REGNUM = currentRegNum;
	
	private String firstName, lastName;
	/**
	 * This is a no-argument constructor that consider default value to firstName and lastName. 
	 */
	public Registrant () {this("unknown unknown");}
	/**
	 * This is a one-argument constructor that transfer firstLastName to their set functions. 
	 * @param firstLastName in the input parameter for this constructor.
	 */
	public Registrant (String firstLastName) {
	  
		setFirstName(firstLastName.split(" ")[0]); 
		setLastName(firstLastName.split(" ")[1]);
		 incrToNextRegNum();
	}
   /**
    * This is a public get function that can be used in Other Classes for getting REGNUM of Registrant. 
	 * 
    * @return it returns the particular Registrant RegNumber.
    */
	public int getRegNum() {return REGNUM;}
	/**
	 * This method is incrementing RegNumber each time the new Registrant object is instantiated. 
	 */
	private static void incrToNextRegNum() {currentRegNum++;}
	/**
	 * This is a public get function that can be used in Other Classes for getting firstName.
	 * @return It returns firstName.
	 */
	public String getFirstName() {return firstName;}
	/**
	 * this is a public set function that can be used in Other Classes for setting the firsetName of the Registrant.And It contains two throw exceptions 
	 * that are checking for MissingMatchValue,NullValue. 
	 * @param It firstName
	 */
	public void setFirstName(
			String firstName) {
		if(!RegControl.isBadRegistrantinputMissingValue(firstName))
			throw new BadLandRegistryException("Miss value \n", "Missing an Input value");
		else
			if (!RegControl.isBadRegistrantinputNullValue(firstName))
				
			throw new BadLandRegistryException("Null value entered \n",
					"An attempt was made to pass a null value to a variable");
		
		else {
		
		this.firstName = firstName;
		   }
		}
	/**
	 * This is a public get function that can be used in Other Classes for getting lastName to avoid using lastName field directly.
	 * @return It returns lastName.
	 */
	public String getLastName() {return lastName;}
	/**
	 * This is a public set function that can be used for setting lastName of Registrant object.and It contains two 
	  * throws exception for testing Missing and Null value.
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		if(!RegControl.isBadRegistrantinputMissingValue(lastName))
			throw new BadLandRegistryException("Miss value \n", "Missing an Input value");
		else
			if (!RegControl.isBadRegistrantinputNullValue(lastName))
				
			throw new BadLandRegistryException("Null value entered \n",
					"An attempt was made to pass a null value to a variable");
		
		else {
		
		this.lastName = lastName;
		}
		 }
	/**
	 * This is the boolean equals function that check that checks two object has same Registrant reference or not.
	 */
	public boolean equals(Object obj) { 
		if (!(obj instanceof Registrant)) return false;
		Registrant reg = (Registrant)obj;
		return ((reg.getFirstName().equals(this.getFirstName())) &&
		   (reg.getLastName().equals(this.getLastName())) &&
		   (reg.getRegNum()==(this.getRegNum())));
	}
	/**
	 * This is the toString method for Registrant class that is printing all the instance variable Registrant object.
	 * 
	 */
	public String toString() {
		return "Name: " + getFirstName() + " " + getLastName() + "\n" +
			   "Registration Number: #" + getRegNum();
	}
	
}
