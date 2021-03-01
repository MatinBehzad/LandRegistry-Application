package cst8284.asgmt4.landRegistry;

import java.util.Comparator;

public class theSortArea implements Comparator<Property>{
	
		public int compare(Property p1, Property p2) {
		   	return p1.getArea() - p2.getArea();
		}

}
