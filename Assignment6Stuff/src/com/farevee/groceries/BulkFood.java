package com.farevee.groceries;

public class BulkFood {
	
	public String name; //string for name
	Units unit; //unit of food
	int pricePerUnit; //price per unit
	int supply; // int for supply
	
	/**
	 * BulkFood Constructor
	 * @param nm
	 * @param u
	 * @param ppu
	 * @param sup
	 */
	public BulkFood(String nm, Units u, int ppu, int sup){
		name = nm;
		unit = u;
		pricePerUnit = ppu;
		supply = sup;
	}//Bulkfood(String, Units, int, int)
	
	/**
	 * equals - finds if two bulkfoods are equal to each other
	 * @param i
	 * @return
	 */
	public boolean equals(Item i){
		//if item is a bulkfood
		if(i instanceof BulkFood){
			//cast to BulkFood
			BulkFood that = (BulkFood) i;
			//if all parameters are equal
			if(this.name.equals(that.name)&&
			   this.unit.name.equals(that.unit.name)&&
			   this.pricePerUnit == that.pricePerUnit&&
			   this.supply == that.supply){
				return true; //true
			}//if
			else{ //false otherwise
				return false;
			}//else	
		}//if
		else{
			return false;
		}//else	
	}//equals(Item)
}//class BulkFood
