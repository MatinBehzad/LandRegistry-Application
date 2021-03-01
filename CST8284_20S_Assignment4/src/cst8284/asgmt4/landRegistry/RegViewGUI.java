/* Assignment 4 GUI code provided by Prof. D. Houtman
 * for use in CST8284 Assignment 4, due August 7, 2020.
 * This code is for one-time use only during the Summer 2020 semester.
 * (c) D. Houtman.  All rights reserved
 */
/* Course Name: CST 8284-300 Object Oriented Programming (Java)
Student Name:Matineh Behzad
Class name:RegViewGUI
Date:8 August 2020
 */


package cst8284.asgmt4.landRegistry;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.lang.*;
import  javax.swing.JOptionPane;
import javax.swing.ImageIcon;



public final class RegViewGUI extends JFrame  {
	
	private static RegControl rc=new RegControl();
	static boolean s=false;
	
// gridx    gridy                  gridwidth  gridheight  weightx weighty anchor                       fill                           insets                      ipadx  ipady 
   private static final GridBagConstraints registrantConstraints = new GridBagConstraints(
   0,       0,                     1,         1,          0.5,     0,     GridBagConstraints.NORTH,    GridBagConstraints.VERTICAL,   new Insets(10, 10, 10, 10), 0,     0);
   private static final GridBagConstraints btnConstraints = new GridBagConstraints(
   1,       0,                     1,         2,          0.5,     0.5,   GridBagConstraints.NORTH,    GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5),   0,     0);
   private static final GridBagConstraints propertyConstraints = new GridBagConstraints(
   0,       1,                     1,         1,          0.5,     1,	  GridBagConstraints.NORTH,    GridBagConstraints.VERTICAL,   new Insets(5, 5, 5, 5),   0,     0);
   private static final GridBagConstraints southBtnConstraints = new GridBagConstraints(
   0,       2,                     1,         1,          0.5,     1,     GridBagConstraints.SOUTH,    GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5),   0,     0);
   private static final GridBagConstraints exitBtnConstraints = new GridBagConstraints(   
   1,       2,                     1,         1,          0.5,     1,	  GridBagConstraints.SOUTH,    GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5),   0,     0);
	
    private static final int     SPACE = 12, BORDER = 24;
    private static final boolean ON = true,  OFF = false;
    
    // eastBtnPanel values
    private static final int ADD_NEW_REGISTRANT = 1, DELETE_REGISTRANT = 2, ADD_PROPERTY = 3, 
    		                 DELETE_CURRENT_PROPERTY = 4, CHANGE_REGISTRATION_NUMBER = 5;
    // southBtn Panel values
    private static final int RELOAD_LAND_REGISTRY = 1, BACKUP_LAND_REGISTRY = 2;
    
    private static final Font      defaultFont         = new Font("Tahoma", Font.PLAIN, 20);	
    private static final Font      textFont         = new Font("Tahoma", Font.BOLD, 20);	

    private static final Dimension defaultDimension    = new Dimension(540, 40);
    private static final Dimension mainDimension       = new Dimension(1024, 540);
    
    public static       JFrame    f                   = new JFrame("");  
    private static final JPanel    mainPanel           = new JPanel(new GridBagLayout());
    private static       JPanel    registrantsPanel    = new JPanel(); 
    private static       JPanel    eastBtnPanel        = new JPanel();
    private static       JPanel    propertiesPanel     = new JPanel(); 
    private static       JPanel    southBtnPanel       = new JPanel();
    
    public static final String PROPERTIES_FILE = "LandRegistry.prop";
	public static final String REGISTRANTS_FILE = "LandRegistry.reg";
	
	public static void launchDialog(){
		
    	//------------------------------------------------------------------------------------------------------//
		     f.setTitle("Land Registry");                                                              // - O X //
		//------------------------------------------------------ -----------------------------------------------//
		//                                                                            //                        //
		             //                        //
		  mainPanel.add( loadEastBtnPanel(),  btnConstraints); 
		//
		  mainPanel.add(loadRegistrantsPanel(getRegControl().listOfRegistrants()));//, registrantConstraints); 
		 
		  
		  mainPanel.add(loadRegistrantsPanel(null), registrantConstraints);
		  
		  mainPanel.add(loadPropertyPanel(null), propertyConstraints); 
		  
		//----------------------------------------------------------------------------//                        //
		//                                                                            //                        //
			mainPanel.add(loadPropertyPanel(null), propertyConstraints);              //                        //
		//                                                                            //                        //		
    	//------------------------------------------------------------------------------------------------------//		
		//                                                                                                      //
            mainPanel.add(loadSouthBtnPanel(), southBtnConstraints);    //        EXIT            // 	                      
		//                                                                                                      //
		//------------------------------------------------------------------------------------------------------//
    
        
        mainPanel.setBorder(BorderFactory.createEmptyBorder(BORDER, BORDER, BORDER, BORDER));
        
        mainPanel.setPreferredSize(mainDimension);
        
	    f.add(mainPanel);
	    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.pack();
		f.setVisible(true);	 

	}
	
	private static JPanel loadRegistrantsPanel(ArrayList<Registrant> regs) {
		
		registrantsPanel.setPreferredSize(getStandardDimension(10, 192));
		registrantsPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		JComboBox<String> cBox = new JComboBox<String>();
		cBox.setFont(textFont);
		cBox.setPreferredSize(getStandardDimension(-5,10));   
		if (regs != null && regs.size() > 0) {
			cBox.addItem("All Registration Numbers");
			for (Registrant reg: regs) 
				cBox.addItem(Integer.toString(reg.getRegNum()));
		}
		
	
		
		JPanel cBoxPanel = new JPanel();
		cBoxPanel.add(cBox);
		registrantsPanel.add(cBoxPanel);
		
		
		
		JScrollPane regScrollPanel = new JScrollPane(getRestrantsScrollPanel(regs)) ;
		regScrollPanel.setPreferredSize(getStandardDimension(10,120));
		registrantsPanel.add(regScrollPanel);
		
		cBox.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				String value = cBox.getSelectedItem().toString();
				
				if (value.equals("All Registration Numbers")) {
					cBoxPanel.add(cBox);
					registrantsPanel.add(cBoxPanel);
					JScrollPane regScrollPanel = new JScrollPane(getRestrantsScrollPanel(regs)) ;
					regScrollPanel.setPreferredSize(getStandardDimension(10,120));
					registrantsPanel.removeAll();
					registrantsPanel.add(cBoxPanel);
					registrantsPanel.add(regScrollPanel);
					registrantsPanel.revalidate();
					registrantsPanel.repaint();
					}
				else
				{
					int regID = cBox.getSelectedIndex()-1;

					JScrollPane regScrollPanel = new JScrollPane(getRestrantsScrollPanel(regs.get(regID))) ;
					regScrollPanel.setPreferredSize(getStandardDimension(10,120));
					registrantsPanel.removeAll();
					registrantsPanel.add(cBoxPanel);
					registrantsPanel.add(regScrollPanel);
					registrantsPanel.revalidate();
					registrantsPanel.repaint();
				}
					
			}
			
		});
		
		return registrantsPanel;
	}
		
	private static JPanel loadEastBtnPanel() {
		
		eastBtnPanel.setLayout(new GridLayout(7, 1, SPACE, SPACE));
		eastBtnPanel.add(loadBtn("    Add New Registrant    ", 'l', true, (ActionEvent e)->viewAddNewRegistrant()));
		    // TODO: replace null with ActionListener to call dialog that prompts for new registrant's name
		eastBtnPanel.add(loadBtn("    Delete Registrant     ", '2', s, (ActionEvent e)->viewDeleteRegistrant()));  // TODO: replace null with ActionListener to delete current Registrant
		eastBtnPanel.add(loadBtn("       Add Property       ", '3', s, (ActionEvent e)->viewAddNewProperty()));  // TODO: replace null with ActionListener to add Property to current registrant
		eastBtnPanel.add(loadBtn("  Delete Current Property ", '4', s, (ActionEvent e)->viewDeleteProperty()));  // TODO: replace null with ActionListener to delete currently displayed property
		eastBtnPanel.add(loadBtn("Change Registration Number", '5', s,(ActionEvent e)->viewChangePropertyRegistrant()));  // TODO: replace null with ActionListener that prompts the user with a dialog of registerd regNums,
		eastBtnPanel.add(loadBtn(" Sort Property By Area    ", '6', s, (ActionEvent e)->viewSortPropertyByArea()));
		eastBtnPanel.add(loadBtn(" Sort Property By Tax     ", '7', s, (ActionEvent e)->viewSortPropertyByTax()));
		return eastBtnPanel;                                                        //       and uses this value in to replace the regNum assigned to each of the currently displayed properties														
	}

	private static JPanel loadPropertyPanel(ArrayList<Property> props) {
		propertiesPanel.setPreferredSize(getStandardDimension(10, 140));
		JScrollPane propertyScrollPanel = new JScrollPane(getPropertiesScrollPanel(props)) ;
		propertyScrollPanel.setPreferredSize(getStandardDimension(5,120));
		propertiesPanel.add(propertyScrollPanel);
		return propertiesPanel;
	}
	
	private static JPanel loadSouthBtnPanel() {
		southBtnPanel.setLayout(new GridLayout(1, 2, SPACE, SPACE));
		southBtnPanel.add(loadBtn("Load Land Registry", 'r', true, (ActionEvent e)->viewLoadLandRegistryFromBackUp()));    // TODO: replace null with ActionListener to load ArrayLists from Backup files
		southBtnPanel.add(loadBtn("Backup Land Registry", 'b', true, (ActionEvent e)->viewSaveLandRegistryToBackUp())); // TODO: replace null with ActionListener to backup ArrayLists to files
		southBtnPanel.setAlignmentX(RIGHT_ALIGNMENT);
		mainPanel.add(loadBtn("       Exit        ", 'x', true, (ActionEvent e)->f.dispose()), exitBtnConstraints);	  
		return southBtnPanel;
	}
	
	private static JPanel getRestrantsScrollPanel(ArrayList<Registrant> regs) {
	   JPanel regsScrollPanel = new JPanel(new GridLayout(regs==null?1:regs.size(), 1));
	   if (regs != null && !regs.isEmpty()) 
	      for (Registrant reg: regs) {
	         JPanel thisRegsNames = new JPanel(new GridLayout(3,1));
	         thisRegsNames.setPreferredSize(getStandardDimension(-20,120));
	         thisRegsNames.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
	         thisRegsNames.add(displayInformation("Registrant's #: ", Integer.toString(reg.getRegNum())));
	         thisRegsNames.add(displayInformation("first Name:     ", reg.getFirstName()));
	         thisRegsNames.add(displayInformation("Last Name:      ", reg.getLastName()));
	         regsScrollPanel.add(thisRegsNames);
	      }  
	   return regsScrollPanel;
	}
	
	private static JPanel getRestrantsScrollPanel(Registrant regs) {
		   JPanel regsScrollPanel = new JPanel(new GridLayout(regs==null?1:0, 1));
		  
		         JPanel thisRegsNames = new JPanel(new GridLayout(3,1));
		         thisRegsNames.setPreferredSize(getStandardDimension(-20,120));
		         thisRegsNames.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		         thisRegsNames.add(displayInformation("Registrant's #: ", Integer.toString(regs.getRegNum())));
		         thisRegsNames.add(displayInformation("first Name:     ", regs.getFirstName()));
		         thisRegsNames.add(displayInformation("Last Name:      ", regs.getLastName()));
		         regsScrollPanel.add(thisRegsNames);
		     
		   return regsScrollPanel;
		}
	
	private static JPanel getPropertiesScrollPanel(ArrayList<Property> props) {
		   JPanel propsScrollPane = new JPanel(new GridLayout(props==null?1:props.size(), 1));
		  
		   
		   if (props != null && !props.isEmpty()) 
		      for (Property prop: props) {
		         JPanel thisPropertyInfo = new JPanel(new GridLayout(3,2));
		         thisPropertyInfo.setPreferredSize(getStandardDimension(-20,240));
		         thisPropertyInfo.setBorder(BorderFactory.createLineBorder(Color.black));
		         thisPropertyInfo.add(displayInformation("Left  ", Integer.toString(prop.getXLeft())));
		         thisPropertyInfo.add(displayInformation("Top   ", Integer.toString(prop.getYTop())));
		         thisPropertyInfo.add(displayInformation("Length", Integer.toString(prop.getXLength())));
		         thisPropertyInfo.add(displayInformation("Width ", Integer.toString(prop.getYWidth())));
		         thisPropertyInfo.add(displayInformation("Area  ", Integer.toString(prop.getArea())));
		         thisPropertyInfo.add(displayInformation("Taxes ", Double.toString(prop.getTaxes())));
		         propsScrollPane.add(thisPropertyInfo);
		      }  
		   return propsScrollPane;
		}
	
	// Utility to set a button in a JPanel ON/OFF.  Use constants above for component number, and ON/OFF values for boolean parameter
	private static boolean setBtnIn(JPanel p, int componentNumber, boolean onOff) {  
		if (p.getComponentCount() < componentNumber) return false;
		JButton b = (JButton)p.getComponent(componentNumber - 1);
		b.setEnabled(onOff);
		return true;
	}
	
	// Utility to set a group of buttons in a JPanel ON/OFF.  Use ON/OFF for boolean parameter, and set the value of exceptFor with the buttons to be excluded
	private static void turnAllBtnsIn(JPanel p, boolean b, Integer... exceptFor) {
		List<Integer> arInt = Arrays.asList(exceptFor);
		for (int n = 1; n <= p.getComponentCount(); n++ ) {
			if (!(arInt.contains(n)))
				setBtnIn(p, n, b);
		}
	}
	
	// Gets standard dimension information and resizes its length and width by deltaX and deltaY
	private static Dimension getStandardDimension(int deltaX, int deltaY) {
		 Dimension d = (Dimension)defaultDimension.clone();
		 d.setSize(d.getWidth() + deltaX, d.getHeight() + deltaY);
		 return d;
	 }
	
	private static JPanel displayInformation(String label, String name) {
		JLabel l = new JLabel(label); 
		l.setFont(defaultFont);
		
		JTextField t = new JTextField(); 
		t.setBackground(Color.WHITE);
	    t.setFont(textFont);   t.setPreferredSize(getStandardDimension(-360,0));
	    t.setEditable(false);     t.setEnabled(false);    
	    t.setText(name); 
	    
	    JPanel p = new JPanel();
	    p.add(l); p.add(t);
	    return p;
	}
	
	private static JButton loadBtn(String label, char keyboardShortcut, boolean onOff, ActionListener act) {
		JButton btn = new JButton(label);
		btn.addActionListener(act);
		btn.setFont(defaultFont);
		btn.setEnabled(onOff);
		btn.setMnemonic(keyboardShortcut);
		return btn;
	}
	
   
  
    
   public static void viewAddNewRegistrant() {
	   
	   

     try {

	   String inputValue = JOptionPane.showInputDialog("Please Enter registrant 's firstName and lastName");
	   
	   if(!RegControl.isBadRegistrantinputMissingValue(inputValue))
			throw new BadLandRegistryException("Miss value \n", "Missing an Input value");
		else
			if (!RegControl.isBadRegistrantinputNullValue(inputValue))
				
			throw new BadLandRegistryException("Null value entered \n",
					"An attempt was made to pass a null value to a variable");
		
	
	   
   else {
			
	   Registrant reg =new Registrant(inputValue);
	   
	  getRegControl().addNewRegistrant(reg);
	  s=true;
	 
	  
	  
         }
     }
     
     catch(BadLandRegistryException e) {
		   JOptionPane.showMessageDialog(f,e.getMessage() + e.getheader());
	 }
      registrantsPanel.removeAll();
	  mainPanel.add(loadRegistrantsPanel(getRegControl().listOfRegistrants()), registrantConstraints);
	  mainPanel.revalidate();
	  mainPanel.repaint();
	  
	  eastBtnPanel.removeAll();
	  mainPanel.add( loadEastBtnPanel(),  btnConstraints);
	  mainPanel.revalidate();
	  mainPanel.repaint();
	  
	  
	  
    }
   
   public static void viewChangePropertyRegistrant() {
		
		
    ArrayList<Property> props = new ArrayList<>();
   	ArrayList<Property> property = new ArrayList<>();
   	
   	
   	
   	
    ImageIcon icon = new ImageIcon(new ImageIcon("src/images/image1.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
   	ArrayList<Integer> registrant = new ArrayList<>();
   
   for(Registrant r :getRegControl().listOfRegistrants()) {
	   registrant.add(r.getRegNum());
	}
   
   Integer[] arrStr = new Integer[registrant.size()];     // new array   
   registrant.toArray(arrStr);
   	
   Integer oldRegStr = (Integer)JOptionPane.showInputDialog(null, "Select one of the current registrant number",
            "Registrant's number", JOptionPane.QUESTION_MESSAGE, icon,arrStr, 1000);
  

   Integer newRegStr = (Integer)JOptionPane.showInputDialog(null, "Select the new registrant number",
           "Registrant's number", JOptionPane.QUESTION_MESSAGE, icon,arrStr, 1000);
  
   
  
  props = getRegControl().listOfProperties(oldRegStr);
	
  getRegControl().deleteProperties(props);
		property=getRegControl().changePropertyRegistrant(props, newRegStr);
		for(Property p:property) {
			getRegControl().addNewProperty(p);
		}
		
		 JOptionPane.showMessageDialog(f,"Operation completed; the new registration number" + newRegStr + 
		 		            "has replaced the old registration number"+oldRegStr + 
		 		       "");
		
		propertiesPanel.removeAll();
		  mainPanel.add(loadPropertyPanel(getRegControl().listOfAllProperties()),propertyConstraints);
		  mainPanel.revalidate();
		  mainPanel.repaint();
  
   
    }
   
   
   public static void viewSortPropertyByArea() {

	   ArrayList<Property> listOfProps = new ArrayList<Property>();
	   listOfProps = rc.listOfAllProperties();
	   Collections.sort(listOfProps,new theSortArea());
	   propertiesPanel.removeAll();
	   mainPanel.add(loadPropertyPanel(listOfProps),propertyConstraints);
	   mainPanel.revalidate();
	   mainPanel.repaint();

} 
   
public static void viewSortPropertyByTax() {

	   ArrayList<Property> listOfProps = new ArrayList<Property>();
	   listOfProps = rc.listOfAllProperties();
	   Collections.sort(listOfProps,new theSortTax());
	   propertiesPanel.removeAll();
	   mainPanel.add(loadPropertyPanel(listOfProps),propertyConstraints);
	   mainPanel.revalidate();
	   mainPanel.repaint();

}  
   public static void viewDeleteRegistrant() {
	   
	   
	   
	 try {  ImageIcon icon = new ImageIcon(new ImageIcon("src/images/image1.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
	 
	   	ArrayList<Integer> registrant = new ArrayList<>();
	   
	   for(Registrant r :getRegControl().listOfRegistrants()) {
		   registrant.add(r.getRegNum());
		}
	   
	   Integer[] arrStr = new Integer[registrant.size()];     // new array   
	   registrant.toArray(arrStr);
	   	
	   Integer registr = (Integer)JOptionPane.showInputDialog(null, "Select one of registrant number that want to be deleted",
	            "Registrant's number", JOptionPane.QUESTION_MESSAGE, icon,arrStr, 1000);
	   
	   int answer = JOptionPane.showConfirmDialog(
			    f,
			    "You are about to delete a registrant and all the its associated properties; do you wish to continue?",
			    "An Inane Question",
			    JOptionPane.YES_NO_OPTION);
	          
	   
	   if (answer == JOptionPane.YES_OPTION) {
		   
		   if(!RegControl.isRegistrantAvailable(getRegControl().listOfRegistrants())) 
				 throw new BadLandRegistryException("No registrant","There are no registrants currently listed"); 
		   
			getRegControl().deleteRegistrant(registr);
			
			getRegControl().deleteProperties(rc.listOfProperties(registr));
			
			JOptionPane.showMessageDialog(f, "Registrant and related properties deleted");
			
		}
	   
	 else {
		 JOptionPane.showMessageDialog(f, "Registrant is not deleted");
		 
	 }
	  }
	   catch(BadLandRegistryException e) {
		   JOptionPane.showMessageDialog(f,e.getMessage() + e.getheader());
		   
		    
	   }
	   
	     registrantsPanel.removeAll();
	     propertiesPanel.removeAll();
	   
		  mainPanel.add(loadRegistrantsPanel(getRegControl().listOfRegistrants()), registrantConstraints);
		  mainPanel.add(loadPropertyPanel(getRegControl().listOfAllProperties()),propertyConstraints);
		  mainPanel.revalidate();
		  mainPanel.repaint();
	   
	   
   }
   
  public static RegControl getRegControl() {
		return rc;
	}
   
	
	
	
public static void viewAddNewProperty() {
	
	
	try {
	
	    JTextField xLeft = new JTextField(5);
	    JTextField ytop = new JTextField(5);
	    JTextField xLength = new JTextField(5);
	    JTextField yWidth = new JTextField(5);
	    
	    
	   String regNum =  JOptionPane.showInputDialog("Please enter the Registrant number");
	    
	    if(!RegControl.isBadRegistrantinputMissingValue(String.valueOf(regNum)))
			throw new BadLandRegistryException("Miss value \n", "Missing an Input value");
		else
			if (!RegControl.isBadRegistrantinputNullValue(String.valueOf(regNum)))
				
			throw new BadLandRegistryException("Null value entered \n",
					"An attempt was made to pass a null value to a variable");
		
		else
			if(!(RegControl.isBadRegistrantinputMisMatch(String.valueOf(regNum)))){
				throw new BadLandRegistryException("Invalid Registration number\n",
						"Registration number must contain digits only; alphabetic and special characters are prohibited");
			}
		
		 if(!RegControl.isRegistrantAvailable(getRegControl().listOfRegistrants())) 
			 throw new BadLandRegistryException("No registrant","There are no registrants currently listed"); 
	 
	    
		else {
	    JPanel myPanel = new JPanel();
	    myPanel.add(new JLabel("Enter XLeft coordinate of property"));
	    myPanel.add(xLeft);
	    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	    myPanel.add(new JLabel("Enter YTop coordinate of property"));
	    myPanel.add(ytop);
	    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	    myPanel.add(new JLabel("Enter yWidth coordinate of property"));
	    myPanel.add(yWidth);
	    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	    myPanel.add(new JLabel("Enter xLength coordinate of property"));
	    myPanel.add(xLength);
	    

	    int result = JOptionPane.showConfirmDialog(null, myPanel,
	        "Please Enter property features Values", JOptionPane.OK_CANCEL_OPTION);
	    if (result == JOptionPane.OK_OPTION) {
	    	  Property property = new Property(Integer.parseInt(xLength.getText()), Integer.parseInt(yWidth.getText()),Integer.parseInt(xLeft.getText()), Integer.parseInt(ytop.getText()), Integer.parseInt(regNum));
	  	
	  	    rc.addNewProperty(property);
	  	    
	  	  JOptionPane.showMessageDialog(f,"New property has been registered");
	
	    }
	
	  

		  propertiesPanel.removeAll();
		  mainPanel.add(loadPropertyPanel(getRegControl().listOfAllProperties()),propertyConstraints);
		  mainPanel.revalidate();
		  mainPanel.repaint();
	}
     	}

	   catch(BadLandRegistryException e) {
		   JOptionPane.showMessageDialog(f,e.getMessage() + e.getheader());
		   
     
	   } 
	 propertiesPanel.removeAll();
	  mainPanel.add(loadPropertyPanel(getRegControl().listOfAllProperties()),propertyConstraints);
	  mainPanel.revalidate();
	  mainPanel.repaint();
}


public static void viewDeleteProperty() {

	ArrayList<Property> prop = new ArrayList<>();
	boolean de=true;
	
	try{
		
		String regNum =  JOptionPane.showInputDialog("Please enter the Registrant number");
	

	 if(!RegControl.isRegistrantAvailable(getRegControl().listOfRegistrants())) 
		 throw new BadLandRegistryException("No registrant","There are no registrants currently listed");
	 
	 else 
	    if(!RegControl.isBadRegistrantinputMissingValue(String.valueOf(regNum)))
			throw new BadLandRegistryException("Miss value \n", "Missing an Input value");
	  
		else
			if (!RegControl.isBadRegistrantinputNullValue(String.valueOf(regNum)))
				
		    	throw new BadLandRegistryException("Null value entered \n",
					"An attempt was made to pass a null value to a variable");
		
		else
			if(!(RegControl.isBadRegistrantinputMisMatch(String.valueOf(regNum)))){
				throw new BadLandRegistryException("Invalid Registration number\n",
						"Registration number must contain digits only; alphabetic and special characters are prohibited");
			}
	 
	 
	
	
	
	
     if(getRegControl().findRegistrant(Integer.parseInt(regNum))==null)
    	 JOptionPane.showMessageDialog(f,"No properties are associated with that registration number");
	  else 
	{
		   prop=rc.listOfProperties(Integer.parseInt(regNum));
		  int answer = JOptionPane.showConfirmDialog(
				    f,
				    "You are about to delete "+prop.size()+"property with this registrant number do you wish to continue?",
				    "An Inane Question",
				    JOptionPane.YES_NO_OPTION);
	      
	      if (answer == JOptionPane.YES_OPTION) {
	    	    rc.deleteProperties(prop);
				
				
			}
	
	}
    de=rc.deleteProperties(prop);
    
    
       if (de==false) {
    	   JOptionPane.showMessageDialog(f,"Arrays property is empty");
      }
	}  
	  catch(BadLandRegistryException e) {
		   JOptionPane.showMessageDialog(f,e.getMessage() + e.getheader());
		   
    
	   }
       
       
         propertiesPanel.removeAll();
		  mainPanel.add(loadPropertyPanel(getRegControl().listOfAllProperties()),propertyConstraints);
		  mainPanel.revalidate();
		  mainPanel.repaint();
}


public static void viewSaveLandRegistryToBackUp() {
	
	
	
	
	JOptionPane.showMessageDialog(f,(getRegControl().saveToFile(getRegControl().listOfRegistrants(), REGISTRANTS_FILE))
			
		&& (getRegControl().saveToFile(getRegControl().listOfAllProperties(), PROPERTIES_FILE))?
		"Land Registry has been backed up to file":
		"Could not back up land registry");
	
	
}

public static void viewLoadLandRegistryFromBackUp() {
	
	int answer = JOptionPane.showConfirmDialog(
		    f,
		    "you are about to overwrite existing records; do you wish to continue?",
		    "An Inane Question",
		    JOptionPane.YES_NO_OPTION);
          
   
   if (answer == JOptionPane.YES_OPTION) {
	   
	   propertiesPanel.removeAll();
 	   registrantsPanel.removeAll(); 
 	  
        getRegControl().refreshProperties();
    	getRegControl().refreshRegistrants();
    	
    	JOptionPane.showMessageDialog(f,"Land Registry has been loaded from backup file");
    	
    	
    		 
    	  mainPanel.add(loadRegistrantsPanel(getRegControl().listOfRegistrants()), registrantConstraints);
          mainPanel.add(loadPropertyPanel(getRegControl().listOfAllProperties()),propertyConstraints);
		  mainPanel.revalidate();
		  mainPanel.repaint();
		  
    	
    
}

    
    
    }
}
