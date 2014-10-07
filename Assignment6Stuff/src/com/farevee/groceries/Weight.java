package com.farevee.groceries;

public class Weight {
	
	Units unit;
	int amount;
	
	/**
	 * Constructor for a Weight
	 * @param u
	 * @param amt
	 */
	public Weight(Units u, int amt){
		unit = u;
		amount = amt;
	}//Weight(Units, int)
	
	/**
	 * returns unit name
	 * @return
	 */
	public String getUnitName(){
		return unit.name;
	}//getUnitName()
	
	/**
	 * returns amount
	 * @return
	 */
	public int getUnitAmount(){
		return amount;
	}//getUnitAmount()
}
