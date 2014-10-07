package com.farevee.groceries;

public class NonFood implements Item{
	
	public String name;
	Weight weight;
	int price;
	
	/**
	 * NonFood Constructor
	 * @param nm
	 * @param wt
	 * @param pr
	 */
	public NonFood(String nm, Weight wt, int pr){
		name = nm;
		weight = wt;
		price = pr;
	}//NonFood(String, Weight, int)
	
	/**
	 * getWeight() returns weight
	 */
	public Weight getWeight(){
		return this.weight;
	}//getWeight()
	
	/**
	 * getPrice() returns price
	 */
	public int getPrice(){
		return this.price;
	}//getPrice()
	
	/**
	 * toString() returns string format of NonFood
	 */
	public String toString(){
		return name;
	}//toString//
	
	/**
	 * getName() returns name
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * equals returns true if Item and object are equal
	 * @param i
	 * @return
	 */
	public boolean equals(Item i){
		//if is a nonfood
		if(i instanceof NonFood){
			//cast to NonFood
			NonFood that = (NonFood) i;
			//if all parameters equal
			if(this.name.equals(that.name)&&
			   this.weight.amount == that.weight.amount&&
			   this.weight.unit.name.equals(that.weight.unit.name)&&
			   this.price == that.price){
				//return true
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
}//class NonFood
