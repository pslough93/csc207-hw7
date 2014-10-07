package com.farevee.tests;

import java.io.PrintWriter;

import com.farevee.groceries.*;
import com.farevee.groceries.Package;
import com.farevee.shopping.*;

public class FareVeeTests {

	public static void main(String[] args) {
		PrintWriter pen = new PrintWriter(System.out, true);
		Cart c1 = new Cart();
		BulkFood bf1 = new BulkFood("Ham", Units.POUND, 100, 2);
		BulkItem bi1 = new BulkItem(bf1, Units.POUND, 2);
		c1.add(bi1);
		BulkContainer bc1 = new BulkContainer("Bag", bf1, Units.POUND, 2);
		c1.add(bc1);
		Package p1 = new Package("Cereal", new Weight(Units.OUNCE, 12), 400);
		c1.add(p1);
		NonFood nf1 = new NonFood("Knife", new Weight(Units.GRAM, 500), 1600);
		c1.add(nf1);
		ManyPackages mp1 = new ManyPackages(new Package("Mac and Cheese", new Weight(Units.KILOGRAM, 1), 100), 6);
		c1.add(mp1);
		
		c1.printContents(pen);
		
		pen.println("Price in cents: " + c1.getPrice());
		pen.println("Number of things: " + c1.numThings());
		pen.println("Number of entries: " + c1.numEntries());
		pen.println("Weights: " + c1.getWeight()[0] + ", " + c1.getWeight()[1] + ", " + c1.getWeight()[2] + ", " + c1.getWeight()[3]);
		pen.println();
		
		BulkItem bi2 = new BulkItem(bf1, Units.POUND, 3);
		c1.add(bi2);
		c1.printContents(pen);
		pen.println("Price in cents: " + c1.getPrice());
		pen.println("Number of things: " + c1.numThings());
		pen.println("Number of entries: " + c1.numEntries());
		pen.println("Weights: " + c1.getWeight()[0] + ", " + c1.getWeight()[1] + ", " + c1.getWeight()[2] + ", " + c1.getWeight()[3]);
		pen.println();
		
		c1.merge();
		c1.printContents(pen);
		pen.println("Price in cents: " + c1.getPrice());
		pen.println("Number of things: " + c1.numThings());
		pen.println("Number of entries: " + c1.numEntries());
		pen.println("Weights: " + c1.getWeight()[0] + ", " + c1.getWeight()[1] + ", " + c1.getWeight()[2] + ", " + c1.getWeight()[3]);
		pen.println();
		
/*		
		c1.remove("Mac and Cheese");
		c1.printContents(pen);
		pen.println("Price in cents: " + c1.getPrice());
		pen.println("Number of things: " + c1.numThings());
		pen.println("Number of entries: " + c1.numEntries());
		pen.println("Weights: " + c1.getWeight()[0] + ", " + c1.getWeight()[1] + ", " + c1.getWeight()[2] + ", " + c1.getWeight()[3]);
*/
		pen.close();
	}

}
