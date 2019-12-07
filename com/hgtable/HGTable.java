package com.hgtable;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import com.hgutil.datarenderer.ColoredTableCellRenderer;
import com.hgutil.datarenderer.HGTableColorModel;

/**
 * author:      hgrein<BR>
 * date:        Jun 8, 2004<BR>
 * Package:     com.hgtable<BR>
 * File Name:   HGTable.java<BR>
 * Type Name:   HGTable<BR>
 * Description: A extension of the javax.swing.JTable class to provide a more user friendly view of the Table.
 * @see javax.swing.JTable
 */
public class HGTable extends javax.swing.JTable implements Serializable
{
  /** Field <code>serialVersionUID</code> : long */
  private static final long serialVersionUID = 558052642828837305L;
  /** Field <code>useColorSchemeTable</code> : boolean */
  protected static boolean useColorSchemeTable = true;

  /**
   * Field <code>minWidthToText</code> : boolean
   * 
   * @uml.property name="minWidthToText" 
   */
  protected boolean minWidthToText = false;

  /**
   * Field <code>scheme</code> : HGTableColorModel
   * 
   * @uml.property name="scheme"
   * @uml.associationEnd multiplicity="(1 1)"
   */
  protected HGTableColorModel scheme = HGTableColorModel.getInstance();


  /**
   * StockTable constructor comment.
   */
  public HGTable()
  {
    super();
    initTable();
  }
  /**
   * StockTable constructor comment.
   * @param dm HGTableModel
   */
  public HGTable(HGTableModel dm)
  {
    initTable();
    setModel(dm);
//    setMinWidthToText(true);
  }
  /**
   * Returns the width of the column header
   * Creation date: (05/31/2001 10:31:00 AM)
   * @return int
   * @param col javax.swing.table.TableColumn
   */
  private int columnHeaderWidth(TableColumn col)
  {
    // Get a Default table cell renderer
    TableCellRenderer renderer = new DefaultTableCellRenderer();
    // get the component
    Component comp = renderer.getTableCellRendererComponent(this, col.getHeaderValue(), false, false, 0, 0);
    // return the preferred width of the Header
    return (comp.getPreferredSize().width + 11); // width plus ImageIcon width
  }

  /**
   * Returns the Color scheme of the table
   * @return HGTableColorModel current color scheme set
   * 
   * @uml.property name="scheme"
   */
  public HGTableColorModel getColorScheme() {
    return (scheme);
  }

  /**
   * Returns the state of Minimum width flag
   * Creation date: (05/31/2001 8:16:24 AM)
   * @return boolean
   * 
   * @uml.property name="minWidthToText"
   */
  public boolean getMinWidthToText() {
    return minWidthToText;
  }

  /**
   * returns the minimum width required for either the column
   * header or the Column cells
   * Creation date: (05/31/2001 10:29:18 AM)
   * @param col - javax.swing.table.TableColumn
   * @return int - the Preferred width for the column
   */
  private int getPreferredWidthForColumn(TableColumn col)
  {
    // Get the Header width for the Column
    int headerLength = columnHeaderWidth(col);
    // Get the width of the Cells
    int columnWidth = widestCellColumn(col);
    // Determine if cell or header is greater
    int width = ((headerLength > columnWidth) ? headerLength : columnWidth);
    // return the Width
    return (width);
  }
  /**
   * Initializes the table 
   * Creation date: (05/31/2001 10:32:13 AM)
   */
  public void initTable()
  {
    //    setFont(defaultFont);
    setAutoCreateColumnsFromModel(false);
    setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
  }
  /**
   * Sets the Table color scheme
   * Creation date: (05/31/2001 10:32:13 AM)
   * @param scheme HGTableColorModel
   */
  public void setColorScheme(HGTableColorModel scheme)
  {
    for (int i = 0; i < this.getColumnCount(); i++)
    {
      // Get the column
      TableColumn col = this.getColumn(this.getColumnName(i));
      TableCellRenderer renderer = col.getCellRenderer();
      if (renderer instanceof ColoredTableCellRenderer)
      {
        ColoredTableCellRenderer.setColorScheme(scheme);
        //((ColoredTableCellRenderer) renderer).setColorScheme(scheme);
      }
    }
  }

  /**
   * Sets the Table to use min width or not
   * Creation date: (05/31/2001 10:32:13 AM)
   * @param minWidthOn boolean
   * 
   * @uml.property name="minWidthToText"
   */
  public void setMinWidthToText(boolean minWidthOn) {
    if (minWidthOn) {
      // If a user - calls this method, turn off auto resize
      this.setAutoResizeMode(AUTO_RESIZE_OFF);
      // int columnCount = getColumnCount();
      int columnCount = this.getColumnModel().getColumnCount();
      for (int i = 0; i < columnCount; i++) {
        // Get the column
        TableColumn col = this.getColumn(this.getColumnName(i));
        int tempSize = widestCellColumn(col);
        // Set the minimum width for a specific column
        col.setMinWidth(tempSize + 10);
        col.setWidth(tempSize + 10);
      }

    } else {
      for (int i = 0; i < this.getColumnCount(); i++) {
        // Get the column
        TableColumn col = this.getColumn(this.getColumnName(i));
        // Set the minimum width for a specific column
        col.setMinWidth(5);
      }
    }
    minWidthToText = minWidthOn;
  }

  /**
   * Sets the Table data model to use for the table
   * Creation date: (05/31/2001 10:32:13 AM)
   * @param dm HGTableModel
   */
  public void setModel(HGTableModel dm)
  {
    super.setModel(dm);

    setupColumnHeaders(dm);

    setupColumnListener(dm);

  }
  /**
   * Method createDefaultRenderer.  Creates a default renderer, mainly used for the Column headers
   * @return TableCellRenderer
   */
  protected TableCellRenderer createDefaultRenderer()
  {
    DefaultTableCellRenderer label = new DefaultTableCellRenderer() {
      /** Field <code>serialVersionUID</code> : long */
      private static final long serialVersionUID = 3257004358599522617L;

      /**
       * Method getTableCellRendererComponent.  
       * @param table - HGTable, the Table
       * @param value - Object, the Object to get a renderer
       * @param isSelected - boolean, is selected
       * @param hasFocus - boolean, has focus
       * @param row - int, the row of the table
       * @param column - int, the column of the table
       * @return Component, the renderer
       */
      public Component getTableCellRendererComponent( HGTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column )
      {
        if (table != null)
        {
          JTableHeader header = table.getTableHeader();
          if (header != null)
          {
            setForeground(header.getForeground());
            setBackground(header.getBackground());
            setFont(header.getFont());
          }
        }

        setText((value == null) ? "" : value.toString());
        setBorder(UIManager.getBorder("TableHeader.cellBorder"));
        return this;
      }
    };
    label.setHorizontalAlignment(JLabel.CENTER);
    return label;
  }
  /**
   * Sets the Table column headers from the model
   * Creation date: (05/31/2001 10:32:13 AM)
   * @param dm HGTableModel
   */
  protected void setupColumnHeaders(HGTableModel dm)
  {
    Vector headers = dm.getHeaders();
    for (int headerIndex = 0; headerIndex < headers.size(); headerIndex++)
    {
      Object obj = headers.elementAt(headerIndex);
      DefaultTableCellRenderer renderer = new ColoredTableCellRenderer( scheme );
      TableCellEditor editor = null;

      int alignment = JLabel.LEFT;
      int defaultWidth = 100;

      if (obj instanceof ColumnHeaderData)
      {
        ColumnHeaderData sd = (ColumnHeaderData) obj;
        alignment = sd.getAlignment();
        defaultWidth = sd.getWidth();
        editor = sd.getCellEditor();
      }

      renderer.setHorizontalAlignment(alignment);

      TableColumn column = new TableColumn(headerIndex, defaultWidth, renderer, editor);
      column.setHeaderRenderer(createDefaultRenderer());
      addColumn(column);

      // Now Calculate the Minimum we would like to see and set it.
      int width = getPreferredWidthForColumn(column);
      column.setWidth(width+5);
      column.setMinWidth(width);
      //       column.setWidth(width);
      column.setPreferredWidth(width+5);

    }
  }
  /**
   * Sets the Table column listeners from the model
   * Creation date: (05/31/2001 10:32:13 AM)
   * @param dm HGTableModel
   */
  protected void setupColumnListener(HGTableModel dm)
  {
    if (!(dm instanceof ColumnMouseEventListener))
    {
      return;
    }

    ColumnMouseEventListener obj = (ColumnMouseEventListener) dm;
    obj.addMouseListenersToTable(this);
  }
  /**
   * returns the widest cell in a column
   * Creation date: (05/31/2001 10:32:13 AM)
   * @return int
   * @param col javax.swing.table.TableColumn
   */
  private int widestCellColumn(TableColumn col)
  {
    int cindex = col.getModelIndex();
    int width = 0;
    int maxw = 0;

    // For the number of rows in a column loop
    for (int lrow = 0; lrow < this.getRowCount(); lrow++)
    {
      // Get a default table cell renderer
      TableCellRenderer renderer = new DefaultTableCellRenderer();
      // Get the component
      Component comp =
        renderer.getTableCellRendererComponent(this, this.getValueAt(lrow, cindex), false, false, lrow, cindex);
      // Get the perferred width
      width = comp.getPreferredSize().width;
      // Determine wich width is greater, current, or some other
      maxw = ((width > maxw) ? width : maxw);
    }
    // return the maximum width of the column
    return (maxw);
  }
  
  /**
   * Method to get the ToolTip from the Model. This will attempt to get the
   * Tooltip from the table first.  If the Model is a instanceof HGTableModel
   * The method will atempt to get the Tooltip from there.  If the Tooltip is 
   * null or empty it will return the tooltip from the table.
   * @see javax.swing.JTable#getToolTipText(java.awt.event.MouseEvent)
   * @see HGTableModel#getToolTipText( int, int )
   */
  public String getToolTipText(MouseEvent event)
  {
    String rc = super.getToolTipText(event);
    TableModel dm = this.getModel();
    if ( dm instanceof HGTableModel )
    {
      TableColumnModel colModel = this.getColumnModel();
      int columnModelIndex = colModel.getColumnIndexAtX(event.getX());
      int col = colModel.getColumn(columnModelIndex).getModelIndex();
      int row = rowAtPoint(event.getPoint());
      String tempRc = ((HGTableModel)dm).getToolTipText(row,col);
      rc = ( tempRc != null && tempRc.length() > 1 ) ? tempRc : rc;
    }
    return rc;
  }

}