package edu.grinnell.csc207.sloughpa.layout;

public class Grid 
	implements TextBlock{
	
	int width; //grid width
	int height; //grid height
	char ch; //character of grid
	
	/**
	 * Constructor for Grid
	 * @param width
	 * @param height
	 * @param ch
	 */
	public Grid(int width, int height, char ch){
		this.width = width;
		this.height = height;
		this.ch = ch;
	}//Grid(int, int, ch)
	
	/**
	 * width - returns width of block
	 * Precondition: none
	 * Postcondition: returns width 
	 */
	public int width(){
		return this.width;
	}//width()
	
	/**
	 * height - returns height of block
	 * Precondition: none
	 * Postcondition: returns height 
	 */
	public int height(){
		return this.height;
	}//height()
	
	/**
	 * row(i) returns row for Centered Block
	 * Preconditions: i is a valid line
	 * Postconditions: returns line corresponding to i
	 */
	public String row(int i) throws Exception{
		//if valid line
		if(i >= 0 && i <= this.height){
			String result = "";
			//adds width amount of characters
			for(int j = 0; j < this.width; j++)
				result += this.ch;
			return result;
		}//if
		else
        throw new Exception("Invalid row " + i);
	}//row(int)
}//class Grid
