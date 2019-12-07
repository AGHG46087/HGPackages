package com.hgtable;

import javax.swing.*;
/**
 * author:      hgrein<BR>
 * date:        Jun 8, 2004<BR>
 * Package:     com.hgtable<BR>
 * File Name:   ColumnMouseEventListener.java<BR>
 * Type Name:   ColumnMouseEventListener<BR>
 * Description: An Event Listener interface for columns.  
 * The Event listener is used to listen for mouse events that are being 
 * executed on the Columns within a table.
 * @see com.hgtable.HGTable
 */
public interface ColumnMouseEventListener
{

  /**
   * Adds any Mouse Listeners to Table
   * Creation date: (12/31/2001 8:18:14 AM)
   * @param tableView JTable
   */
  public void addMouseListenersToTable(JTable tableView);
}