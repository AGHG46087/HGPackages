package com.hgtable;

import java.io.Serializable;

/**
 * author:      hgrein<BR>
 * date:        Jun 8, 2004<BR>
 * Package:     com.hgtable<BR>
 * File Name:   GenericDataComparator.java<BR>
 * Type Name:   GenericDataComparator<BR>
 * Description: A Generic Comparator for comparing fields in a column of a HGTable.
 * @see java.util.Comparator
 * @see com.hgtable.HGTable
 */
public class GenericDataComparator implements java.util.Comparator, Serializable
{
  /** Field <code>serialVersionUID</code> : long */
  private static final long serialVersionUID = -2997623971019403141L;

  /**
   * Field <code>sortCol</code> : int
   * 
   * @uml.property name="sortCol" 
   */
  protected int sortCol;

  /**
   * Field <code>sortAsc</code> : boolean
   * 
   * @uml.property name="sortAsc" 
   */
  protected boolean sortAsc;


  /**
   * StockDataComparator constructor comment.
   */
  public GenericDataComparator()
  {
    super();
  }
  /**
   * Constructor GenericDataComparator
   * Creation date: (12/31/2001 8:18:14 AM)
   * @param sortCol int the Column to sort
   * @param sortAsc boolean Sort in ascending or descending order
   */
  public GenericDataComparator(int sortCol, boolean sortAsc)
  {
    this.sortCol = sortCol;
    this.sortAsc = sortAsc;
  }
  /**
   * Compares the string representation of the two objects
   * Creation date: (12/31/2001 8:18:14 AM)
   * @return int  <0 Less than, 0 equal, >0 greater than 
   */
  public int compare(Object o1, Object o2)
  {

    String str1 = (String) o1.toString();
    String str2 = (String) o2.toString();
    int result = str1.compareTo(str2);

    if (!sortAsc)
    {
      result = -result;
    }
    return result;
  }
  /**
   * Returns true/false if the Object is the equal or not
   * Creation date: (12/31/2001 8:18:14 AM)
   * @param obj Object compares objects
   * @return booleam 
   */
  public boolean equals(Object obj)
  {
    if (obj instanceof GenericDataComparator)
    {
      GenericDataComparator compObj = (GenericDataComparator) obj;
      return ((compObj.getSortCol() == sortCol) && (compObj.getSortAsc() == sortAsc));
    }
    return false;
  }

  /**
   * Returns true/false if the object is ascending or descending order
   * Creation date: (12/31/2001 8:18:14 AM)
   * @return boolean
   * 
   * @uml.property name="sortAsc"
   */
  public boolean getSortAsc() {
    return (sortAsc);
  }

  /**
   * returns the sorting column
   * Creation date: (12/31/2001 8:18:14 AM)
   * @return int
   * 
   * @uml.property name="sortCol"
   */
  public int getSortCol() {
    return (sortCol);
  }

}