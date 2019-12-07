package com.hgtable;

/**
 * author:      hgrein<BR>
 * date:        Jun 8, 2004<BR>
 * Package:     com.hgtable<BR>
 * File Name:   ColumnDataCellValue.java<BR>
 * Type Name:   ColumnDataCellValue<BR>
 * Description: Interface Object that can indicate which piece of data is being returned
 * at all times.
 * @see com.hgtable.HGTable
 */
public interface ColumnDataCellValue
{
  /**
   * Insert the method's description here.
   * Creation date: (12/31/2001 8:18:14 AM)
   * @param col - int, column number
   * @return Object 
   */
  public Object getColumnDataCell(int col);
  
}