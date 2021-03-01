/* Assignment 3 starter code provided by Prof. D. Houtman
 * for use in CST8284 Assignment 3, due July 26, 2020.
 * This code is for one-time use only during the Summer 2020 semester.
 * (c) D. Houtman.  All rights reserved
 */
/* Course Name: CST 8284-300 Object Oriented Programming (Java)
Student Name:Matineh Behzad
Class name:Property
Date:8 August 2020
 */
package cst8284.asgmt4.landRegistry;

import java.io.Serializable;
import java.util.Comparator;

/**
 * The Property class stores information associated with each property, which includes:the length and width of the property, the
 * coordinates of the top left corner of the property, and the REGNUM of that property�s registrant.
 * @author  Matineh Behzad, based on code supplied by Prof.Dave Houtman
 * 
 */
public class Property implements Serializable{
	
	private static final double TAX_RATE_PER_M2 = 12.50;
	private static final int DEFAULT_REGNUM = 999;
	
	private int xLeft, yTop;
	private int xLength, yWidth;
	private int regNum = DEFAULT_REGNUM;
	private int area = getArea();
	private double taxes = getTaxes();
	/**
	 * This is a no-argument constructor that consider default values to xLength, yWidth, xLeft,  yTop of property.
	 */
	public Property() {this (0, 0, 0, 0);}
	/**
	 * This is Constructor four-argument that receives property Parameters, and it chains its values to third constructor.
	 * @param xLength of Property.
	 * @param yWidth of Property.
	 * @param xLeft of Property.
	 * @param yTop of Property.
	 */
	private Property(int xLength, int yWidth, int xLeft, int yTop) {
		this (xLength, yWidth, xLeft, yTop, DEFAULT_REGNUM);
	}
	/**
	 * This if two-argument constructor that chain its values to last constructor.
	 * @param prop is a kind of Property.
	 * @param regNum is the considered regNumber for Prop.
	 */
	public Property(Property prop, int regNum) {
		this (prop.getXLength(), prop.getYWidth(), prop.getXLeft(), prop.getYTop(), regNum);
	}
	/**
	 * This is five-argument constructor that sets the Property attributes. 
	 * @param xLength of property.
	 * @param yWidth of property.
	 * @param xLeft of property.
	 * @param yTop of property.
	 * @param regNum of property.
	 */
	public Property(int xLength, int yWidth, int xLeft, int yTop, int regNum) {
		setXLength(xLength); setYWidth(yWidth); setXLeft(xLeft); setYTop(yTop); setRegNum(regNum);
	}
	/**
	 * This is a public get function that can be used in Other Classes for getting XLeft of property. 
	 * @return it returns xLeft.
	 */
	public int getXLeft() { return xLeft;}
	/**
	 * setXLeft is one kind of function that set the amount of XLeft of a property to Object.
	 * @param left is the amount of XLeft of a property. 
	 */
	public void setXLeft(int left) { this.xLeft = left; }
	/**
	 * This is a public get function that can be used in Other Classes for getting XRight of property. 
	 * @return it returns sum of the XLeft and XLenghth of the property.
	 */
	public int getXRight(){return getXLeft() + getXLength();}
	/**
	 *This is a public get function that can be used in Other Classes for getting  YTop of property. 
	 * @return
	 */
	public int getYTop() { return yTop; }
	/**
	 * This is a public set function that can be used in Other Classes for setting the yTop of the Property.And It contains three throw exceptions 
	 * that are checking for MissingMatchValue,NullValue and if the Property within the default LandSize. 
	 * @param top is taken as Parameter of setYTop function.
	 */
	public void setYTop(int top) {
		
if(!(RegControl.isBadRegistrantinputMissingValue(String.valueOf(getYWidth()))) || !(RegControl.isBadRegistrantinputMissingValue(String.valueOf(getXLength())))
 || !(RegControl.isBadRegistrantinputMissingValue(String.valueOf(getXLeft()))) || !(RegControl.isBadRegistrantinputMissingValue(String.valueOf(top))))
		throw new BadLandRegistryException("Miss value \n", "Missing an Input value");
else 
	if(!(RegControl.isBadRegistrantinputNullValue(String.valueOf(getYWidth()))) || !(RegControl.isBadRegistrantinputNullValue(String.valueOf(getXLength())))
			 || !(RegControl.isBadRegistrantinputNullValue(String.valueOf(getXLeft()))) || !(RegControl.isBadRegistrantinputNullValue(String.valueOf(top))))
		throw new BadLandRegistryException("Null value entered \n",
				"An attempt was made to pass a null value to a variable");
else 
	if(!(RegControl.isBadRegistrantinputMaxPropertyError(getXLeft(),getXLength(),top,getYWidth())))
	
	throw new BadLandRegistryException("Property exceeds available size\n","The Property requested extends beyond the boundary of the available land");	
	
	else
		this.yTop = top; 
	
		}
	/**
	 * This is a public get function that can be used in Other Classes for getting YBottom of property. 
	 * @return it returns sum of the YTop and YWidth of the property.
	 * 
	 * @return it returns the sum of YTop() and YWidth. 
	 */
	public int getYBottom() { return getYTop() + getYWidth(); }
   /**
    *  This is a public get function that can be used in Other Classes for getting YWidth of property.
    * @return it returns yWidth.
    */
	public int getYWidth() { return yWidth; }
	/**
	 * This is public set function that can be used in Other Classes for setting yWidth ,also it contain a test method for checking if 
	 * the property Minimum size is valid. 
	 * @param yWidth is the Parameter input of function.
	 */
	public void setYWidth(int yWidth) { 
		if(RegControl.isBadRegistrantinputMinPropertyError(getXLength() , yWidth))
		this.yWidth = yWidth;
		
		else
	throw new BadLandRegistryException("Property below minimum size\n","The minimum Property size entered must have a lenght of at least 20 m and width of 10 m");	
		
		}
	/**
	 *  This is a public get function that can be used in Other Classes for getting XLength of property.
	 * @return it returns XLength.
	 */
	public int getXLength() { return xLength;}
	/**
	 * This is public set function that can be used in Other Classes for setting xLength.
	 * @param It returns xLength of property.
	 */
	public void setXLength(int xLength) { this.xLength = xLength; }
	/**
	 *  This is a public get function that can be used in Other Classes for getting RegNum of property. 
	 * @return It returns regNum of property.
	 */
	public int getRegNum() {
		
		return regNum;}
	/**
	 *  This is private set function that can be used in property Class for setting RegNumber,and it contains one throw error exception
	 *  that check for the highest RegNumber.
	 * @param regNum is the input parameter for setRegNumer.
	 */
	private void setRegNum(int regNum) {
		
		
	   if(!(RegControl.isBadRegistrantinputHighestNumber(String.valueOf(regNum)))) {
				throw new BadLandRegistryException("Unregistered value\n",
						"There is no registrant having that registration number");
				}
	   
		else {
		  this.regNum = regNum; 
			}
		}
	/**
	 * This is a public get function that can be used in Other Classes for getting Area of property.
	 * @return It returns the multiplication of XLength and YWidth of property. 
	 */
	public int getArea() {return (getXLength() * getYWidth()); }
	/**
	 * getTaxes is the method which is calculating the suitable tax for each property.
	 * @return
	 */
	public double getTaxes() {return getArea() * TAX_RATE_PER_M2;}

	@Override
	/**
	 * This is the toString method for Property class that is printing all the instance variable property object.
	 */
	public String toString() {
		return "Coordinates: " + getXLeft() + ", " + getYTop() + "\n" +
			   "Length: " + getXLength() + " m  Width: " + getYWidth() + " m\n" +
			   "Registrant #: " + getRegNum() + "\n" +
			   "Area: " + getArea() + " m2\nProperty Taxes : $" + getTaxes();
	}
	
	@Override
	/**
	 * This is the boolean equals function that check that checks two object has same property reference or not.
	 */
	public boolean equals(Object obj) {
		if (!(obj instanceof Property)) return false;
		Property prop = (Property)obj;
		return 
			prop.getYTop()==this.getYTop() &&
			prop.getXLeft()==this.getXLeft() &&
			hasSameSides(prop);
	}
	/**
	 * hasSameSides is a particular function that return true if two properties has same length and width.
	 * @param prop is a parameter input for hasSameSides function.
	 * @return it returns true if two properties has same length and width.
	 */
	public boolean hasSameSides(Property prop) {
		return prop.getXLength()==this.getXLength() && prop.getYWidth()==this.getYWidth();
	}
	/**
	 * overlaps is a particular function that check if the new property overlap with property Object
	 * @param prop is a parameter input for overlaps function.
	 * @return It returns true if the overlap algorithm is true.
	 */
	public boolean overlaps(Property prop) {
		return  prop.getXRight() > this.getXLeft() && prop.getXLeft() < this.getXRight() &&
				prop.getYTop() < this.getYBottom() && prop.getYBottom() > this.getYTop();
	}
	
		
	
}
