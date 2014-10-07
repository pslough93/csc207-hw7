package com.farevee.shopping;

import java.io.PrintWriter;
import java.util.ArrayList;

import com.farevee.groceries.*;
import com.farevee.groceries.Package;

public class Cart {
	
	ArrayList<Item> itemsInCart; //arraylist of items
	int totalCost; 
	int numThings;
	int numEntries;
	int[] weight;
	
	/**
	 * Cart Constructor
	 */
	public Cart(){
		itemsInCart = new ArrayList<Item>();
		totalCost = 0;
		numThings = 0;
		numEntries = 0;
		weight = new int[4]; //initialize all variables to zero
		for(int i = 0; i < 4; i++){
			weight[i] = 0;
		}
	}//Cart()
	
	/**
	 * Adds an Item to the arraylist and updates other fields as necessary
	 * @param i
	 */
	public void add(Item i){
		itemsInCart.add(i); //add item to end of list
		totalCost += i.getPrice(); //add item price to total cost
		numEntries++; //add an entry
		if(i instanceof ManyPackages){ //if item is a ManyPackages
			ManyPackages mp = (ManyPackages) i;
			numThings += mp.getAmount(); //add in number of things in ManyPackage
		}
		else{
			numThings++; //otherwise add one thing
		}
		Weight wt = i.getWeight(); //get weight isolated
		switch(wt.getUnitName()){ //add weight to corresponding space in weight array
		case "pound": weight[0] += wt.getUnitAmount();
					break;
		case "ounce": weight[1] += wt.getUnitAmount();
					break;
		case "kilogram": weight[2] += wt.getUnitAmount();
					break;
		case "gram": weight[3] += wt.getUnitAmount();
					break;
		}
	}//add(Item)
	
	/**
	 * returns number of things
	 * @return
	 */
	public int numThings(){
		return numThings;
	}//numThings()
	
	/**
	 * returns number of entries
	 * @return
	 */
	public int numEntries(){
		return numEntries;
	}//numEntries()
	
	/**
	 * Uses pen to print all items in cart
	 * @param pen
	 */
	public void printContents(PrintWriter pen){
		for(int i = 0; i < itemsInCart.size(); i++){
			pen.println(itemsInCart.get(i).toString());
		}//for
	}//printContents(PrintWriter)
	
	/**
	 * returns price of all items in cart
	 * @return
	 */
	public int getPrice(){
		return totalCost;
	}//totalCost()
	
	/**
	 * returns array of weights in cart
	 * @return
	 */
	public int[] getWeight(){
		return weight;
	}//getWeight()
	
	/**
	 * Removes all instances of an item with the given name
	 * @param name
	 */
	public void remove(String name){
		for(int i = 0; i < itemsInCart.size(); i++){
			//if name equals item name
			if(name.equals(itemsInCart.get(i).getName())){
				//take out cost of item
				totalCost -= itemsInCart.get(i).getPrice();
				//take out entry
				numEntries--;
				//take out things with number dependent on 
				//whether its a ManyPackges
				if(itemsInCart.get(i) instanceof ManyPackages){
					ManyPackages mp = (ManyPackages) itemsInCart.get(i);
					numThings -= mp.getAmount();
				}//if
				else{
					numThings--;
				}//else
				Weight wt = itemsInCart.get(i).getWeight(); //isolate weight
				//subtract wieght from corresponding space in weight array
				switch(wt.getUnitName()){
				case "pound": weight[0] -= wt.getUnitAmount();
							break;
				case "ounce": weight[1] -= wt.getUnitAmount();
							break;
				case "kilogram": weight[2] -= wt.getUnitAmount();
							break;
				case "gram": weight[3] -= wt.getUnitAmount();
							break;
				}//switch
				//remove item in cart
				itemsInCart.remove(i);
				//subtract i 
				i--;
			}//if
		}//for
	}//remove(String)
	
	/**
	 * Merges all mergable items. Packages and manyPackages of similar types
	 * can be merged, and similar bulkitems can be merged.
	 */
	public void merge(){
		int index = 0;
		while(index < itemsInCart.size()){
			//if item is a package
			if(itemsInCart.get(index) instanceof Package){
				//cast to item to package
				Package indexItem = (Package) itemsInCart.get(index);
				//for every item beyond it
				for(int j = index + 1; j < itemsInCart.size(); j++){
					//find if item is a package
					if(itemsInCart.get(j) instanceof Package){
						//if so, cast to new package
						Package curItem = (Package) itemsInCart.get(j);
						//if they equal each other
						if(curItem.equals(indexItem)){
							//make new manyPackage and insert it at index
							itemsInCart.add(index, new ManyPackages(curItem, 2));
							//remove others
							numEntries --; //subtract one entry
							itemsInCart.remove(index + 1); //remove shifted index
							itemsInCart.remove(j); //removed absorbed item
						}//if
					}//if
					//or if item is a ManyPackage
					else if(itemsInCart.get(j) instanceof ManyPackages){
						//cast to manyPackage
						ManyPackages curItemMP = (ManyPackages) itemsInCart.get(j);
						//cast package part of item to package
						Package curItem = curItemMP.getPackage();
						//if packages are equal
						if(curItem.equals(indexItem)){
							//make new manyPackage
							itemsInCart.add(index, new ManyPackages(curItem, (curItemMP.getAmount() + 1)));
							//remove others
							numEntries--;
							itemsInCart.remove(index + 1);
							itemsInCart.remove(j);
						}//if
					}//else if
				}//for
			}//if
			//if item is a ManyPackage
			if(itemsInCart.get(index) instanceof ManyPackages){
				//cast to ManyPackages
				ManyPackages indexItem = (ManyPackages) itemsInCart.get(index);
				for(int j = index + 1; j < itemsInCart.size(); j++){
					//if item is package
					if(itemsInCart.get(j) instanceof Package){
						//cast to package
						Package curItem = (Package) itemsInCart.get(j);
						//if package part of indexitem equals curItem.
						if(indexItem.getPackage().equals(curItem)){
							//add updated ManyPackages
							itemsInCart.add(index, new ManyPackages(curItem, indexItem.getAmount() + 1));
							//remove others
							numEntries--;
							itemsInCart.remove(index + 1);
							itemsInCart.remove(j);
						}//if
					}//if
					else if(itemsInCart.get(j) instanceof ManyPackages){
						ManyPackages curItem = (ManyPackages) itemsInCart.get(j);
						if(indexItem.getPackage().equals(curItem.getPackage())){
							itemsInCart.add(index, new ManyPackages(curItem.getPackage(), indexItem.getAmount() + curItem.getAmount()));
							numEntries--;
							itemsInCart.remove(index + 1);
							itemsInCart.remove(j);
						}//if
					}//else if
				}//for
			}//if
			if(itemsInCart.get(index) instanceof BulkItem){
				BulkItem indexItem = (BulkItem) itemsInCart.get(index);
				for(int j = index + 1; j < itemsInCart.size(); j++){
					//if item is a bulkItem
					if(itemsInCart.get(j) instanceof BulkItem){
						//cast item to BulkItem
						BulkItem curItem = (BulkItem) itemsInCart.get(j);
						if(indexItem.getFood().equals(curItem.getFood())){
							BulkFood food = indexItem.getFood(); //intialize BulkFood Parts
							Units unit = indexItem.getUnits();
							int amt = indexItem.getWeight().getUnitAmount() + curItem.getWeight().getUnitAmount();
							//add item at index and work 
							itemsInCart.add(index, new BulkItem(food, unit, amt));
							numEntries--;
							itemsInCart.remove(index + 1);
							itemsInCart.remove(j);
						}//if
					}//if
				}//for
			}//if
			index++; //iterate index
		}//while
	}//merge()
}//class Cart
