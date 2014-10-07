package com.farevee.groceries;

public class BulkContainer extends BulkItem{
	
	String container; //string for container
	
	/**
	 * Constructor for BulkContainer
	 * @param cont
	 * @param food
	 * @param u
	 * @param amt
	 */
	public BulkContainer(String cont, BulkFood food, Units u, int amt){
		super(food, u, amt);
		container = cont;
	}//BulkContainer(String, BulkFood, Units, int)

	/**
	 * toString() - returns BulkContainer String
	 */
	public String toString(){
		return container + " of " + super.toString();
	}//toString()
	
	/**
	 * Equals - determines if two BulkContainers are equal
	 */
	public boolean equals(Item i){
		//if item is a BulkContainer
		if(i instanceof BulkContainer){
			//cast to BulkContainer
			BulkContainer that = (BulkContainer) i;
			//if strings and foods are the same true
			if(this.container.equals(that.container)&&
			   this.equals(that)){
				return true;
			}//if
			else{
				//otherwise false
				return false;
			}//else	
		}//if
		else{
			return false;
		}//else	
	}//equals(Item)
}//class BulkContainer
