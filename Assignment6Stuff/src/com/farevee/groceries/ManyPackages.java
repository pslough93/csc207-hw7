package com.farevee.groceries;

public class ManyPackages implements Item{
	
	Package type; //package
	int amount; //quantity
	
	/**
	 * Constructor for ManyPackages
	 * @param pack
	 * @param amt
	 */
	public ManyPackages(Package pack, int amt){
		type = pack;
		amount = amt;
	}//ManyPackages(Package, int)
	
	/**
	 * getWeight() returns weight
	 */
	public Weight getWeight(){
		return new Weight(type.weight.unit, type.weight.amount*amount);
	}//getWeight()
	
	/**
	 * getPrice() returns price
	 */
	public int getPrice(){
		return type.price*amount;
	}//getPrice()
	
	/**
	 * toString returns ManyPackages string format
	 */
	public String toString(){
		return amount + " x " + type.toString();
	}//toString()
	
	/**
	 * getAmount() returns amount
	 * @return
	 */
	public int getAmount(){
		return amount;
	}//getAmount()
	
	/**
	 * getPackage() returns package part of ManyPackages
	 * @return
	 */
	public Package getPackage(){
		return type;
	}//getPackage()
	
	/**
	 * getName() returns name
	 */
	public String getName(){
		return type.name;
	}//getName()
	
	/**
	 * equals() returns true if two manyPackages have the same address
	 * @param i
	 * @return
	 */
	public boolean equals(Item i){
		return i.equals(this);
	}//equals(Item)
}//class ManyPackages
