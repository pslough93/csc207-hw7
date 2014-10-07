package com.farevee.groceries;

public interface Item {
	//Item Interface
	
	/**
	 * returns weight of item
	 * @return
	 */
	public Weight getWeight();
	
	/**
	 * returns price of item
	 * @return
	 */
	public int getPrice();
	
	/**
	 * returns string of item. Format depends on type
	 * @return
	 */
	public String toString();
	
	/**
	 * returns name of item;
	 * @return
	 */
	public String getName();
}
