package com.hgtable;

/**
 * author:      hgrein<BR>
 * date:        Jun 8, 2004<BR>
 * Package:     com.hgtable<BR>
 * File Name:   ColumnDataCellTooltip.java<BR>
 * Type Name:   ColumnDataCellTooltip<BR>
 * Description: Interface Object that can indicate which piece of data is being returned
 * at all times.  And Presents a Tooltip representation.
 * @see com.hgtable.HGTable
 */
public interface ColumnDataCellTooltip
{
  /**
   * Insert the method's description here.
   * Creation date: (12/31/2001 8:18:14 AM)
   * @return String
   * @param col int column number
   */
  public String getCellToolTip(int col);
  
}
