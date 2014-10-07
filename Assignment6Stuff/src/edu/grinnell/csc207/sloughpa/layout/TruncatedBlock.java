package edu.grinnell.csc207.sloughpa.layout;

public class TruncatedBlock
    implements TextBlock
{

  TextBlock contents;
  int width;

  /**
  * Create a new truncated block of the specified width.
  */
  public TruncatedBlock(TextBlock tb, int width)
  {
    contents = tb;
    this.width = width;
  } // TruncatedBlock(TextBlock, int)

  /**
   * Get the ith row of the block.
   * @throws Exception 
   */
  public String row(int i)
    throws Exception
  {
    if (i >= 0 && i < contents.height())
      {
        return contents.row(i).substring(0, width);
      }
    else
      {
        throw new Exception("Invalid row " + i);
      }
  } // row(int)

  /**
   * Determine how many rows are in the block.
   */
  public int height()
  {
    return contents.height();
  } // height()

  /**
   * Determine how many columns are in the block.
   */
  public int width()
  {
    return contents.width();
  } // width()
} // class TruncatedBlock

