/*
 * @author:		Owner
 * date:		Dec 14, 2003
 * Package:		com.hgutil.data
 * File Name:		FixedDouble.java
 */
package com.hgutil.data;

import com.hgutil.ParseData;

/**
 * author:      hgrein<BR>
 * date:        Jun 8, 2004<BR>
 * Package:     com.hgutil.data<BR>
 * File Name:   FixedDouble.java<BR>
 * Type Name:   FixedDouble<BR>
 * Description: Maintains a double value, and allows for the Format String to be used in toString()
 */
public class FixedDouble implements java.io.Serializable, Comparable
{

  /** Field <code>serialVersionUID</code> : long */
  private static final long serialVersionUID = 3257846580244983863L;

  /**
   * Field <code>value</code> : double
   * 
   * @uml.property name="value" 
   */
  private double value = 0.0;

  /** Field <code>fmtString</code> : String */
  private static String fmtString = "###0.000000";
  /**
   * Constructor for FixedDouble. 
   *  Default Contructor
   */
  public FixedDouble()
  {
    super();
    setValue(0.0);
  }
  /**
   * Constructor for FixedDouble. 
   * @param value - double, primitive value
   */
  public FixedDouble(double value)
  {
    setValue(value);
  }
  /**
   * Constructor for FixedDouble. 
   * @param value - Double, Can Be an String data
   */
  public FixedDouble(Double value)
  {
    setValue(value);
  }
  /**
   * Constructor for FixedDouble. 
   * @param value - String, Can Be an String data
   */
  public FixedDouble(String value)
  {
    setValue(value);
  }
  /**
   * Constructor for FixedDouble. 
   * @param value - Object, Can Be an Object, in the case of data from a Vector
   */
  public FixedDouble(Object value)
  {
    setValue(value);
  }
  /**
   * Method doubleValue.  
   * @return double
   */
  public double doubleValue()
  {
    return (this.value);
  }
  /**
   * Method asDoubleObject.  
   * @return Double
   */
  public Double asDoubleObject()
  {
    return (new Double(this.value));
  }

  /**
   * Method setValue.  
   * @param value - double , primitive type
   * 
   * @uml.property name="value"
   */
  public void setValue(double value) {
    this.value = value;
  }

  /**
   * Method setValue.  
   * @param value - Double, Can Be an String data
   */
  public void setValue(Double value)
  {
    this.value = ( value != null ) ? value.doubleValue() : 0.0;
  }
  /**
   * Method setValue.  
   * @param value - String, Can Be an String data
   */
  public void setValue(String value)
  {
    setValue(ParseData.parseNum(value, 0.0));
  }
  /**
   * Method setValue.  
   * @param value - Object, Can Be an Object, in the case of data from a Vector
   */
  public void setValue(Object value)
  {
    setValue("" + value);
  }
  /**
   * Overridden Method:  
   * @see java.lang.Object#toString()
   * @return String
   */
  public String toString()
  {
    String rc = null;
    rc = com.hgutil.ParseData.format(doubleValue(), fmtString);
    return (rc);
  }
  /**
   * Method setFormatString.  
   * @param fmt
   */
  public static void setFormatString( String fmt )
  {
    if ( fmt != null )
    {
      fmtString = fmt;
    }
  }
  /**
   * Overridden Method:  
   * @see java.lang.Object#equals(java.lang.Object)
   * @param obj
   * @return boolean
   */
  public boolean equals(Object obj)
  {
    boolean rc = ( this == obj);
    if ( !rc && obj instanceof FixedDouble)
    {
      FixedDouble that = (FixedDouble)obj;
      Double left = new Double(this.value);
      Double right = new Double(that.value);
      rc = left.equals(right);
    }
    
    return rc;
  }
  /**
   * Overridden Method:  
   * @see java.lang.Object#hashCode()
   * @return int
   */
  public int hashCode()
  {
    int rc = 7;
    rc = (int)(rc * 11 + this.value);
    return rc;
    
  }
  
  /**
   * Overridden Method:  
   * @see java.lang.Comparable#compareTo(java.lang.Object)
   * @param obj
   * @return int
   */
  public int compareTo(Object obj)
  {
    int result = -1;
    if( obj instanceof FixedDouble)
    {
      FixedDouble that = (FixedDouble)obj;
      double d1 = this.doubleValue();
      double d2 = that.doubleValue();
      result = d1 < d2 ? -1 : (d1 > d2 ? 1 : 0);
    }
    return result;
  }

}
