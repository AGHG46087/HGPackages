package com.hgtable;

import java.util.*;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.*;
import java.awt.event.*;
import java.io.Serializable;

import javax.swing.event.*;

/**
 * author:      hgrein<BR>
 * date:        May 31, 2004<BR>
 * Package:     com.hgtable<BR>
 * File Name:   HGTableModel.java<BR>
 * Type Name:   HGTableModel<BR>
 * Description: Generic Table Model Providing Base fuinctionality, as well as Generic Sorting
 */
public class HGTableModel extends AbstractTableModel implements ColumnMouseEventListener, Serializable
{
  /** Field <code>serialVersionUID</code> : long */
  private static final long serialVersionUID = 9083630345796214395L;

  /**
   * Field <code>headers</code> : Vector
   * 
   * @uml.property name="headers"
   * @uml.associationEnd multiplicity="(0 -1)" elementType="com.hgtable.ColumnHeaderData"
   */
  protected Vector headers = null;

  /**
   * Field <code>data</code> : Vector
   * 
   * @uml.property name="data"
   * @uml.associationEnd multiplicity="(0 -1)" elementType="com.hgtable.ColumnDataCellTooltip"
   */
  protected Vector data = null;

  /** Field <code>sortCol</code> : int */
  protected int sortCol = 0;
  /** Field <code>sortAsc</code> : boolean */
  protected boolean sortAsc = true;
  /** Field <code>columnsCount</code> : int */
  protected int columnsCount = 0;
  /** Field <code>COLUMN_UP</code> : ImageIcon */
  public static ImageIcon COLUMN_UP = null;
  /** Field <code>COLUMN_DOWN</code> : ImageIcon */
  public static ImageIcon COLUMN_DOWN = null;
  /** Field <code>COLUMN_BLANK_ICON</code> : ImageIcon */
  public static ImageIcon COLUMN_BLANK_ICON = null;

  /**
   * author:      hgrein<BR>
   * date:        May 31, 2004<BR>
   * Package:     com.hgtable<BR>
   * File Name:   HGTableModel.java<BR>
   * Type Name:   MainModelColumnListener<BR>
   * Description: A Inner class to listen to the Column events
   */
  public class MainModelColumnListener extends MouseAdapter
  {
    protected JTable table;

    /**
     * Constructor for MainModelColumnListener. 
     * @param table
     */
    public MainModelColumnListener(JTable table)
    {
      this.table = table;
    }

    /**
     * Overridden Method:  
     * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
     * @param e
     */
    public void mouseClicked(MouseEvent e)
    {
      TableColumnModel colModel = table.getColumnModel();
      int columnModelIndex = colModel.getColumnIndexAtX(e.getX());
      int modelIndex = colModel.getColumn(columnModelIndex).getModelIndex();

      if (modelIndex < 0)
      {
        return;
      }
      if (sortCol == modelIndex)
      {
        sortAsc = !sortAsc;
      }
      else
      {
        sortCol = modelIndex;
      }

      for (int i = 0; i < HGTableModel.this.columnsCount; i++)
      {
        TableColumn column = colModel.getColumn(i);
        column.setHeaderValue(getColumnName(column.getModelIndex()));
        int index = column.getModelIndex();
        JLabel renderer = (JLabel) column.getHeaderRenderer();
        renderer.setIcon(getColumnIcon(index));
      }
      
      table.getTableHeader().repaint();

      Collections.sort(data, new GenericDataComparator(modelIndex, sortAsc));
      table.tableChanged(new TableModelEvent(HGTableModel.this));
      table.repaint();
    }
  }

  /**
   * author:      hgrein<BR>
   * date:        May 31, 2004<BR>
   * Package:     com.hgtable<BR>
   * File Name:   HGTableModel.java<BR>
   * Type Name:   ColumnMovementListener<BR>
   * Description: A Inner Class to listen to the Columns Coving events
   */
  protected class ColumnMovementListener implements TableColumnModelListener
  {
    /**
     * Overridden Method:  
     * @see javax.swing.event.TableColumnModelListener#columnAdded(javax.swing.event.TableColumnModelEvent)
     * @param e
     */
    public void columnAdded(javax.swing.event.TableColumnModelEvent e)
    {
      columnsCount++;
    }

    /**
     * Overridden Method:  
     * @see javax.swing.event.TableColumnModelListener#columnRemoved(javax.swing.event.TableColumnModelEvent)
     * @param e
     */
    public void columnRemoved(TableColumnModelEvent e)
    {
      columnsCount--;
      if (sortCol >= e.getFromIndex())
        sortCol = 0;
    }

    /**
     * Overridden Method:  
     * @see javax.swing.event.TableColumnModelListener#columnMarginChanged(javax.swing.event.ChangeEvent)
     * @param e
     */
    public void columnMarginChanged(ChangeEvent e)
    {}
    /**
     * Overridden Method:  
     * @see javax.swing.event.TableColumnModelListener#columnMoved(javax.swing.event.TableColumnModelEvent)
     * @param e
     */
    public void columnMoved(TableColumnModelEvent e)
    {}
    /**
     * Overridden Method:  
     * @see javax.swing.event.TableColumnModelListener#columnSelectionChanged(javax.swing.event.ListSelectionEvent)
     * @param e
     */
    public void columnSelectionChanged(ListSelectionEvent e)
    {}
  }

  /**
   * Constructor for HGTableModel. Set the Icon Images for the Table Column Headers
   */
  public HGTableModel()
  {
    super();
    COLUMN_BLANK_ICON = createImageIcon("blank.gif");
    //COLUMN_UP = createImageIcon("ArrUpClear.gif");
    //COLUMN_DOWN = createImageIcon("ArrDownClear.gif");
    COLUMN_UP = createImageIcon("ArrUpBlue.gif");
    COLUMN_DOWN = createImageIcon("ArrDownBlue.gif");
  }
  /**
   * StockTableData constructor comment.
   * @param headers Vector ( single Vector )
   * @param data    Wector ( default should be a Vector of Vectors )
   */
  public HGTableModel(Vector headers, Vector data)
  {
    this();
    this.headers = headers;
    this.data = data;
    if (headers != null)
    {
      columnsCount = headers.size();
    }
    if( data != null )
    {  
      Collections.sort(data, new GenericDataComparator(sortCol, sortAsc));
    }
  }
  /**
   * Return an ImageIcon by the specified name
   * Creation date: (05/31/2001 10:32:13 AM)
   * @param filename String
   * @return ImageIcon
   */
  protected ImageIcon createImageIcon(String filename)
  {
    String path = "/resources/images/table/" + filename;
    ImageIcon ii = null;
    try
    {
      ii = new ImageIcon(getClass().getResource(path));
    }
    catch( Exception exc )
    {
      System.err.println("Exception caught: swallow exc " + exc.getClass().getName() + " : " + path );
    }
    return ii;
  }
  /**
   * Adds any Mouse Listeners to Table
   * This method also adds a ColumnMovementListener to trap adding adding
   * and deleting of columns, which should be also added to 
   * <B>addMouseListenerstoTable( JTable )<B> from the <B><I>ColumnMouseEventListener</I><B> 
   * in this package or overridden
   * Creation date: (12/31/2001 8:18:14 AM)
   * @param tableView JTable
   */
  protected void addColumnModelListener(JTable tableView)
  {
    // Setup Column Movement event.  for trapping adding and deleting of columns
    ColumnMovementListener colMovementListner = new ColumnMovementListener();
    tableView.getColumnModel().addColumnModelListener(colMovementListner);
  }
  /**
   * Adds any Mouse Listeners to Table
   * Allow for trapping click events for sorting, This is basic.
   * All Extending Objects of HGTableModel, should override
   * this method and provdide a more robust sorting, specific 
   * to the Model being utilized.
   * This method also adds a ColumnMovementListener to trap adding adding
   * and deleting of columns, which should be also added, or overridden
   * Creation date: (12/31/2001 8:18:14 AM)
   * @param tableView JTable
   */
  public void addMouseListenersToTable(JTable tableView)
  {
    // Setup Mouse Header click events
    MouseListener mouseListener = new MainModelColumnListener(tableView);

    JTableHeader header = tableView.getTableHeader();
    header.setUpdateTableInRealTime(true);
    header.addMouseListener(mouseListener);
    header.setReorderingAllowed(true);

    addColumnModelListener(tableView);
  }
  /**
   * Method to delete a Row from the table
   * Creation date: (1/1/2002 3:11:05 PM)
   * @return boolean
   * @param row int
   */
  public boolean delete(int row)
  {
    int rowCount = getRowCount();
    boolean rc = false;
    // Only allow delete if more than 2 or more
    if (data.size() > 1)
    {
      // Only allow delete if Row is a valid row
      if (0 <= row && row < rowCount)
      {
        // Delete it
        data.remove(row);
        rc = true;
        // Let em know boys, it has been smoked.
        fireTableDataChanged();
      }
    }
    else
    {
      String msg = "The Table must have at least 2 rows, \nbefore the current selected " + 
                   "row can be deleted.\nDeleting this Row will leave the Table empty." + 
                   "\nHINT:  Add a Row Before Deleting this Row!";
      JOptionPane.showMessageDialog(
          null,
          msg,
          "HGTable:  Delete Selected Row ["+row+"]",
          JOptionPane.ERROR_MESSAGE);
    }
    return rc;
  }
  /**
   * Method getColumnIcon.  Returns the Icon for the Column
   * @param column - int, the index of the column
   * @return Icon. the icon to display
   */
  public Icon getColumnIcon( int column )
  {
    if (column == sortCol)
    {
      return sortAsc ? COLUMN_DOWN : COLUMN_UP;
    }
    return COLUMN_BLANK_ICON;
  }
  /**
   * Returns the The Object located at Ro, Column.
   * If the object implments the <B><I>ColumnDataCellValue</I></B> interface
   * a call to the method, <B>getColumnDataCell( nCol )</B> will be made
   * and return the value, otherwise if the data will attempt to pull 
   * the data as a Vector, If there still is no use it will attempt to
   * just return a empty String object.
   * @param nCol int - The column
   * @return Object - Class, The Class of the Object.
   */
  public Class getColumnClass(int nCol)
  {
    Object obj = data.elementAt(0);
    if (obj instanceof ColumnDataCellValue)
    {
      return (((ColumnDataCellValue) obj).getColumnDataCell(nCol).getClass());
    }
    Object cellData = "";
    if (obj instanceof Vector)
    {
      cellData = ((Vector) obj).elementAt(nCol);
    }
    return cellData.getClass();
  }
  /**
   * Returns the Column Count
   * @return int
   */
  public int getColumnCount()
  {
    int count = columnsCount; //headers.size();
    return count;
  }
  /**
   * Returns tThe Column Name, and signifies if the sort order
   * is ascending or descending via <B><I>»</I></B> or the
   * <B><I>«</I></B> symbols
   * @param column int, the index of the Column
   * @return String - The String representation of the Colum header
   */
  public String getColumnName(int column)
  {
    Object obj = headers.elementAt(column);
    String str = (obj != null) ? obj.toString() : "Unknown";
    if (obj instanceof ColumnHeaderData)
    {
      str = ((ColumnHeaderData) obj).getTitle();
    }
    if ((column == sortCol) && ( COLUMN_DOWN == null ) && ( COLUMN_UP == null ) )
    {
      str += sortAsc ? " »" : " «";
    }
    return str;
  }

  /**
   * Returns the Vector containing all the Data
   * @return Vector
   * 
   * @uml.property name="data"
   */
  public Vector getData() {
    return (data);
  }

  /**
   * Returns the Vector containing all the Headers
   * @return Vector
   * 
   * @uml.property name="headers"
   */
  public Vector getHeaders() {
    return (headers);
  }

  /**
   * Returns the Row Count
   * @return int
   */
  public int getRowCount()
  {
    int count = (data == null) ? 0 : data.size();
    return count;
  }
  /**
   * Returns the The Object located at Ro, Column.
   * If the object implments the <B><I>ColumnDataCellValue</I></B> interface
   * a call to the method, <B>getColumnDataCell( nCol )</B> will be made
   * and return the value, otherwise if the data will attempt to pull 
   * the data as a Vector, If there still is no use it will attempt to
   * just return a empty String object.
   * @param nRow int, the Row of the Table
   * @param nCol int, the Column of the table
   * @return Object, the Object at the cell of row, col
   */
  public Object getValueAt(int nRow, int nCol)
  {
    if (nRow < 0 || nRow >= getRowCount())
    {
      return "";
    }
    Object obj = data.elementAt(nRow);
    if (obj instanceof ColumnDataCellValue)
    {
      return (((ColumnDataCellValue) obj).getColumnDataCell(nCol));
    }
    Object cellData = "";
    if (obj instanceof Vector)
    {
      cellData = ((Vector) obj).elementAt(nCol);
      System.out.println("CellData " + nRow + "," + nCol + " = " + cellData);
    }
    return cellData;
  }
  /**
   * Method to insert a row of data into the Table Model
   * Creation date: (1/1/2002 3:10:40 PM)
   * @param row The row to which insert
   */
  public void insert(int row)
  {

    if (row < 0)
    {
      row = 0;
    }
    int rowCount = getRowCount();
    if (row > rowCount)
    {
      row = rowCount;
    }

    // Ok Grab the Element
    Object tmpObj = data.elementAt(0);
    try
    {
      String objClassName = tmpObj.getClass().getName();
      Class objClass = Class.forName(objClassName);
      java.lang.reflect.Constructor construct = objClass.getConstructor(null);
      //java.lang.reflect.Constructor[] objConstructors = objClass.getConstructors();
      Object obj = construct.newInstance(null);
      if (tmpObj instanceof java.util.Vector)
      {
        populateSubitemsForVector((Vector) obj, (Vector) tmpObj);
      }
      data.insertElementAt(obj, row);
    }
    catch (Exception exc)
    {
      System.err.println("Exception Caught : " + exc);
      System.err.println("Exception ocurred when attempting to insert a class into a Row of the Data Vector");
    }
  }
  /**
   * Method to insert a row of data into the Table Model
   * Creation date: (1/1/2002 3:10:40 PM)
   * @param obj The Object type wished to be inserted
   * @param row The row to which insert
   */
  public void insert(Object obj, int row)
  {

    if (row < 0)
    {
      row = 0;
    }
    int rowCount = getRowCount();
    if (row > rowCount)
    {
      row = rowCount;
    }

    data.insertElementAt(obj, row);

  }
  /**
   * Method to insert Columns of the Vector that is being inserted as a row
   * Creation date: (1/1/2002 4:05:55 PM)
   * @param newVector java.util.Vector
   * @param modelVector java.util.Vector
   */
  private void populateSubitemsForVector(Vector newVector, Vector modelVector)
  {

    try
    {
      for (int i = 0; i < modelVector.size(); i++)
      {
        // Ok Grab the Element
        Object tmpObj = modelVector.elementAt(i);
        String objClassName = tmpObj.getClass().getName();
        Class objClass = Class.forName(objClassName);
        java.lang.reflect.Constructor construct = objClass.getConstructor(null);
        Object obj = construct.newInstance(null);
        newVector.insertElementAt(obj, i);
      }
    }
    catch (Exception exc)
    {
      System.err.println("Exception Caught : " + exc);
      System.err.println("Exception ocurred when attempting to insert a class into a Column of the Data Vector");
    }

  }
  /**
   * Method getToolTipText.  Will retrieve the tooltip for the value of the
   * object at column
   * @param row - int, the Row of the table
   * @param col - int, the col of the table
   * @return String - The tooltip
   */
  public String getToolTipText(int row,int col)
  {
    String rc = "";
    
    Object obj = data.elementAt(row);
    
    if ( obj instanceof ColumnDataCellTooltip )
    {
      rc = ((ColumnDataCellTooltip)obj).getCellToolTip(col);
    }
    
    return rc;
  }
}