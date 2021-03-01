
/* Course Name: CST 8284-300 Object Oriented Programming (Java)
Student Name:Matineh Behzad
Class name:BadLandRegistryException
Date:8 August 2020
 */
package cst8284.asgmt4.landRegistry;



/**
 * This class is BadLandRegistryException that is used as an exiting exception thrown for handling the exceptions errors in system and 
 * demonstrate the appropriate message to user during Run-time.
 * @author Matineh Behzad, based on code supplied by Prof.Dave Houtman
 *
 */
public class BadLandRegistryException extends java.lang.RuntimeException{

private static final long serialVersionUID = 1L; 
	
private String header;

/**
 * BadLandRegistryException is a no-argument default constructor that contains default header and message. 
 */
public BadLandRegistryException() {
this("please try again","Bad put registry data entered");
}

/**
 * BadLandRegistryException is two-argument constructor that is transfering message of exception to the RuntimeException
 * class and is setting the header.
 * @param Message 
 * @param header
 */
public BadLandRegistryException(String Message,String header) {
super(Message);
setheader(header);


}
/**
 * setheader is the public function that can set the header of exception.
 * @param header
 */
public void setheader(String header) {
this.header=header;
}
/**
 *  getheader is the public function that can be used in other classes to get the suitable header to user.
 * @return it returns header of exception.
 */
public String getheader() {
return header;	

}

}
