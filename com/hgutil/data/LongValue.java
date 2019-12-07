package com.hgutil.data;

import java.text.*;

/**
 * author:      hgrein<BR>
 * date:        Jun 8, 2004<BR>
 * Package:     com.hgutil.data<BR>
 * File Name:   LongValue.java<BR>
 * Type Name:   LongValue<BR>
 * Description: Class Object to maintain the Long Value and formating
 */
public class LongValue implements java.io.Serializable
{
  /** Field <code>serialVersionUID</code> : long */
  private static final long serialVersionUID = 3761128258108077874L;
  protected static transient NumberFormat FORMAT;
  static {
    FORMAT = NumberFormat.getInstance();
    FORMAT.setGroupingUsed(true);
  }

  /**
   * 
   * @uml.property name="value" 
   */
  private long value;

  /**
   * LongValue constructor comment.
   */
  public LongValue()
  {
    super();
  }
  /**
   * Constructor
   * @param value long
   */
  public LongValue(long value)
  {
    this.value = value;
  }
  /**
   * Method to get the long Value
   * @return long
   */
  public long longValue()
  {
    return value;
  }

  /**
   * Method to set the Data Object
   * @param value long
   * 
   * @uml.property name="value"
   */
  public void setValue(long value) {
    this.value = value;
  }

  /**
   * Method to return the Data Object as a string
   * @return String
   */
  public String toString()
  {
    return FORMAT.format(value);
  }
}