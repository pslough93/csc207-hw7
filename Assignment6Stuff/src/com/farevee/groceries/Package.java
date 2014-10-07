package com.farevee.groceries;

public class Package
    implements Item
{

  public String name;
  Weight weight;
  int price;

  /**
   * Constructor for Package
   * @param nm
   * @param wt
   * @param pr
   */
  public Package(String nm, Weight wt, int pr)
  {
    name = nm;
    weight = wt;
    price = pr;
  }//Package(String, Weight, int)

  /**
   * toString() returns string version of Package
   */
  public String toString()
  {
    String result =
        "" + weight.amount + " " + weight.unit.abbrev + " package of " + name;
    return result;
  }//toString()

  /**
   * getWeight() returns weight
   */
  public Weight getWeight()
  {
    return this.weight;
  }//getWeight()

  /**
   * getPrice() returns price
   */
  public int getPrice()
  {
    return this.price;
  }//getPrice()

  /**
   * getName() returns name
   */
  public String getName()
  {
    return name;
  }//getName

  /**
   * returns true if items is equal in all areas
   * to Package item
   * @param i
   * @return
   */
  public boolean equals(Item i)
  {
    //if i is a package
    if (i instanceof Package)
      {
        //cast to Package
        Package that = (Package) i;
        //if all parameters are equal
        if (this.name.equals(that.name)
            && this.weight.amount == that.weight.amount
            && this.weight.unit.name.equals(that.weight.unit.name)
            && this.price == that.price)
          {
            //return true
            return true;
          }//if
        else
          {
            return false;
          }//else	
      }//if
    else
      {
        return false;
      }//else	
  }//equals(Item)
}//class Package
