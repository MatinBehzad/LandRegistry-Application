/* Assignment 3 starter code provided by Prof. D. Houtman
 * for use in CST8284 Assignment 3, due July 26, 2020.
 * This code is for one-time use only during the Summer 2020 semester.
 * (c) D. Houtman.  All rights reserved
 */
/* Course Name: CST 8284-300 Object Oriented Programming (Java)
Student Name:Matineh Behzad
Class name:RegControl
Date:8 August 2020
 */

package cst8284.asgmt4.landRegistry;

import java.io.*;
import java.util.ArrayList;




/**
 * 
 * 
 * Most of the real actions in this application and most of the work programmer need to do to make this application function 
 * reliably is done in the RegControlclass.As with RegView,Also this class’s methods according to their functionality can be divide 
 * with RegView methods.
 * 
 * @author Matineh Behzad, based on code supplied by Prof.Dave Houtman
 *
 */
public class RegControl {
	
	private ArrayList<Registrant> registrants = new ArrayList<Registrant>();
	private ArrayList<Property> properties = new ArrayList<Property>();
	/**
	 * 
	 * @return This getRegistrants just returns the whole registrants arrayList.it is used in RegControl methods instead of using registrants array directly.
	 */
	private ArrayList<Registrant> getRegistrants(){ return registrants; }
	/**
	 * 
	 * @return This getProperties just returns the whole properties arrayList.it is used in RegControl methods instead of using properties array directly.
	 */
	private ArrayList<Property> getProperties(){ return properties; }
	/**
	 * addNewRegistrant is a kind of method that add new Registrant object in registrants arrayList.
	 * @param reg 
	 * @return it returns the Registrant reg which was added to arraylist.
	 */
    public Registrant addNewRegistrant(Registrant reg) {
    	getRegistrants().add(reg);
    	return reg;
    }
    /**
     * findRegistrant is searching for one particular Registrant object with particular RegNumber. and it consist of two throw exceptions one for
     * checking that the array is empty or not, two for checking the particular regNum is less that 1017 or not.
     * @param regNum 
     * @return It returns the Registrant object which is finded in arrayList.if it does not find any registrant with that
     * RegNumber it returns null.
     */
    public Registrant findRegistrant(int regNum) {
    	
      Registrant c = null;
   if(!isRegistrantAvailable(getRegistrants())) 
    	throw new BadLandRegistryException("No registrants available\n","There are no registrants currently listed"); 
   
   
	else
		if(!(RegControl.isBadRegistrantinputHighestNumber(String.valueOf(regNum)))) {
			throw new BadLandRegistryException("Unregistered value\n",
					"There is no registrant having that registration number");
			}
	
        
    else {
    	for (int i = 0; i < getRegistrants().size(); i++) {
    		if (getRegistrants().get(i).getRegNum() == regNum) 
    		c=getRegistrants().get(i);
    	    }
    	}
    
    	return c;
    }
    /**
     * This ListOfRegistrant demonstrate the whole Objects from registrants array.
     * @return it returns one ArrayList of whole Registrant objects which are stored in registrants array.
     */
    public ArrayList <Registrant> listOfRegistrants() {
    	ArrayList<Registrant> listOfRegs = new ArrayList<Registrant>();
    	for(int listRegCtr = 0, regCtr = 0; regCtr < getRegistrants().size(); regCtr++)
    		if (getRegistrants().get(regCtr) != null) listOfRegs.add(listRegCtr++, getRegistrants().get(regCtr));
    	return listOfRegs;
    }
    /**
     * This particular Function takes a ArrayList of OldRegNumber that was the wish RegNumber of user,and change their RegNumber to
     * the New required RegNumber.
     * @param oldRegNumPropertyArrayList is the array consists of Registrant objects with old RegNumber. 
     * @param newRegNum the new RegNumber required.
     * @return it returns new ArrayList with new RegNumber.
     */
    public ArrayList<Property> changePropertyRegistrant(ArrayList<Property> oldRegNumPropertyArrayList, int newRegNum){
  	  
   	 ArrayList<Property> propert = new ArrayList<>();
   	 for(Property prop:oldRegNumPropertyArrayList) {
   		propert.add(new Property(prop,newRegNum));
     	 }
   	 
   	 return propert;
    }
    /**
     * addNewProperty is a function that add new Property to Peraperties arrayList, this method has a particular throw exception which is checking
     * for overlaps property.  
     * @param prop
     * @return it returns the particular property that was taken for adding.
     */
    public Property addNewProperty(Property prop) {
    	
    	
    	
    	Property overlapProperty = checkPropertyOverlap(prop);
    	
    	 if( !(RegControl.isPropertyOverlab(prop,properties)))
  		   throw new BadLandRegistryException("Property overlap \n",
  					"The property entered overlaps with an existing property with coordinates"
  							+" " + overlapProperty.getXLeft() +" "+ overlapProperty.getYTop() +" "+ " and size" + " " + overlapProperty.getXLength()
  							+ " " +  overlapProperty.getYWidth());
    	 else {
        	getProperties().add(prop);
    	
    	return prop;
    	 }
    }
    /**
     * This ListOfProperties method take the required regNumber and return the new arrayList of Property objects with that RegNumber.It has a
     * throw exception for checking if the RegNumber is less than HighestRequreidRegNumber or not.
     * @param regNum
     * @return it returns the arraylist of required RegNumber,
     */
    public ArrayList<Property> listOfProperties(int regNum) {
    	
    	if(!(RegControl.isBadRegistrantinputHighestNumber(String.valueOf(regNum)))) {
				throw new BadLandRegistryException("Unregistered value\n",
						"There is no registrant having that registration number");
				}
	
    	else {
    	ArrayList<Property> listOfProps = new ArrayList<Property>();
    	for (Property prop: listOfAllProperties()) 
    		if (prop.getRegNum() == regNum) listOfProps.add(prop);
    	return listOfProps;
    	}
    }
    /**
     * This Function returns all Properties Objects from ArrayList Properties in a new ArrayList.
     * @return it returns whole Property Objects that was stored is ArrayList Properties.
     */
    public ArrayList<Property> listOfAllProperties() {
     	ArrayList<Property> listOfProps = new ArrayList<Property>();
    	for(int listPropCtr = 0; listPropCtr < getProperties().size(); listPropCtr++)
    		if (getProperties().get(listPropCtr) != null) listOfProps.add(getProperties().get(listPropCtr));
    	return listOfProps;
    }
   /**
    * This method is checking for PropertyOverlap with take a new Property object, this is invoked in addNewProperty Function to
    * decide for adding a new property or not.
    * @param it takes prop as new property.
    * @return it returns the overlaped property with new Property. If the new Property did not overlap with any Properties in Property 
    * array function returns null vale.
    */
    private Property checkPropertyOverlap(Property prop) {
    	if (listOfAllProperties().size() > 0) {
	    	for (int i = 0; i < listOfAllProperties().size(); i++) {
	    		Property registeredProp = getProperties().get(i);
	    		if (registeredProp.overlaps(prop)) return registeredProp;
	    	}
    	}
    	return null;
    }
   /**
    * deleteProperties is a boolean function that is deleting a list of selected properties from Properties ArrayList if the properties ArrayList
    * is not Empty.
    * @param props is a arrayList of selected Properties.
    * @return If The Function Operation is Successful it returns true ,Otherwise false.
    */
    public boolean deleteProperties(ArrayList<Property> props) {
    	if ((props == null)||(props.size() == 0)) return false;
    	for (Property deleteProperty : props)
    		getProperties().remove(deleteProperty);
    	return true;
    }
    /**
     * deleteRegistrant is deleting particular Registrant from Registrant ArrayList.
     * @param It takes a regNumber.
     * @return It returns the deleted Registrant Object if there is a Registrant object with the specified regNum.
     */
    public Registrant deleteRegistrant(int regNum) {
    	
    	Registrant registrantToDelete = findRegistrant(regNum);
    	if (registrantToDelete == null) return null;
    	 getRegistrants().remove(registrantToDelete);
    	return registrantToDelete;
    }
    /**
     * saveToFile boolean function is the particular function that saves all data from any kinds of ArrayList to File with specific name. 
     * @param <T>
     * @param source is the any kinds of array that is taken to this function.
     * @param fileName is the specific name for the file that is pass to function.
     * @return It returns true if the array save successfully in file. But if not it returns false. 
     */
    public <T> boolean saveToFile(ArrayList<T> source, String fileName) {
		File file = new File (fileName);
		if (file.exists()) file.delete();
     	try 
     	{
     	    FileOutputStream objectFileStream = new FileOutputStream(fileName);
    	    ObjectOutputStream oos = new ObjectOutputStream(objectFileStream);    		
     		for (T obj: source) oos.writeObject(obj);
     		return true;
    	} catch(Exception e) { 
        	return false;
    	}
    }
   /**
    * loadFromFile is kind of function that load whole objects to any kind of arrayList from the file.
    * @param <T> ArrayList of generic <T> form
    * @param fileName is the parameter that that is taken by function.
    * @return It returns the ArrayList of loaded data , if there is no error in the processes of reading Object from file ,otherwise it returns null.
    */
   	public <T>ArrayList<T> loadFromFile(String fileName) {
		if (!new File (fileName).exists()) return null; 	
		ArrayList <T> al = new ArrayList<>();
    	try (
       			FileInputStream objectFileStream = new FileInputStream(fileName);
    			ObjectInputStream ois = new ObjectInputStream(objectFileStream);
    	) {
    		while (true) al.add((T)ois.readObject());
     	} catch(EOFException e) { 
        	return al;
    	} catch(Exception e) {
    		return null;
    	}
   	}
    	/**
    	 * refreshRegistrants is a method that clear the whole ArrayList of Registrant and then  load the new data to ArrayList Registrant.
    	 */
    	public void refreshRegistrants() {
    		
    		getRegistrants().clear();
    		
    		 if ((new File(RegViewGUI.REGISTRANTS_FILE)).exists()) {
    			ArrayList<Registrant> regArrayList =loadFromFile(RegViewGUI.REGISTRANTS_FILE);
    			for (Registrant reg : regArrayList)
    			getRegistrants().add(reg);
    			
    			
    		}
    	}
    	/**
    	 *refreshProperties is a method that clear the whole ArrayList of Property and then  load the new data to ArrayList Property.  
    	 */
    	public void refreshProperties() {
    		
    		getProperties().clear();
    	if ((new File(RegViewGUI.PROPERTIES_FILE)).exists()) {
    		ArrayList<Property> propArrayList =loadFromFile(RegViewGUI.PROPERTIES_FILE);
    		for (Property prop : propArrayList)
    			getProperties().add(prop);
    	   }
    	}
    	/**
    	 * isBadRegistrantinputMisMatch is boolean test method that is checking if the RegNumber format is entered correctly. 
    	 * @param number is specific RegNumber.
    	 * @return it returns true if the inputValue is correct. 
    	 */
    	public static boolean isBadRegistrantinputMisMatch(String number) {
    	
    	return((number.matches("[0-9]+") && Integer.parseInt(number) >= 1000));

		}
    	/**
    	 * isRegistrantAvailable is boolean test method that is checking if Registrant arrayList is empty or not.
    	 * @param it take registrant as a one kind of Registrant ArrayList.
    	 * @return it returns true if the the registrants array is not empty.
    	 */
        public static boolean isRegistrantAvailable( ArrayList <Registrant> registrant) {
        	
        return !(registrant.isEmpty()); 
        	
       }
       /**
        * isBadRegistrantinputHighestNumber is boolean test method that is checking if entered RegNumber is less the required valid RegNumber.
        * @param number is a RegNumber.
        * @return it returns true if the condition is true.
        */
       public static boolean isBadRegistrantinputHighestNumber(String number) {
    	   
    	   return (Integer.parseInt(number) <= 1016); 
			
    	}
       /**
        * isBadRegistrantinputMinPropertyError is boolean test method that checks for the minimum dimension for Property.
        * @param length of property.
        * @param width of property.
        * @return it returns true if the condition is true.
        */
       public static boolean isBadRegistrantinputMinPropertyError(int length, int width){
    	   
    	  return ((length>=10 && width>=20)) ||  ((width>=10 && length>=20));
   		
     	}
       /**
        * isBadRegistrantinputMaxPropertyError is  boolean test method that check property is within 1000x 1000y land.
        * @param xLeft of Property.
        * @param xLength of Property.
        * @param yTop of Property.
        * @param yWidth of Property.
        * @return it returns true if the condition is true.
        */
       public static boolean isBadRegistrantinputMaxPropertyError(int xLeft,int xLength,int yTop,int yWidth) {
    	   
    	   return ((xLeft+xLength<1000)&&(yTop+yWidth<1000)) ;
    	}
       /**
        * isBadRegistrantinputMissingValu is boolean test method that check the user to avoid entering  missing value.
        * @param number is the any kind of input.
        * @return it returns true if the condition is true.
        */
       public static boolean isBadRegistrantinputMissingValue(String number) {
   		    
    	   return !(number.equals(""));
				
    	}
       /**
        * isBadRegistrantinputNullValu is boolean test method that check the user to avoid entering  null value.  
        * @param number is the any kind of input.
        * @return it returns true if the condition is true.
        */
       public static boolean isBadRegistrantinputNullValue(String number) {
    	   
    	   return  !(number==null); 
   		
    	}
       /**
        * isPropertyOverlab is boolean test method that check Property is overLaping with any other Properties in Property ArrayList.
        * @param prop is a particular property.
        * @param properties is the arrayList of property.
        * @return it returns true if the property entered does not overlap with any properties.
        */
       public static boolean isPropertyOverlab(Property prop,ArrayList<Property> properties) {
    	 
    	   if (properties.size() > 0) {
   	    	for (int i = 0; i < properties.size(); i++) {
   	    		Property registeredProp = properties.get(i);
   	    		if (registeredProp.overlaps(prop)) 
   	    			return false;
   	    	}
       	}
    	   return true;
  
   	 }
  
}
