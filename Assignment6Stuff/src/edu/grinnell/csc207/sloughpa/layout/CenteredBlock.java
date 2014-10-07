package edu.grinnell.csc207.sloughpa.layout;

public class CenteredBlock implements TextBlock{

	TextBlock contents; //TextBlock of contents
	int width; //width of block
	
	/**
	 * Constructor for new Centered Block
	 * @param tb
	 * @param width
	 */
	public CenteredBlock(TextBlock tb, int width){
		contents = tb; 
		this.width = width;
	}//CenteredBlock(TextBlock, width)
	
	/**
	 * row(i) returns row for Centered Block
	 * Preconditions: i is a valid line
	 * Postconditions: returns line corresponding to i
	 */
	public String row(int i) throws Exception{
		//throw exception if width is too short
		if(this.width < contents.width())
			throw new Exception("Invalid width");
		//if valid row
		if(i >= 0 && i < this.height()){
			//find spaces at beginning
			int spaces = (this.width - contents.width())/2;
			//find spaces at end
			int remaining = (this.width - contents.width()) - spaces;
			String result = "";
			//Put spaces at beginning
			for(int j = 0; j < spaces; j++)
				result += ' ';
			result += contents.row(i);
			//put spaces at end
			for(int k = 0; k < remaining; k++)
				result += ' ';
			return result;
		}//if
		else
			throw new Exception("Invalid Height " + i);
	}//row(int)
	
	/**
	 * width-returns width of contents
	 * Preconditions: none
	 * Postconditions: returns width
	 */
	public int width(){
		return this.width;
	}//width()
	
	/**
	 * height - returns height of contents
	 * Preconditions: none
	 * Postconditions: returns height
	 */
	public int height(){
		return contents.height();
	}//height()
}//class CenteredBlock
