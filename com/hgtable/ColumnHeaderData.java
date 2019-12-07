package com.hgtable;

import java.io.Serializable;

import javax.swing.table.TableCellEditor;
/**
 * author:      hgrein<BR>
 * date:        Jun 8, 2004<BR>
 * Package:     com.hgtable<BR>
 * File Name:   ColumnHeaderData.java<BR>
 * Type Name:   ColumnHeaderData<BR>
 * Description: Class object maintains header information for each individual Header in a
 * table.
 * @see com.hgtable.HGTable
 */
public class ColumnHeaderData implements Serializable
{
  /** Field <code>serialVersionUID</code> : long */
  private static final long serialVersionUID = 1334041592637520153L;
  /**
   * Field <code>title</code> : String
   * 
   * @uml.property name="title" 
   */
  private String title;

  /**
   * Field <code>width</code> : int
   * 
   * @uml.property name="width" 
   */
  private int width;

  /**
   * Field <code>alignment</code> : int
   * 
   * @uml.property name="alignment" 
   */
  private int alignment;

  /**
   * Field <code>cellEditor</code> : TableCellEditor
   * 
   * @uml.property name="cellEditor"
   * @uml.associationEnd multiplicity="(0 1)"
   */
  protected TableCellEditor cellEditor = null;

  /**
   * ColumnData constructor comment.
   */
  public ColumnHeaderData()
  {
    super();
  }
  /**
   * Constructor, constructs a Column header data object
   * Creation date: (12/31/2001 8:18:14 AM)
   * @param title String Title of the header
   * @param width int the width of the header
   * @param alignment int the alignment of the text in the header
   */
  public ColumnHeaderData(String title, int width, int alignment)
  {
    setColumnData(title, width, alignment);
  }
  /**
   * Constructor, constructs a Column header data object
   * Creation date: (12/31/2001 8:18:14 AM)
   * @param title String Title of the header
   * @param width int the width of the header
   * @param alignment int the alignment of the text in the header
   */
  public ColumnHeaderData(Object title, int width, int alignment)
  {
    setColumnData(""+title, width, alignment);
  }
  /**
   * Constructor, constructs a Column header data object
   * Creation date: (12/31/2001 8:18:14 AM)
   * @param title String Title of the header
   * @param width int the width of the header
   * @param alignment int the alignment of the text in the header
   */
  public ColumnHeaderData(String title, int width, int alignment, TableCellEditor editor)
  {
    setColumnData(title, width, alignment, editor);
  }

  /**
   * Returns alignment
   * Creation date: (12/31/2001 8:18:14 AM)
   * @return int The alignment
   * 
   * @uml.property name="alignment"
   */
  public int getAlignment() {
    return (alignment);
  }

  /**
   * Insert the method's description here.
   * Creation date: (1/1/2002 8:01:48 PM)
   * @return javax.swing.table.TableCellEditor
   * 
   * @uml.property name="cellEditor"
   */
  public javax.swing.table.TableCellEditor getCellEditor() {
    return cellEditor;
  }

  /**
   * returns the title
   * Creation date: (12/31/2001 8:18:14 AM)
   * @return String Title of the header
   * 
   * @uml.property name="title"
   */
  public String getTitle() {
    return (title);
  }

  /**
   * returns width
   * Creation date: (12/31/2001 8:18:14 AM)
   * @return int the width of the header
   * 
   * @uml.property name="width"
   */
  public int getWidth() {
    return (width);
  }

  /**
   * sets the alignment
   * Creation date: (12/31/2001 8:18:14 AM)
   * @param alignment int the alignment of the text in the header
   * 
   * @uml.property name="alignment"
   */
  public void setAlignment(int alignment) {
    this.alignment = (alignment >= 0) ? alignment : 0;
  }

  /**
   * Method to set the Cell Editor for the Object
   * Creation date: (1/1/2002 8:01:48 PM)
   * @param newCellEditor javax.swing.table.TableCellEditor
   * 
   * @uml.property name="cellEditor"
   */
  public void setCellEditor(TableCellEditor newCellEditor) {
    cellEditor = newCellEditor;
  }

  /**
   * constructs a Column header data object
   * Creation date: (12/31/2001 8:18:14 AM)
   * @param title String Title of the header
   * @param width int the width of the header
   * @param alignment int the alignment of the text in the header
   */
  public void setColumnData(String title, int width, int alignment)
  {
    setTitle(title);
    setWidth(width);
    setAlignment(alignment);
  }
  /**
   * constructs a Column header data object
   * Creation date: (12/31/2001 8:18:14 AM)
   * @param title String Title of the header
   * @param width int the width of the header
   * @param alignment int the alignment of the text in the header
   */
  public void setColumnData(String title, int width, int alignment, TableCellEditor editor)
  {
    setTitle(title);
    setWidth(width);
    setAlignment(alignment);
    setCellEditor(editor);
  }

  /**
   * sets the title
   * Creation date: (12/31/2001 8:18:14 AM)
   * @param title String Title of the header
   * 
   * @uml.property name="title"
   */
  public void setTitle(String title) {
    this.title = "" + title;
  }

  /**
   * sets the width
   * Creation date: (12/31/2001 8:18:14 AM)
   * @param width int the width of the header
   * 
   * @uml.property name="width"
   */
  public void setWidth(int width) {
    this.width = (width >= 1) ? width : 0;
  }

}