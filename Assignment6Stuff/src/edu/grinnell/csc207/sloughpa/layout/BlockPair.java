package edu.grinnell.csc207.sloughpa.layout;

public class BlockPair implements TextBlock{
	
	//Textblock containing contents
	TextBlock contents;
	/**
	 * Constructor for Blockpairs
	 * @param tb
	 */
	public BlockPair(TextBlock tb){
		contents = tb;
	}//BlockPair(TextBlock)
	
	/**
	 * row(i) - returns row of BlockPair
	 * Precondition: i must correspond to a valid row
	 * PostCondition: will return the row of I with the contents doubled
	 */
	public String row(int i) throws Exception{
		//If valid row
		if(i >= 0 && i < contents.height()){
			//return 2*contents
			String result = contents.row(i) + contents.row(i);
			return result;
		}//if
		else{
			throw new Exception("invalid row "+ i);
		}//else
	}//row(int)
	
	/**
	 * width - returns width of BlockPair
	 * Precondition: none
	 * Postcondition: will return width of contents
	 */
	public int width(){
		return contents.width();
	}//width()
	
	/**
	 * height - returns width of BlockPair
	 * Precondition: none
	 * Postcondition: will return height of contents
	 */
	public int height(){
		return contents.height();
	}//height()
}//class BlockPair
