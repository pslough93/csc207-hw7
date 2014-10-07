package edu.grinnell.csc207.sloughpa.layout;

public class RightJustified implements TextBlock{
	
	TextBlock contents; //contents
	int width; //width
	
	/**
	 * RightJustified block constructor
	 * @param tb
	 * @param width
	 */
	public RightJustified(TextBlock tb, int width){
		contents = tb;
		this.width = width;
	}//RightJustified(TextBlock, width);
	
	/**
	 * row(i) returns row for Centered Block
	 * Preconditions: i is a valid line
	 * Postconditions: returns line corresponding to i
	 */
	public String row(int i) throws Exception{
		//throws exception when width is too small
		if(this.width < contents.width())
			throw new Exception("invalid width");
		//if valid row
		if(i >= 0 && i < contents.height()){
			//counts spaces
			int spaces = this.width - contents.width();
			String result = "";
			for(int j = 0; j < spaces; j++)
				result += ' ';
			//fill with spaces and add string
			result += contents.row(i);
			return result;
		}//if
		else
			throw new Exception("invalid row " + i);
	}//row(int)
	
	/**
	 * width - returns width of RightJustified
	 * Precondition: none
	 * Postcondition: will return width of contents
	 */
	public int width(){
		return this.width;
	}//width()
	
	/**
	 * height - returns height of BlockPair
	 * Precondition: none
	 * Postcondition: will return height of contents
	 */
	public int height(){
		return contents.height();
	}//height()
}//class RightJustified
