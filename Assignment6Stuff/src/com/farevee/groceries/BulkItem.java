package com.farevee.groceries;

public class BulkItem implements Item{
	
	BulkFood food;
	Units unit; 
	int amount;
	
	/**
	 * Constructor for BulkItem
	 * @param bf
	 * @param u
	 * @param amt
	 */
	public BulkItem(BulkFood bf, Units u, int amt){
		food = bf;
		unit = u;
		amount = amt;
	}//BulkItem(BulkFood, Units, int)
	
	/**
	 * toString - returns string in BulkItem format
	 */
	public String toString(){
		if(amount == 1){
			String result = "" + amount + " " + unit.name + " of " + food.name;
			return result;
		}//if
		else{
			String result = "" + amount + " " + unit.plural + " of " + food.name;
			return result;
		}//else
	}//toString()
	
	/**
	 * getWeight() returns weight of object
	 */
	public Weight getWeight(){
		return new Weight(unit, amount);
	}//getWeight()
	
	/**
	 * getPrice() returns price of object
	 */
	public int getPrice(){
		return amount * food.pricePerUnit;
	}//getPrice()
	
	/**
	 * getName() returns name of object
	 */
	public String getName(){
		return food.name;
	}//getName()
	
	/**
	 * getFood() returns food part of item
	 */
	public BulkFood getFood(){
		return food;
	}//getFood()
	
	/**
	 * getUnits() returns unit of object
	 */
	public Units getUnits(){
		return unit;
	}//getUnits()
	
	/**
	 * equals(Item) returns whether or not a BulkItem is equal to item
	 */
	public boolean equals(Item i){
		//if i is a BulkItem
		if(i instanceof BulkItem){
			//cast to BulkItem
			BulkItem that = (BulkItem) i;
			//if all fields are equal
			if(this.food.equals(that.food)&&
			   this.unit.name.equals(that.unit.name)&&
			   this.amount == that.amount){
				return true;
			}//if
			else{
				return false;
			}//else	
		}//if
		else{
			return false;
		}//else	
	}//equals(Item)
}//class BulkItem
