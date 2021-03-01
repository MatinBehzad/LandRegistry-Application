package cst8284.asgmt4.landRegistry;

import java.util.Comparator;

public class theSortTax implements Comparator<Property>{ 
		public int compare(Property p1, Property p2) {
			int a = (int) Math.round(p1.getTaxes());
			int b = (int) Math.round(p2.getTaxes());
		   	return a - b;
		}

}
